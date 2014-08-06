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
package cn.misaka.ability.client.render.entity;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.ability.block.tile.TileAbilityDeveloper;
import cn.misaka.ability.client.model.ModelBipedAP;
import cn.misaka.ability.system.event.APEventListener;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

/**
 * @author WeAthFolD
 *
 */
public class RenderPlayerAP extends RenderPlayer {

	private static final ResourceLocation steveTextures = new ResourceLocation("textures/entity/steve.png");
	private static final ModelBiped bipedHack = new ModelBipedAP();
	/**
	 * 
	 */
	public RenderPlayerAP() {
		this.modelBipedMain = bipedHack;
	}
	
	private static final float rotations[] = new float[] { 0, 90, 180, -90 };
	
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
    	EntityPlayer player = (EntityPlayer) par1Entity;
    	
    	GL11.glPushMatrix(); {
    		if(player.getEntityData().getBoolean("ac_ondev")) {
    			RenderUtils.loadTexture(steveTextures);
            	GL11.glTranslated(par2, par4, par6);
            	GL11.glRotatef(rotations[player.getEntityData().getByte("ac_devdir")], 0F, -1F, 0F);
            	GL11.glRotatef(90F, 1F, 0F, 0F);
            	final float frntOffset = 0.0F;
            	GL11.glTranslatef(0F, 0F, -frntOffset);
            	modelBipedMain.render(player, 0, 0, 0, 0, 0, 0.0625F);
        	} else 
        		super.doRender(par1Entity, 0D, 0D, 0D, 0F, 0F);
    	} GL11.glPopMatrix();
    }
}
