/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet250CustomPayload;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cn.liutils.api.register.IChannelProcess;
import cn.misaka.ability.system.AbilityDataHelper;
import cn.misaka.ability.system.PlayerAbilityData;
import cn.misaka.ability.system.ServerAbilityMain;
import cn.misaka.core.network.AMPacketHandler;
import cn.misaka.core.proxy.AMGeneralProps;

/**
 * 负责从Server到Client的数据同步。
 * @author WeAthFolD
 * 
 */
public class AbilityDataSyncer_Server implements IChannelProcess {

	private static final byte CHANNEL = AMGeneralProps.NET_ID_ABILITY_SYNC_SERVER;

	public enum EnumDataType {
		SIMPLE, FULL, LEVEL4;
	}

	public static void sendPacketFromServer(EntityPlayer player,
			EnumDataType type) {
		// TODO:SIMPLE只发送有无能力[boolean]、是否激活[boolean],FULL加发送计算力点数[int]、能力类别[short]；
		// LEVEL4加发送技能设定[待加入]
		
		ByteArrayOutputStream bos = null;
		DataOutputStream stream;
		PlayerAbilityData data = ServerAbilityMain.getPlayerData(player);
		if (data == null) {
			data = new PlayerAbilityData(player);
			ServerAbilityMain.resetPlayerData(player, data);
		}

		try {
			if (type == EnumDataType.SIMPLE) {
				bos = AMPacketHandler.getStream(CHANNEL, 2);
				stream = new DataOutputStream(bos);
				stream.writeByte(0);
				writeSimple(data, stream);

			} else if (type == EnumDataType.FULL) {
				bos = AMPacketHandler.getStream(CHANNEL, 8);
				stream = new DataOutputStream(bos);
				stream.writeByte(1);
				writeFull(data, stream);
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		if (bos != null) {
			Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.channel = AMGeneralProps.NET_CHANNEL_CLIENT;
			packet.data = bos.toByteArray();
			packet.length = bos.size();

			PacketDispatcher.sendPacketToPlayer(packet, (Player) player);
			PacketDispatcher.sendPacketToAllPlayers(packet);
		}
	}

	private static void writeSimple(PlayerAbilityData data,
			DataOutputStream stream) throws IOException {
		byte b = 0;
		b = (byte) (data.isAvailable ? 2 : 1);
		if (!data.isActivated)
			b = (byte) -b;
		stream.writeByte(b);
	}

	private static void writeFull(PlayerAbilityData data,
			DataOutputStream stream) throws IOException {
		writeSimple(data, stream);
		stream.writeByte(data.level);
		stream.writeByte(data.type);
		stream.writeInt(data.calcPoint);
	}

	/*
	 * 接收能力信息。(From server)
	 */
	@Override
	public void onPacketData(DataInputStream stream, Player fake) {
		try {
			byte b = stream.readByte();
			EntityPlayer player = (EntityPlayer) fake;
			PlayerAbilityData data = new PlayerAbilityData(player);
			if (b == 0) {
				System.out.println("Getting simple packet");
				readSimple(data, stream);
			} else if (b == 1) {
				System.out.println("Getting full packet");
				readFull(data, stream);
			}
			ServerAbilityMain.resetPlayerData(player, data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readSimple(PlayerAbilityData data, DataInputStream stream) throws IOException {
		byte b1 = stream.readByte();
		data.isAvailable = Math.abs(b1) == 2;
		data.isActivated = b1 > 0;
	}
	
	private void readFull(PlayerAbilityData data, DataInputStream stream) throws IOException {
		readSimple(data, stream);
		data.level = stream.readByte();
		data.type = stream.readByte();
		data.calcPoint = stream.readInt();
	}

}
