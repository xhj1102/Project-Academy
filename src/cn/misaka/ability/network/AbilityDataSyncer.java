/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;

import net.minecraft.entity.player.EntityPlayer;

import cpw.mods.fml.common.network.Player;
import cn.liutils.api.register.IChannelProcess;

/**
 * TODO:求Hope汝们把core的packetHandler做了
 * @author WeAthFolD
 *
 */
public class AbilityDataSyncer implements IChannelProcess {

	public enum EnumDataType {
		SIMPLE, FULL, LEVEL4;
	}
	
	public static void sendPacketFromServer(EntityPlayer player, EnumDataType type) {
		//ByteArrayOutputStream bos = ;
		//TODO:SIMPLE只发送有无能力[boolean]、类型[short], FULL加发送计算力点数[int], LEVEL4加发送技能设定[待加入]
	}
	
	public static void sendPacketFromClient(EnumDataType type) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see cn.liutils.api.register.IChannelProcess#onPacketData(java.io.DataInputStream, cpw.mods.fml.common.network.Player)
	 */
	@Override
	public void onPacketData(DataInputStream stream, Player player) {
		// TODO Auto-generated method stub

	}

}
