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
package cn.misaka.ability.category.electromaster.client;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import cn.liutils.api.client.render.Vertex;
import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.ability.api.client.render.SkillRender;
import cn.misaka.ability.api.client.render.SkillRender.SkillRenderType;
import cn.misaka.ability.api.control.SkillControlStat;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.core.proxy.APClientProps;

/**
 * @author WeAthFolD
 *
 */
public class SkillRenderArc extends SkillRender {

	/**
	 * 
	 */
	public SkillRenderArc() {
		// TODO Auto-generated constructor stub
	}
	
	private final Random rng = new Random();
	
	public void onRender(EntityPlayer player, SkillControlStat stat, PlayerData data, SkillRenderType type) {
		//Currently 1st person settings
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_LIGHTING);
		Tessellator t = Tessellator.instance;
		GL11.glPushMatrix(); {
			
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
			int texID = new Random(player.ticksExisted).nextInt(6);
			RenderUtils.loadTexture(APClientProps.TEX_ELEC_SMALL[texID]);
			GL11.glRotatef(40F, 0, 0, 1);
			
			GL11.glColor4f(1F, 1F, 1F, .8F);
			GL11.glTranslatef(.9F, -.5F, .46F);
			GL11.glRotatef(10F, 0F, 0F, 1F);
			GL11.glRotatef(80F, 0, 1, 0);
			GL11.glScalef(1.1F, 1.1F, 1.1F);
			//GL11.glRotatef(90F, 0, -1, 0);
			
			Vertex[] vecs = {
					new Vertex(0D, 0D, 0D, 0D, 0D),
					new Vertex(0D, 1D, 0D, 0D, 1D),
					new Vertex(1D, 1D, 0D, 1D, 1D),
					new Vertex(1D, 0D, 0D, 1D, 0D),
			};
			
			t.startDrawingQuads();
			t.setBrightness(15728880);
			for(Vertex vec : vecs)
				vec.addTo(t);
			t.draw();
			
		} GL11.glPopMatrix();
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_LIGHTING);
	}

}
