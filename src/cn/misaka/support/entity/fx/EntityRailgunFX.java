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
import net.minecraft.world.World;

/**
 * @author WeAthFolD
 *
 */
public class EntityRailgunFX extends EntityArcFX {

	/**
	 * @param world
	 * @param player
	 * @param dist
	 */
	public EntityRailgunFX(World world, EntityPlayer player, double dist) {
		super(world, player, dist);
		this.setTexture(APClientProps.TEX_EFF_RAILGUN);
	}

	/**
	 * @param world
	 * @param player
	 */
	public EntityRailgunFX(World world, EntityPlayer player) {
		super(world, player);
		this.setTexture(APClientProps.TEX_EFF_RAILGUN);
	}

}
