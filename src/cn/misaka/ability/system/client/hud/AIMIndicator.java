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
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * AIMIndicator GUI绘制。
 * @author WeAthFolD
 *
 */
public class AIMIndicator {

	public static void drawHud(ScaledResolution sr) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		PlayerDataClient data = (PlayerDataClient) APDataMain.loadPlayerData(player);
		if(!data.isDataStateGood() || data.getCategoryID() == 0) return;
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		GL11.glPushMatrix(); {
			HudUtils.setZLevel(-90);
			GL11.glColor4f(1F, 1F, 1F, 1F);
			
			drawLogo(sr.getScaledWidth(), sr.getScaledHeight(), data.getCategoryID(), data.getAbilityCategory().getLogo());
			if(data.isActivated) 
				drawCPBar(sr.getScaledWidth(), sr.getScaledHeight(), data);
			
			GL11.glColor4f(1F, 1F, 1F, 1F);
		} GL11.glPopMatrix();
		
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	private static final float SCALE = .38F;
	
	private static void drawLogo(int w, int h, int cl_id, ResourceLocation logo) {
		
		GL11.glColor4f(1F, 1F, 1F, .8F);
		GL11.glPushMatrix(); {
			
			GL11.glTranslatef(w - 107, h - 60, 0F);
			
			HudUtils.setZLevel(-89.99D);
			GL11.glDepthFunc(GL11.GL_EQUAL);
			HudUtils.setTextureResolution(128, 128);
			//Draw the logo with depth mask
			if(logo != null) { 
				RenderUtils.loadTexture(logo);
				HudUtils.drawTexturedModalRect(127 * SCALE, 15 * SCALE, 88 * SCALE, 88 * SCALE);
			}
			
			//Depth Mask
			GL11.glDepthFunc(GL11.GL_LEQUAL);
			HudUtils.setTextureResolution(128, 111);
			RenderUtils.loadTexture(APClientProps.TEX_GUI_LOGO_DMASK);
			HudUtils.drawTexturedModalRect(110 * SCALE, 0, 0, 0, 128 * SCALE, 111 * SCALE, 128, 111);
			
			//Frame
			HudUtils.setZLevel(-90D);
			RenderUtils.loadTexture(APClientProps.TEX_HUD_LOGO);
			HudUtils.setTextureResolution(238, 111);
			HudUtils.drawTexturedModalRect(0, 0, 0, 0, 238 * SCALE, 111 * SCALE, 238, 111);
			
		} GL11.glPopMatrix();
	}
	
	private static final float CP_SCALE = 0.3F;
	private static void drawCPBar(int w, int h, PlayerDataClient data) {
		final int texWidth = 512, texHeight = 52;
		
		final int width = (int) (texWidth * CP_SCALE), height = (int) (texHeight * CP_SCALE);
		int barTexLength = (int) (data.maxCP == 0 ? 400 : data.currentCP * texWidth / data.maxCP),
				barWidth = (int) (barTexLength * CP_SCALE),
				barHeight = 49;
		
		GL11.glColor4f(1F, 1F, 1F, .8F);
		GL11.glPushMatrix(); {
			int x = w - 20 - width, y = 18;
			Pair<ResourceLocation, ResourceLocation> decoration = data.getAbilityCategory().getHudTextureOverride();
			
			GL11.glTranslatef(x, y, 0F); 
			
			if(decoration.first != null) ; //Draw logo decoration
			if(decoration.second != null) //Draw CPBar decoration
				drawCPDecoration(w, h, decoration.second);
			
			RenderUtils.loadTexture(APClientProps.TEX_HUD_CPBAR);
			
			//CP indication
			GL11.glColor4f(1F, 1F, 1F, .4F);
			drawBar(width - barWidth, 0, texWidth - barTexLength, 52, barWidth, (int) (barHeight * CP_SCALE), barTexLength, barHeight);
		
			//Back
			GL11.glColor4f(1F, 1F, 1F, .8F);
			HudUtils.setTextureResolution(512, 104);
			HudUtils.drawTexturedModalRect(0, 0, 0, 0, width, height, texWidth, texHeight);
		} GL11.glPopMatrix();
		
	}
	
	//特殊的切边~
	private static void drawBar(float x, float y, int u, int v, float width, float height, float texWidth, float texHeight) {
		float f = 0.001953125F;
        float f1 = 0.00961538461538F;
        float k = 0.44F;
        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.addVertexWithUV(x + k * height, y + height, -90, (u + k * texHeight) * f, (v + texHeight) * f1); //left down
        t.addVertexWithUV(x + width, y + height, -90, (u + texWidth) * f, (v + texHeight) * f1); //right down
        t.addVertexWithUV(x + width, y + 0, -90, (u + texWidth) * f, (v + 0) * f1); //right up
        t.addVertexWithUV(x + 0, y + 0, -90, (u + 0) * f, (v + 0) * f1); //left up
        t.draw();
	}
	
	private static void drawCPDecoration(int w, int h, ResourceLocation src) {
		final int WIDTH = 576, HEIGHT = 116;
		final float offsetX = -8F, offsetY = -9F;
		RenderUtils.loadTexture(src);
		HudUtils.setTextureResolution(WIDTH, HEIGHT);
		HudUtils.drawTexturedModalRect(offsetX, offsetY, 0, 0, WIDTH * CP_SCALE, HEIGHT * CP_SCALE, WIDTH, HEIGHT);
	}
	
}
