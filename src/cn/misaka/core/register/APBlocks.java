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
import cn.misaka.support.block.*;
import cn.misaka.support.block.tile.*;
import cn.misaka.support.block.windgen.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import cn.misaka.core.register.APItems;


/**
 * 方块统一注册
 */
public class APBlocks {

	public static Block 
		ability_developer,
		ad_module_magnet,
		windGen,
		//矿物部分
		copperore,
		tinore;
	
	
	public static void init(Configuration conf) {
		ability_developer = new BlockAbilityDeveloper();
		ad_module_magnet = new BlockFieldIncrease();
		windGen = new BlockWindGenerator();
		copperore = new APBlockOre("copperore", 1);
		tinore = new APBlockOre("tinore", 1);
		
		GameRegistry.registerBlock(ability_developer, "abi lity_developer");
		GameRegistry.registerBlock(ad_module_magnet, "ad_module_fi");
		GameRegistry.registerBlock(windGen, "ad_windgen");
		
		GameRegistry.registerTileEntity(TileAbilityDeveloper.class, "tile_ability_developer");
		GameRegistry.registerTileEntity(TileFieldIncrease.class, "tile_field_increase");
		GameRegistry.registerTileEntity(TileWindGenerator.class, "tile_windGen");
		
		//矿物
		GameRegistry.registerBlock(copperore, "copper_ore");
		GameRegistry.registerBlock(tinore, "tin_ore");
		
		//矿物词典 
		OreDictionary.registerOre("oreCopper", copperore);
		OreDictionary.registerOre("oreTin", tinore);
		
		//熔炉配方
		GameRegistry.addSmelting(copperore, new ItemStack(APItems.CopperIngot), 0.1f);
		GameRegistry.addSmelting(tinore,new ItemStack(APItems.TinIngot), 0.1f);
		
	}

}
