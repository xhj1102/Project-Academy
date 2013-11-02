/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.system;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

import cn.misaka.ability.ability.test.AbilityClassTest;
import cn.misaka.ability.register.AbilityItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

/**
 * Ability Module的处理核心，包含所有的Ability信息。
 * @author WeAthFolD
 *
 */
public final class ServerAbilityMain implements ITickHandler {

	private static HashMap<EntityPlayer, PlayerAbilityData> dataMap = new HashMap();
	
	private static ArrayList<AbilityClass> abilityClasses = new ArrayList();
	
	static {
		//TODO: 在这里添加所有的能力类别。
		abilityClasses.add(new AbilityClassTest());
	}
	
	public String getLabel() {
		return "AcademyCraft-Ability TickHandler";
	}
	
	public static void forceUpdate(EntityPlayer player) {
		PlayerAbilityData data = dataMap.get(player);
		if(data != null)
			data.updateInformation();
	}
	
	public static void resetPlayerData(EntityPlayer player, PlayerAbilityData data) {
		dataMap.put(player, data);
	}
	
	public static AbilityClass getAbilityClass(EntityPlayer player) {
		int index = getAbilityData(player).type;
		return getAbilityClass(index);
	}
	
	private static AbilityClass getAbilityClass(int index) {
		return abilityClasses.size() > index ? abilityClasses.get(index) : null;
	}
	
	public static PlayerAbilityData getAbilityData(EntityPlayer player) {
		PlayerAbilityData data = dataMap.get(player);
		if(data == null) {
			data = new PlayerAbilityData(player);
			dataMap.put(player, data);
			//TODO: if(player.worldObj.isRemote) [REQUIRE SYNC]
		}
		return data;
	}
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		EntityPlayer player = (EntityPlayer) tickData[0];
		if(player != null)
			onPlayerTick(player);
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) { }

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.PLAYER);
	}
	
	private void onPlayerTick(EntityPlayer player) {
		PlayerAbilityData data = dataMap.get(player);
		World world = player.worldObj;
		
		if(data == null) {
			if(AbilityDataHelper.playerHasAbility(player)) {
				dataMap.put(player, new PlayerAbilityData(player));
			}
		} else {
			//System.out.println("pDataTick " + (player.worldObj.isRemote ? "_CLIENT" : "_SERVER") + " : " + data.isAvailable + " " + data.level);
			if(data.isActivated) { //在这里执行玩家的各种能力操作
				if(world.isRemote) {
					ItemStack is = player.getCurrentEquippedItem();
					if(is == null) {
						player.setCurrentItemOrArmor(0, new ItemStack(AbilityItems.abilityVoid));
					}
				}
				
				AbilityClass ac = getAbilityClass(data.type);
				if(ac != null) {
					ac.onTick(player, world, data);
				} else {
					System.err.println("Didn't find the right AbilityClass for type " + data.type);
				}
			}
			
			if(!data.isAvailable) {
				dataMap.remove(player);
			}
		}
	}
}
