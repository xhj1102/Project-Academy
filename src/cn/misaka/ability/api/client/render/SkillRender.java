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
package cn.misaka.ability.api.client.render;

import cn.misaka.ability.api.control.SkillControlStat;
import cn.misaka.ability.api.data.PlayerData;
import net.minecraft.entity.player.EntityPlayer;

/**
 * 技能渲染。不需管手什么的，只渲染该技能的特效即可。
 * @author WeAthFolD
 *
 */
public class SkillRender {
	
	public enum SkillRenderType {
		FIRSTPERSON, EQUIPPED
	}

	public void onRender(EntityPlayer player, SkillControlStat stat, PlayerData data, SkillRenderType type) {
	}
	

	
}
