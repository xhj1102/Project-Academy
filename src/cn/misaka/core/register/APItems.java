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
import cn.misaka.ability.system.item.ItemAPRecord;
import cn.misaka.ability.system.item.ItemCoin;
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
		itemLogo,
		itemAPRecord1,
		itemAPRecord2,
		itemAPRecord3,
		itemCoin;

	public static void init(Configuration conf) {
		itemVoid = new ItemVoid();
		itemLogo = new Item().setUnlocalizedName("ap_logo").setTextureName("academy:logo");
		itemAPRecord1 = new ItemAPRecord("ac1", 1);
		itemAPRecord2 = new ItemAPRecord("ac2", 2);
		itemAPRecord3 = new ItemAPRecord("ac3", 3);
		itemCoin = new ItemCoin();
		
		GameRegistry.registerItem(itemVoid, "ability_void");
		GameRegistry.registerItem(itemLogo, "ap_logo");
		GameRegistry.registerItem(itemAPRecord1, "ap_record1");
		GameRegistry.registerItem(itemAPRecord2, "ap_record2");
		GameRegistry.registerItem(itemAPRecord3, "ap_record3");
		GameRegistry.registerItem(itemCoin, "ap_coin");
	}

}
