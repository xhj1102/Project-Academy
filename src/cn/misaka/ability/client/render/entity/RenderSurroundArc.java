/**
 * Copyright (C) Lambda-Innovation, 2013-2014
 * This code is open-source. Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer. 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 */
package cn.misaka.ability.client.render.entity;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.core.proxy.APClientProps;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;

/**
 * @author WeAthFolD
 *
 */
public class RenderSurroundArc extends Render {

	/**
	 * 
	 */
	public RenderSurroundArc() {
		// TODO Auto-generated constructor stub
	}
	
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
    	renderAABB(par1Entity.boundingBox, par2 - par1Entity.lastTickPosX, par4 - par1Entity.lastTickPosY, par6 - par1Entity.lastTickPosZ);
    }
	
    public void renderAABB(AxisAlignedBB par0AxisAlignedBB, double par1, double par3, double par5)
    {
    	
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    	GL11.glDisable(GL11.GL_LIGHTING);
    	GL11.glPushMatrix();
    	
    	RenderUtils.loadTexture(APClientProps.TEX_ARC_SHELL);
    	OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
        Tessellator t = Tessellator.instance;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, .5F);
        t.startDrawingQuads();
        t.setBrightness(15728880);
        t.setTranslation(par1, par3, par5);
        t.setNormal(0.0F, 0.0F, -1.0F);
        t.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ, 0D, 0D);
        t.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ, 1D, 0D);
        t.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ, 1D, 1D);
        t.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ, 0D, 1D);
        t.setNormal(0.0F, 0.0F, 1.0F);
        t.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ, 0D, 0D);
        t.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ, 1D, 0D);
        t.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ, 1D, 1D);
        t.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ, 0D, 1D);
        t.setNormal(0.0F, -1.0F, 0.0F);
        t.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ, 0D, 0D);
        t.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ, 1D, 0D);
        t.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ, 1D, 1D);
        t.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ, 0D, 1D);
        t.setNormal(0.0F, 1.0F, 0.0F);
        t.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ, 0D, 0D);
        t.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ, 1D, 0D);
        t.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ, 1D, 1D);
        t.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ, 0D, 1D);
        t.setNormal(-1.0F, 0.0F, 0.0F);
        t.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ, 0D, 0D);
        t.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ, 1D, 0D);
        t.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ, 1D, 1D);
        t.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ, 0D, 1D);
        t.setNormal(1.0F, 0.0F, 0.0F);
        t.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ, 0D, 0D);
        t.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ, 1D, 0D);
        t.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ, 1D, 1D);
        t.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ, 0D, 1D);
        t.setTranslation(0.0D, 0.0D, 0.0D);
        t.draw();
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		// TODO Auto-generated method stub
		return null;
	}

}
