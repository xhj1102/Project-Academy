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
package cn.misaka.core.register;

import cpw.mods.fml.common.registry.GameRegistry;
import cn.misaka.ability.system.item.ItemVoid;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

/**
 * @author WeAthFolD
 *
 */
public class APItems {
	
	public static Item 
		itemVoid,
		itemLogo;

	public static void init(Configuration conf) {
		itemVoid = new ItemVoid();
		itemLogo = new Item().setUnlocalizedName("ap_logo").setTextureName("academy:logo");
		
		GameRegistry.registerItem(itemVoid, "ability_void");
		GameRegistry.registerItem(itemLogo, "ap_logo");
	}

}
