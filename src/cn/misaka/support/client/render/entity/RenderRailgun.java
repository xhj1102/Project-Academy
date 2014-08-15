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
package cn.misaka.support.client.render.entity;

import net.minecraft.entity.Entity;

/**
 * @author WeAthFolD
 *
 */
public class RenderRailgun extends RenderArcAnim {

	/**
	 * 
	 */
	public RenderRailgun() {
		setHalfHeight(.1F);
		setRatio(2F);
	}
	
	@Override
	public void doRender(Entity var1, double var2, double var4, double var6,
			float var8, float var9) {
		setHalfHeight(.09F);
		setRatio(2F);
		this.blend = .7F;
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

}
