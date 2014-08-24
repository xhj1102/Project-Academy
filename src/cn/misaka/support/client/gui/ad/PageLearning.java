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

import cn.liutils.api.client.gui.LIGuiPage;
import cn.liutils.api.client.gui.part.LIGuiButton;
import cn.liutils.api.client.gui.part.LIGuiPart;
import cn.liutils.api.client.util.HudUtils;
import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.core.proxy.APClientProps;

/**
 * @author WeAthFolD
 *
 */
public class PageLearning extends LIGuiPage {

	final float PRT_OFFX = 53F, PRT_OFFY = 49.5F;
	
	public PageLearning(GuiAbilityDeveloper dev) {
		super(dev, "page.adlearning", GuiAbilityDeveloper.PG_OFFSET_X, GuiAbilityDeveloper.PG_OFFSET_Y);
	}
	
	private void drawEstimation() {
		HudUtils.drawTexturedModalRect(0 + PRT_OFFX, 0 + PRT_OFFY, 363, 0, 74.5F, 28.5F, 149, 57);
	}

	private void drawProgress() {
		
	}
	
	@Override
	public void addElements(Set<LIGuiPart> set) { 
		set.add(new LIGuiButton("lrn_learn", 34F, 23F, 61.5F, 13.5F)
			.setDownCoords(1, 419).setInvaildCoords(1, 477).setTexSize(123F, 27F)
			.setTextureCoords(1, 448).setTextureOverride(APClientProps.TEX_GUI_AD_LEARNING));
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
			
			if(true) { //TODO: Is currently not developing?
				drawEstimation();
			} else {
				
			}
		} GL11.glPopMatrix();
	}

	@Override
	public void onPartClicked(LIGuiPart part, float x, float y) {
		System.out.println("Yahoo!");
	}

}
