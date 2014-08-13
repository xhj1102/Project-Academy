package cn.misaka.ability.api.control;

import java.util.HashMap;

public class PlayerControlStat {
	
	public HashMap<Integer, SkillControlStat> keyStateMap = new HashMap();

	public PlayerControlStat() {
	}
	
	/**
	 * 当按键状态改变是调用
	 * @param skillID
	 * @param maxKeys
	 * @param keyid
	 * @param isDown
	 * @return 按键状态是否“真正的”改变。
	 */
	public boolean onKeyStateChange(int skillID, int maxKeys, int keyid, boolean isDown) {
		SkillControlStat stat = loadSkillStat(skillID, maxKeys);
		boolean preStat = stat.isKeyDown(keyid);
		stat.setKeyDown(keyid, isDown);
		return preStat != isDown;
	}
	
	public SkillControlStat loadSkillStat(int ability_id, int maxKeys) {
		SkillControlStat states = getSkillStat(ability_id);
		if(states == null) {
			states = new SkillControlStat(maxKeys);
			keyStateMap.put(Integer.valueOf(ability_id), states);
		}
		return states;
	}
	
	public boolean getKeyState(int skillID, int subkey) {
		SkillControlStat states = getSkillStat(skillID);
		return states == null ? false : states.isKeyDown(subkey);
	}
	
	public SkillControlStat getSkillStat(int skillID) {
		return keyStateMap.get(Integer.valueOf(skillID));
	}

}
