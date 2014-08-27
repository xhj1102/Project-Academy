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

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.gui.LIGuiPage;
import cn.liutils.api.client.gui.part.LIGuiButton;
import cn.liutils.api.client.gui.part.LIGuiPart;
import cn.liutils.api.client.util.HudUtils;
import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.ability.api.APDataMain;
import cn.misaka.ability.api.ability.AbilityCategory;
import cn.misaka.ability.api.ability.AbilityLevel;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.core.proxy.APClientProps;

/**
 * @author WeAthFolD
 *
 */
public class PageLearning extends LIGuiPage {

	final float PRT_OFFX = 53F, PRT_OFFY = 49.5F;
	final int estm_exp, estm_energy;
	final boolean available;
	
	public PageLearning(GuiAbilityDeveloper dev) {
		super(dev, "page.adlearning", GuiAbilityDeveloper.PG_OFFSET_X, GuiAbilityDeveloper.PG_OFFSET_Y);
		PlayerData data = APDataMain.loadPlayerData(Minecraft.getMinecraft().thePlayer);
		int du;
		AbilityCategory cat = data.getAbilityCategory();
		if(cat != null) {
			AbilityLevel nextLevel = cat.getLevel(data.getLevelID() + 1);
			if(nextLevel == null) {
				available = false;
				estm_exp = estm_energy = 0;
				return;
			}
			du = nextLevel.getUpgradeCost();
		} else {
			du = ADActionHandler.INITIAL_LEARNING_COST;
		}
		available = true;
		estm_exp = ADActionHandler.DU2EXP(du);
		estm_energy = (int) ADActionHandler.DU2EU(du);
	}
	
	private void drawEstimation(FontRenderer font) {
		LIGuiPage.drawString(font, String.valueOf(estm_exp), PRT_OFFX + 18, PRT_OFFY + 12, 0xa1c798, .65F);
		LIGuiPage.drawString(font, String.valueOf(estm_energy), PRT_OFFX + 18, PRT_OFFY + 20.5F, 0x9f4f39, .65F);
		LIGuiPage.drawString(font, "Estm. Consumption: ", PRT_OFFX + 3F, PRT_OFFY + 3, GuiAbilityDeveloper.REC_FONT_COLOR, .65F);
		
		
		RenderUtils.loadTexture(APClientProps.TEX_GUI_AD_LEARNING);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		HudUtils.drawTexturedModalRect(0 + PRT_OFFX, 0 + PRT_OFFY, 363, 0, 74.5F, 28.5F, 149, 57);
	}

	private void drawProgress() {
		
	}
	
	@Override
	public void addElements(Set<LIGuiPart> set) { 
		LIGuiButton btn = new LIGuiButton("lrn_learn", 34F, 23F, 61.5F, 13.5F) {
			
			@Override
			public void drawAtOrigin(float mx, float my, boolean mouseHovering) {
				int texU = 0, texV = 0;

				if (isInvalid) {
					texU = this.invaildTexU;
					texV = this.invaildTexV;
				} else if(mouseHovering) {
					texU = this.downTexU;
					texV = this.downTexV;
				} else {
					texU = this.texU;
					texV = this.texV;
				}
				if(this.hasTexOverride())
					RenderUtils.loadTexture(texOverride);
				HudUtils.drawTexturedModalRect(0F, 0F, texU, texV, width, height, texWidth, texHeight);
				
				GuiAbilityDeveloper dev = ((GuiAbilityDeveloper)myGui);
				FontRenderer font = dev.getFontRenderer();
				float length = font.getStringWidth("Learn Ability") * .7F;
				LIGuiPage.drawString(font, "Learn Ability", 31F - length / 2F, 4F, GuiAbilityDeveloper.REC_FONT_COLOR, 0.7F);
			}
		};
		btn.setDownCoords(1, 419).setInvaildCoords(1, 477).setTexSize(123F, 27F)
		.setTextureCoords(1, 448).setTextureOverride(APClientProps.TEX_GUI_AD_LEARNING);
		set.add(btn);
	}

	@SuppressWarnings("unused")
	@Override
	public void drawPage() {
		GL11.glPushMatrix(); {
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1F, 1F, 1F, 1F);
			GL11.glTranslatef(0F, -3F, 0F);
			
			RenderUtils.loadTexture(APClientProps.TEX_GUI_AD_LEARNING);
			HudUtils.setTextureResolution(512, 512);
			HudUtils.drawTexturedModalRect(0, 0, 0, 0, 
					GuiAbilityDeveloper.PG_WIDTH, GuiAbilityDeveloper.PG_HEIGHT,
					2 * GuiAbilityDeveloper.PG_WIDTH, 2 * GuiAbilityDeveloper.PG_HEIGHT);
			
			GuiAbilityDeveloper dev = ((GuiAbilityDeveloper)myGui);
			FontRenderer font = dev.getFontRenderer();
			if(true) { //TODO: Is currently not developing?
				drawEstimation(font);
			} else {
				
			}
			
			LIGuiPage.drawString(font, StatCollector.translateToLocal("curenergy.name")
					, 7, 103, GuiAbilityDeveloper.REC_FONT_COLOR, .7F);
			
			LIGuiPage.drawString(font, StatCollector.translateToLocal("transspd.name") + ": " +
					dev.myTile.getDUModifier() * 100 +  "%"
					, 7, 131, GuiAbilityDeveloper.REC_FONT_COLOR, .7F);
			
		} GL11.glPopMatrix();
	}

	@Override
	public void onPartClicked(LIGuiPart part, float x, float y) {
		System.out.println("Yahoo!");
	}

}
