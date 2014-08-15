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
package cn.misaka.ability.system.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;

/**
 * @author WeAthFolD
 *
 */
public class ModelBipedAP extends ModelBiped {

	private final float PI_DIV_180 = 0.0174533F;
	
	/**
	 * 
	 */
	public ModelBipedAP() {
		this.isChild = false;
	}

	/**
	 * @param par1
	 */
	public ModelBipedAP(float par1) {
		super(par1);
		this.isChild = false;
	}

	/**
	 * @param par1
	 * @param par2
	 * @param par3
	 * @param par4
	 */
	public ModelBipedAP(float par1, float par2, int par3, int par4) {
		super(par1, par2, par3, par4);
	}
	
    @Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
    	//GL11.glRotated(90, 1F, 0F, 0F);
    	super.render(par1Entity, par2, par3, par4, par5, par6, par7);
    }
    
    @Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
    	this.bipedHead.rotateAngleX = wrap(par7Entity.rotationPitch + 15F, 10, 60) * PI_DIV_180;
    	this.bipedHead.rotateAngleY = wrap(par7Entity.rotationYaw % 360F, -45, 45) * PI_DIV_180;
    }
    
    private float wrap(float f, float min, float max) {
    	return f > max ? max : (f < min ? min : f);
    }

}
