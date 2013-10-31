/**
 * 
 */
package cn.misaka.ability;

import net.minecraft.command.CommandHandler;
import cn.liutils.core.LIUtilsMod;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cn.misaka.ability.command.CommandGetString;
import cn.misaka.ability.command.CommandSetString;
import cn.misaka.ability.proxy.CommonProxy;
import cn.misaka.core.AcademyMod;

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
		
	}
	
	@EventHandler()
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	@EventHandler()
	public void serverStarting(FMLServerStartingEvent event) {
		CommandHandler cm = (CommandHandler) event.getServer().getCommandManager();
		cm.registerCommand(new CommandGetString());
		cm.registerCommand(new CommandSetString());
	}
	
}
