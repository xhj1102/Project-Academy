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
package cn.misaka.ability.category.electromaster;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.ability.api.control.PlayerControlStat;
import cn.misaka.ability.api.control.SkillControlStat;
import cn.misaka.core.proxy.APClientProps;

/**
 * 电磁吸取能力。在空间中
 * @author WeAthFolD
 */
public class SkillElectricAttraction extends AbilitySkill {

	public SkillElectricAttraction(int id) {
		super("skill.elec.attract", id);
	}

	@Override
	public ResourceLocation getLogo() {
		return APClientProps.ELEC_ATTRACT;
	}

	@Override
	public int getSuggestKey(int skillKeyID) {
		return 2;
	}
	
	@Override
	public boolean useSkillWithItem() {
		return true;
	}
	
	@Override
	public boolean onSkillTick(World world, EntityPlayer player, SkillControlStat stat, PlayerControlStat mctrl) {
		ItemStack item = player.getCurrentEquippedItem();
		//TODO:Judge if is electricity item, if so, do charging&consume cp
		return true;
	}
	
	public float getConsumption(int exp) {
		return 6.0F + exp * 0.3F;
	}
	
	public float getChargeSpeed(int exp) {
		return 1.0F + exp * 3.0F;
	}
	

}
