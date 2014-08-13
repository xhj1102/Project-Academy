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

import cn.misaka.ability.api.ability.AbilityLevel;
import cn.misaka.ability.api.ability.AbilitySkill;

/**
 * @author WeAthFolD
 *
 */
public class LevelTest extends AbilityLevel {
	
	public LevelTest(int id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.api.ability.AbilityLevel#canStudySkill(cn.misaka.ability.api.ability.AbilitySkill, int)
	 */
	@Override
	public boolean canStudySkill(AbilitySkill skill, int id) {
		return id == 0 || id == 1;
	}

	@Override
	public boolean isSkillDefaultActivated(int id) {
		return id == 0 || id == 1;
	}

}
