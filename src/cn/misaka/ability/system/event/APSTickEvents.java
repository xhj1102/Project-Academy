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

import java.lang.reflect.Field;
import java.util.IdentityHashMap;

import cn.misaka.ability.api.APControlMain;
import cn.misaka.ability.api.APDataMain;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.ability.system.client.render.ItemRenderHacker;
import cn.misaka.core.register.APBlocks;
import cn.misaka.core.register.APItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
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
	
	private Field fldRenderMap;
	
	public APSTickEvents() {
		try {
			fldRenderMap = MinecraftForgeClient.class.getDeclaredField("customItemRenderers");
			fldRenderMap.setAccessible(true);
		} catch(Exception e) {}
	}

	@SubscribeEvent
	public void onServerTick(ServerTickEvent event) {
		if(event.phase == Phase.START) {
			APControlMain.onTick(false);
			APDataMain.onTick(false);
		}
	}
	
	private Item lastOverrideItem;
	private IItemRenderer lastRenderer;
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onClientTick(ClientTickEvent event) {
		if(event.phase == Phase.START) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			if(player == null) return;
			
			APControlMain.onTick(true);
			APDataMain.onTick(true);
			PlayerData data = APDataMain.loadPlayerData(player);
			if(data.isActivated) player.isSwingInProgress = false; //Cease swing for effect rendering
			if(player != null && data.isDataStateGood() && data.isActivated) {
				equipVoid(player); //黑科技万岁
			}
		}
	}
	
	
	private void equipVoid(EntityPlayer player) {
		ItemStack curStack = player.getCurrentEquippedItem();
		if(curStack == null) {
			player.setCurrentItemOrArmor(0, new ItemStack(APItems.abilityVoid));
			if(lastOverrideItem != null) {
				MinecraftForgeClient.registerItemRenderer(lastOverrideItem, lastRenderer);
			}
		} else if(curStack.getItem() != APItems.abilityVoid) {
			if(lastOverrideItem != null) {
				MinecraftForgeClient.registerItemRenderer(lastOverrideItem, lastRenderer);
			}
			lastOverrideItem = curStack.getItem();
			lastRenderer = getRenderer(lastOverrideItem);
			MinecraftForgeClient.registerItemRenderer(lastOverrideItem, new ItemRenderHacker(lastRenderer));
		}
	}
	
	private IItemRenderer getRenderer(Item item) {
		try {
			return (IItemRenderer) ((IdentityHashMap)fldRenderMap.get(null)).get(item);
		} catch(Exception e) {}
		return null;
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
