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
package cn.misaka.ability.api.control.modifier;

import net.minecraft.entity.player.EntityPlayer;
import cn.misaka.ability.api.ability.AbilityCategory;
import cn.misaka.ability.api.ability.AbilityLevel;
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.ability.api.data.PlayerDataClient;
import cn.misaka.ability.system.control.APControlMain;
import cn.misaka.ability.system.control.preset.ControlPreset;
import cn.misaka.ability.system.control.preset.ControlPreset.Entry;

/**
 * 自hu动luan的安排某preset的键位。
 * @author WeAthFolD
 *
 */
public class PresetAutoArrange implements IPresetModifier {

	/**
	 * 
	 */
	public PresetAutoArrange() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyModification(EntityPlayer player, PlayerData data,
			ControlPreset preset) {
		int nextKey = 0;
		AbilityCategory cat = data.getAbilityClass();
		if(cat == null) return;
		for(int i = 0; i < cat.getMaxSkills() && nextKey < APControlMain.KEYS; i++) {
			if(data.skill_open == null) data.resetSkillInf();
			if(data.skill_open[i]) {
				AbilitySkill skl = cat.getSkill(i);
				for(int j = 0; j < skl.getMaxKeys() && nextKey < APControlMain.KEYS; j++) {
					preset.settings[nextKey++] = new Entry(i, j);
				}
			}
		}
		for(; nextKey < APControlMain.KEYS; nextKey++)
			preset.settings[nextKey] = new Entry(-1, 0);
	}

}
