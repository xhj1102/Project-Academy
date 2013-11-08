/**
 * 
 */
package cn.misaka.ability.proxy;

import cn.misaka.ability.network.AbilityControlSyncer;
import cn.misaka.ability.network.AbilityDataSyncer;
import cn.misaka.core.network.AMPacketHandler;
import cn.misaka.core.proxy.AMGeneralProps;

/**
 * @author WeAthFolD
 *
 */
public class CommonProxy {
	
	public void preInit() {}
	
	public void init() {
		AMPacketHandler.addChannel(AMGeneralProps.NET_ID_ABILITY_SYNC_SERVER, new AbilityDataSyncer());
		AMPacketHandler.addChannel(AMGeneralProps.NET_ID_ABILITY_CONTROL, new AbilityControlSyncer());
	}
	
	public void postInit() {}

}
