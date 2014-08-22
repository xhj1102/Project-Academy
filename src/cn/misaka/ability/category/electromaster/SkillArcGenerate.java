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
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cn.liutils.api.util.GenericUtils;
import cn.liutils.api.util.Motion3D;
import cn.misaka.ability.api.APDataMain;
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.ability.api.control.PlayerControlStat;
import cn.misaka.ability.api.control.SkillControlStat;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.ability.category.electromaster.client.SkillRenderArc;
import cn.misaka.core.proxy.APClientProps;
import cn.misaka.support.entity.EntityArc;
import cn.misaka.support.entity.fx.EntityElecArcFX;
import cn.misaka.support.entity.fx.EntitySurroundArcFX;

/**
 * @author WeAthFolD
 *
 */
public class SkillArcGenerate extends AbilitySkill {

	/**
	 * @param name
	 */
	public SkillArcGenerate(int id) {
		super("skill.elec.arcgen", id);
		this.skillRender = new SkillRenderArc();
	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.api.ability.AbilitySkill#getLogo()
	 */
	@Override
	public ResourceLocation getLogo() {
		return APClientProps.ELEC_ARC;
	}
	
	@Override
	public int getMaxKeys() {
		return 1;
	}

	@Override
	public int getSuggestKey(int skillKeyID) {
		return 2;
	}
	
	@Override
	public void onKeyStateChange(World world, EntityPlayer player, SkillControlStat stat, int kid, PlayerControlStat mctrl) {
		PlayerData data = APDataMain.loadPlayerData(player);
		float exp = data.getSkillExp(skillID);
		if(stat.isKeyDown(0)) {
			player.getEntityData().setBoolean("ap_arcdown", true);
			world.spawnEntityInWorld(world.isRemote ? new EntityElecArcFX(world, player, 
					EntityArc.DST_CONV_RATE * getDamage((int) exp))
				: new EntityArc(world, player, getDamage((int) exp)));
		} else {
			player.getEntityData().setBoolean("ap_arcdown", false);
		}
	}
	
	public boolean onSkillTick(World world, EntityPlayer player, SkillControlStat stat, PlayerControlStat mctrl) {
		PlayerData data = APDataMain.loadPlayerData(player);
		if(!data.drainCP((int) getConsumption((int) data.getSkillExp(skillID)))) {
			player.getEntityData().setBoolean("ap_arcdown", false);
		}
		return false;
	}
	
	private float getConsumption(int exp) {
		return 10.0F + exp * 0.2F;
	}
	
	private float getDamage(int exp) {
		return 0.9F + exp * 0.8F;
	}
	
	/*
	@Override
	public void onKeyStateChange(World world, EntityPlayer player, SkillControlStat stat, int kid, PlayerControlStat mctrl) {
		if(stat.isKeyDown(0) && world.isRemote) {
			Motion3D mot = new Motion3D(player, true);
			
			MovingObjectPosition pos = GenericUtils.rayTraceEntities(null, world, mot.getPosVec(world), mot.move(30).getPosVec(world), player);
			if(pos != null) {
				world.spawnEntityInWorld(new EntitySurroundArcFX(pos.entityHit));
			}
		}
	}*/

}
