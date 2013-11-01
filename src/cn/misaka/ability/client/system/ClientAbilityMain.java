/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.client.system;

import java.util.EnumSet;

import cn.misaka.ability.register.AbilityItems;

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

	Minecraft mc = Minecraft.getMinecraft();
	EntityPlayer player;
	
	public ClientAbilityMain() { }

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.ITickHandler#tickStart(java.util.EnumSet, java.lang.Object[])
	 */
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if(player != null) {
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
	
	private void onClientTick() {}

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.ITickHandler#tickEnd(java.util.EnumSet, java.lang.Object[])
	 */
	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
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
