/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system;

import net.minecraft.command.CommandHandler;
import cn.liutils.core.register.ConfigHandler;
import cn.misaka.core.AcademyMod;
import cn.misaka.system.command.CommandAim;
import cn.misaka.system.item.ItemAbilityVoid;
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
 * 能力系统底层的主类。
 * @author WeAthFolD
 *
 */
@Mod(modid = "AcademyCraft-System", name = "AcademyCraft Ability System", version = AcademyMod.VERSION, dependencies = AcademyMod.DEPEDENCY_CORE)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ModuleSystem {

	@Instance("AcademyCraft-System")
	public static ModuleSystem instance;
	
	@SidedProxy(clientSide = "cn.misaka.system.proxy.ClientProxy", serverSide = "cn.misaka.system.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static ItemAbilityVoid itemAbilityVoid;
	
	@EventHandler()
	public void preLoad(FMLPreInitializationEvent event) {
		proxy.preInit();
		itemAbilityVoid = new ItemAbilityVoid(ConfigHandler.getItemId(AcademyMod.config, "ability_void", 4));
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
		CommandHandler cm = (CommandHandler) event.getServer().getCommandManager();
		cm.registerCommand(new CommandAim());
	}

}
