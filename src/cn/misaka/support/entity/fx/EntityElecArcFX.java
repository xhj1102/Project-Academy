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
package cn.misaka.support.entity.fx;

import cn.misaka.core.proxy.APClientProps;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * @author WeAthFolD
 *
 */
public class EntityElecArcFX extends EntityArcFX {

	/**
	 * @param world
	 * @param player
	 */
	public EntityElecArcFX(World world, EntityPlayer player, float dist) {
		super(world, player, dist);
		this.texture = new ResourceLocation[] {
				APClientProps.TEX_DBG_31
		};
	}
	
	@Override
	public void onUpdate() {
		this.updatePosition();
		if(!player.getEntityData().getBoolean("ap_arcdown"))
			this.setDead();
		
	}

}
