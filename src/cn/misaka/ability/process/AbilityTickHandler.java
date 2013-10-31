/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.process;

import java.util.EnumSet;

import cn.misaka.ability.register.AbilityItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

/**
 * Ability Module的TickHandler。
 * 目前功能：voidItem的处理
 * @author WeAthFolD
 *
 */
public class AbilityTickHandler implements ITickHandler {

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.ITickHandler#tickStart(java.util.EnumSet, java.lang.Object[])
	 */
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		EntityPlayer player = (EntityPlayer) tickData[0];
		if(player != null)
			onPlayerTick(player);
	}

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
		return EnumSet.of(TickType.PLAYER);
	}
	
	private void onPlayerTick(EntityPlayer player) {
		if(true) {
			ItemStack is = player.getCurrentEquippedItem();
			if(is == null) {
				player.setCurrentItemOrArmor(0, new ItemStack(AbilityItems.abilityVoid));
			}
		} 
	}

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.ITickHandler#getLabel()
	 */
	@Override
	public String getLabel() {
		return "AcademyCraft-Ability TickHandler";
	}

}
