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
package cn.misaka.api.data;

import cn.misaka.api.ability.AbilityClass;
import cn.misaka.system.AbilityMain;

/**
 * 在这里进行对信息的处理和修改等内容
 * @author WeAthFolD
 */
public class PlayerDataHelper {

	public static AbilityClass getAbilityClass(PlayerData data) {
		return AbilityMain.getAbility(data.classid);
	}
}
