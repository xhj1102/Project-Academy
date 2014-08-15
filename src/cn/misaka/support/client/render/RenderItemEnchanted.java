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
package cn.misaka.support.client.render;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

/**
 * @author WeAthFolD
 *
 */
public class RenderItemEnchanted implements IItemRenderer {


	@Override
	public boolean handleRenderType(ItemStack stack, ItemRenderType type) {
		switch(type) {
		case EQUIPPED:
		case EQUIPPED_FIRST_PERSON:
		case INVENTORY:
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack stack,
			ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {
		if(type == ItemRenderType.INVENTORY)
			renderInv(stack);
		else renderEquipped(stack);
	}
	
	protected void renderEquipped(ItemStack stack) {
		//TODO
	}
	
	protected void renderInv(ItemStack stack) {
		//TODO
	}

}
