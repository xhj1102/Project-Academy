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
import cn.misaka.ability.api.APDataMain;
import cn.misaka.ability.system.data.PlayerDataClient;
import cn.misaka.core.proxy.APClientProps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author WeAthFolD
 *
 */
public class AIMIndicator {

	public static void drawHud(ScaledResolution sr) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		PlayerDataClient data = (PlayerDataClient) APDataMain.loadPlayerData(player);
		//if(!data.isDataStateGood() || data.classid == 0) return;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		HudUtils.setZLevel(-90);
		drawLogo(sr.getScaledWidth(), sr.getScaledHeight(), data.getCategoryID());
		//if(data.isActivated) 
			drawCPBar(sr.getScaledWidth(), sr.getScaledHeight(), data);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(1F, 1F, 1F, 1F);
	}
	
	private static void drawLogo(int w, int h, int cl_id) {
		final float SCALE = 0.38F;
		RenderUtils.loadTexture(APClientProps.TEX_HUD_LOGO);
		HudUtils.setTextureResolution(238, 111);
		GL11.glColor4f(1F, 1F, 1F, .8F);
		GL11.glPushMatrix(); {
			//HudUtils.drawTexturedModalRect(w - 30, h - 30, 0, 0, 238, 111, (int)(238 * SCALE), (int)(111 * SCALE));
			HudUtils.drawTexturedModalRect(w - 107, h - 60, 0, 0, (int)(238 * SCALE), (int)(111 * SCALE), 238, 111);
		} GL11.glPopMatrix();
	}
	
	private static void drawCPBar(int w, int h, PlayerDataClient data) {
		final int LENGTH = 512, HEIGHT = 52;
		final float SCALE = 0.3F;
		final int SCR_LEN = (int) (LENGTH * SCALE), SCR_HGT = (int) (HEIGHT * SCALE);
		int length = (int) (data.max_cp == 0 ? 400 : data.current_cp * LENGTH / data.max_cp),
				scr_length = (int) (length * SCALE),
				bar_height = 49;
		HudUtils.setTextureResolution(512, 104);
		RenderUtils.loadTexture(APClientProps.TEX_HUD_CPBAR);
		GL11.glColor4f(1F, 1F, 1F, .8F);
		GL11.glPushMatrix(); {
			int x = w - 20 - SCR_LEN, y = 10;
			HudUtils.drawTexturedModalRect(x, y, 0, 0, SCR_LEN, SCR_HGT, LENGTH, HEIGHT);
			GL11.glColor4f(1F, 1F, 1F, .4F);
			HudUtils.drawTexturedModalRect(x + SCR_LEN - scr_length, y, LENGTH - length, 52, scr_length, (int) (bar_height * SCALE), length, bar_height);
		} GL11.glPopMatrix();
		
	}
	
}
