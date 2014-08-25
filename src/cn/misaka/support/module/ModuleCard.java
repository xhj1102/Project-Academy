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
package cn.misaka.support.module;

import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.core.proxy.APClientProps;
import cn.misaka.core.register.APItems;
import cn.misaka.support.block.IADModuleAttached;
import cn.misaka.support.item.ItemModuleAttached;

/**
 * @author WeAthFolD
 *
 */
public class ModuleCard implements IADModuleAttached {

	public ModuleCard() {
		
	}

	@Override
	public String getModifyAttribute() {
		return "1";
	}

	@Override
	public String getModifyValue() {
		return "energy";
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void renderAtOrigin() {
		float scale = 0.0017F;
		GL11.glScalef(scale, scale, scale);
		RenderUtils.loadTexture(APClientProps.TEX_MDL_ELEC_CARD);
		GL11.glRotatef(180F, 1F, 0F, 0F);
		APClientProps.MDL_ELEC_CARD.renderAll();
	}

	@Override
	public ItemStack getDrop() {
		return new ItemStack(APItems.adEnergyCard);
	}

}
