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
import cn.misaka.core.AcademyCraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * META: 第1位：0HEAD 1TAIL 第2、3位：byte(2) facingDirection 值和ForgeDirection有一个映射
 * HEAD方块执行渲染行为
 * TODO：活塞推动会发生什么呢（笑）
 * @author WeAthFolD
 *
 */
public class BlockAbilityDeveloper extends BlockContainer {
	
	private final int[] dirMap = { 3, 4, 2, 5 };
	
	public BlockAbilityDeveloper() {
		super(Material.iron);
		setHardness(2.0F);
		setCreativeTab(AcademyCraft.cct);
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
    
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
    	super.onNeighborBlockChange(world, x, y, z, block);
    	int metadata = world.getBlockMetadata(x, y, z);
		ForgeDirection dir = getFacingDirection(metadata);
		if((metadata & 0x01) == 1) {
			dir = dir.getOpposite();
		}
		if(world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) != this)
			world.setBlockToAir(x, y, z);
    }
    
    @Override
    public boolean isBed(IBlockAccess world, int x, int y, int z, EntityLivingBase player)
    {
    	return true;
    }
    
    public int getBedDirection(IBlockAccess world, int x, int y, int z)
    {
    	return world.getBlockMetadata(x, y, z) >> 1;
    }
    
    public boolean isBedFoot(IBlockAccess world, int x, int y, int z)
    {
        return (world.getBlockMetadata(x, y, z) & 0x01) == 0;
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz)
    {
    	System.out.println("OnBlockActivated on " + world.isRemote);
    	if(world.isRemote) return false;
    	{
    		int meta = world.getBlockMetadata(x, y, z);
        	if((meta & 0x01) == 0) {
        		ForgeDirection dir = getFacingDirection(meta).getOpposite();
        		x += dir.offsetX;
        		z += dir.offsetZ;
        	}
    	}
    	if(world.getBlock(x, y, z) != this) return false;
    	System.out.println("Now trying to mount player~");
    	TileAbilityDeveloper dev = (TileAbilityDeveloper) world.getTileEntity(x, y, z);
    	dev.tryMount(player);
    	return true;
    }
	
}
