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

import net.minecraft.util.ResourceLocation;
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.core.proxy.APClientProps;

/**
 * @author WeAthFolD
 *
 */
public class SkillTest2 extends AbilitySkill {

	/**
	 * @param name
	 */
	public SkillTest2(int id) {
		super("skill.test2.name", id);
	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.api.ability.AbilitySkill#getLogo()
	 */
	@Override
	public ResourceLocation getLogo() {
		return APClientProps.SKL_TEST_2;
	}
	
	@Override
	public int getMaxKeys() {
		return 1;
	}

	@Override
	public int getSuggestKey(int skillKeyID) {
		return 1;
	}

}
