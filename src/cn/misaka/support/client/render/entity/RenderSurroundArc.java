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
package cn.misaka.support.client.render.entity;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.shape.ShpGeneratorArcSquared;
import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.core.proxy.APClientProps;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

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
	
	double[] offset = {0.0D, 0.3D, -0.5D};
    @Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    	GL11.glDisable(GL11.GL_LIGHTING);
    	GL11.glDisable(GL11.GL_CULL_FACE);
    	GL11.glPushMatrix();
    	int id = par1Entity.ticksExisted % 3;
    	double uvOffset = par1Entity.ticksExisted / 80.0D + offset[id];
        boolean mirror = new Random(par1Entity.ticksExisted).nextBoolean();
    	RenderUtils.loadTexture(APClientProps.TEX_ARC_SHELL[par1Entity.ticksExisted % 3]);
    	OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
        Tessellator t = Tessellator.instance;
        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, .5F);
        GL11.glTranslated(par2, par4, par6);
        float s = .05F;
        GL11.glScalef(1.1F, 2.2F, 1.1F);
        ShpGeneratorArcSquared.drawArc(0.1D, 10, 3.0D, mirror, uvOffset);
        GL11.glScalef(s, s, s);
       
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_CULL_FACE);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		// TODO Auto-generated method stub
		return null;
	}

}
