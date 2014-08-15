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
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.ability.api.control.PlayerControlStat;
import cn.misaka.ability.api.control.SkillControlStat;
import cn.misaka.core.proxy.APClientProps;
import cn.misaka.support.entity.fx.EntitySurroundArcFX;

/**
 * @author WeAthFolD
 *
 */
public class SkillArcGenerate extends AbilitySkill {

	/**
	 * @param name
	 */
	public SkillArcGenerate() {
		super("skill.elec.arcgen");
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
		return 0;
	}
	
	@Override
	public void onKeyStateChange(World world, EntityPlayer player, SkillControlStat stat, int kid, PlayerControlStat mctrl) {
		if(stat.isKeyDown(0) && world.isRemote) {
			Motion3D mot = new Motion3D(player, true);
			
			MovingObjectPosition pos = GenericUtils.rayTraceEntities(null, world, mot.getPosVec(world), mot.move(30).getPosVec(world), player);
			if(pos != null) {
				world.spawnEntityInWorld(new EntitySurroundArcFX(pos.entityHit));
			}
		}
	}

}
