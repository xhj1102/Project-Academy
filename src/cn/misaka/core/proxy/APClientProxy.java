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
import cn.misaka.ability.system.client.key.KeySkillActivation;
import cn.misaka.ability.system.client.key.KeySkillControl;
import cn.misaka.ability.system.client.model.ModelBipedAP;
import cn.misaka.ability.system.client.render.RenderAbilityVoid;
import cn.misaka.ability.system.client.render.RenderPlayerAP;
import cn.misaka.ability.system.command.CommandControlModification;
import cn.misaka.core.register.APBlocks;
import cn.misaka.core.register.APItems;
import cn.misaka.support.block.tile.TileAbilityDeveloper;
import cn.misaka.support.block.tile.TileFieldIncrease;
import cn.misaka.support.client.render.entity.RenderArcAnim;
import cn.misaka.support.client.render.entity.RenderRailgun;
import cn.misaka.support.client.render.entity.RenderSurroundArc;
import cn.misaka.support.client.render.item.RenderCoin;
import cn.misaka.support.client.render.tile.RenderAbilityDeveloper;
import cn.misaka.support.client.render.tile.RenderFieldIncrease;
import cn.misaka.support.entity.fx.EntityArcFX;
import cn.misaka.support.entity.fx.EntityElecArcFX;
import cn.misaka.support.entity.fx.EntityRailgunFX;
import cn.misaka.support.entity.fx.EntitySurroundArcFX;

/**
 * 客户端的加载代理
 */
public class APClientProxy extends APCommonProxy {
	
	private static ModelBipedAP playerModelHacker = new ModelBipedAP();

	@Override
	public void preInit() {
		//TODO:操作可编辑化
		LIKeyProcess.addKey("AP_ML", LIKeyProcess.MOUSE_LEFT, false, new KeySkillControl(0));
		LIKeyProcess.addKey("AP_MR", LIKeyProcess.MOUSE_RIGHT, false, new KeySkillControl(1));
		LIKeyProcess.addKey("AP_R", Keyboard.KEY_R, false, new KeySkillControl(2));
		LIKeyProcess.addKey("AP_F", Keyboard.KEY_F, false, new KeySkillControl(3));
		LIKeyProcess.addKey("AP_ACTIVATE", Keyboard.KEY_V, false, new KeySkillActivation());
	}
	
	@Override
	public void init() {
		MinecraftForgeClient.registerItemRenderer(APItems.abilityVoid, new RenderAbilityVoid());
		MinecraftForgeClient.registerItemRenderer(APItems.coin, new RenderCoin());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(APBlocks.ability_developer), new RenderAbilityDeveloper.ItemRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileAbilityDeveloper.class, new RenderAbilityDeveloper());
		ClientRegistry.bindTileEntitySpecialRenderer(TileFieldIncrease.class, new RenderFieldIncrease());
		RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderPlayerAP());
		RenderingRegistry.registerEntityRenderingHandler(EntityArcFX.class, new RenderArcAnim());
		RenderingRegistry.registerEntityRenderingHandler(EntitySurroundArcFX.class, new RenderSurroundArc());
		RenderingRegistry.registerEntityRenderingHandler(EntityRailgunFX.class, new RenderRailgun());
		RenderingRegistry.registerEntityRenderingHandler(EntityElecArcFX.class, new RenderArcAnim());
	}
	
	@Override
	public void commandInit(CommandHandler cm) {
		cm.registerCommand(new CommandControlModification());
	}

}
