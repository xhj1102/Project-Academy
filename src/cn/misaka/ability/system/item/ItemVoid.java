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
package cn.misaka.ability.system.item;

import cn.liutils.core.LIUtilsMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author WeAthFolD
 *
 */
public class ItemVoid extends Item {

	public ItemVoid() {
		setUnlocalizedName("ability_void");
		setTextureName(LIUtilsMod.DEBUG ? "academy:void" : "academy:blank");
	}
	
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		EntityPlayer player = (EntityPlayer) par3Entity;
		player.isSwingInProgress = false;
		if(!par5) { //If player is not equipping this itemVoid, destroy it 
			((EntityPlayer)par3Entity).inventory.setInventorySlotContents(par4, null);
			par1ItemStack.stackSize = 0;
		}
	}
	
    @Override
	public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
    {
    	return !player.capabilities.isCreativeMode;
    }
    
    @Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        return true;
    }

}
