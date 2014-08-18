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

import cn.liutils.api.util.Pair;
import cn.misaka.ability.api.APControlMain;
import cn.misaka.ability.api.APDataMain;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.ability.category.electromaster.IRailgunQTE;
import cn.misaka.ability.category.electromaster.SkillRailgun;
import cn.misaka.ability.system.client.hud.AIMIndicator;
import cn.misaka.core.AcademyCraft;
import cn.misaka.support.client.gui.GUIRailgun;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;

/**
 * @author WeAthFolD
 *
 */
public class APSEventListener {
	
	public APSEventListener() {
	}

	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent event) {
		EntityPlayer player = event.getPlayer();
		PlayerData pstat = APDataMain.loadPlayerData(player);
		if(pstat != null && pstat.isActivated && player.getCurrentEquippedItem() == null) {
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onRenderGameOverlay(RenderGameOverlayEvent event) {
		if(event.type == ElementType.CROSSHAIRS) {
			
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			
			if(SkillRailgun.isPreparing(APDataMain.loadPlayerData(player))) {
				IRailgunQTE qte = (IRailgunQTE) player.getCurrentEquippedItem().getItem();
				Pair<Float, Float> interval = qte.getAcceptedRange();
				double m = (interval.first + interval.second) / 2D;
				GUIRailgun.draw(event.resolution.getScaledWidth(), event.resolution.getScaledHeight(),
						m, qte.getQTEProgress(player.getCurrentEquippedItem()));
				event.setCanceled(true);
			}
			
		} else if(event.type == RenderGameOverlayEvent.ElementType.HOTBAR) {
			AIMIndicator.drawHud(event.resolution);
		}
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onWorldSave(WorldEvent.Save event) {
		APControlMain.saveToConfig(AcademyCraft.config);
		APDataMain.savePlayerData(false);
	}

}
