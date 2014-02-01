/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system;

import cn.misaka.core.AcademyMod;
import cn.misaka.system.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;

/**
 * @author WeAthFolD
 *
 */
@Mod(modid = "AcademyCraft-System", name = "AcademyCraft Ability System", version = AcademyMod.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ModuleSystem {

	@Instance("AcademyCraft-System")
	public static ModuleSystem instance;
	
	@SidedProxy(clientSide = "cn.misaka.system.proxy.ClientProxy", serverSide = "cn.misaka.system.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler()
	public void preLoad(FMLPreInitializationEvent event) {
		proxy.preInit();
	}
	
	@EventHandler()
	public void load(FMLInitializationEvent event) {
		proxy.init();
	}

	@EventHandler()
	public void postLoad(FMLPostInitializationEvent event) {
		proxy.postInit();
	}
	
	@EventHandler()
	public void serverStarting(FMLServerStartingEvent event) {
		
	}

}
