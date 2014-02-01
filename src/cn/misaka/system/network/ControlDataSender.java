/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import cn.misaka.core.network.AMPacketHandler;
import cn.misaka.core.proxy.AMGeneralProps;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 *  * 数据结构：
 * 0-byte-[keyID]
 * 1-boolean-[keyDown:true keyUp:false]
 * @author WeAthFolD
 *
 */
public class ControlDataSender {

	public static void sendControlDataToServer(EntityPlayer player, int keyID, boolean UporDown) {
		ByteArrayOutputStream stream = AMPacketHandler.getStream(AMGeneralProps.NET_ID_ABILITY_SYNC_SERVER, 1);
		DataOutputStream strm = new DataOutputStream(stream);
		try {
			strm.writeByte(keyID);
			strm.writeBoolean(UporDown);
		} catch(Exception e) {
			e.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = AMGeneralProps.NET_CHANNEL_SERVER;
		packet.length = stream.size();
		packet.data = stream.toByteArray();
		PacketDispatcher.sendPacketToServer(packet);
	}

}
