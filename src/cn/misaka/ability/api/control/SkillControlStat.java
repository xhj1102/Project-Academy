/**
 * Copyright (C) Lambda-Innovation, 2013-2014
 * This code is open-source. Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer. 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 */
package cn.misaka.ability.api.control;

/**
 * @author WeAthFolD
 *
 */
public class SkillControlStat {

	private boolean[] keyDown;
	
	public final int maxKeys;
	
	public SkillControlStat(int max) {
		maxKeys = max;
		keyDown = new boolean[max];
	}
	
	/**
	 * @return true if ANY key is down
	 */
	public boolean isKeyDown() {
		for(boolean b : keyDown)
			if(b) return true;
		return false;
	}
	
	public boolean isKeyDown(int kid) {
		return keyDown[kid];
	}
	
	public void setKeyDown(int kid, boolean downOrUp) {
		keyDown[kid] = downOrUp;
	}
	
	public void clear() {
		for(boolean b : keyDown) b = false;
	}

}
