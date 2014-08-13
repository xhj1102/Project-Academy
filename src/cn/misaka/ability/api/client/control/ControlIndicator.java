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
package cn.misaka.ability.api.client.control;

/**
 * 玩家的键位操作提示条目。
 * @author WeAthFolD
 */
public class ControlIndicator {
	
	int[] keyCombos; //该提示对应的skillKey
	String instruction;
	
	public ControlIndicator(String instruct, int[] keys) {
		keyCombos = keys;
		instruction = instruct;
	}

}
