/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.client.system;

import java.util.EnumSet;

import cn.liutils.core.LIUtilsMod;
import cn.liutils.core.client.register.LIKeyProcess;
import cn.misaka.ability.client.AbilityClientEventHandler;
import cn.misaka.ability.register.AbilityItems;
import cn.misaka.ability.system.AbilityClass;
import cn.misaka.ability.system.AbilityComponent;
import cn.misaka.ability.system.PlayerAbilityData;
import cn.misaka.ability.system.ServerAbilityMain;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

/**
 * @author WeAthFolD
 *
 */
public class ClientAbilityMain implements ITickHandler {

	Minecraft mc = Minecraft.getMinecraft();
	EntityPlayer player;
	
	public ClientAbilityMain() { }

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.ITickHandler#tickStart(java.util.EnumSet, java.lang.Object[])
	 */
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if(player == null) {
			player = mc.thePlayer; 
			return;
		}
		
		if(type.contains(TickType.CLIENT)) {
			onClientTick();
		} else if(type.contains(TickType.RENDER)) {
			onRenderTick((Float) tickData[0]);
		}
	}
	
	private void onRenderTick(float time) {}
	
	private void onClientTick() {
		if(player == null) return;
		
	}

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.ITickHandler#tickEnd(java.util.EnumSet, java.lang.Object[])
	 */
	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if(player == null) return;
		if(LIUtilsMod.DEBUG) {
			PlayerAbilityData data = ServerAbilityMain.getAbilityData(player);
			AbilityClass ac = ServerAbilityMain.getAbilityClass(player);
			if(data.isAvailable) {
				String[] texts = new String[4];
				texts[0] = player.getEntityName() + (data.isActivated ? EnumChatFormatting.GREEN + " ACTIVATED" : EnumChatFormatting.RED + " DEACTIVATED");
				texts[1] = "Class : " + (ac == null ? "null" : ac.getAbilityName()) + "[" + data.type + "]";
				texts[2] = "Level : " + data.level;
				AbilityComponent component = ac.getComponent(data.level);
				texts[3] = "Component : " + (component == null ? null : ac.getComponent(data.level).getComponentName());
				AbilityClientEventHandler.setHoveringText(texts);
			} else {
				String[] texts = new String[2];
				texts[0] = player.getEntityName();
				texts[1] = EnumChatFormatting.RED + "Ability unavailable";
				AbilityClientEventHandler.setHoveringText(texts);
			}
		}
		ItemStack is = player.getCurrentEquippedItem();
		if(is != null && is.itemID == AbilityItems.abilityVoid.itemID) {
			LIKeyProcess.addKeyOverride(mc.gameSettings.keyBindAttack);
			LIKeyProcess.addKeyOverride(mc.gameSettings.keyBindUseItem);
		} else {
			LIKeyProcess.removeKeyOverride(mc.gameSettings.keyBindAttack);
			LIKeyProcess.removeKeyOverride(mc.gameSettings.keyBindUseItem);
		}
	}

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.ITickHandler#ticks()
	 */
	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT, TickType.RENDER);
	}

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.ITickHandler#getLabel()
	 */
	@Override
	public String getLabel() {
		return "AcademyCraft-Ability ClientTickHandler";
	}

}
