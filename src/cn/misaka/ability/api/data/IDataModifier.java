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
package cn.misaka.ability.api.data;

import net.minecraft.entity.player.EntityPlayer;

/**
 * 对玩家数据的快速修改。用于能力开发、升级等的轻量级实现。
 * @author WeAthFolD
 */
public interface IDataModifier {
	public void applyModification(EntityPlayer player, PlayerData data);
}
