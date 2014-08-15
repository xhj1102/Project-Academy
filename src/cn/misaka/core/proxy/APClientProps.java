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
package cn.misaka.core.proxy;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

/**
 * 一些客户端的信息，存放贴图、模型路径等
 */
public class APClientProps {

	//Textures
	public static final ResourceLocation
		TEX_MDL_ABILITY_DEVELOPER = src("academy:textures/models/ability_developer.png"),
		TEX_GUI_ABILITY_DEVELOPER = src("academy:textures/guis/ability_dev.png"),
		TEX_HUD_CPBAR = src("academy:textures/guis/cpbar.png"),
		TEX_HUD_LOGO = src("academy:textures/guis/logo.png"),
		TEX_COIN_FRONT = src("academy:textures/items/coin-front.png"),
		TEX_COIN_BACK = src("academy:textures/items/coin-back.png"),
		TEX_ARC_SHELL = src("academy:textures/effects/electro_arc_shell.png"),
		TEX_EFF_RAILGUN = src("academy:textures/effects/railgun.png");
	
	//Ability Textures
	public static final ResourceLocation
		SKL_TEST_1 = src("academy:textures/abilities/test/skill1.png"),
		SKL_TEST_2 = src("academy:textures/abilities/test/skill2.png");
		
	//OBJ models
	public static final
		IModelCustom MDL_ABILITY_DEVELOPER = AdvancedModelLoader.loadModel(src("academy:models/ability_developer.obj"));
	
	//Animations
	public static final ResourceLocation
		ANIM_ARC_LONG[] = {
		src("academy:textures/effects/elearc0.png"),
		src("academy:textures/effects/elearc1.png"),
		src("academy:textures/effects/elearc2.png")
	};
	
	//Abilities
	public static final ResourceLocation
		ELEC_LOGO = src("academy:textures/abilities/electromaster/main.png"),
		ELEC_ATTACK = src("academy:textures/abilities/electromaster/attack_large.png"),
		ELEC_ATTRACT = src("academy:textures/abilities/electromaster/attraction.png"),
		ELEC_CHARGE = src("academy:textures/abilities/electromaster/itemcharge.png"),
		ELEC_VIEWMINE = src("academy:textures/abilities/electromaster/mineview.png"),
		ELEC_MOVE = src("academy:textures/abilities/electromaster/moving.png"),
		ELEC_RAILGUN = src("academy:textures/abilities/electromaster/railgun.png"),
		ELEC_SWORD = src("academy:textures/abilities/electromaster/sword.png"),
		ELEC_ARC = src("academy:textures/abilities/electromaster/arc.png");
	
	//GUI IDs
	public static final int
		GUI_ID_ABILITY_DEV = 0;
	
	private static ResourceLocation src(String s) {
		return new ResourceLocation(s);
	}
}
