/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.client.keys;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cn.liutils.api.client.register.IKeyProcess;
import cn.misaka.ability.network.AbilityControlSyncer;

/**
 * @author WeAthFolD
 *
 */
@SideOnly(Side.CLIENT)
public class KeyAbilityControl implements IKeyProcess {

	final int keyID;
	
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
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		if(player != null) {
			if(mc.currentScreen != null)
				AbilityControlSyncer.syncControl(player, keyID, true);
			else AbilityControlSyncer.syncStop(player);
		}
	}

	/* (non-Javadoc)
	 * @see cn.liutils.api.client.register.IKeyProcess#onKeyUp(int, boolean)
	 */
	@Override
	public void onKeyUp(int keyCode, boolean tickEnd) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		if(player != null) {
			if(mc.currentScreen != null)
				AbilityControlSyncer.syncControl(player, keyID, false);
			else AbilityControlSyncer.syncStop(player);
		}
	}

}
