/**
 * 
 */
package cn.liutils.api.client.util;

import java.util.Map;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Maps;

import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;

/**
 * @author WeAthFolD
 *
 */
@SideOnly(Side.CLIENT)
public class RenderUtils {

	private static Tessellator t = Tessellator.instance;
	public static Random rand = new Random();
	private static Map<String, ResourceLocation> srcMap  = Maps.newHashMap();
	
	/**
	 * 添加带UV的三维顶点。
	 * 
	 * @param vec3
	 * @param texU
	 * @param texV
	 */
	public static void addVertex(Vec3 vec3, double texU, double texV) {
		t.addVertexWithUV(vec3.xCoord, vec3.yCoord, vec3.zCoord, texU, texV);
	}

	/**
	 * 添加不带UV的三维顶点。
	 * 
	 * @param vec3
	 */
	public static void addVertex(Vec3 vec3) {
		t.addVertex(vec3.xCoord, vec3.yCoord, vec3.zCoord);
	}
	

	/**
	 * 获取并加载一个贴图。
	 * 
	 * @param path
	 *            贴图路径
	 */
	public static void loadTexture(String path) {
		ResourceLocation src = srcMap.get(path);
		if(src == null) {
			src = new ResourceLocation(path);
			srcMap.put(path, src);
		}
		Minecraft.getMinecraft().renderEngine.bindTexture(src);
	}

	
	public static void renderItemIn2d(EntityLivingBase par1EntityLiving,
			ItemStack stackToRender, double w) {
		renderItemIn2d(par1EntityLiving, stackToRender, w, null);
	}

	/**
	 * 将Item渲染成一个有厚度的薄片。
	 * 
	 * @param t
	 * @param w
	 *            宽度
	 */
	public static void renderItemIn2d(Entity par1EntityLivingBase,
			ItemStack stackToRender, double w, Icon specialIcon) {
		Vec3 a1 = newV3(0, 0, w), a2 = newV3(1, 0, w), a3 = newV3(1, 1, w), a4 = newV3(
				0, 1, w), a5 = newV3(0, 0, -w), a6 = newV3(1, 0, -w), a7 = newV3(
				1, 1, -w), a8 = newV3(0, 1, -w);

		Icon icon = stackToRender.getIconIndex();
		if (specialIcon != null)
			icon = specialIcon;

		Minecraft mc = Minecraft.getMinecraft();

		if (icon == null) {
			GL11.glPopMatrix();
			return;
		}

		mc.renderEngine.bindTexture(mc.renderEngine.getResourceLocation(stackToRender.getItemSpriteNumber()));

		float u1 = 0.0F, v1 = 0.0F, u2 = 0.0F, v2 = 0.0F;
		u1 = icon.getMinU();
		v1 = icon.getMinV();
		u2 = icon.getMaxU();
		v2 = icon.getMaxV();

		Tessellator t;
		t = Tessellator.instance;
		GL11.glPushMatrix();
		t.startDrawingQuads();
		t.setNormal(0.0F, 0.0F, 1.0F);
		addVertex(a1, u2, v2);
		addVertex(a2, u1, v2);
		addVertex(a3, u1, v1);
		addVertex(a4, u2, v1);
		t.draw();

		t.startDrawingQuads();
		t.setNormal(0.0F, 0.0F, -1.0F);
		addVertex(a8, u2, v1);
		addVertex(a7, u1, v1);
		addVertex(a6, u1, v2);
		addVertex(a5, u2, v2);
		t.draw();

		int var7;
		float var8;
		float var9;
		float var10;
		
		/*
		 * Gets the width/16 of the currently bound texture, used to fix the
		 * side rendering issues on textures != 16
		 */
		int tileSize = 32;
		float tx = 1.0f / (32 * tileSize);
		float tz = 1.0f / tileSize;

		t.startDrawingQuads();
		t.setNormal(-1.0F, 0.0F, 0.0F);
		for (var7 = 0; var7 < tileSize; ++var7) {
			var8 = (float) var7 / tileSize;
			var9 = u2 - (u2 - u1) * var8 - tx;
			var10 = 1.0F * var8;
			t.addVertexWithUV(var10, 0.0D, -w, var9, v2);
			t.addVertexWithUV(var10, 0.0D, w, var9, v2);
			t.addVertexWithUV(var10, 1.0D, w, var9, v1);
			t.addVertexWithUV(var10, 1.0D, -w, var9, v1);

			t.addVertexWithUV(var10, 1.0D, w, var9, v1);
			t.addVertexWithUV(var10, 0.0D, w, var9, v2);
			t.addVertexWithUV(var10, 0.0D, -w, var9, v2);
			t.addVertexWithUV(var10, 1.0D, -w, var9, v1);
		}
		t.draw();

		GL11.glPopMatrix();
	}
	
	public static void renderEnchantGlint_Equip() {
		GL11.glDepthFunc(GL11.GL_EQUAL);
    	GL11.glDisable(GL11.GL_LIGHTING);
    	loadTexture("textures/misc/enchanted_item_glint.png");
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
    	float f7 = 0.76F;
    	GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
    	GL11.glMatrixMode(GL11.GL_TEXTURE);
    	GL11.glPushMatrix();
        float f8 = 0.125F;
        GL11.glScalef(f8, f8, f8);
        float f9 = Minecraft.getSystemTime() % 3000L / 3000.0F * 8.0F;
        GL11.glTranslatef(f9, 0.0F, 0.0F);
        GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
        ItemRenderer.renderItemIn2D(Tessellator.instance, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glScalef(f8, f8, f8);
        f9 = Minecraft.getSystemTime() % 4873L / 4873.0F * 8.0F;
        GL11.glTranslatef(-f9, 0.0F, 0.0F);
        GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
        ItemRenderer.renderItemIn2D(Tessellator.instance, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
	}
	
	public static void renderEnchantGlint_Inv() {
		GL11.glDepthFunc(GL11.GL_EQUAL);
    	GL11.glDisable(GL11.GL_LIGHTING);
    	loadTexture("textures/misc/enchanted_item_glint.png");
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
    	float f7 = 0.76F;
    	GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
    	GL11.glMatrixMode(GL11.GL_TEXTURE);
    	GL11.glPushMatrix();
        float f8 = 0.125F;
        GL11.glScalef(f8, f8, f8);
        float f9 = Minecraft.getSystemTime() % 3000L / 3000.0F * 8.0F;
        GL11.glTranslatef(f9, 0.0F, 0.0F);
        GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
        t.startDrawingQuads();
        t.addVertexWithUV(0.0, 0.0, 0.0, 0.0, 0.0);
		t.addVertexWithUV(0.0, 16.0, 0.0, 0.0, 1.0);
		t.addVertexWithUV(16.0, 16.0, 0.0, 1.0, 1.0);
		t.addVertexWithUV(16.0, 0.0, 0.0, 1.0, 0.0);
        t.draw();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glScalef(f8, f8, f8);
        f9 = Minecraft.getSystemTime() % 4873L / 4873.0F * 8.0F;
        GL11.glTranslatef(-f9, 0.0F, 0.0F);
        GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
        t.startDrawingQuads();
        t.addVertexWithUV(0.0, 0.0, 0.0, 0.0, 0.0);
		t.addVertexWithUV(0.0, 16.0, 0.0, 0.0, 1.0);
		t.addVertexWithUV(16.0, 16.0, 0.0, 1.0, 1.0);
		t.addVertexWithUV(16.0, 0.0, 0.0, 1.0, 0.0);
        t.draw();
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
	}
	
	/**
	 * 直接在物品栏渲染物品icon。确认你已经绑定好贴图。
	 * @param item
	 */
	public static void renderItemInventory(ItemStack item) {
		Icon icon = item.getIconIndex();
		if(icon != null) {
			t.startDrawingQuads();
			t.addVertexWithUV(0.0, 0.0, 0.0, icon.getMinU(), icon.getMinV());
			t.addVertexWithUV(0.0, 16.0, 0.0, icon.getMinU(), icon.getMaxV());
			t.addVertexWithUV(16.0, 16.0, 0.0, icon.getMaxU(), icon.getMaxV());
			t.addVertexWithUV(16.0, 0.0, 0.0, icon.getMaxU(), icon.getMinV());
			t.draw();
		}
	}

	/**
	 * 创建一个新的Vec3顶点。
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static Vec3 newV3(double x, double y, double z) {
		return Vec3.createVectorHelper(x, y, z);
	}

}
