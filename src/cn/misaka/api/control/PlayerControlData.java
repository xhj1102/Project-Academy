package cn.misaka.api.control;

import java.util.HashMap;

public class PlayerControlData {
	
	public HashMap<Integer, SkillControlStat> keyStateMap = new HashMap();

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
		SkillControlStat stat = loadKeyStates(ability_id, max_keys);
		boolean preStat = stat.isKeyDown(keyid);
		stat.setKeyDown(keyid, isDown);
		return preStat != isDown;
	}
	
	public SkillControlStat loadKeyStates(int ability_id, int max_keys) {
		SkillControlStat states = getKeyStates(ability_id);
		if(states == null) {
			states = new SkillControlStat();
			keyStateMap.put(Integer.valueOf(ability_id), states);
		}
		return states;
	}
	
	public boolean getKeyState(int ability_id, int subkey) {
		SkillControlStat states = getKeyStates(ability_id);
		return states == null ? false : states.isKeyDown(subkey);
	}
	
	public SkillControlStat getKeyStates(int ability_id) {
		return keyStateMap.get(Integer.valueOf(ability_id));
	}

}
