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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cn.misaka.ability.system.event.APEventListener;
import cn.misaka.ability.system.event.APTickEvents;
import cn.misaka.ability.system.network.message.MsgControl;
import cn.misaka.core.proxy.APCommonProxy;
import cn.misaka.core.proxy.APGeneralProps;
import cn.misaka.core.register.APItems;
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
 * @author WeAthFolD
 *
 */
@Mod(modid = "project-academy", name = "AcademyCraft", version = AcademyCraft.VERSION)
public class AcademyCraft {
	
	public static final String VERSION = "0.0.1dev";
	
	@Instance("academy-craft")
	public static AcademyCraft INSTANCE;
	
	@SidedProxy(clientSide = "cn.misaka.core.proxy.APClientProxy", serverSide = "cn.misaka.core.proxy.APCommonProxy")
	public static APCommonProxy proxy;
	
	public static Logger log = FMLLog.getLogger();
	
	public static Configuration config;
	
	public static SimpleNetworkWrapper netHandler = NetworkRegistry.INSTANCE.newSimpleChannel(APGeneralProps.NET_CHANNEL);
	private static int nextNetID = 0;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		log.info("Starting AcademyCraft " + VERSION);
		log.info("Copyright (c) Lambda Innovation, 2013-2014");
		log.info("http://www.lambdacraft.cn");
		
		APItems.init(config);
		FMLCommonHandler.instance().bus().register(new APTickEvents());
		MinecraftForge.EVENT_BUS.register(new APEventListener());
		
		proxy.preInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		netHandler.registerMessage(MsgControl.Handler.class, MsgControl.class, getNextChannelID(), Side.SERVER);
		proxy.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
	}
	
	@EventHandler()
	public void serverStarting(FMLServerStartingEvent event) {
		CommandHandler commandManager = (CommandHandler) event.getServer()
				.getCommandManager();
	}
	
	public static int getNextChannelID() {
		return nextNetID++;
	}
	
}
