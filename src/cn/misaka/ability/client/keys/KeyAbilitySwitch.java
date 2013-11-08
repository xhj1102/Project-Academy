/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.client.keys;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cn.liutils.api.client.register.IKeyProcess;
import cn.misaka.ability.network.AbilityDataSyncer;
import cn.misaka.ability.network.AbilityDataSyncer.EnumDataType;
import cn.misaka.ability.system.PlayerAbilityData;
import cn.misaka.ability.system.ServerAbilityMain;

/**
 * @author WeAthFolD
 *
 */
public class KeyAbilitySwitch implements IKeyProcess {

	

	/* (non-Javadoc)
	 * @see cn.liutils.api.client.register.IKeyProcess#onKeyDown(int, boolean)
	 */
	@Override
	public void onKeyDown(int keyCode, boolean tickEnd) {
		if(tickEnd) return;
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		if(player != null && mc.currentScreen == null) {
			PlayerAbilityData data = ServerAbilityMain.getAbilityData(player);
			data.isActivated = !data.isActivated;
			AbilityDataSyncer.sendPacketFromClient(player, EnumDataType.SIMPLE);
			System.out.println("deactivated");
		}
	}

	/* (non-Javadoc)
	 * @see cn.liutils.api.client.register.IKeyProcess#onKeyUp(int, boolean)
	 */
	@Override
	public void onKeyUp(int keyCode, boolean tickEnd) {
		// TODO Auto-generated method stub

	}

}
