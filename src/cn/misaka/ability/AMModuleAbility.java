/**
 * 
 */
package cn.misaka.ability;

import net.minecraft.command.CommandHandler;
import cn.liutils.core.register.Config;
import cn.misaka.ability.proxy.CommonProxy;
import cn.misaka.ability.register.AbilityItems;
import cn.misaka.ability.system.ServerAbilityMain;
import cn.misaka.ability.system.command.CommandAim;
import cn.misaka.core.AcademyMod;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * @author WeAthFolD
 *
 */
@Mod(modid = "AcademyCraft-Ability", name = "AcademyCraft Ability Module", version = AcademyMod.VERSION /*, dependencies = */ )
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class AMModuleAbility {

	@SidedProxy(clientSide = "cn.misaka.ability.proxy.ClientProxy", serverSide = "cn.misaka.ability.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance("AcademyCraft-Ability")
	public static AMModuleAbility instance;
	
	@EventHandler()
	public void preInit(FMLPreInitializationEvent event) {
		Config config = AcademyMod.config;
		AbilityItems.init(config);
		
		proxy.preInit();
	}
	
	@EventHandler()
	public void init(FMLInitializationEvent event) {
		TickRegistry.registerTickHandler(new ServerAbilityMain(), Side.SERVER);
		TickRegistry.registerTickHandler(new ServerAbilityMain(), Side.CLIENT);
		
		proxy.init();
	}
	
	@EventHandler()
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
	}
	
	@EventHandler()
	public void serverStarting(FMLServerStartingEvent event) {
		CommandHandler cm = (CommandHandler) event.getServer().getCommandManager();
		cm.registerCommand(new CommandAim());
	}
	
}
