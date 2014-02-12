/**
 * 
 */
package cn.misaka.ability;

import net.minecraft.command.CommandHandler;
import net.minecraft.entity.Entity;
import cn.liutils.core.register.Config;
import cn.misaka.ability.entity.EntityMeltdowner;
import cn.misaka.ability.entity.EntityTeleportIndicator;
import cn.misaka.ability.proxy.CommonProxy;
import cn.misaka.ability.register.AbilityBlocks;
import cn.misaka.ability.register.AbilityItems;
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
import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * @author WeAthFolD
 *
 */
@Mod(modid = "AcademyCraft-Ability", name = "AcademyCraft Ability Module", version = AcademyMod.VERSION , dependencies = AcademyMod.DEPEDENCY_CORE )
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class AMModuleAbility {
	
	private int nextEntityID = 0;

	@SidedProxy(clientSide = "cn.misaka.ability.proxy.ClientProxy", serverSide = "cn.misaka.ability.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance("AcademyCraft-Ability")
	public static AMModuleAbility instance;
	
	@EventHandler()
	public void preInit(FMLPreInitializationEvent event) {
		Config config = AcademyMod.config;
		AbilityItems.init(config);
		AbilityBlocks.init(config);
		
		proxy.preInit();
	}
	
	@EventHandler()
	public void init(FMLInitializationEvent event) {
		
		registerEntity(EntityMeltdowner.class, "melt_downer");
		registerEntity(EntityTeleportIndicator.class, "tele_indicator");
		
		proxy.init();
	}
	
	private void registerEntity(Class<? extends Entity> cs, String name) {
		EntityRegistry.registerModEntity(cs, name, ++nextEntityID, AMModuleAbility.instance, 32, 3, true);
	}
	
	@EventHandler()
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
	}
	
	@EventHandler()
	public void serverStarting(FMLServerStartingEvent event) {
		CommandHandler cm = (CommandHandler) event.getServer().getCommandManager();
	}
	
}
