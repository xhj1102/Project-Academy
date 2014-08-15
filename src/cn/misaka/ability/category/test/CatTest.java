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
import cn.liutils.api.util.Pair;
import cn.misaka.ability.api.ability.AbilityCategory;
import cn.misaka.ability.api.ability.AbilityLevel;
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.core.proxy.APClientProps;

/**
 * @author WeAthFolD
 *
 */
public class CatTest extends AbilityCategory {

	
	private static Pair<ResourceLocation, ResourceLocation> texture = new Pair(APClientProps.TEX_HUD_LOGO, APClientProps.TEX_HUD_CPBAR);

	public CatTest(int id) {
		super("test", id);
	}

	@Override
	public Pair<ResourceLocation, ResourceLocation> getHudTextureOverride() {
		return texture;
	}
	
	@Override
	protected void initClass() {
		this.ability_levels = new AbilityLevel[] {
				new LevelTest(0),
				new LevelTest(1),
				new LevelTest(2),
				new LevelTest(3)
		};
		
		this.ability_skills = new AbilitySkill[] {
				new SkillTest1(0),
				new SkillTest2(1)
		};
	}

	@Override
	public ResourceLocation getLogo() {
		return null;
	}
	

}
