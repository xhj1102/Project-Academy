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
import cn.misaka.ability.block.BlockAbilityDeveloper;
import cn.misaka.core.proxy.APClientProps;
import cn.misaka.core.register.APBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author WeAthFolD
 *
 */
public class RenderAbilityDeveloper extends TileEntitySpecialRenderer {

	private final IModelCustom model = APClientProps.MDL_ABILITY_DEVELOPER;
	private final float rotations[] = new float[] { 90, 0, -90, 180 };
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float subtick) {
		
		int meta = te.getBlockMetadata();
		if((meta & 0x01) == 1) return; //Render only HEAD
		float scale = 0.0215F;
		ForgeDirection dir = BlockAbilityDeveloper.getFacingDirection(te.blockMetadata);
		
		GL11.glPushMatrix(); {
			RenderUtils.loadTexture(APClientProps.TEX_MDL_ABILITY_DEVELOPER);
			
			GL11.glTranslated(x + 0.5 + dir.offsetX * 0.5, y, z + 0.5 + dir.offsetZ * 0.5);
			
			GL11.glRotatef(rotations[meta >> 1], 0.0F, 1.0F, 0.0F);
			GL11.glTranslated(.1D, 0.0D, -0.12D);
			GL11.glScalef(scale, scale, scale);
			model.renderAll();
			
		} GL11.glPopMatrix();
	}

}
