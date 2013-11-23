/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.client.render.ability.heat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cn.misaka.ability.system.PlayerAbilityData;
import cn.misaka.ability.system.client.system.AbilityRender;

/**
 * @author WeAthFolD
 *
 */
public class RenderHeatBase extends AbilityRender {

	/**
	 * 
	 */
	public RenderHeatBase() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.system.client.system.AbilityRender#onRenderEquipped(net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, cn.misaka.ability.system.PlayerAbilityData, boolean)
	 */
	@Override
	public void onRenderEquipped(EntityPlayer player, World world,
			PlayerAbilityData data, boolean isEquipped) {
		renderHand(player);
	}

}
