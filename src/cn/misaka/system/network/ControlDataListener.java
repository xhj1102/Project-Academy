/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.network;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;

import cpw.mods.fml.common.network.Player;
import cn.liutils.api.register.IChannelProcess;
import cn.misaka.system.proxy.CommonProxy;

/**
 * 数据结构：
 * 0-byte-[keyID]
 * 1-boolean-[keyDown:true keyUp:false]
 * @author WeAthFolD
 *
 */
public class ControlDataListener implements IChannelProcess {

	/**
	 * 
	 */
	public ControlDataListener() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.liutils.api.register.IChannelProcess#onPacketData(java.io.DataInputStream, cpw.mods.fml.common.network.Player)
	 */
	@Override
	public void onPacketData(DataInputStream stream, Player player) {
		try {
			int keyID = stream.readByte();
			boolean b = stream.readBoolean();
			CommonProxy.abilityMain.onControlStatChange((EntityPlayer) player, keyID, b);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
