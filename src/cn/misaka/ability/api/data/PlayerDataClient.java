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

import net.minecraft.entity.player.EntityPlayer;
import cn.misaka.ability.system.data.PlayerDataUpdater;
import cn.misaka.ability.system.network.message.MsgSyncToClient;
import cn.misaka.core.AcademyCraft;
import cn.misaka.core.proxy.APGeneralProps;

/**
 * 客户端玩家数据
 * @author WeAthFolD
 */
public final class PlayerDataClient extends PlayerData {
	
	public boolean initialized = false;
	
	public PlayerDataClient(EntityPlayer player) {
		super(player);
	}

	/* (non-Javadoc)
	 * @see cn.misaka.api.data.APPlayerData#isDataStateGood()
	 */
	@Override
	public boolean isDataStateGood() {
		return initialized;
	}
	
	@Override
	public void fromAbilityData(PlayerDataUpdater data, int flag) {
		this.initialized = true;
		System.out.println("Client received server pack");
		super.fromAbilityData(data, flag);
	}

	@Override
	protected void loadData() { }

	
	private int ticker = 0;
	@Override
	public void updateTick() {
		if(!initialized && ++ticker == APGeneralProps.SYNC_FREQ) {
			System.out.println("Client sending sync request");
			AcademyCraft.netHandler.sendToServer(new MsgSyncToClient.Request(0x03));
		}
	}
	

}
