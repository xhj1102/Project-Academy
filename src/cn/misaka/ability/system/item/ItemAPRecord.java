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

import java.util.List;

import cn.misaka.core.AcademyCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

/**
 * @author KSkun
 * Record的实现方法居然没变！
 */
public class ItemAPRecord extends ItemRecord {
	
	int recID; //Record的ID

	/**
	 * 
	 * @param p_i45350_1_
	 * 				RecordName
	 * 
	 * @param subID
	 * 				Record的ID
	 */
	public ItemAPRecord(String p_i45350_1_, int subID) {
		super(p_i45350_1_);
		setUnlocalizedName("APRecord");
		setCreativeTab(AcademyCraft.cct);
		recID = subID;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		return this.itemIcon;
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		//机智地使用了switch
		switch (recID) {
		case 1:
			par3List.add("Only My Railgun by FripSide");
			break;
		case 2:
			par3List.add("Sister's Noise by FripSide");
			break;
		case 3:
			par3List.add("LEVEL5 -Judgelight- by FripSide");
			break;
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getRecordNameLocal() {
		//依旧是一个switch
		String ACRECname = null;
		switch (recID) {
		case 1:
			ACRECname = "FripSide - Only My Railgun";
			break;
		case 2:
			ACRECname = "FripSide - Sister's Noise";
			break;
		case 3:
			ACRECname = "FripSide - LEVEL5 -Judgelight-";
			break;
		}
		return ACRECname;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		super.getSubItems(par1, par2CreativeTabs, par3List);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("academy:record" + recID);
	}
	
	private static final ResourceLocation sources[] = new ResourceLocation[] {
		new ResourceLocation("academy:records.ac1"),
		new ResourceLocation("academy:records.ac2"),
		new ResourceLocation("academy:records.ac3")
	};
	
	@Override
	@SideOnly(Side.CLIENT)
	public ResourceLocation getRecordResource(String par1) {
		return sources[recID - 1];
	}
	
}
