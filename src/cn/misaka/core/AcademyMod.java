package cn.misaka.core;

import java.util.Arrays;
import java.util.logging.Logger;

import cn.liutils.core.register.Config;
import cn.misaka.core.proxy.AMGeneralProps;
import cn.misaka.core.network.AMPacketHandler;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;

@Mod(modid = "AcademyCraft", name = "AcademyCraft Core", version = AcademyMod.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec = @SidedPacketHandler(channels = { AMGeneralProps.NET_CHANNEL_CLIENT }, packetHandler = AMPacketHandler.class), 
serverPacketHandlerSpec = @SidedPacketHandler(channels = { AMGeneralProps.NET_CHANNEL_SERVER }, packetHandler = AMPacketHandler.class))
public class AcademyMod {
	public static final String VERSION = "no-run";
	public static final String DEPEDENCY_CORE = "required-after:AcademyCraft@" + VERSION;

	@Instance("AcademyCraft")
	public static AcademyMod instance;

	public static Logger log = Logger.getLogger("AcademyCraft");
	
	public static Config config;

	@SidedProxy(clientSide = "cn.misaka.core.proxy.ClientProxy", serverSide = "cn.misaka.core.proxy.Proxy")
	public static cn.misaka.core.proxy.Proxy proxy;

	@EventHandler()
	public void preLoad(FMLPreInitializationEvent event) {
		config = new Config(event.getSuggestedConfigurationFile());
		setMetadata(event.getModMetadata());
		log.setParent(FMLLog.getLogger());
	}

	@EventHandler()
	public void load(FMLInitializationEvent event) {

	}

	@EventHandler()
	public void postLoad(FMLPostInitializationEvent event) {

	}

	public String getVersion() {
		return AcademyMod.VERSION;
	}

	private void setMetadata(ModMetadata aModMetadata) {
		aModMetadata.description = "[put the desc of your mod on here]";
		aModMetadata.logoFile = "";
		aModMetadata.authorList = Arrays.asList("Lambda Innovation");
		aModMetadata.url = "";
	}
}
