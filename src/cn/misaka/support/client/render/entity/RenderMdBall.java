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
import net.minecraft.util.ResourceLocation;
import cn.liutils.api.client.render.RenderIcon;
import cn.liutils.api.client.render.RenderIconAnimated;
import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.core.proxy.APClientProps;
import cn.misaka.support.entity.EntityMdBall;

/**
 * @author WeAthFolD
 *
 */
public class RenderMdBall extends RenderIcon {

	public RenderMdBall() {
		super(null);
		this.setHasLight(false);
	}
	
	@Override
	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {
		EntityMdBall ball = (EntityMdBall) par1Entity;
		this.alpha = .8F;
		this.hasLight = false;
		if(ball.isCharging()) {
			if(ball.curFrameID == 4) ball.curFrameID = 3;
			RenderUtils.loadTexture(APClientProps.ANIM_MDBALL_ARC[ball.curFrameID]);
		} else {
			RenderUtils.loadTexture(APClientProps.ANIM_MDBALL_STB[ball.curFrameID]);
		}
		this.setSize(ball.currentSize);
		super.doRender(par1Entity, par2, par4, par6, par8, par9);
	}

}
