/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.network;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import cn.liutils.api.register.IChannelProcess;
import cn.misaka.system.data.AbilityControlData;
import cn.misaka.system.data.PlayerAbilityData;
import cn.misaka.system.network.AbilityDataSender.EnumDataType;
import cn.misaka.system.proxy.CommonProxy;
import cpw.mods.fml.common.network.Player;

/**
 * 服务端发来的数据同步的监听器。
 * @author WeAthFolD
 *
 */
public class AbilityDataListener implements IChannelProcess {

	/* (non-Javadoc)
	 * @see cn.liutils.api.register.IChannelProcess#onPacketData(java.io.DataInputStream, cpw.mods.fml.common.network.Player)
	 */
	@Override
	public void onPacketData(DataInputStream stream, Player plr) {
		EntityPlayer player = (EntityPlayer) plr;
		try  {
			int ord = stream.readByte();
			PlayerAbilityData data = CommonProxy.abilityMain.getAbilityData(player);
			if(ord == 0) 
				readSimple(stream, data);
			else if(ord == 1)
				readFull(stream, data);
			else if(ord == 2)
				readControl(stream, data);
			else if(ord == 4)
				AbilityDataSender.sendAbilityDataFromServer(data, EnumDataType.values()[stream.readByte()]);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void readSimple(DataInputStream stream, PlayerAbilityData data) throws IOException {
		data.ability_class = stream.readByte();
		data.ability_level = stream.readByte();
		data.calcPoint = stream.readInt();
		data.isDeveloped = stream.readBoolean();
		data.isActivated = stream.readBoolean();
	}
	
	private void readFull(DataInputStream stream, PlayerAbilityData data) throws IOException {
		readSimple(stream, data);
		data.props_speed = stream.readByte();
		data.props_power = stream.readByte();
		data.props_defense = stream.readInt();
	}
	
	private void readControl(DataInputStream stream, PlayerAbilityData d) throws IOException {
		if(d.controlData == null) d.controlData = new AbilityControlData(d.player.getEntityData(), d);
		AbilityControlData data = d.controlData;
		data.currentSetID = stream.readByte();
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++) {
				data.controlSets[i].keyData[j][0] = stream.readByte();
				data.controlSets[i].keyData[j][1] = stream.readByte();
			}
				
	}

}
