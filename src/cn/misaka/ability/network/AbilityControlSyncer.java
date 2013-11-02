/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.network;

import java.io.DataInputStream;

import net.minecraft.entity.player.EntityPlayer;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cn.liutils.api.register.IChannelProcess;
import cn.misaka.core.proxy.AMGeneralProps;

/**
 * TODO：或许数据结构有些松散？谁有空就来写一下吧……
 * 0. byte- 包类型(control or stop)
 * 1. stop:空  control:byte-keyID
 * 2. stop:空 control:boolean-downOrUp
 * @author WeAthFolD
 *
 */
public class AbilityControlSyncer implements IChannelProcess {

	private final byte CHANNEL = AMGeneralProps.NET_ID_ABILITY_CONTROL;
	
	/**
	 * 接受服务端的操作信息，同步并丢给server。
	 * @param player
	 * @param keyID
	 * @param upDown
	 */
	@SideOnly(Side.CLIENT)
	public static void syncControl(EntityPlayer player, int keyID, boolean upDown) {
		
	}

	/**
	 * 同步玩家的操作停止讯息。(Opening GUI)
	 */
	@SideOnly(Side.CLIENT)
	public static void syncStop(EntityPlayer player) {
		
	}
	
	private static void onControlChange(EntityPlayer player, int keyID, boolean upDown) {}
	
	private static void resetControl(EntityPlayer player) {}

	/* (non-Javadoc)
	 * @see cn.liutils.api.register.IChannelProcess#onPacketData(java.io.DataInputStream, cpw.mods.fml.common.network.Player)
	 */
	@Override
	public void onPacketData(DataInputStream stream, Player player) {
		// TODO Auto-generated method stub

	}

}
