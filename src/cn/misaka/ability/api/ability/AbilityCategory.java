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
package cn.misaka.ability.api.ability;

import cn.liutils.api.util.Pair;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 能力类。提供该系的总体说明，Logo，等级类等信息。
 * @author WeAthFolD
 */
public abstract class AbilityCategory {

	protected final String unl_name;
	
	public AbilityLevel[] ability_levels;
	public AbilitySkill[] ability_skills;
	
	/**
	 * 在统一加载时给予的绝对id，和该能力系在总存储中的下标号相同。
	 */
	public final int id;
	
	public AbilityCategory(String name, int id) {
		unl_name = name;
		this.id = id;
		initClass();
	}
	
	protected void initClass() {
		ability_levels = new AbilityLevel[0];
		ability_skills = new AbilitySkill[0];
	}
	
	public AbilityLevel getLevel(int id) {
		if(id >= ability_levels.length)
			return null;
		return ability_levels[id];
	}
	
	public AbilitySkill getSkill(int id) {
		if(id >= ability_skills.length)
			return null;
		return ability_skills[id];
	}
	
	public final int getMaxLevels() {
		return ability_levels.length;
	}
	
	public final int getMaxSkills() {
		return ability_skills.length;
	}
	
	/**
	 * 获取Hud贴图的覆盖。贴图的位置必须和模板贴图严格一致，first为logo框，second为CP框
	 * @return
	 */
	@SideOnly(Side.CLIENT)
	public abstract Pair<ResourceLocation, ResourceLocation> getHudTextureOverride();
	
	@SideOnly(Side.CLIENT)
	public abstract ResourceLocation getLogo();
	
	/**
	 * 获取经过translate的系名。
	 * @return
	 */
	public final String getCategoryNameForDisplay() {
		return StatCollector.translateToLocal(unl_name);
	}
	
	/**
	 * 获取未translate的原始名称。
	 * @return
	 */
	public final String getUnlocalizedName() {
		return unl_name;
	}

}
