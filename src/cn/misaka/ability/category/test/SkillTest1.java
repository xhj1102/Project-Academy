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
package cn.misaka.ability.category.test;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.ability.api.control.PlayerControlStat;
import cn.misaka.ability.api.control.SkillControlStat;
import cn.misaka.core.proxy.APClientProps;
import cn.misaka.support.entity.fx.EntityArcFX;

/**
 * @author WeAthFolD
 *
 */
public class SkillTest1 extends AbilitySkill {

	/**
	 * @param name
	 */
	public SkillTest1(int id) {
		super("skill.test1.name", id);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.api.ability.AbilitySkill#getLogo()
	 */
	@Override
	public ResourceLocation getLogo() {
		return APClientProps.SKL_TEST_1;
	}
	
	@Override
	public void onKeyStateChange(World world, EntityPlayer player, SkillControlStat stat, int kid, PlayerControlStat mctrl) {
		System.out.println("SkillTest1 onKeyStateChange to " +  stat.isKeyDown(kid) + "at " + world.isRemote + " Side");
		if(world.isRemote && stat.isKeyDown(kid)) {
			world.spawnEntityInWorld(new EntityArcFX(world, player, 200.0D));
		}
	}
	
	@Override
	public int getMaxKeys() {
		return 1;
	}

	@Override
	public int getSuggestKey(int skillKeyID) {
		return 0; 
	}

}
