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
package cn.misaka.api.client.data;

import net.minecraft.entity.player.EntityPlayer;
import cn.misaka.api.data.PlayerData;
import cn.misaka.system.control.preset.ControlPreset;

/**
 * TODO: Waiting to be done
 * @author WeAthFolD
 *
 */
public final class PlayerDataClient extends PlayerData {

	public boolean isActivated;
	
	/**
	 * @param player
	 */
	public PlayerDataClient(EntityPlayer player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.misaka.api.data.APPlayerData#isDataStateGood()
	 */
	@Override
	public boolean isDataStateGood() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see cn.misaka.api.data.APPlayerData#isDataStateGood(int)
	 */
	@Override
	public boolean isDataStateGood(int flag) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public ControlPreset.Entry[] getCurrentPreset() {
		return new ControlPreset.Entry[4];
	}

}
