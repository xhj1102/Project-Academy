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
package cn.misaka.support.block.tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.liutils.api.util.EntityUtils;
import cn.misaka.core.AcademyCraft;
import cn.misaka.support.block.BlockAbilityDeveloper;
import cn.misaka.support.block.IADModule;
import cn.misaka.support.block.IADModuleAttached;
import cn.misaka.support.network.message.MsgDeveloperPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * 
 * @author WeAthFolD
 */
public class TileAbilityDeveloper extends TileEntity {
	
	private EntityPlayer mountPlayer;
	
	public static final int MAX_SIDES = 4;
	public Set<IADModule> plainModules = new HashSet();
	public IADModuleAttached[] sidedModules = new IADModuleAttached[4];
	
	public TileAbilityDeveloper() {
	}
	
	public boolean insertModule(IADModule module) {
		if(module instanceof IADModuleAttached) {
			for(int i = 0; i < 4; i++) {
				if(sidedModules[i] == null) {
					sidedModules[i] = (IADModuleAttached) module;
					return true;
				}
			}
			return false;
		} else plainModules.add(module);
		return true;
	}
	
	@Override
	public void updateEntity() {
		if(!this.tileEntityInvalid && mountPlayer != null) {
			setPosition();
		}
	}
	
	@Override
    public void invalidate()
    {
    	disMount();
    	super.invalidate();
    }
	
	public EntityPlayer getMountedPlayer() {
		return mountPlayer;
	}
	
	public boolean tryMount(EntityPlayer player) {
		if(mountPlayer == null || mountPlayer == player) {
			mountPlayer = player;
			setPosition();
			player.getEntityData().setBoolean("ac_ondev", true);
			player.getEntityData().setByte("ac_devdir", (byte) (getBlockMetadata() >> 1));
			if(!player.worldObj.isRemote)
				AcademyCraft.netHandler.sendToDimension(new MsgDeveloperPlayer(player, true, getBlockMetadata() >> 1), worldObj.provider.dimensionId);
			return true;
		}
		return false;
	}
	
	public void disMount() {
		if(mountPlayer != null) {
			mountPlayer.getEntityData().setBoolean("ac_ondev", false);
			mountPlayer.yOffset = worldObj.isRemote ? 1.62F : 0.0F;
			Vec3 vec3 = calculateExitPosition();
			EntityUtils.applyEntityToPos(mountPlayer, vec3);
			//mountPlayer.onGround = true;
			if(!mountPlayer.worldObj.isRemote)
				AcademyCraft.netHandler.sendToDimension(new MsgDeveloperPlayer(mountPlayer, false, getBlockMetadata() >> 1), worldObj.provider.dimensionId);
			mountPlayer = null;
		}
	}
	
	private void setPosition() {
		ForgeDirection dir = BlockAbilityDeveloper.getFacingDirection(getBlockMetadata());
		mountPlayer.yOffset = 1.00F;
		mountPlayer.motionX = mountPlayer.motionY = mountPlayer.motionZ = 0.0;
		double x = xCoord + 0.5,
				y = yCoord + .63,
				z = zCoord + 0.5;
		mountPlayer.posX = x;
		mountPlayer.posY = y;
		mountPlayer.posZ = z;
	}
	
	private Vec3 calculateExitPosition() {
		ForgeDirection ndir = BlockAbilityDeveloper.getFacingDirection(getBlockMetadata()).getRotation(ForgeDirection.UP);
		return worldObj.getWorldVec3Pool().getVecFromPool(xCoord + 0.5 + ndir.offsetX, yCoord + 3.0F, zCoord + 0.5 + ndir.offsetZ);
	}
}
