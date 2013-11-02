/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cn.liutils.api.register.IChannelProcess;
import cn.misaka.ability.system.AbilityClass;
import cn.misaka.ability.system.PlayerAbilityData;
import cn.misaka.ability.system.ServerAbilityMain;
import cn.misaka.core.network.AMPacketHandler;
import cn.misaka.core.proxy.AMGeneralProps;

/**
 * TODO：或许数据结构有些松散？谁有空就来写一下吧…… 0. byte- 包类型(control or stop) 1. stop:空
 * control:byte-keyID 2. stop:空 control:boolean-downOrUp
 * 
 * @author WeAthFolD
 * 
 */
public class AbilityControlSyncer implements IChannelProcess {

	private final static byte CHANNEL = AMGeneralProps.NET_ID_ABILITY_CONTROL;

	/**
	 * 接受服务端的操作信息，同步并丢给server。
	 * 
	 * @param player
	 * @param keyID
	 * @param upDown
	 */
	@SideOnly(Side.CLIENT)
	public static void syncControl(EntityPlayer player, int keyID,
			boolean upDown) {
		ByteArrayOutputStream bos = AMPacketHandler.getStream(CHANNEL, 2);
		DataOutputStream cout = new DataOutputStream(bos);

		try {
			cout.writeByte(keyID);
			cout.writeBoolean(upDown);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = AMGeneralProps.NET_CHANNEL_SERVER;
		packet.data = bos.toByteArray();
		packet.length = bos.size();

		onControlChange(player, keyID, upDown);
		PacketDispatcher.sendPacketToServer(packet);
	}

	/**
	 * 同步玩家的操作停止讯息。(Opening GUI)
	 */
	@SideOnly(Side.CLIENT)
	public static void syncStop(EntityPlayer player) {
		ByteArrayOutputStream bos = AMPacketHandler.getStream(CHANNEL, 1);
		DataOutputStream cout = new DataOutputStream(bos);

		try {
			cout.writeByte(-1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = AMGeneralProps.NET_CHANNEL_SERVER;
		packet.data = bos.toByteArray();
		packet.length = bos.size();

		resetControl(player);
		PacketDispatcher.sendPacketToServer(packet);
	}

	private static void onControlChange(EntityPlayer player, int keyID,
			boolean upDown) {
		AbilityClass playerAbility = ServerAbilityMain.getAbilityClass(player);
		PlayerAbilityData data = ServerAbilityMain.getAbilityData(player);
		playerAbility.onButtonStateChange(player, player.worldObj, data, keyID, upDown);
	}

	private static void resetControl(EntityPlayer player) {
		AbilityClass playerAbility = ServerAbilityMain.getAbilityClass(player);
		PlayerAbilityData data = ServerAbilityMain.getAbilityData(player);
		playerAbility.resetPlayerStat(player);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.liutils.api.register.IChannelProcess#onPacketData(java.io.DataInputStream
	 * , cpw.mods.fml.common.network.Player)
	 */
	@Override
	public void onPacketData(DataInputStream stream, Player player) {
		try {
			byte b0 = stream.readByte();
			if (b0 == -1) {
				resetControl((EntityPlayer) player);
			} else {
				boolean b1 = stream.readBoolean();
				onControlChange((EntityPlayer) player, (int) b0, b1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
