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

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.model.ITileEntityModel;
import cn.liutils.api.client.model.TileEntityModelCustom;
import cn.liutils.api.client.render.RenderTileModelSided;
import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.core.proxy.APClientProps;

/**
 * @author WeAthFolD
 *
 */
public class RenderWindGenerator extends RenderTileModelSided {

	private ITileEntityModel fan = new TileEntityModelCustom(APClientProps.MDL_WINDGEN_FAN);
	
	/**
	 * @param mdl
	 */
	public RenderWindGenerator() {
		super(new TileEntityModelCustom(APClientProps.MDL_WINDGEN));
		this.scale = 1.0F;
	}
	
	@Override
	protected void renderAtOrigin(TileEntity te) {
		if(te.getBlockMetadata() >> 2 != 0) return;
		int meta = te.getBlockMetadata();
		scale = 0.15F;
		GL11.glPushMatrix(); {
			GL11.glRotatef(rotations[meta], 0F, 1F, 0F);
			GL11.glScalef(scale, scale, scale);
			
			RenderUtils.loadTexture(APClientProps.TEX_MDL_WINDGEN_FAN);
			GL11.glPushMatrix(); {
				
				GL11.glTranslated(-.1D, 71.95D, 4.9D);
				GL11.glRotatef(Minecraft.getSystemTime() / 100F, 0F, 0F, 1F);
				fan.render(te, 0D, 0D, 0D, 0F, .9F, 0F);
				
			} GL11.glPopMatrix();
			
			RenderUtils.loadTexture(APClientProps.TEX_MDL_WINDGEN);
			theModel.render(te, 0D, 0D, 0D, 1F, 1F, 1F);
		} GL11.glPopMatrix();
	}

}
