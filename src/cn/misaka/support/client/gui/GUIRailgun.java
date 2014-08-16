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
package cn.misaka.support.client.gui;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.util.HudUtils;
import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.core.proxy.APClientProps;

/**
 * 超电磁炮（弹射型）的QTE提示。
 * @author WeAthFolD
 *
 */
public class GUIRailgun {

	
	private static float MAJOR_SCALE = .2F;
	
	public static void draw(int w, int h, double m, float progress) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glPushMatrix(); {
			
			float upper = -128 * MAJOR_SCALE, size = 256 * MAJOR_SCALE;
			GL11.glTranslatef(w/2, h/2, 0F);
			HudUtils.setTextureResolution(256, 256);
			GL11.glPushMatrix(); {
				GL11.glColor4f(1F, 1F, 1F, .5F);
				RenderUtils.loadTexture(APClientProps.TEX_GUI_RAILGUN_PRG);
				float scale = calcScale(m, progress);
				float upper2 = -128 * MAJOR_SCALE * scale, size2 = 256 * MAJOR_SCALE * scale;
				HudUtils.drawTexturedModalRect(upper2, upper2, 0,
						0, size2, size2, 256, 256);
			} GL11.glPopMatrix();
			
			GL11.glColor4f(1F, 1F, 1F, .6F);
			RenderUtils.loadTexture(APClientProps.TEX_GUI_RAILGUN);
			HudUtils.drawTexturedModalRect(upper, upper, 0, 0, size, size, 256, 256);
			
		} GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	private static float calcScale(double m, float prog) {
		return (float) (-(0.51 / m) * prog + 1.0);
	}

}
