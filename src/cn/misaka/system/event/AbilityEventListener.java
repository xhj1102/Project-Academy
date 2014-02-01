/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.event;

import cn.misaka.system.network.AbilityDataSender.EnumDataType;
import cn.misaka.system.proxy.CommonProxy;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;

/**
 * 喜闻乐见的EventHandler。
 * @author WeAthFolD
 *
 */
public class AbilityEventListener {

	/**
	 * 
	 */
	public AbilityEventListener() {
		// TODO Auto-generated constructor stub
	}
	
	@ForgeSubscribe
	public void onWorldLoad(WorldEvent.Load event) {
		CommonProxy.abilityMain.cleanup(); //清理对应表以防出现数据错乱
		if(event.world.isRemote) {
			System.out.println("Sending sync request...");
			CommonProxy.abilityMain.syncAllPlayers(EnumDataType.FULL);
			CommonProxy.abilityMain.syncAllPlayers(EnumDataType.CONTROL);
		}
	}

	@ForgeSubscribe
	public void onWorldSave(WorldEvent.Save event) {
		CommonProxy.abilityMain.onWorldSave(); //跟随MC保存自动保存
	}
	
	@ForgeSubscribe
	public void onWorldUnload(WorldEvent.Unload event) {
		System.out.println("World unload...");
		CommonProxy.abilityMain.syncAllPlayers(EnumDataType.FULL);
		CommonProxy.abilityMain.syncAllPlayers(EnumDataType.CONTROL);
		CommonProxy.abilityMain.cleanup();
	}
}
