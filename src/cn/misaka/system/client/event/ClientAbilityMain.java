/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.client.event;

import java.util.EnumSet;

import cn.misaka.system.ModuleSystem;
import cn.misaka.system.data.PlayerAbilityData;
import cn.misaka.system.proxy.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

/**
 * @author WeAthFolD
 *
 */
public class ClientAbilityMain implements ITickHandler {

	public ClientAbilityMain() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		if(player != null && mc.inGameHasFocus) {
			PlayerAbilityData data = CommonProxy.abilityMain.getAbilityData(player);
			if(data != null) {
				if(data.canPlayerUseAbility())
					setPlayerInventory(player);
			}
		}
	}
	
	private void setPlayerInventory(EntityPlayer player) {
		if(player.getCurrentEquippedItem() == null)
			player.setCurrentItemOrArmor(0, new ItemStack(ModuleSystem.itemAbilityVoid));
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
	}

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.ITickHandler#ticks()
	 */
	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.ITickHandler#getLabel()
	 */
	@Override
	public String getLabel() {
		return "AP-System-ClientTickHandler";
	}

}
