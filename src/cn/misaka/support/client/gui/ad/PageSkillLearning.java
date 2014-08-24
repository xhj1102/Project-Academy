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

import java.util.List;
import java.util.Set;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import cn.liutils.api.client.gui.LIGuiPage;
import cn.liutils.api.client.gui.part.LIGuiButton;
import cn.liutils.api.client.gui.part.LIGuiPart;
import cn.liutils.api.client.gui.part.LIGuiScrollBar;
import cn.liutils.api.client.gui.part.LIGuiScrollerHorizonal;
import cn.liutils.api.client.gui.part.LIGuiScrollerHorizonal.ScrollerEntry;
import cn.liutils.api.client.util.HudUtils;
import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.ability.api.APDataMain;
import cn.misaka.ability.api.ability.AbilityCategory;
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.core.proxy.APClientProps;

/**
 * @author WeAthFolD
 *
 */
public class PageSkillLearning extends LIGuiPage {

	Scroller scl;
	
	/**
	 * @param unlocalized_name
	 */
	public PageSkillLearning(GuiAbilityDeveloper d) {
		super(d, "page.adskill", d.PG_OFFSET_X, d.PG_OFFSET_Y - 3F);
	}

	@Override
	public void drawPage() {
		RenderUtils.loadTexture(APClientProps.TEX_GUI_AD_SKILL);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		HudUtils.drawTexturedModalRect(0, 0, 0, 0, GuiAbilityDeveloper.PG_WIDTH, 
				GuiAbilityDeveloper.PG_HEIGHT, 2 * GuiAbilityDeveloper.PG_WIDTH, 2 * GuiAbilityDeveloper.PG_HEIGHT);
	}
	
	@Override
	public void addElements(Set<LIGuiPart> set) {
		scl = new Scroller((GuiAbilityDeveloper) myGui);
		set.add(scl);
		set.add(new LIGuiScrollBar("bar", scl, 120.5F, 13, 5.5F, 93.5F, 5.5F, 12F, 280, 0, 11, 24).setTextureOverride(APClientProps.TEX_GUI_AD_SKILL));
		set.add(new LIGuiButton("down", 120.5F, 107, 5.5F, 5.5F));
		set.add(new LIGuiButton("up", 120.5F, 7, 5.5F, 5.5F));
	}

	@Override
	public void onPartClicked(LIGuiPart part, float x, float y) {
		if(part.name.equals("up")) {
			scl.roll(false);
		} else if(part.name.equals("down")) {
			scl.roll(true);
		}
	}
	
	public static class Scroller extends LIGuiScrollerHorizonal {
		
		public Scroller(GuiAbilityDeveloper d) {
			super("main", 9.5F, 9, 110.5F, 101, 33.5F);
			
			PlayerData data = APDataMain.loadPlayerData(Minecraft.getMinecraft().thePlayer);
			AbilityCategory cat = data.getAbilityCategory();
			if(cat == null) return;
			for(AbilitySkill skill : cat.ability_skills) {
				this.entryList.add(new EntrySkill(skill, d));
			}
			initList();
		}
		
	}
	
	public static class EntrySkill extends ScrollerEntry {
		
		AbilitySkill theSkill;
		GuiAbilityDeveloper gui;

		public EntrySkill(AbilitySkill skl, GuiAbilityDeveloper g) {
			super(291, 0, 221, 67);
			theSkill = skl;
			this.gui = g;
		}

		@Override
		public void addElements(Set<LIGuiPart> set) {
			set.add(new LIGuiPart("all", 0, 0, 110.5F, 34.5F));
		}

		@Override
		public void onPartClicked(LIGuiPart part, float subX, float subY) {
			System.out.println(theSkill.getNameForDisplay());
		}
		
		@Override
		public void drawEntry(float width, float height) {
			
			RenderUtils.loadTexture(APClientProps.TEX_GUI_AD_SKILL);
			GL11.glColor4f(1F, 1F, 1F, 1F);
			super.drawEntry(width, height);
			LIGuiPage.drawString(
					gui.getFontRenderer(),
					theSkill.getNameForDisplay(), 29.5F, 7F,
					GuiAbilityDeveloper.REC_FONT_COLOR, 0.7F);
			
			GL11.glColor4f(1F, 1F, 1F, 1F);
			RenderUtils.loadTexture(theSkill.getLogo());
			HudUtils.drawTexturedModalRect(6, 8.5F, 16.5F, 16.5F);
			
		}
		
	}

}
