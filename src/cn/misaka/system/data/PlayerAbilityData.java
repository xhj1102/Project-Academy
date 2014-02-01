/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.data;

import java.util.HashMap;
import java.util.Map;

import cn.misaka.system.network.AbilityDataSender;
import cn.misaka.system.network.AbilityDataSender.EnumDataType;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * 玩家的完整能力信息。从NBT中读取并实时维护。
 * @author WeAthFolD
 */
public class PlayerAbilityData {

	/**
	 * 对应的玩家实例。
	 */
	public EntityPlayer player;
	
	public int ability_class, ability_level;
	public int calcPoint;
	public int currentCalcPoint;
	public boolean isDeveloped, isActivated;
	
	
	public AbilityControlData controlData;
	
	public int props_speed, props_power, props_defense;
	
	public int tickBeforeRequest = 0;
	
	public PlayerAbilityData(EntityPlayer p) {
		player = p;
		controlData = new AbilityControlData(p.getEntityData(), this);
		reloadProperties();
		currentCalcPoint = calcPoint;
	}
	
	/**
	 * 重新从NBT读取信息。
	 */
	public void reloadProperties() {
		if(player.worldObj.isRemote) {
			AbilityDataSender.sendSyncRequestFromClient(EnumDataType.FULL);
			return;
		}
		NBTTagCompound tag = player.getEntityData();
		ability_class = tag.getByte(AbilityDataHelper.PRF_BASE + "class");
		ability_level = tag.getByte(AbilityDataHelper.PRF_BASE + "level");
		calcPoint = tag.getInteger(AbilityDataHelper.PRF_BASE + "cp");
		isDeveloped = tag.getBoolean(AbilityDataHelper.PRF_BASE + "isDeveloped");
		isActivated = tag.getBoolean(AbilityDataHelper.PRF_BASE + "isActivated");
		
		props_speed = tag.getByte(AbilityDataHelper.PRF_PROPS + "speed");
		props_power = tag.getByte(AbilityDataHelper.PRF_PROPS + "power");
		props_defense = tag.getByte(AbilityDataHelper.PRF_PROPS + "defense");
		
		controlData.reloadControlData();
	}
	
	/**
	 * 保存到NBT。
	 */
	public void saveProperties() {
		if(player.worldObj.isRemote) return;
		NBTTagCompound tag = player.getEntityData();
		tag.setByte(AbilityDataHelper.PRF_BASE + "class", (byte) ability_class);
		tag.setByte(AbilityDataHelper.PRF_BASE + "level", (byte) ability_level);
		tag.setInteger(AbilityDataHelper.PRF_BASE +"cp", calcPoint);
		tag.setBoolean(AbilityDataHelper.PRF_PROPS + "isDeveloped", isDeveloped);
		tag.setBoolean(AbilityDataHelper.PRF_PROPS + "isActivated", isActivated);
		
		tag.setByte(AbilityDataHelper.PRF_PROPS + "speed", (byte) props_speed);
		tag.setByte(AbilityDataHelper.PRF_PROPS + "power", (byte) props_power);
		tag.setByte(AbilityDataHelper.PRF_PROPS + "defense", (byte) props_defense);
		
		controlData.saveData();
	}
	
	/**
	 * 每tick进行的更新。如果是client段可能从服务器要求数据更新。
	 */
	public void updateTick() {
		if(!player.worldObj.isRemote) return;
		if(++tickBeforeRequest >= 60 && !isDataStateGood()) {
			tickBeforeRequest = 0;
			AbilityDataSender.sendSyncRequestFromClient(EnumDataType.FULL);
			AbilityDataSender.sendSyncRequestFromClient(EnumDataType.CONTROL);
		}
	}
	
	/**
	 * 数据是否处于良好的同步状态？
	 * @return
	 */
	public boolean isDataStateGood() {
		return !player.worldObj.isRemote || (!isDeveloped || ability_class == 0);
	}
	
	/**
	 * 当前玩家是否可以使用能力？
	 * @return
	 */
	public boolean canPlayerUseAbility() {
		return isDeveloped && isActivated;
	}

}
