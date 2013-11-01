/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.system;

import java.util.EnumSet;
import java.util.HashMap;

import cn.misaka.ability.register.AbilityItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

/**
 * Ability Module的处理核心。
 * 目前功能：voidItem的处理
 * @author WeAthFolD
 *
 */
public final class ServerAbilityMain implements ITickHandler {

	private static HashMap<EntityPlayer, PlayerAbilityData> dataMap = new HashMap();
	
	public String getLabel() {
		return "AcademyCraft-Ability TickHandler";
	}
	
	public static void forceUpdate(EntityPlayer player) {
		PlayerAbilityData data = dataMap.get(player);
		if(data != null)
			data.updateInformation();
	}
	
	public static PlayerAbilityData getPlayerData(EntityPlayer player) {
		return dataMap.get(player);
	}
	
	public static void resetPlayerData(EntityPlayer player, PlayerAbilityData data) {
		dataMap.put(player, data);
	}
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		EntityPlayer player = (EntityPlayer) tickData[0];
		if(player != null)
			onPlayerTick(player);
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.PLAYER);
	}
	
	private void onPlayerTick(EntityPlayer player) {
		PlayerAbilityData data = dataMap.get(player);
		
		if(data == null) {
			if(AbilityDataHelper.playerHasAbility(player)) {
				dataMap.put(player, new PlayerAbilityData(player));
			}
		} else {
			System.out.println("pDataTick " + (player.worldObj.isRemote ? "_CLIENT" : "_SERVER") + " : " + data.isAvailable + " " + data.level);
			if(data.isActivated) { //在这里执行玩家的各种能力操作
				ItemStack is = player.getCurrentEquippedItem();
				if(is == null) {
					player.setCurrentItemOrArmor(0, new ItemStack(AbilityItems.abilityVoid));
				}
			}
			
			if(!data.isAvailable) {
				dataMap.remove(player);
			}
		}
	}


}
