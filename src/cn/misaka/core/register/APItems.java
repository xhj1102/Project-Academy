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
import cn.misaka.ability.system.item.*;
import cn.misaka.support.item.ItemAPRecord;
import cn.misaka.support.item.ItemCapsule;
import cn.misaka.support.item.ItemCoin;
import cn.misaka.support.item.ItemNeedle;
import cn.misaka.support.item.ItemTablet;
import cn.misaka.support.item.ItemEnergyCrystal;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

/**
 * @author WeAthFolD
 *
 */
public class APItems {
	
	public static Item 
		abilityVoid,
		logo,
		record1,
		record2,
		record3,
		coin,
		//H即High, M即Medium, L即Low.
		capsuleH,
		capsuleM,
		capsuleL,
		tabletH,
		tabletM,
		tabletL,
		needle,
		enchantedOther,
		EnergyCrystal;
	
	public static Item
		enchantedSword[],
		enchantedTool[];

	public static void init(Configuration conf) {
		abilityVoid = new ItemVoid();
		logo = new Item().setUnlocalizedName("ap_logo").setTextureName("academy:logo");
		record1 = new ItemAPRecord("ac1", 1).setUnlocalizedName("ap_record1");
		record2 = new ItemAPRecord("ac2", 2).setUnlocalizedName("ap_record2");
		record3 = new ItemAPRecord("ac3", 3).setUnlocalizedName("ap_record3");
		coin = new ItemCoin();
		capsuleH = new ItemCapsule(1).setUnlocalizedName("ability_capsule1");
		capsuleM = new ItemCapsule(2).setUnlocalizedName("ability_capsule2");
		capsuleL = new ItemCapsule(3).setUnlocalizedName("ability_capsule3");
		tabletH = new ItemTablet(1).setUnlocalizedName("ability_tablet1");
		tabletM = new ItemTablet(2).setUnlocalizedName("ability_tablet2");
		tabletL = new ItemTablet(3).setUnlocalizedName("ability_tablet3");
		needle = new ItemNeedle();
		EnergyCrystal = new ItemEnergyCrystal();
		
		
		GameRegistry.registerItem(abilityVoid, "ability_void");
		GameRegistry.registerItem(logo, "ap_logo");
		GameRegistry.registerItem(record1, "ap_record1");
		GameRegistry.registerItem(record2, "ap_record2");
		GameRegistry.registerItem(record3, "ap_record3");
		GameRegistry.registerItem(coin, "ap_coin");
		GameRegistry.registerItem(capsuleH, "ability_capsule1");
		GameRegistry.registerItem(capsuleM, "ability_capsule2");
		GameRegistry.registerItem(capsuleL, "ability_capsule3");
		GameRegistry.registerItem(tabletH, "ability_tablet1");
		GameRegistry.registerItem(tabletM, "ability_tablet2");
		GameRegistry.registerItem(tabletL, "ability_tablet3");
		GameRegistry.registerItem(needle, "ap_needle");
		GameRegistry.registerItem(EnergyCrystal, "ap_energycrystal");
	}
	
	public static Item getEnchantedSword(Item.ToolMaterial mat) {
		return enchantedSword[mat.ordinal()];
	}
	
	public static Item getEnchantedTool(Item.ToolMaterial mat) {
		return enchantedTool[mat.ordinal()];
	}

}
