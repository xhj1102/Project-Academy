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
import cn.liutils.api.client.key.IKeyHandler;
import cn.liutils.api.util.GenericUtils;
import cn.misaka.ability.api.APControlMain;

/**
 * 技能操作键位。传给APControlMain进行下一步动作。
 */
public class KeySkillControl implements IKeyHandler {

	Minecraft mc = Minecraft.getMinecraft();
	
	private final int subid;
	
	public KeySkillControl(int kid) {
		subid = kid;
	}

	@Override
	public void onKeyDown(int keyCode, boolean tickEnd) {
		if(tickEnd || !GenericUtils.isPlayerInGame())
			return;
		EntityPlayer player = mc.thePlayer;
		APControlMain.onKeyChangedClient(subid, true);
	}

	@Override
	public void onKeyUp(int keyCode, boolean tickEnd) {
		if(tickEnd || !GenericUtils.isPlayerInGame())
			return;
		EntityPlayer player = mc.thePlayer;
		APControlMain.onKeyChangedClient(subid, false);
	}

	/* (non-Javadoc)
	 * @see cn.liutils.api.client.register.IKeyHandler#onKeyTick(int, boolean)
	 */
	@Override
	public void onKeyTick(int keyCode, boolean tickEnd) {
		//NOPE
	}

}
