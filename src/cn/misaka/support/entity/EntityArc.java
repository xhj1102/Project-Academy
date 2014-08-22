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

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cn.liutils.api.util.BlockPos;
import cn.liutils.api.util.GenericUtils;
import cn.liutils.api.util.Motion3D;
import cn.misaka.support.entity.fx.EntityRayAttenuate;

/**
 * @author WeAthFolD
 *
 */
public class EntityArc extends Entity {
	
	public EntityPlayer player;
	public float damage;
	public static final float 
		CONV_RATE = 0.3F,
		DST_CONV_RATE = 3F;

	public EntityArc(World par1World, EntityPlayer pl, float dmg) {
		super(par1World);
		player = pl;
		damage = dmg;
		this.setPosition(player.posX, player.posY, player.posZ);
	}
	
	Map<BlockPos, Integer> affectedBlocks = new HashMap();
	
    @SuppressWarnings("unused")
    @Override
	public void onUpdate()
    {
    	this.setPosition(player.posX, player.posY, player.posZ);
    	if(!player.getEntityData().getBoolean("ap_arcdown")) {
    		setDead();
    		return;
    	}
    	
    	Motion3D mo = new Motion3D(player, true);
    	MovingObjectPosition res = GenericUtils.rayTraceBlocksAndEntities(null, worldObj, 
    			mo.getPosVec(worldObj), mo.move(damage * DST_CONV_RATE).getPosVec(worldObj), this, player);
    	if(res != null) {
    		if(res.typeOfHit == MovingObjectType.BLOCK) {
    			Block blck = worldObj.getBlock(res.blockX, res.blockY, res.blockZ);
    			if(false) { //(IF HITS AN ELECTRICITY ONE
    				
    			} else {
    				BlockPos bp = new BlockPos(res.blockX, res.blockY, res.blockZ, blck);
    				Integer a = affectedBlocks.get(bp);
    				if(a == null) a = 0;
    				a += 1;
    				if(a >= getBurnCost(blck)) {
    					//setonfire
    					if(worldObj.getBlock(res.blockX, res.blockY + 1, res.blockZ) == Blocks.air) {
    						worldObj.setBlock(res.blockX, res.blockY + 1, res.blockZ, Blocks.fire);
    					}
    					a = 0;
    				}
    				affectedBlocks.put(bp, a);
    			}
    		} else {
    			System.out.println("Attacking " + res.entityHit);
    			res.entityHit.attackEntityFrom(DamageSource.causeMobDamage(player), damage);
    		}
    	}
    }
    
    public static boolean canBurn(Block type) {
    	return true;
    }
    
    public static int getBurnCost(Block type) {
    	return 20;
    }

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound var1) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound var1) {
	}
}
