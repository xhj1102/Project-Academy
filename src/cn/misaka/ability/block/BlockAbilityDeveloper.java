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
package cn.misaka.ability.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cn.liutils.core.proxy.LIClientProps;
import cn.misaka.ability.block.tile.TileAbilityDeveloper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * META: 第1位：0HEAD 1TAIL 第2、3位：byte(2) facingDirection 值和ForgeDirection有一个映射
 * HEAD方块执行渲染行为
 * @author WeAthFolD
 *
 */
public class BlockAbilityDeveloper extends BlockContainer {
	
	private final int[] dirMap = { 3, 4, 2, 5 };
	
	public BlockAbilityDeveloper() {
		super(Material.iron);
		setBlockName("ability_developer");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileAbilityDeveloper();
	}

	@Override
    public boolean isOpaqueCube()
    {
		return false;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return LIClientProps.RENDER_TYPE_EMPTY;
	}
	
	public ForgeDirection getFacingDirection(int metadata) {
		return ForgeDirection.values()[dirMap[metadata >> 1]];
	}
	
	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack)
    {
        int l = MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int metadata = l << 1;

        world.setBlockMetadataWithNotify(x, y, z, metadata, 0x02);
        
        ForgeDirection dir = getFacingDirection(metadata);
        world.setBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, this, metadata + 1, 0x02); //set the 'tail' block
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, Block me, int what)
    {
    	super.breakBlock(world, x, y, z, me, what);
    	System.out.println(what);
    }
	
}
