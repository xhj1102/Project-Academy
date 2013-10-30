/**
 * 
 */
package cn.misaka.core.support;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 * @author Administrator
 *
 */
public class AMPacketHandler implements IPacketHandler {

	/**
	 * 
	 */
	public AMPacketHandler() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.network.IPacketHandler#onPacketData(net.minecraft.network.INetworkManager, net.minecraft.network.packet.Packet250CustomPayload, cpw.mods.fml.common.network.Player)
	 */
	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		// TODO Auto-generated method stub

	}

}
