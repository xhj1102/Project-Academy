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
package cn.misaka.support.entity;

import cn.liutils.api.util.Motion3D;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * 原子崩坏的光球，补完中。
 * @author WeAthFolD
 */
public class EntityMdBall extends Entity {
	
	private static final double 
		/**
		 * 光球离玩家距离（每个轴）的最大值。
		 */
		MAX_DISPERSE_RAD = 0.07,
		/**
		 * 光球随机运动（其实是正弦）的最大范围
		 */
		MAX_MOVING_RAD = 0.5,
		FRONT_DST = 0.6;
	
	/**
	 * 最大充能时间
	 */
	public int chargeTime;
	
	/**
	 * 光球最大大小
	 */
	public float maxSize;
	
	/**
	 * 光球当前大小
	 */
	public float currentSize;
	
	/**
	 * 光球充能的时间
	 */
	public int chargeTick;
	
	/**
	 * 生命期长度
	 */
	public final int lifeTime;
	
	public static final int 
		GEN_FRAMES = 4, //生成帧数
		STB_FRAMES = 5; //稳定状态帧数
	
	public int curFrameID;
	
	/**
	 * 想对玩家位置的位移
	 */
	private double
		relatX,
		relatY,
		relatZ;
	
	private double
		oriX,
		oriY,
		oriZ;
	
	private int phaseOffset;
	
	private EntityPlayer thePlayer;
	
	private Motion3D motion;
	
	public EntityMdBall(World world, EntityPlayer player, int chgTime, float size, int life) {
		super(world);
		thePlayer = player;
		chargeTime = chgTime;
		maxSize = size;
		currentSize = 0.5F * maxSize;
		motion = new Motion3D(player, true);
		lifeTime = life;
		this.setSize(size, size);
		oriX = MAX_MOVING_RAD * (rand.nextDouble() - 1D);
		oriY = MAX_MOVING_RAD * (rand.nextDouble() - 1D);
		oriZ = MAX_MOVING_RAD * (rand.nextDouble() - 1D);
		phaseOffset = rand.nextInt(200);
		setPosition();
		System.out.println("Spawned in server");
	}

	/**
	 * 客户端构造器
	 */
	public EntityMdBall(World world) {
		super(world);
		maxSize = chargeTime = 0;
		lifeTime = 0;
		System.out.println("Spawned in client");
	}
	
	@Override
	public void onUpdate() {
		++ticksExisted;
		if(!worldObj.isRemote) {
			if(ticksExisted >= lifeTime) {
				this.setDead();
				return;
			}
			
			//setPosition();
			
			if(thePlayer == null) {
				setDead();
				return;
			}
			
			if(isCharging()) {
				++chargeTick;
			}
			
			dataWatcher.updateObject(5, chargeTick);
			dataWatcher.updateObject(6, chargeTime);
			dataWatcher.updateObject(7, maxSize);
		} else {
			if(isCharging()) {
				++chargeTick;
				currentSize = maxSize * ((0.5F * chargeTick / chargeTime) + 0.5F);
				curFrameID = rand.nextInt(GEN_FRAMES);
			} else {
				currentSize = this.maxSize;
				curFrameID = rand.nextInt(STB_FRAMES);
			}
			
			chargeTick = dataWatcher.getWatchableObjectInt(5);
			chargeTime = dataWatcher.getWatchableObjectInt(6);
			maxSize = dataWatcher.getWatchableObjectFloat(7);
		}
	}
	
	private void setPosition() {
		//更新相对位置
		int phi = ticksExisted + phaseOffset;
		relatX = MAX_DISPERSE_RAD * Math.sin(phi/40F * Math.PI);
		relatY = MAX_DISPERSE_RAD * Math.cos(phi/40F * Math.PI);
		relatZ = MAX_DISPERSE_RAD * Math.sin((phi + 20)/40F * Math.PI);
		
		motion.update(thePlayer, true);
		motion.move(1);
		
		//放置到玩家前方
		motion.posX += relatX + oriX;
		motion.posY += relatY + oriY;
		motion.posZ += relatZ + oriZ;
		
		motion.applyToEntity(this);
	}

	public boolean isCharging() {
		return chargeTick < chargeTime;
	}
	
	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(5, Integer.valueOf(0));
		this.dataWatcher.addObject(6, Integer.valueOf(0));
		this.dataWatcher.addObject(7, Float.valueOf(0));
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound var1) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound var1) {
	}

}
