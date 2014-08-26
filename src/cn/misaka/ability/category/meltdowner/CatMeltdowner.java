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
public class CatMeltdowner extends AbilityCategory {

	/**
	 * @param name
	 * @param id
	 */
	public CatMeltdowner(int id) {
		super("category.meltdowner", id);
	}
	
	protected void initClass() {
		this.ability_levels = new AbilityLevel[] {
				new MDLevels.Level1(0),
				new MDLevels.Level2(1),
				new MDLevels.Level3(2),
				new MDLevels.Level4(3),
				new MDLevels.Level5(4)
		};
		
		this.ability_skills = new AbilitySkill[] {
				
		};
	}

	@Override
	public Pair<ResourceLocation, ResourceLocation> getHudTextureOverride() {
		//TODO:坐等？
		return null;
	}

	@Override
	public ResourceLocation getLogo() {
		return APClientProps.MD_LOGO;
	}

}
