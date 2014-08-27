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

import cn.liutils.api.util.Motion3D;
import cn.misaka.core.proxy.APClientProps;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
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
	protected final EntityPlayer player;
	private Motion3D motion;
	protected int lifeTime = Integer.MAX_VALUE;
	public double realLength;
	public int delay = 500;
	public final long shootTime;

	public EntityArcFX(World world, EntityPlayer player, double dist) {
		super(world);
		this.player = player;
		Motion3D mo = new Motion3D(player, true);
		mo.applyToEntity(this);
		realLength = dist;
		this.rotationPitch = player.rotationPitch;
		this.rotationYaw = player.rotationYaw;
		this.ignoreFrustumCheck = true;
		shootTime = Minecraft.getSystemTime();
	}
	
	/**
	 * 设置光束展开的速度。单位为方块/秒
	 * @param velocity
	 * @return
	 */
	public EntityArcFX setExtensionVelocity(double velocity) {
		delay = (int) (realLength / velocity * 1000);
		System.out.println(delay);
		return this;
	}
	
	public EntityArcFX(World world, EntityPlayer player) {
		super(world);
		this.player = player;
		MovingObjectPosition res = player.rayTrace(70.0, 1.0F);
		Motion3D mo = new Motion3D(player, true);
		mo.applyToEntity(this);
		realLength = res == null || res.typeOfHit == MovingObjectType.MISS ? 
				70.0D : this.getDistance(res.hitVec.xCoord, res.hitVec.yCoord, res.hitVec.zCoord);
		this.rotationPitch = player.rotationPitch;
		this.rotationYaw = player.rotationYaw;
		this.ignoreFrustumCheck = true;
		shootTime = Minecraft.getSystemTime();
	}
	
	public boolean isPlayerCreator(EntityPlayer player) {
		return player == this.player;
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
		if(++ticksExisted > lifeTime)
			this.setDead();
		if(ticksExisted < delay) {
			this.length = realLength * ((float)ticksExisted / delay);
		} else this.length = realLength;
		//啥也不干萌萌哒;w;
	}
	
	protected void updatePosition() {
		//Only for those who requires constant following.
		if(motion == null) {
			motion = new Motion3D(player, true);
		} else motion.update(player, true);
		
		motion.applyToEntity(this);
		this.rotationPitch = player.rotationPitch;
		this.rotationYaw = player.rotationYaw;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound var1) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound var1) {
	}

}
