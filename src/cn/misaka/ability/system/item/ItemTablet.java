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

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import cn.misaka.core.AcademyCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author KSkun
 * 跟capsule完全一样有木有
 */
public class ItemTablet extends Item {

	int tabletID;
	
	/**
	 * 
	 * @param subID
	 * metadata
	 * 
	 * @param capsuleID
	 * metadata2
	 */
	
	public ItemTablet(int subID) {
		setCreativeTab(AcademyCraft.cct);
		tabletID = subID;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("academy:tablet" + tabletID);
	}
	
}
