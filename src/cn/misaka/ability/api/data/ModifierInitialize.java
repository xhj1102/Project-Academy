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
package cn.misaka.ability.api.data;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import cn.misaka.ability.system.AbilityMain;

/**
 * @author WeAthFolD
 *
 */
public class ModifierInitialize implements IDataModifier {

	protected static final Random RNG = new Random();
	
	public ModifierInitialize() {
	}

	@Override
	public void applyModification(EntityPlayer player, PlayerData data) {
		data.current_cp = data.max_cp = PlayerData.INIT_CP[0];
		data.level = 0;
		data.catid = RNG.nextInt(AbilityMain.getMaxAbilities()) + 1;
	}
	
	

}
