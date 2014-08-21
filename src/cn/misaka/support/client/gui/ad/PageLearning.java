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

import cn.liutils.api.client.gui.LIGuiButton;
import cn.liutils.api.client.gui.LIGuiPart;
import cn.liutils.api.client.util.HudUtils;
import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.core.proxy.APClientProps;
import cn.misaka.support.client.gui.ad.GuiAbilityDeveloper.Page;

/**
 * @author WeAthFolD
 *
 */
public class PageLearning extends Page {

	final float PRT_OFFX = 53F, PRT_OFFY = 49.5F;
	
	public PageLearning(GuiAbilityDeveloper dev) {
		dev.super("ad.learning");
	}

	@SuppressWarnings("unused")
	@Override
	public void renderPageArea() {
		GL11.glPushMatrix(); {
			RenderUtils.loadTexture(APClientProps.TEX_GUI_AD_LEARNING);
			HudUtils.setTextureResolution(512, 512);
			HudUtils.drawTexturedModalRect(0, 0, 0, 0, WIDTH, HEIGHT, 2 * WIDTH, 2 * HEIGHT);
			
			if(true) { //TODO: Is currently not developing?
				drawEstimation();
			} else {
				
			}
		} GL11.glPopMatrix();
	}
	
	private void drawEstimation() {
		HudUtils.drawTexturedModalRect(0 + PRT_OFFX, 0 + PRT_OFFY, 363, 0, 74.5F, 28.5F, 149, 57);
	}

	private void drawProgress() {
		
	}
	
	@Override
	public void addElements(Set<LIGuiPart> set) {
		set.add(new LIGuiButton("lrn_learn", this.OFFSET_X + 34F, OFFSET_Y + 21F, 61.5F, 13.5F)
			.setDownCoords(1, 419).setInvaildCoords(1, 477).setTexSize(123F, 27F)
			.setTextureCoords(1, 448).setTextureOverride(APClientProps.TEX_GUI_AD_LEARNING));
	}

	@Override
	public void onButtonClicked(LIGuiButton button) {
		System.out.println(button.name + "clicked");
	}

}
