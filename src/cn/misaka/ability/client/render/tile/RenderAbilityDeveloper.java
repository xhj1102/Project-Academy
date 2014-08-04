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
package cn.misaka.ability.client.render.tile;

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
public class RenderAbilityDeveloper extends TileEntitySpecialRenderer {

	private static final IModelCustom model = APClientProps.MDL_ABILITY_DEVELOPER;
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float subtick) {
		
		if((te.blockMetadata & 0x01) == 1) return; //Render only HEAD
		float scale = 0.015F;
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glPushMatrix(); {
			RenderUtils.loadTexture(APClientProps.TEX_ABILITY_DEVELOPER);
			
			GL11.glTranslated(x, y, z);
			GL11.glScalef(scale, scale, scale);
			model.renderAll();
			
		} GL11.glPopMatrix();
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

}
