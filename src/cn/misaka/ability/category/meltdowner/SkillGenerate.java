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
package cn.misaka.ability.category.meltdowner;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cn.misaka.ability.api.APDataMain;
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.ability.api.control.PlayerControlStat;
import cn.misaka.ability.api.control.SkillControlStat;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.core.proxy.APClientProps;
import cn.misaka.support.entity.EntityMdBall;

/**
 * @author WeAthFolD
 *
 */
public class SkillGenerate extends AbilitySkill {

	public SkillGenerate(int id) {
		super("skill.md.generate", id);
	}

	@Override
	public int getSuggestKey(int skillKeyID) {
		return 2;
	}
	
	@Override
	public int getMaxKeys() {
		return 1;
	}

	@Override
	public ResourceLocation getLogo() {
		return APClientProps.MD_GENERATE;
	}
	
	@Override
	public void onKeyStateChange(World world, EntityPlayer player, SkillControlStat stat, int kid, PlayerControlStat mctrl) {
		if(!stat.isKeyDown(0)) return;
		PlayerData data = APDataMain.loadPlayerData(player);
		int xp = (int) data.getSkillExp(skillID);
		if(!world.isRemote) {
			if(data.drainCP((int) getConsumption(xp))) {
				System.out.println("Attempting gen");
				world.spawnEntityInWorld(new EntityMdBall(world, player, getChargeTime(xp), getSize(xp), 300));
			}
		}
	}
	
	private int getConsumption(int exp) {
		return (int) (800 * Math.pow(2, exp * 0.18)); //指数增长真的没关系？
	}
	
	private float getSize(int exp) {
		return 0.4F + exp * 0.02F;
	}

	private int getChargeTime(int exp) {
		return 40 - exp * 2;
	}
}
