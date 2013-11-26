/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.client.render.ability.misc;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cn.misaka.ability.system.AbilityClass.ControlStat;
import cn.misaka.ability.system.PlayerAbilityData;
import cn.misaka.ability.system.client.system.AbilityRender;

/**
 * @author WeAthFolD
 *
 */
public class RenderMeltdowner extends AbilityRender {

	/**
	 * 
	 */
	public RenderMeltdowner() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.system.client.system.AbilityRender#onRenderEquipped(net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, cn.misaka.ability.system.PlayerAbilityData, cn.misaka.ability.system.AbilityClass.ControlStat, boolean)
	 */
	@Override
	public void onRenderEquipped(EntityPlayer player, World world,
			PlayerAbilityData data, ControlStat control, boolean isEquipped) {
		// TODO Auto-generated method stub
		//Transformations
		float tickTime = player.ticksExisted;
		
		if(control.keyDown[0]) {
			GL11.glRotatef(MathHelper.sin(tickTime / 20) * 20, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(MathHelper.cos(tickTime / 20) * 20, 1.0F, 0.0F, 1.0F);
		}
		
		this.renderHand(player);
	}

}
