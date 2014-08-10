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
package cn.misaka.ability.system.event;

import cn.misaka.ability.system.control.APControlMain;
import cn.misaka.ability.system.data.APDataMain;
import cn.misaka.core.register.APBlocks;
import cn.misaka.core.register.APItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author WeAthFolD
 *
 */
public class APSTickEvents {

	@SubscribeEvent
	public void onServerTick(ServerTickEvent event) {
		if(event.phase == Phase.END) {
			APControlMain.onTick(false);
			APDataMain.onTick(false);
		}
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onClientTick(ClientTickEvent event) {
		if(event.phase == Phase.START) {
			APControlMain.onTick(true);
			APDataMain.onTick(true);
			
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			if(player != null) //(&& ability is activated)
				equipVoid(player);
		}
	}
	
	private void equipVoid(EntityPlayer player) {
		if(player.getCurrentEquippedItem() == null) {
			player.setCurrentItemOrArmor(0, new ItemStack(APItems.itemVoid));
		}
	}
	
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		//Tick consistency
		if(event.phase == Phase.START) {
			if(event.player.getEntityData().getBoolean("ac_ondev")) {
				if(event.player.worldObj.getBlock(MathHelper.floor_double(event.player.posX),
						MathHelper.floor_double(event.player.posY),
						MathHelper.floor_double(event.player.posZ)) != APBlocks.ability_developer)
					event.player.getEntityData().setBoolean("ac_ondev", false);
			}
		}
	}

}
