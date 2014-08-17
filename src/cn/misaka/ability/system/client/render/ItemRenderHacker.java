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
package cn.misaka.ability.system.client.render;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.ability.api.APControlMain;
import cn.misaka.ability.api.APDataMain;
import cn.misaka.ability.api.ability.AbilityCategory;
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.ability.api.client.render.SkillRender.SkillRenderType;
import cn.misaka.ability.api.control.PlayerControlStat;
import cn.misaka.ability.api.control.SkillControlStat;
import cn.misaka.ability.api.data.PlayerData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

/**
 * @author WeAthFolD
 *
 */
public class ItemRenderHacker implements IItemRenderer {
	
	IItemRenderer original;

	/**
	 * 
	 */
	public ItemRenderHacker(IItemRenderer another) {
		original = another;
	}

	/* (non-Javadoc)
	 * @see net.minecraftforge.client.IItemRenderer#handleRenderType(net.minecraft.item.ItemStack, net.minecraftforge.client.IItemRenderer.ItemRenderType)
	 */
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		if(original != null) {
			return original.handleRenderType(item, type);
		}
		return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
	}

	/* (non-Javadoc)
	 * @see net.minecraftforge.client.IItemRenderer#shouldUseRenderHelper(net.minecraftforge.client.IItemRenderer.ItemRenderType, net.minecraft.item.ItemStack, net.minecraftforge.client.IItemRenderer.ItemRendererHelper)
	 */
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		if(original != null) {
			return original.shouldUseRenderHelper(type, item, helper);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see net.minecraftforge.client.IItemRenderer#renderItem(net.minecraftforge.client.IItemRenderer.ItemRenderType, net.minecraft.item.ItemStack, java.lang.Object[])
	 */
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		if(original != null)
			original.renderItem(type, item, data);
		else {
			RenderUtils.renderItemIn2d(item, 0.0625F);
		}
		GL11.glPopMatrix();
		
		if(type != ItemRenderType.EQUIPPED && type != ItemRenderType.EQUIPPED_FIRST_PERSON)
			return;
		EntityLivingBase base = (EntityLivingBase) data[1];
		if(!(base instanceof EntityPlayer) || ((EntityPlayer)base).getCurrentEquippedItem() != item)
			return;
		EntityPlayer player = (EntityPlayer) base;
		SkillRenderType type2;
		type2 = type == ItemRenderType.EQUIPPED ? SkillRenderType.EQUIPPED : SkillRenderType.FIRSTPERSON;
		PlayerData data2 = APDataMain.loadPlayerData(player);
		PlayerControlStat pstat = APControlMain.loadControlData(player);
		if(data2.isDataStateGood() && data2.isActivated) {
			GL11.glPushMatrix(); {
				
				AbilityCategory ac = data2.getAbilityCategory();
				
				if(ac != null) { //遍历skill渲染器然后执行渲染
					for(int i = 0; i < ac.getMaxSkills(); i++) {
						AbilitySkill skl = ac.getSkill(i);
						SkillControlStat sklstat = pstat.getSkillStat(i);
						if(sklstat != null && skl.isSkillActivated(player.worldObj, player, sklstat, pstat)) 
							skl.getSkillRender().onRender(player, sklstat, data2, type2);
					}
				}
				
			} GL11.glPopMatrix();
		}
	}

}
