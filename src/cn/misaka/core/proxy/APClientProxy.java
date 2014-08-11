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

import net.minecraft.command.CommandHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cn.liutils.core.client.register.LIKeyProcess;
import cn.misaka.ability.block.tile.TileAbilityDeveloper;
import cn.misaka.ability.client.model.ModelBipedAP;
import cn.misaka.ability.client.render.entity.RenderPlayerAP;
import cn.misaka.ability.client.render.tile.RenderAbilityDeveloper;
import cn.misaka.ability.system.client.key.KeySkillActivation;
import cn.misaka.ability.system.client.key.KeySkillControl;
import cn.misaka.ability.system.client.render.RenderAbilityVoid;
import cn.misaka.ability.system.client.render.RenderItemEnchanted;
import cn.misaka.ability.system.command.CommandControlModification;
import cn.misaka.core.register.APItems;

/**
 * @author WeAthFolD
 *
 */
public class APClientProxy extends APCommonProxy {
	
	private static ModelBipedAP hacker = new ModelBipedAP();

	@Override
	public void preInit() {
		LIKeyProcess.addKey("AP_ML", LIKeyProcess.MOUSE_LEFT, false, new KeySkillControl(0));
		LIKeyProcess.addKey("AP_MR", LIKeyProcess.MOUSE_RIGHT, false, new KeySkillControl(1));
		LIKeyProcess.addKey("AP_R", Keyboard.KEY_R, false, new KeySkillControl(2));
		LIKeyProcess.addKey("AP_F", Keyboard.KEY_F, false, new KeySkillControl(3));
		LIKeyProcess.addKey("AP_ACTIVATE", Keyboard.KEY_V, false, new KeySkillActivation());
	}
	
	@Override
	public void init() {
		MinecraftForgeClient.registerItemRenderer(APItems.abilityVoid, new RenderAbilityVoid());
		for(Item it : APItems.enchantedSword)
			MinecraftForgeClient.registerItemRenderer(it, new RenderItemEnchanted());
		ClientRegistry.bindTileEntitySpecialRenderer(TileAbilityDeveloper.class, new RenderAbilityDeveloper());
		RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderPlayerAP());
	}
	
	@Override
	public void commandInit(CommandHandler cm) {
		cm.registerCommand(new CommandControlModification());
	}

}
