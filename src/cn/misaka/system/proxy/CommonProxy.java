/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.proxy;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cn.misaka.system.AbilityMain;
import cn.misaka.system.event.AbilityEventListener;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author WeAthFolD
 *
 */
public class CommonProxy {

	public static AbilityMain abilityMain;
	
	public void preInit() {
		MinecraftForge.EVENT_BUS.register(new AbilityEventListener());
		
	}
	
	public void init() {
		abilityMain = new AbilityMain();
		TickRegistry.registerTickHandler(abilityMain, Side.SERVER);
		TickRegistry.registerTickHandler(abilityMain, Side.CLIENT);
	}
	
	public void postInit() {}

}
