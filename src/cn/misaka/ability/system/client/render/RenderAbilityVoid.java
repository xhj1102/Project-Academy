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

import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.ability.api.client.data.PlayerDataClient;
import cn.misaka.ability.api.client.render.SkillRender;
import cn.misaka.ability.api.client.render.SkillRender.SkillRenderType;
import cn.misaka.ability.api.control.PlayerControlData;
import cn.misaka.ability.api.control.SkillControlStat;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.ability.system.AbilityMain;
import cn.misaka.ability.system.control.APControlMain;
import cn.misaka.ability.system.data.APDataMain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

/**
 * @author WeAthFolD
 *
 */
public class RenderAbilityVoid implements IItemRenderer {

	/**
	 * 
	 */
	public RenderAbilityVoid() {
		// TODO Auto-generated constructor stub
	}

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

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		SkillRenderType tp = type == ItemRenderType.EQUIPPED_FIRST_PERSON ? SkillRenderType.FIRSTPERSON : SkillRenderType.EQUIPPED;
		EntityPlayer player = (EntityPlayer) data[1];
		PlayerData pdata = APDataMain.loadPlayerData(player);
		PlayerControlData pstat = APControlMain.loadControlData(player);
		SkillRender render = AbilitySkill.DEFAULT_SKILL_RENDER;
		if(pstat.activateSkill >= 0 && pdata != null) {
			AbilitySkill skl = AbilityMain.getAbility(pdata.classid).getSkill(pstat.activateSkill);
			if(skl.useRender())
				render = skl.getSkillRender();
		}
		
		render.onRender(player, pstat.getSkillStates(pstat.activateSkill), pdata, tp);
	}

}
