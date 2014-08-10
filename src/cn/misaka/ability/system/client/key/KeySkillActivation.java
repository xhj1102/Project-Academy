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
package cn.misaka.ability.system.client.key;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cn.liutils.api.client.register.IKeyHandler;
import cn.liutils.api.util.GenericUtils;
import cn.misaka.ability.api.data.PlayerDataClient;
import cn.misaka.ability.system.data.APDataMain;
import cn.misaka.ability.system.network.message.MsgSyncToServer;
import cn.misaka.core.AcademyCraft;

/**
 * @author WeAthFolD
 *
 */
public class KeySkillActivation implements IKeyHandler {
	
	public KeySkillActivation() {
	}

	/* (non-Javadoc)
	 * @see cn.liutils.api.client.register.IKeyHandler#onKeyDown(int, boolean)
	 */
	@Override
	public void onKeyDown(int keyCode, boolean tickEnd) { 
		if(tickEnd || !GenericUtils.isPlayerInGame()) return;
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		PlayerDataClient data = (PlayerDataClient) APDataMain.loadPlayerData(player);
		data.isActivated = !data.isActivated;
		System.out.println("Activation change client : " + data.isActivated);
		AcademyCraft.netHandler.sendToServer(new MsgSyncToServer(data));
	}

	/* (non-Javadoc)
	 * @see cn.liutils.api.client.register.IKeyHandler#onKeyUp(int, boolean)
	 */
	@Override
	public void onKeyUp(int keyCode, boolean tickEnd) {}

	/* (non-Javadoc)
	 * @see cn.liutils.api.client.register.IKeyHandler#onKeyTick(int, boolean)
	 */
	@Override
	public void onKeyTick(int keyCode, boolean tickEnd) {}

}
