/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.system;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * 玩家的能力信息存储。通过它可以直接得到当前玩家的大量能力信息。
 * @author WeAthFolD
 *
 */
public class PlayerAbilityData {

	protected EntityPlayer player;
	
	public boolean isAvailable, isActivated;
	
	public int level; //级别
	
	public int type;
	
	public int calcPoint; //计算力点数
	
	public ArrayList<int[]> controlArray; //自定义控制。序号对应键位序号[施工中]
	
	/**
	 * 
	 */
	public PlayerAbilityData(EntityPlayer pl) {
		player = pl;
		updateInformation();
		isActivated = true;
	}
	
	public void updateInformation() {
		NBTTagCompound tag = player.getEntityData();
		isAvailable = AbilityDataHelper.playerHasAbility(tag);
		level = AbilityDataHelper.getLevel(tag);
		type = AbilityDataHelper.getType(tag);
		calcPoint = AbilityDataHelper.getCalcPoint(tag);
	}
	
	public void writeInformationToTag() {
		NBTTagCompound tag = player.getEntityData();
		AbilityDataHelper.setHasAbility(tag, isAvailable);
		AbilityDataHelper.setCalcPoint(tag, calcPoint);
		AbilityDataHelper.setType(tag, type);
		AbilityDataHelper.setLevel(tag, level);
	}
	
	@Override
	public String toString() {
		return "[abilityData " + (isAvailable ? "AVAILABLE" : "UNAVAILABLE") + " " + 
	(isActivated ? "ACTIVATED" : "DEACTIVATED") + "]";
	}

}
