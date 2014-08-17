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
package cn.misaka.ability.system.client.hud;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.util.HudUtils;
import cn.liutils.api.client.util.RenderUtils;
import cn.liutils.api.util.Pair;
import cn.misaka.ability.api.APDataMain;
import cn.misaka.ability.system.data.PlayerDataClient;
import cn.misaka.core.proxy.APClientProps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * @author WeAthFolD
 *
 */
public class AIMIndicator {

	public static void drawHud(ScaledResolution sr) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		PlayerDataClient data = (PlayerDataClient) APDataMain.loadPlayerData(player);
		if(!data.isDataStateGood() || data.getCategoryID() == 0) return;
		GL11.glColor4f(1F, 1F, 1F, 1F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		HudUtils.setZLevel(-90);
		drawLogo(sr.getScaledWidth(), sr.getScaledHeight(), data.getCategoryID(), data.getAbilityCategory().getLogo());
		if(data.isActivated) 
			drawCPBar(sr.getScaledWidth(), sr.getScaledHeight(), data);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(1F, 1F, 1F, 1F);
	}
	
	private static void drawLogo(int w, int h, int cl_id, ResourceLocation logo) {
		final float SCALE = 0.38F;
		
		GL11.glColor4f(1F, 1F, 1F, .8F);
		GL11.glPushMatrix(); {
			//HudUtils.drawTexturedModalRect(w - 30, h - 30, 0, 0, 238, 111, (int)(238 * SCALE), (int)(111 * SCALE));
			GL11.glTranslatef(w - 107, h - 60, 0F);
			
			HudUtils.setZLevel(-89.99D);
			GL11.glDepthFunc(GL11.GL_EQUAL);
			HudUtils.setTextureResolution(128, 128);
			if(logo != null) { //Draw the logo with depth mask
				RenderUtils.loadTexture(logo);
				HudUtils.drawTexturedModalRect(127 * SCALE, 15 * SCALE, 96 * SCALE, 96 * SCALE);
			}
			GL11.glDepthFunc(GL11.GL_LEQUAL);
			HudUtils.setTextureResolution(128, 111);
			RenderUtils.loadTexture(APClientProps.TEX_GUI_LOGO_DMASK);
			HudUtils.drawTexturedModalRect(110 * SCALE, 0, 0, 0, 128 * SCALE, 111 * SCALE, 128, 111);
			
			HudUtils.setZLevel(-90D);
			RenderUtils.loadTexture(APClientProps.TEX_HUD_LOGO);
			HudUtils.setTextureResolution(238, 111);
			HudUtils.drawTexturedModalRect(0, 0, 0, 0, 238 * SCALE, 111 * SCALE, 238, 111);
		} GL11.glPopMatrix();
	}
	
	private static void drawCPBar(int w, int h, PlayerDataClient data) {
		final int LENGTH = 512, HEIGHT = 52;
		final float SCALE = 0.3F;
		final int SCR_LEN = (int) (LENGTH * SCALE), SCR_HGT = (int) (HEIGHT * SCALE);
		int length = (int) (data.maxCP == 0 ? 400 : data.currentCP * LENGTH / data.maxCP),
				scr_length = (int) (length * SCALE),
				bar_height = 49;
		
		GL11.glColor4f(1F, 1F, 1F, .8F);
		GL11.glPushMatrix(); {
			int x = w - 20 - SCR_LEN, y = 18;
			Pair<ResourceLocation, ResourceLocation> decoration = data.getAbilityCategory().getHudTextureOverride();
			if(decoration.first != null) ;
			
			
			GL11.glTranslatef(x, y, 0F);
			if(decoration.second != null) drawCPDecoration(w, h, SCALE, decoration.second);
			
			RenderUtils.loadTexture(APClientProps.TEX_HUD_CPBAR);
			HudUtils.setTextureResolution(512, 104);
			HudUtils.drawTexturedModalRect(0, 0, 0, 0, SCR_LEN, SCR_HGT, LENGTH, HEIGHT);
			GL11.glColor4f(1F, 1F, 1F, .4F);
			HudUtils.drawTexturedModalRect(SCR_LEN - scr_length, 0, LENGTH - length, 52, scr_length, (int) (bar_height * SCALE), length, bar_height);
		} GL11.glPopMatrix();
		
	}
	
	private static void drawCPDecoration(int w, int h, float scale, ResourceLocation src) {
		final int WIDTH = 576, HEIGHT = 116;
		final float offsetX = -8F, offsetY = -9F;
		RenderUtils.loadTexture(src);
		HudUtils.setTextureResolution(WIDTH, HEIGHT);
		HudUtils.drawTexturedModalRect(offsetX, offsetY, 0, 0, WIDTH * scale, HEIGHT * scale, WIDTH, HEIGHT);
	}
	
}
