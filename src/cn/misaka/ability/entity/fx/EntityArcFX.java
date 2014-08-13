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
package cn.misaka.ability.entity.fx;

import cn.liutils.api.util.Motion3D;
import cn.misaka.core.proxy.APClientProps;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * 条状+动画类特效的通用实体
 * @author WeAthFolD
 *
 */
public class EntityArcFX extends Entity {
	
	public double length;
	public ResourceLocation[] texture = APClientProps.ANIM_ARC_LONG;

	public EntityArcFX(World world, EntityPlayer player, double dist) {
		super(world);
		Motion3D mo = new Motion3D(player, true);
		mo.applyToEntity(this);
		length = dist;
		this.rotationPitch = player.rotationPitch;
		this.rotationYaw = player.rotationYaw;
		this.ignoreFrustumCheck = true;
	}
	
	public EntityArcFX(World world, EntityPlayer player) {
		super(world);
		MovingObjectPosition res = player.rayTrace(100.0, 1.0F);
		
	}
	
	public EntityArcFX setTexture(ResourceLocation... r) {
		texture = r;
		return this;
	}

	@Override
	protected void entityInit() {
	}
	
	@Override
	public void onUpdate() {
		++ticksExisted;
		//啥也不干萌萌哒;w;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound var1) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound var1) {
	}

}
