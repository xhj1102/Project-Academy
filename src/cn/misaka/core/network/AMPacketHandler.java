/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.core.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cn.liutils.api.register.IChannelProcess;
import cn.misaka.core.proxy.AMGeneralProps;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 * @author WeAthFolD
 *
 */
public class AMPacketHandler  implements IPacketHandler {

	private static HashMap<Byte, IChannelProcess> channels = new HashMap();

	protected final String server, client;
	
	public AMPacketHandler() {
		server = AMGeneralProps.NET_CHANNEL_SERVER;
		client = AMGeneralProps.NET_CHANNEL_CLIENT;
	}
	
	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {

		DataInputStream inputStream = new DataInputStream(
				new ByteArrayInputStream(packet.data));

		byte i = -1;
		try {
			i = inputStream.readByte();
		} catch (IOException e) {
			e.printStackTrace();
		}
		IChannelProcess p = channels.get(i);
		if (packet.channel.equals(client) || packet.channel.equals(server)) {
			if (p != null)
				p.onPacketData(inputStream, player);
		}
	}

	/**
	 * 注册一个网络包频道和一个处理类。
	 * 
	 * @param channel
	 *            频道
	 * @param process
	 *            包处理类
	 */
	public static boolean addChannel(byte channel, IChannelProcess process) {
		if (channels.containsKey(channel)) {
			return false;
		}
		channels.put(channel, process);
		return true;
	}

	/**
	 * 获取一个没有被使用的Channel ID。
	 * 
	 * @return ID
	 */
	public static byte getUniqueChannelID() {
		for (byte i = 0; i < Byte.MAX_VALUE; i++) {
			if (!channels.containsKey(i))
				return i;
		}
		return -1;
	}

	/**
	 * 如果使用CBCNetHandler来处理数据，请使用这个来获取用于发送的OutputStream。
	 * 
	 * @param id
	 *            频道ID
	 * @param size
	 *            包数据大小。
	 * @return 所要求的输出流，频道信息已经预先写入。
	 */
	public static ByteArrayOutputStream getStream(short id, int size) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(size + 1);
		DataOutputStream stream = new DataOutputStream(bos);
		try {
			stream.writeByte(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bos;
	}

}
