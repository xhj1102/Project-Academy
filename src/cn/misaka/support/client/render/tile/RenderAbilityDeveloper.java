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

import cn.liutils.api.client.model.IItemModel;
import cn.liutils.api.client.model.ItemModelCustom;
import cn.liutils.api.client.render.RenderModelItem;
import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.core.proxy.APClientProps;
import cn.misaka.support.block.BlockAbilityDeveloper;
import cn.misaka.support.block.IADModuleAttached;
import cn.misaka.support.block.tile.TileAbilityDeveloper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author WeAthFolD
 *
 */
public class RenderAbilityDeveloper extends TileEntitySpecialRenderer {

	private final IModelCustom model = APClientProps.MDL_ABILITY_DEVELOPER;
	private final float rotations[] = new float[] { -90, 180, 90, 0 };
	
	public static class ItemRenderer extends RenderModelItem {

		public ItemRenderer() {
			super(new ItemModelCustom(APClientProps.MDL_ABILITY_DEVELOPER), APClientProps.TEX_MDL_ABILITY_DEVELOPER);
			setOffset(-0.09F, 0F, 0F);
			setScale(-.152F);
			setEquipOffset(0.27F, -0.003F, -6.245E-17F);
			setEquipRotation(-12.054F, -85.203F, -0.534F);
		}
		
		@Override
		public boolean handleRenderType(ItemStack item, ItemRenderType type) {
			switch (type) {
			case EQUIPPED:
			case EQUIPPED_FIRST_PERSON:
			case ENTITY:
				return true;
			default:
				return false;
			}
		}
		
	}
	
	private final Vec3[] offsets = {
		Vec3.createVectorHelper(0D, 0D, 0D),
		Vec3.createVectorHelper(0D, 0D, 0D),
		Vec3.createVectorHelper(0D, 0D, 0D),
		Vec3.createVectorHelper(0D, 0D, 0D)
	};
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float subtick) {
		
		int meta = te.getBlockMetadata();
		TileAbilityDeveloper ad = (TileAbilityDeveloper) te;
		if((meta & 0x01) == 1) return; //Render only HEAD
		float scale = 0.0215F;
		ForgeDirection dir = BlockAbilityDeveloper.getFacingDirection(te.blockMetadata);
		
		GL11.glPushMatrix(); {
			RenderUtils.loadTexture(APClientProps.TEX_MDL_ABILITY_DEVELOPER);
			
			GL11.glTranslated(x + 0.5 + dir.offsetX * 0.5, y, z + 0.5 + dir.offsetZ * 0.5);	
			
			GL11.glTranslated(.1D, 0.0D, -0.12D);
			GL11.glRotatef(rotations[meta >> 1], 0.0F, 1.0F, 0.0F);
			for(int i = 0; i < 4; i++) {
				Vec3 off = offsets[i];
				GL11.glPushMatrix(); {
					IADModuleAttached mod = ad.sidedModules.get(i);
					GL11.glTranslated(off.xCoord, off.yCoord, off.zCoord);
					if(i >= 2)
						GL11.glRotatef(180F, 0F, 1F, 1F);
					mod.renderAtOrigin();
				} GL11.glPopMatrix();
			}
			
			GL11.glScalef(scale, scale, scale);
			model.renderAll();
			
		} GL11.glPopMatrix();
	}

}
