/**
 * 
 */
package cn.misaka.ability.proxy;

import cn.misaka.ability.client.process.AbilityClientTickHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * @author WeAthFolD
 *
 */
public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
		super.preInit();
		TickRegistry.registerTickHandler(new AbilityClientTickHandler(), Side.CLIENT);
	}
	
	@Override
	public void init() {
		super.preInit();
	}
	
	@Override
	public void postInit() {
		super.postInit();
	}

	
}
