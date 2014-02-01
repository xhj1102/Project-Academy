/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.client.render.ability.misc;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cn.misaka.system.client.render.SkillRender;
import cn.misaka.system.control.PlayerControlStat;
import cn.misaka.system.data.PlayerAbilityData;

/**
 * @author WeAthFolD
 *
 */
public class SkillRenderTest extends SkillRender {

	/**
	 * 
	 */
	public SkillRenderTest() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onRenderEquipped(EntityPlayer player, World world,
			PlayerAbilityData data, PlayerControlStat control, boolean b) {
		if(b) {
			this.renderHand2(player);
		}
	}

}
