/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.data;

import net.minecraft.nbt.NBTTagCompound;

/**
 * 属于玩家能力信息的控制信息，保存了所有自定义键位相关的内容。
 * @author WeAthFolD
 *
 */
public class AbilityControlData {

	public NBTTagCompound data;
	public PlayerAbilityData baseData;
	
	public int currentSetID; //当前使用的presetID
	public ControlSet[] controlSets = new ControlSet[4]; //所有的预设。
	private final static String PREFIX = AbilityDataHelper.PRF_CNTRL;

	public AbilityControlData(NBTTagCompound nbt, PlayerAbilityData base) {
		data = nbt;
		baseData = base;
		for(int i = 0; i < 4; i++)
			controlSets[i] = new ControlSet();
		reloadControlData();
	}
	
	/**
	 * 从NBT中重新加载控制数据。
	 */
	public void reloadControlData() {
		currentSetID = data.getByte(PREFIX + "setid");
		for(int i = 0; i < 4; i++) {
			controlSets[i].reload(data, baseData.ability_class, baseData.ability_level, i);
		}
	}
	
	/**
	 * 保存实时控制数据到NBT中。
	 */
	public void saveData() {
		data.setByte(PREFIX + "setid", (byte) currentSetID);
		for(int i = 0; i < 4; i++) {
			controlSets[i].save(data, baseData.ability_class, baseData.ability_level, i);
		}
	}
	
	/**
	 * 单个自定义操作预设。
	 * @author WeAthFolD
	 *
	 */
	public static class ControlSet {
		public int keyData[][] = new int[4][];
		
		public ControlSet() {
			for(int i = 0; i < 4; i++)
				keyData[i] = new int[2];
		}
		
		public ControlSet(NBTTagCompound data, int classid, int levelid, int setid) {
			reload(data, classid, levelid, setid);
		}
		
		public void reload(NBTTagCompound data, int classid, int levelid, int setid) {
			for(int i = 0; i < 4; i++) {
				keyData[i] = data.getIntArray(PREFIX + classid + "_" + levelid + "_set" + setid + "_" + i);
			}
		}
		
		public void save(NBTTagCompound data, int classid, int levelid, int setid) {
			for(int i = 0; i < 4; i++) {
				if(keyData[i] == null)
					keyData[i] = new int[2];
				data.setIntArray(PREFIX + classid + "_" + levelid + "_set" + setid + "_" + i, keyData[i]);
			}
		}
	}
	
	

}
