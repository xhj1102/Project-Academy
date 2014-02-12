/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.client.render.entity;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.core.client.props.AMClientProps;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * @author WeAthFolD
 *
 */
public class RenderTeleportIndicator extends Render {
	
	ModelBiped model = new ModelBiped();

	public RenderTeleportIndicator() {}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float f, float f1) {
		
		Tessellator t = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.6F);
		GL11.glDisable(GL11.GL_CULL_FACE);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
		t.setBrightness(15728880);
		
		RenderUtils.loadTexture(AMClientProps.TEX_EFFECT_STEVE);
		
		GL11.glTranslated(x, y + 2.0F, z);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

}
