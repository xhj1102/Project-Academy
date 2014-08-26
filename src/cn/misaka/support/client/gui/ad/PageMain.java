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
package cn.misaka.support.client.gui.ad;

import java.util.Set;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import cn.liutils.api.client.gui.LIGuiPage;
import cn.liutils.api.client.gui.part.LIGuiButton;
import cn.liutils.api.client.gui.part.LIGuiPart;
import cn.liutils.api.client.util.HudUtils;
import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.ability.api.APDataMain;
import cn.misaka.ability.api.ability.AbilityCategory;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.ability.system.client.render.RenderPlayerAP;
import cn.misaka.core.proxy.APClientProps;

/**
 * @author WeAthFolD
 *
 */
public class PageMain extends LIGuiPage {
	
	GuiAbilityDeveloper dev;
	EntityPlayer player;
	PlayerData data;
	ModelBiped model = new ModelBiped();
	
	public PageMain(GuiAbilityDeveloper d) {
		super(d, "page.admain", 0F, 0F);
		this.dev = d;
		player = Minecraft.getMinecraft().thePlayer;
		data = APDataMain.loadPlayerData(player);
	}

	@Override
	public void drawPage() {
		PlayerData data = APDataMain.loadPlayerData(Minecraft.getMinecraft().thePlayer);

    	FontRenderer font = dev.getFontRenderer();
    	drawPlayer(182, 100, 32, 20F, 20F);
		
    	
    	GL11.glColor4f(1F, 1F, 1F, 1F);
		RenderUtils.loadTexture(APClientProps.TEX_GUI_AD_MAIN);
		HudUtils.drawTexturedModalRect(0, 0, 0, 0, 228F, 184.5F, 456, 369);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		renderPlayerInf(data);
		
		String st = dev.getCurrentSubpage().getLocalizedName();
    	
    	font.drawString(st, (int)dev.TITLE_CENTER_X - font.getStringWidth(st)/2, (int)dev.TITLE_CENTER_Y - 5, dev.REC_FONT_COLOR);
    	
    	if(dev.isLearned) {
    		drawString(font, data.getAbilityCategory().getCategoryNameForDisplay(), 168F, 130, dev.REC_FONT_COLOR, 0.75F);
    		drawString(font, StatCollector.translateToLocal("admain.level") + (data.getLevelID() + 1), 170, 140, dev.REC_FONT_COLOR, 0.65F);
    	} else {
    		drawString(font, StatCollector.translateToLocal("admain.undev"), 172, 126, dev.REC_FONT_COLOR, 0.5F);
    	}
    	
    	drawString(font, StatCollector.translateToLocal("admain.cp"), 146, 161, dev.REC_FONT_COLOR, 0.65F);
    	drawString(font, StatCollector.translateToLocal("admain.prog"), 146, 172, dev.REC_FONT_COLOR, 0.65F);
	}
	
	private void drawPlayer(float x, float y, float scale, float p3, float p4) {
		RenderUtils.loadTexture(RenderPlayerAP.steveTextures);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, 50.0F);
        GL11.glScalef((float)(-scale), (float)scale, (float)scale);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f2 = player.renderYawOffset;
        float f3 = player.rotationYaw;
        float f4 = player.rotationPitch;
        float f5 = player.prevRotationYawHead;
        float f6 = player.rotationYawHead;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float)Math.atan((double)(p4 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        player.renderYawOffset = (float)Math.atan((double)(p3 / 40.0F)) * 20.0F;
        player.rotationYaw = (float)Math.atan((double)(p3 / 40.0F)) * 40.0F;
        player.rotationPitch = -((float)Math.atan((double)(p4 / 40.0F))) * 20.0F;
        player.rotationYawHead = player.rotationYaw;
        player.prevRotationYawHead = player.rotationYaw;
        GL11.glTranslatef(0.0F, player.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        GL11.glRotatef(Minecraft.getSystemTime() / 100F, 0F, 1F, 0F);
        //GL11.glRotatef(90F, 0F, 0F, -1F);
        RenderManager.instance.renderEntityWithPosYaw(player, 3.1415926D, 3.1415926D, 3.1415926D, 0.0F, 1.0F);
        player.renderYawOffset = f2;
        player.rotationYaw = f3;
        player.rotationPitch = f4;
        player.prevRotationYawHead = f5;
        player.rotationYawHead = f6;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

	@Override
	public void addElements(Set<LIGuiPart> set) {
		set.add(new LIGuiButton("pglf", 89, 3.5F, 8.5F, 7.5F).setTextureOverride(APClientProps.TEX_GUI_AD_MAIN));
		set.add(new LIGuiButton("pgrt", 216, 3.5F, 8.5F, 7.5F).setTextureOverride(APClientProps.TEX_GUI_AD_MAIN));
	}

	@Override
	public void onPartClicked(LIGuiPart part, float x, float y) {
		System.out.println("prcl");
		if(part.name.equals("pgrt")) {
			++dev.pageID;
			if(dev.pageID == dev.MAX_PAGES)
				dev.pageID = 0;
		} else if(part.name.equals("pglf")) {
			--dev.pageID;
			if(dev.pageID < 0)
				dev.pageID = dev.MAX_PAGES - 1;
		}
		if(!dev.isLearned && dev.pageID == 1)
			dev.pageID = 0;
	}
	
    private void renderPlayerInf(PlayerData data) {
    	AbilityCategory cat = data.getAbilityCategory();
    	if(cat != null) {
    		
    		ResourceLocation logo = cat.getLogo();
    		if(logo != null) {
    			RenderUtils.loadTexture(logo);
    			HudUtils.drawTexturedModalRect(148.5F, 129.5F, 15.5F, 15.5F);
    		}
    	} else {
    		
    	}
    }
}
