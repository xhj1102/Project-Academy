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
 * 键位操作提示条目。
 * @author WeAthFolD
 */
public class ComboIndicator {
	
	/**
	 * 该提示对应的skillKey
	 */
	int[] keyCombos;
	
	/**
	 * 附加说明。不超过7个汉字或4个英文单词
	 */
	String instruction;
	
	public ComboIndicator(String instruct, int[] keys) {
		keyCombos = keys;
		instruction = instruct;
	}

}
