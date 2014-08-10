package cn.misaka.ability.api.control;

import java.util.HashMap;

public class PlayerControlData {
	
	public HashMap<Integer, SkillControlStat> keyStateMap = new HashMap();
	
	public int activateSkill = -1;

	public PlayerControlData() {
	}
	
	/**
	 * 当按键状态改变是调用
	 * @param ability_id
	 * @param max_keys
	 * @param keyid
	 * @param isDown
	 * @return 按键状态是否“真正的”改变。
	 */
	public boolean onKeyStateChange(int ability_id, int max_keys, int keyid, boolean isDown) {
		SkillControlStat stat = loadSkillStat(ability_id, max_keys);
		boolean preStat = stat.isKeyDown(keyid);
		stat.setKeyDown(keyid, isDown);
		return preStat != isDown;
	}
	
	/**
	 * -1 if skill disabled
	 * @param skillID
	 */
	public void setActivateSkill(int skillID) {
		activateSkill = skillID;
	}
	
	public SkillControlStat loadSkillStat(int ability_id, int max_keys) {
		SkillControlStat states = getSkillStat(ability_id);
		if(states == null) {
			states = new SkillControlStat();
			keyStateMap.put(Integer.valueOf(ability_id), states);
		}
		return states;
	}
	
	public boolean getKeyState(int ability_id, int subkey) {
		SkillControlStat states = getSkillStat(ability_id);
		return states == null ? false : states.isKeyDown(subkey);
	}
	
	public SkillControlStat getSkillStat(int ability_id) {
		return keyStateMap.get(Integer.valueOf(ability_id));
	}

}
