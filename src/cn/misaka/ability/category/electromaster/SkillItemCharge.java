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

import net.minecraft.util.ResourceLocation;
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.core.proxy.APClientProps;

/**
 * @author WeAthFolD
 *
 */
public class SkillItemCharge extends AbilitySkill {

	public SkillItemCharge() {
		super("skill.elec.itemchg");
	}

	@Override
	public ResourceLocation getLogo() {
		return APClientProps.ELEC_CHARGE;
	}

	@Override
	public int getSuggestKey(int skillKeyID) {
		return 3;
	}

}
