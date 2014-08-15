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
package cn.misaka.support.entity;

import cn.misaka.support.entity.fx.EntityRayAttenuate;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * @author WeAthFolD
 *
 */
public class EntityRailgun extends EntityRayAttenuate {

	/**
	 * @param par1World
	 * @param par2EntityLiving
	 * @param dmg
	 */
	public EntityRailgun(World par1World, EntityLivingBase par2EntityLiving, float dmg) {
		super(par1World, par2EntityLiving, dmg);
	}

	/**
	 * @param world
	 */
	public EntityRailgun(World world) {
		super(world);
	}
	
	@Override
	protected void doBlockCollision(MovingObjectPosition r) {
		final float factor = .4F;
		worldObj.newExplosion(this, r.blockX, r.blockY, r.blockZ, damage * factor, false, true);
		this.setDead();
	}

}
