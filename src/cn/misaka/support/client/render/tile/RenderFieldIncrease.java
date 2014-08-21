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
package cn.misaka.support.client.render.tile;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.core.proxy.APClientProps;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.IModelCustom;

/**
 * @author WeAthFolD
 *
 */
public class RenderFieldIncrease extends TileEntitySpecialRenderer {

	public RenderFieldIncrease() {
	}

	private final float rotations[] = new float[] { 90, 180, -90, 0 };
	
	@Override
	public void renderTileEntityAt(TileEntity var1, double x, double y,
			double z, float var8) {
		IModelCustom model = APClientProps.MDL_MAGNET_MODULE;
		int meta = var1.getBlockMetadata();
		GL11.glPushMatrix(); {
			
			RenderUtils.loadTexture(APClientProps.TEX_MDL_MAGNET_MODULE);
			GL11.glTranslated(x, y, z);
			float scale = 0.0036F;
			GL11.glTranslatef(0.5F, 0.0F, 0.5F);
			GL11.glRotatef(rotations[meta], 0F, -1F, 0F);
			GL11.glScalef(scale, scale, scale);
			model.renderAll();
			
		} GL11.glPopMatrix();
	}

}
