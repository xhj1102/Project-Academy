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

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * 仍然在测试中
 * @author WeAthFolD
 *
 */
public class EntitySurroundArcFX extends Entity {

	public Entity attachedEntity;
	
	/**
	 * @param par1World
	 */
	public EntitySurroundArcFX(Entity another) {
		super(another.worldObj);
		this.setSize(another.width * 1.8F, another.height * 1.3F);
		attachedEntity = another;
		setPositionAndRotation(another.posX, another.posY, another.posZ, another.rotationYaw, another.rotationPitch);
	}
	
	@Override
	public void onUpdate() {
		setPositionAndRotation(attachedEntity.posX, attachedEntity.posY, attachedEntity.posZ, 
				attachedEntity.rotationYaw, attachedEntity.rotationPitch);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.entity.Entity#entityInit()
	 */
	@Override
	protected void entityInit() {
	}

	/* (non-Javadoc)
	 * @see net.minecraft.entity.Entity#readEntityFromNBT(net.minecraft.nbt.NBTTagCompound)
	 */
	@Override
	protected void readEntityFromNBT(NBTTagCompound var1) {
	}

	/* (non-Javadoc)
	 * @see net.minecraft.entity.Entity#writeEntityToNBT(net.minecraft.nbt.NBTTagCompound)
	 */
	@Override
	protected void writeEntityToNBT(NBTTagCompound var1) {
	}

}
