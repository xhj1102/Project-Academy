/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.client.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.system.control.PlayerControlStat;
import cn.misaka.system.data.PlayerAbilityData;

/**
 * TODO:补完中。
 * @author WeAthFolD
 *
 */
public abstract class SkillRender {

	protected static ModelBiped model = new ModelBiped();
	
	/**
	 * 
	 */
	public SkillRender() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 在特定技能级被激活的时候调用。进行判断并进行能力动作和特效的渲染。
	 * 目前由voidItem的ItemRender调用，所以坐标轴和渲染位置参考IItemRender.
	 * @param player
	 * @param world
	 * @param data
	 * @param control
	 */
	public abstract void onRenderEquipped(EntityPlayer player, World world, PlayerAbilityData data, PlayerControlStat control, boolean isFirstPerson);
	
	public static final void renderHand(EntityPlayer player) {
		GL11.glPushMatrix();

		GL11.glDisable(GL11.GL_CULL_FACE);

		RenderUtils.renderEnchantGlint_Equip();
		RenderUtils.loadTexture("minecraft:textures/entity/steve.png");
		GL11.glRotated(-23.75, 0.0F, 0.0F, 1.0F);
		GL11.glRotated(21.914, 0.0F, 1.0F, 0.0F);
		GL11.glRotated(32.75, 1.0F, 0.0F, 0.0F);
		GL11.glTranslatef(.758F, -.072F, -.402F);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);

		        model.onGround = 0.0F;
		        model.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
		        model.bipedRightArm.render(0.0625F);

		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
	}
	
	protected static final void renderHand2(EntityPlayer player) {
		GL11.glPushMatrix();

		GL11.glDisable(GL11.GL_CULL_FACE);

		RenderUtils.renderEnchantGlint_Equip();
		RenderUtils.loadTexture("minecraft:textures/entity/steve.png");
		GL11.glRotated(-23.75, 0.0F, 0.0F, 1.0F);
		GL11.glRotated(21.914, 0.0F, 1.0F, 0.0F);
		GL11.glRotated(32.75, 1.0F, 0.0F, 0.0F);
		GL11.glTranslatef(.758F, -.072F, -.402F);
		GL11.glColor3f(0.0F, 1.0F, 1.0F);

		        model.onGround = 0.0F;
		        model.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
		        model.bipedRightArm.render(0.0625F);

		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
	}

}
