/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

import net.minecraft.network.packet.Packet250CustomPayload;

import cn.misaka.core.network.AMPacketHandler;
import cn.misaka.core.proxy.AMGeneralProps;
import cn.misaka.system.data.AbilityControlData;
import cn.misaka.system.data.PlayerAbilityData;

/**
 * 从服务端发送的数据同步。详见代码
 * @author WeAthFolD
 *
 */
public class AbilityDataSender {
	
	public enum EnumDataType {
		SIMPLE(8), FULL(11), CONTROL(33), NONE(-1);
		public int dataSize;
		private EnumDataType(int ds) {
			dataSize = ds;
		}
	};
	
	/**
	 * SIMPLE: [byte]class [byte]level [int]calcPoint
	 * @param data
	 * @param type
	 */
	public static void sendAbilityDataFromServer(PlayerAbilityData data, EnumDataType type) {
		ByteArrayOutputStream stream = AMPacketHandler.getStream(AMGeneralProps.NET_ID_ABILITY_SYNC_SERVER, 1 + type.dataSize);
		DataOutputStream strm = new DataOutputStream(stream);
		try {
			strm.writeByte(type.ordinal());
			if(type == EnumDataType.SIMPLE)
				sendSimple(data, strm);
			else if(type == EnumDataType.FULL)
				sendFull(data, strm);
			else if(type == EnumDataType.CONTROL)
				if(!sendControl(data, strm)) return;
		} catch(Exception e) {
			e.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = AMGeneralProps.NET_CHANNEL_CLIENT;
		packet.length = stream.size();
		packet.data = stream.toByteArray();
		PacketDispatcher.sendPacketToPlayer(packet, (Player) data.player);
	}
	
	public static void sendSyncRequestFromClient(EnumDataType type) {
		ByteArrayOutputStream stream = AMPacketHandler.getStream(AMGeneralProps.NET_ID_ABILITY_SYNC_SERVER, 1);
		DataOutputStream strm = new DataOutputStream(stream);
		try {
			strm.writeByte(4);
			strm.writeByte(type.ordinal());
		} catch(Exception e) {
			e.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = AMGeneralProps.NET_CHANNEL_SERVER;
		packet.length = stream.size();
		packet.data = stream.toByteArray();
		PacketDispatcher.sendPacketToServer(packet);
	}
	
	public static void sendAbilityDataFromClient(PlayerAbilityData data, EnumDataType type) {
		ByteArrayOutputStream stream = AMPacketHandler.getStream(AMGeneralProps.NET_ID_ABILITY_SYNC_SERVER, 1 + type.dataSize);
		DataOutputStream strm = new DataOutputStream(stream);
		try {
			strm.writeByte(type.ordinal());
			if(type == EnumDataType.SIMPLE)
				sendSimple(data, strm);
			else if(type == EnumDataType.FULL)
				sendFull(data, strm);
			else if(type == EnumDataType.CONTROL)
				sendControl(data, strm);
		} catch(Exception e) {
			e.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = AMGeneralProps.NET_CHANNEL_SERVER;
		packet.length = stream.size();
		packet.data = stream.toByteArray();
		PacketDispatcher.sendPacketToServer(packet);
	}
	
	private static void sendSimple(PlayerAbilityData data, DataOutputStream stream) throws IOException {
		stream.writeByte(data.ability_class);
		stream.writeByte(data.ability_level);
		stream.writeInt(data.calcPoint);
		stream.writeBoolean(data.isDeveloped);
		stream.writeBoolean(data.isActivated);
	}
	
	private static void sendFull(PlayerAbilityData data, DataOutputStream stream) throws IOException {
		sendSimple(data, stream);
		stream.writeByte(data.props_speed);
		stream.writeByte(data.props_power);
		stream.writeByte(data.props_defense);
	}
	
	private static boolean sendControl(PlayerAbilityData data, DataOutputStream stream) throws IOException {
		AbilityControlData ctrData = data.controlData;
		if(data.ability_class <= 2) return false;
		stream.writeByte(ctrData.currentSetID);
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++)
				stream.write(new byte[] {
						(byte) ctrData.controlSets[i].keyData[j][0], 
						(byte) ctrData.controlSets[i].keyData[j][1]}
				);
			}
		return true;
	}

}
