/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.process;

import cn.misaka.ability.network.AbilityDataSyncer_Server;
import cn.misaka.ability.network.AbilityDataSyncer_Server.EnumDataType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

/**
 * @author WeAthFolD
 *
 */
public class AbilityEventHandler {

	@ForgeSubscribe
	public void onJoinWorld(EntityJoinWorldEvent event) {
		if(event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			if(!event.world.isRemote) {
				AbilityDataSyncer_Server.sendPacketFromServer(player, EnumDataType.FULL);
			}
		}
	}

}
