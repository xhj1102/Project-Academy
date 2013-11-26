/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.client.render.entity;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.ability.entity.EntityMdRay;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

/**
 * @author WeAthFolD
 * 
 */
public class RenderMdRay extends Render {

	/**
	 * 
	 */
	public RenderMdRay() {
		// TODO Auto-generated constructor stub
	}
	
	final double WIDTH = 0.1F;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.client.renderer.entity.Render#doRender(net.minecraft.entity
	 * .Entity, double, double, double, float, float)
	 */
	@Override
	public void doRender(Entity entity, double x0, double y0, double z0,
			float f, float f1) {
		EntityMdRay ray = (EntityMdRay) entity;
		Tessellator t = Tessellator.instance;
		
		
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
		double d = 50.0;
		
		Vec3 v1 = newV3(0.05, 0, -WIDTH), v2 = newV3(0.05, 0, WIDTH), v3 = newV3(
				d, 0, -WIDTH), v4 = newV3(d, 0, WIDTH),

		v5 = newV3(0.05, WIDTH, 0), v6 = newV3(0.05, -WIDTH, 0), v7 = newV3(d,
				-WIDTH, 0), v8 = newV3(d, WIDTH, 0);
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glColor3f(0.7F, 0.95F, 0.7F);
		
		GL11.glTranslated(x0, y0, z0);
		GL11.glRotatef(ray.rotationYaw - 90, 0.0F, 1.0F, 0.0F); // 左右旋转
		GL11.glRotatef(ray.rotationPitch, 0.0F, 0.0F, 1.0F); // 上下旋转
		
		t.startDrawingQuads();
		t.setBrightness(15728880);
		
		RenderUtils.addVertex(v1);
		RenderUtils.addVertex(v2);
		RenderUtils.addVertex(v3);
		RenderUtils.addVertex(v3);
		
		RenderUtils.addVertex(v5);
		RenderUtils.addVertex(v6);
		RenderUtils.addVertex(v7);
		RenderUtils.addVertex(v8);
		
		t.draw();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
	
	private Vec3 newV3(double x, double y, double z) {
		return RenderUtils.newV3(x, y, z);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.client.renderer.entity.Render#getEntityTexture(net.minecraft
	 * .entity.Entity)
	 */
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

}
