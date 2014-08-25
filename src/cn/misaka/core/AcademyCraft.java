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
package cn.misaka.core;

import org.apache.logging.log4j.Logger;

import net.minecraft.command.CommandHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cn.liutils.api.register.LIGuiHandler;
import cn.misaka.ability.api.APCategoryStorage;
import cn.misaka.ability.api.APControlMain;
import cn.misaka.ability.category.electromaster.CatElectroMaster;
import cn.misaka.ability.category.test.CatTest;
import cn.misaka.ability.system.command.CommandAim;
import cn.misaka.ability.system.event.APSEventListener;
import cn.misaka.ability.system.event.APSTickEvents;
import cn.misaka.ability.system.network.message.MsgControl;
import cn.misaka.ability.system.network.message.MsgSyncToClient;
import cn.misaka.ability.system.network.message.MsgSyncToServer;
import cn.misaka.core.misc.APCreativeTab;
import cn.misaka.core.proxy.APClientProps;
import cn.misaka.core.proxy.APCommonProxy;
import cn.misaka.core.proxy.APGeneralProps;
import cn.misaka.core.register.APBlocks;
import cn.misaka.core.register.APItems;
import cn.misaka.support.client.gui.ad.GuiAbilityDeveloper;
import cn.misaka.support.network.message.MsgDeveloperAttachment;
import cn.misaka.support.network.message.MsgDeveloperDismount;
import cn.misaka.support.network.message.MsgDeveloperPlayer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

/**
 * AcademyCraft主mod类。
 */
@Mod(modid = "academy-craft", name = "AcademyCraft", version = AcademyCraft.VERSION)
public class AcademyCraft {
	
	/**
	 * 当前版本。请记得在每次compile的时候进行更新
	 */
	public static final String VERSION = "0.0.1dev";
	
	@Instance("academy-craft")
	/**
	 * MOD实例
	 */
	public static AcademyCraft INSTANCE;
	
	@SidedProxy(clientSide = "cn.misaka.core.proxy.APClientProxy", serverSide = "cn.misaka.core.proxy.APCommonProxy")
	/**
	 * 加载代理
	 */
	public static APCommonProxy proxy;
	
	/**
	 * 日志
	 */
	public static Logger log = FMLLog.getLogger();
	
	/**
	 * 设置文件
	 */
	public static Configuration config;
	
	/**
	 * 网络发包处理实例
	 */
	public static SimpleNetworkWrapper netHandler = NetworkRegistry.INSTANCE.newSimpleChannel(APGeneralProps.NET_CHANNEL);
	
	/**
	 * 创造栏
	 */
	public static CreativeTabs cct = new APCreativeTab();
	
	/**
	 * GUI管理器
	 */
	public static LIGuiHandler guiHandler = new LIGuiHandler();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		log.info("Starting AcademyCraft " + VERSION);
		log.info("Copyright (c) Lambda Innovation, 2013-2014");
		log.info("http://www.lambdacraft.cn");
		
		config = new Configuration(event.getSuggestedConfigurationFile());
		
		APBlocks.init(config);
		APItems.init(config);
		APControlMain.init(config);
		
		//Tick及杂项注册
		MinecraftForge.EVENT_BUS.register(new APSEventListener());
		FMLCommonHandler.instance().bus().register(new APSTickEvents());
		NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
		
		//能力注册BEGIN
		APCategoryStorage.registerAbility(new CatTest(1));
		APCategoryStorage.registerAbility(new CatElectroMaster(2));
		//能力注册END
		
		guiHandler.addGuiElement(APClientProps.GUI_ID_ABILITY_DEV, new GuiAbilityDeveloper.Element());
		
		proxy.preInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		//网络信息注册
		netHandler.registerMessage(MsgControl.Handler.class, MsgControl.class, getNextChannelID(), Side.SERVER);
		netHandler.registerMessage(MsgDeveloperPlayer.Handler.class, MsgDeveloperPlayer.class, getNextChannelID(), Side.CLIENT);
		netHandler.registerMessage(MsgSyncToClient.Request.Handler.class, MsgSyncToClient.Request.class, getNextChannelID(), Side.SERVER);
		netHandler.registerMessage(MsgSyncToClient.Handler.class, MsgSyncToClient.class, getNextChannelID(), Side.CLIENT);
		netHandler.registerMessage(MsgSyncToServer.Handler.class, MsgSyncToServer.class, getNextChannelID(), Side.SERVER);
		netHandler.registerMessage(MsgDeveloperDismount.Handler.class, MsgDeveloperDismount.class, getNextChannelID(), Side.SERVER);
		netHandler.registerMessage(MsgDeveloperAttachment.Handler.class, MsgDeveloperAttachment.class, getNextChannelID(), Side.CLIENT);
		
		proxy.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
	}
	
	@EventHandler()
	public void serverStarting(FMLServerStartingEvent event) {
		CommandHandler cm = (CommandHandler) event.getServer()
				.getCommandManager();
		cm.registerCommand(new CommandAim());
		proxy.commandInit(cm);
	}
	
	private static int nextNetID = 0;
	/**
	 * 获取下一个空闲的网络channelID。
	 */
	public static int getNextChannelID() {
		return nextNetID++;
	}
	
}
