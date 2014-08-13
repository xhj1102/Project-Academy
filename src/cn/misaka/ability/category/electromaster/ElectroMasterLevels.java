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
import net.minecraft.world.World;
import cn.misaka.ability.api.ability.AbilityLevel;
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.ability.api.control.PlayerControlStat;

/**
 * @author WeAthFolD
 *
 */
public class ElectroMasterLevels {

	public static class Level1 extends AbilityLevel {

		public Level1() {
			super(0);
		}

		@Override
		public boolean canStudySkill(AbilitySkill skill, int id) {
			return false;
		}

		@Override
		public boolean isSkillDefaultActivated(int id) {
			return false;
		}
		
	}
	
	public static class Level2 extends AbilityLevel {

		public Level2() {
			super(1);
		}

		@Override
		public boolean canStudySkill(AbilitySkill skill, int id) {
			return false;
		}

		@Override
		public boolean isSkillDefaultActivated(int id) {
			return false;
		}
		
	}
	
	public static class Level3 extends AbilityLevel {

		public Level3() {
			super(2);
		}

		@Override
		public boolean canStudySkill(AbilitySkill skill, int id) {
			return false;
		}

		@Override
		public boolean isSkillDefaultActivated(int id) {
			return false;
		}
		
	}
	
	public static class Level4 extends AbilityLevel {

		public Level4() {
			super(3);
		}

		@Override
		public boolean canStudySkill(AbilitySkill skill, int id) {
			return false;
		}

		@Override
		public boolean isSkillDefaultActivated(int id) {
			return false;
		}
		
		@Override
		public void onPlayerUpdate(World world, EntityPlayer player, PlayerControlStat ctrl) {
			//TODO:自动给附近的电力方块充电
		}
		
	}
	
	public static class Level5 extends AbilityLevel {

		public Level5() {
			super(4);
		}

		@Override
		public boolean canStudySkill(AbilitySkill skill, int id) {
			return true;
		}

		@Override
		public boolean isSkillDefaultActivated(int id) {
			//反正都激活了，没必要接管啦~
			return false;
		}
		
		@Override
		public void onPlayerUpdate(World world, EntityPlayer player, PlayerControlStat ctrl) {
			//TODO:自动给附近的电力方块充电
		}
		
	}

}
