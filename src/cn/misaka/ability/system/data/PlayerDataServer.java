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
package cn.misaka.ability.system.data;

import cn.misaka.ability.api.data.PlayerData;
import net.minecraft.entity.player.EntityPlayer;

/**
 * 服务端玩家数据。
 */
public class PlayerDataServer extends PlayerData {

	public PlayerDataServer(EntityPlayer player) {
		super(player);
	}

	@Override
	protected void loadData() {
		this.fromAbilityData(PlayerData.getUpdater(thePlayer));
	}

	@Override
	public boolean isDataStateGood() {
		return true; //Loaded then good
	}

	@Override
	public void updateTick() {
		//SERVER DOSEN'T QUITE DO ANYTHING
	}

}
