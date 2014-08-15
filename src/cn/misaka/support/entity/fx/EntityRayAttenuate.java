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
package cn.misaka.support.entity.fx;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cn.liutils.api.entity.EntityBullet;

/**
 * 根据距离进行衰减的bullet类型。
 * @author WeAthFolD
 *
 */
public class EntityRayAttenuate extends EntityBullet {

	public enum AttenuateType {
		EXP, LINEAR, SQUARED;
	}
	
	public double atteFactor = 0.3D;
	public AttenuateType atteType = AttenuateType.LINEAR;
	
	public EntityRayAttenuate(World par1World,
			EntityLivingBase par2EntityLiving, float dmg) {
		super(par1World, par2EntityLiving, dmg);
	}

	public EntityRayAttenuate(World par1World,
			EntityLivingBase par2EntityLiving, float dmg, int scatterRadius) {
		super(par1World, par2EntityLiving, dmg, scatterRadius);
	}

	public EntityRayAttenuate(World par1World, Entity ent, Entity target,
			float dmg) {
		super(par1World, ent, target, dmg);
	}

	public EntityRayAttenuate(World world, Vec3 begin, Vec3 motion, float dmg) {
		super(world, begin, motion, dmg);
	}

	public EntityRayAttenuate(World world) {
		super(world);
	}
	
	public EntityRayAttenuate setAttenuateType(AttenuateType tp) {
		atteType = tp;
		return this;
	}
	
	private float getAttenuateAmount(double dist) {
		switch(atteType) {
		case EXP:
			return (float) (atteFactor * Math.pow(2.0, dist));
		case LINEAR:
			return (float) (atteFactor * dist);
		case SQUARED:
			return (float) (atteFactor * dist * dist);
		default:
			return 0;
		}
	}
	

	
	@Override
	protected void doEntityCollision(MovingObjectPosition result) {
		if (selector != null && !selector.isEntityApplicable(result.entityHit))
			return;
		float dmg = damage - 
				getAttenuateAmount(motion.distanceTo(result.entityHit.posX, result.entityHit.posY, result.entityHit.posZ));
		System.out.println("Attenuated dmg : " + dmg);
		if(dmg < 0) dmg = 0;
		result.entityHit.attackEntityFrom(DamageSource.causeMobDamage(getThrower()), dmg);
		result.entityHit.hurtResistantTime = -1;
	}

}
