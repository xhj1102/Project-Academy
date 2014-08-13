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
package cn.misaka.ability.category.electromaster;

import cn.liutils.api.util.Pair;
import net.minecraft.item.ItemStack;

/**
 * 可以被超电磁炮用qte的方式射出的物品，需要实现这个接口。
 * @author WeAthFolD
 *
 */
public interface IRailgunQTE {
	
	/**
	 * 当前物品是否被抛起
	 * @param stack
	 * @return
	 */
	boolean isQTEinProgress(ItemStack stack);
	/**
	 * 获取进度。0.0为刚刚抛弃，1.0为落下前瞬间。
	 * @param stack
	 * @return
	 */
	float getQTEProgress(ItemStack stack);
	
	/**
	 * 获取容许的进度误差区间。
	 * @return
	 */
	Pair<Float, Float> getAcceptedRange();
	
}
