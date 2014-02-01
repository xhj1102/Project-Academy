/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.control.keys;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cn.liutils.api.client.register.IKeyProcess;
import cn.misaka.system.network.ControlDataSender;
import cn.misaka.system.proxy.CommonProxy;

/**
 * 能力控制的键位。
 * @author WeAthFolD
 *
 */
public class KeyAbilityControl implements IKeyProcess {
	
	final int keyID;
	Minecraft mc = Minecraft.getMinecraft();

	/**
	 * 
	 */
	public KeyAbilityControl(int id) {
		keyID = id;
	}

	/* (non-Javadoc)
	 * @see cn.liutils.api.client.register.IKeyProcess#onKeyDown(int, boolean)
	 */
	@Override
	public void onKeyDown(int keyCode, boolean tickEnd) {
		EntityPlayer player = mc.thePlayer;
		if(!tickEnd && mc.inGameHasFocus && mc.currentScreen == null) {
			if(CommonProxy.abilityMain.onControlStatChange(player, keyID, true))
				ControlDataSender.sendControlDataToServer(player, keyID, true);
		}
	}

	/* (non-Javadoc)
	 * @see cn.liutils.api.client.register.IKeyProcess#onKeyUp(int, boolean)
	 */
	@Override
	public void onKeyUp(int keyCode, boolean tickEnd) {
		EntityPlayer player = mc.thePlayer;
		if(!tickEnd && mc.inGameHasFocus && mc.currentScreen == null) {
			if(CommonProxy.abilityMain.onControlStatChange(player, keyID, false))
				ControlDataSender.sendControlDataToServer(player, keyID, false);
		}
	}

}
