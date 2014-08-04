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
package cn.misaka.core.misc;

import cn.misaka.core.register.APItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @author WeAthFolD
 *
 */
public class APCreativeTab extends CreativeTabs {

	/**
	 * @param lable
	 */
	public APCreativeTab() {
		super("AcademyCraft");
	}

	/**
	 * @param par1
	 * @param par2Str
	 */
	public APCreativeTab(int par1) {
		super(par1, "AcademyCraft");
	}

	/* (non-Javadoc)
	 * @see net.minecraft.creativetab.CreativeTabs#getTabIconItem()
	 */
	@Override
	public Item getTabIconItem() {
		return APItems.itemLogo;
	}

}
