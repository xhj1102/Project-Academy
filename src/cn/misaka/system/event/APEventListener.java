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
package cn.misaka.system.event;

import cn.misaka.api.data.PlayerData;
import cn.misaka.system.data.APDataMain;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.world.BlockEvent;

/**
 * @author WeAthFolD
 *
 */
public class APEventListener {

	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent event) {
		EntityPlayer player = event.getPlayer();
		PlayerData pstat = APDataMain.loadPlayerData(player);
		if(pstat != null && pstat.isActivated && player.getCurrentEquippedItem() == null) {
			event.setCanceled(true);
		}
	}
}
