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
package cn.misaka.support.client.gui.ad;

/**
 * @author WeAthFolD
 *
 */
public class ADActionHandler {
	
	public static final int INITIAL_LEARNING_COST = 1200;

	public static double DU2EU(int pu) {
		return 150 * pu;
	}
	
	public static int DU2EXP(int i) {
		return (int) (0.017 * i);
	}

}
