/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.data;

import net.minecraft.nbt.NBTTagCompound;

/**
 * @author WeAthFolD
 *
 */
public class AbilityControlData {

	public NBTTagCompound data;
	public PlayerAbilityData baseData;
	
	public int currentSetID;
	public ControlSet[] controlSets = new ControlSet[4];
	private final static String PREFIX = AbilityDataHelper.PRF_CNTRL;

	public AbilityControlData(NBTTagCompound nbt, PlayerAbilityData base) {
		data = nbt;
		baseData = base;
		for(int i = 0; i < 4; i++)
			controlSets[i] = new ControlSet();
		reloadControlData();
	}
	
	public void reloadControlData() {
		currentSetID = data.getByte(PREFIX + "setid");
		for(int i = 0; i < 4; i++) {
			controlSets[i].reload(data, baseData.ability_class, baseData.ability_level, i);
		}
	}
	
	public void saveData() {
		data.setByte(PREFIX + "setid", (byte) currentSetID);
		for(int i = 0; i < 4; i++) {
			controlSets[i].save(data, baseData.ability_class, baseData.ability_level, i);
		}
	}
	
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
