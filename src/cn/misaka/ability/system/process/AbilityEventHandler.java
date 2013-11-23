/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.system.process;

import cn.misaka.ability.system.network.AbilityDataSyncer;
import cn.misaka.ability.system.network.AbilityDataSyncer.EnumDataType;
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
				AbilityDataSyncer.sendPacketFromServer(player, EnumDataType.FULL);
			}
		}
	}

}
