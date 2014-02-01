/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.proxy;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cn.misaka.core.network.AMPacketHandler;
import cn.misaka.core.proxy.AMGeneralProps;
import cn.misaka.system.AbilityMain;
import cn.misaka.system.event.AbilityEventListener;
import cn.misaka.system.network.AbilityDataListener;
import cn.misaka.system.network.ControlDataListener;
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
		
		AMPacketHandler.addChannel(AMGeneralProps.NET_ID_ABILITY_CONTROL, new ControlDataListener());
		AMPacketHandler.addChannel(AMGeneralProps.NET_ID_ABILITY_SYNC_SERVER, new AbilityDataListener());
	}
	
	public void postInit() {}

}
