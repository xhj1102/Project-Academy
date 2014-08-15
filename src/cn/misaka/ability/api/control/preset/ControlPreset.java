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
package cn.misaka.ability.api.control.preset;

import cn.liutils.api.util.Pair;
import cn.misaka.ability.api.APControlMain;

/**
 * 玩家操作预设。总共有四个预设，玩家选择的当前预设决定了每个键位对应的技能操作。
 * @author WeAthFolD
 */
public class ControlPreset  {
	
	/**
	 * key represents abilityID, value represents keyID
	 */
	public static class SkillKey extends Pair<Integer, Integer> {

		public SkillKey(int k, int v) {
			super(k, v);
		}
		
		public static SkillKey fromString(String s) {
			String[] ss = s.split(",");
			if(ss.length != 2) {
				throw new RuntimeException("AcademyCraft splitting entry config failed, maybe an invalid modifying?");
			}
			return new SkillKey(Integer.valueOf(ss[0]), Integer.valueOf(ss[1]));
		}
	}
	
	public SkillKey[] settings = new SkillKey[APControlMain.KEYS];

}
