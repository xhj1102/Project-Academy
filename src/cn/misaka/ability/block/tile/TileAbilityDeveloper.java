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
package cn.misaka.ability.block.tile;

import java.lang.reflect.Field;

import cn.misaka.core.register.APBlocks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * 
 * @author WeAthFolD
 */
public class TileAbilityDeveloper extends TileEntity {
	
	public EntityPlayer mountPlayer;
	
	
	private static Field field_sleeping,
			field_sleep_timer;

	static {
		try {
			field_sleeping =  EntityPlayer.class.getDeclaredField("sleeping");
			field_sleep_timer = EntityPlayer.class.getDeclaredField("sleepTimer");
			field_sleeping.setAccessible(true);
			field_sleep_timer.setAccessible(true);
		} catch (Exception e) {
			//NOPE
		}
	}
	
	public TileAbilityDeveloper() {
	}
	
	@Override
	public void updateEntity() {
		if(mountPlayer != null) {
			try {
				mountPlayer.motionX = mountPlayer.motionY = mountPlayer.motionZ = 0.0;
				field_sleep_timer.set(mountPlayer, 0);
				field_sleeping.set(mountPlayer, true);
			} catch (Exception e) {
				System.err.println("Error while processing player tick update in AbilityDeveloper, this is a bug!");
			}
		}
	}
	
	public void tryMount(EntityPlayer player) {
		if(mountPlayer == null || mountPlayer == player) {
			mountPlayer = player;
			setPosition();
			player.width = player.height = 0.2F;
			player.yOffset = 0.2F;
			player.motionX = player.motionY = player.motionZ = 0.0;
			player.playerLocation = new ChunkCoordinates(xCoord, yCoord, zCoord);
			try {
				field_sleeping.set(player, true);
				field_sleep_timer.set(player, 0);
			} catch(Exception e) {
				System.err.println("Error while handling player sleep, this is a bug!");
			}
		}
	}
	
	private void setPosition() {
		float yOffset = 0.3F, frntOffset = -0.1F;
		ForgeDirection dir = APBlocks.ability_developer.getFacingDirection(blockMetadata);
		mountPlayer.setPosition(xCoord + 0.5 + dir.offsetX * (0.5 + frntOffset), yCoord + 0, zCoord + 0.5 + dir.offsetZ * (0.5 + frntOffset));
	}
}
