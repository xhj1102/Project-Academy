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
import cn.misaka.support.block.BlockAbilityDeveloper;
import cn.misaka.support.block.BlockFieldIncrease;
import cn.misaka.support.block.tile.TileAbilityDeveloper;
import cn.misaka.support.block.tile.TileFieldIncrease;
import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;

/**
 * 方块统一注册
 */
public class APBlocks {

	public static Block ability_developer,
		ad_module_magnet,
		ad_module_card;
	
	
	public static void init(Configuration conf) {
		ability_developer = new BlockAbilityDeveloper();
		ad_module_magnet = new BlockFieldIncrease();
		
		GameRegistry.registerBlock(ability_developer, "ability_developer");
		GameRegistry.registerBlock(ad_module_magnet, "ad_module_fi");
		
		GameRegistry.registerTileEntity(TileAbilityDeveloper.class, "tile_ability_developer");
		GameRegistry.registerTileEntity(TileFieldIncrease.class, "tile_field_increase");
	}

}
