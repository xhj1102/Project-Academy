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
package cn.misaka.support.client.render.item;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.util.RenderUtils;
import cn.liutils.api.util.GenericUtils;
import cn.misaka.core.proxy.APClientProps;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.IItemRenderer;

/**
 * @author WeAthFolD
 *
 */
public class RenderCoin implements IItemRenderer {

	/**
	 * 
	 */
	public RenderCoin() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.minecraftforge.client.IItemRenderer#handleRenderType(net.minecraft.item.ItemStack, net.minecraftforge.client.IItemRenderer.ItemRenderType)
	 */
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type) {
		case EQUIPPED:
		case EQUIPPED_FIRST_PERSON:
			return true;
		default:
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see net.minecraftforge.client.IItemRenderer#shouldUseRenderHelper(net.minecraftforge.client.IItemRenderer.ItemRenderType, net.minecraft.item.ItemStack, net.minecraftforge.client.IItemRenderer.ItemRendererHelper)
	 */
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return false;
	}

	/* (non-Javadoc)
	 * @see net.minecraftforge.client.IItemRenderer#renderItem(net.minecraftforge.client.IItemRenderer.ItemRenderType, net.minecraft.item.ItemStack, java.lang.Object[])
	 */
	@Override
	public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {
		NBTTagCompound nbt = GenericUtils.loadCompound(stack);
		if(nbt.getBoolean("isThrowing")) {
			if(type == ItemRenderType.EQUIPPED_FIRST_PERSON)renderFirstPerson(nbt, nbt.getInteger("throwTick"));
			else renderEquipped(nbt, nbt.getInteger("throwTick"));
		} else renderFirstPerson(nbt, 0);
	}
	
	private void renderFirstPerson(NBTTagCompound nbt, int tick) {
		float angle = nbt.getFloat("angle");
		angle = tick == 0 ? 0 : angle + 35F;
		if(angle >= 360F) angle -= 360F;
		nbt.setFloat("angle", angle);
		float height = -0.01F * (tick * tick) + 0.4F * tick;
		GL11.glTranslatef(-height * .4F, height, 0F);
		GL11.glTranslatef(.5F, .5F, 0F);
		GL11.glRotatef(angle, 1F, (tick / 20F), 0F);
		GL11.glScalef(.5F, .5F, .5F);
		GL11.glTranslatef(-.5F, -.5F, 0F);
		RenderUtils.renderItemIn2d(0.0625F, APClientProps.TEX_COIN_FRONT, APClientProps.TEX_COIN_BACK);
	}
	
	private void renderEquipped(NBTTagCompound nbt, int tick) {
		float angle = nbt.getFloat("angle");
		angle = tick == 0 ? 0 : angle + 35F;
		if(angle >= 360F) angle -= 360F;
		nbt.setFloat("angle", angle);
		float height = -0.01F * (tick * tick) + 0.4F * tick;
		GL11.glTranslatef(-.3F * height, 0F, -height);
		GL11.glTranslatef(.5F, .5F, 0F);
		GL11.glRotatef(angle, 1F, (tick / 20F), 0F);
		GL11.glScalef(.5F, .5F, .5F);
		GL11.glTranslatef(-.5F, -.5F, 0F);
		RenderUtils.renderItemIn2d(0.0625F, APClientProps.TEX_COIN_FRONT, APClientProps.TEX_COIN_BACK);
	}

}
