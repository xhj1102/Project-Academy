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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cn.liutils.api.util.GenericUtils;
import cn.liutils.api.util.PlayerPositionLock;
import cn.misaka.ability.api.APDataMain;
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.ability.api.control.PlayerControlStat;
import cn.misaka.ability.api.control.SkillControlStat;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.core.proxy.APClientProps;

/**
 * @author WeAthFolD
 *
 */
public class SkillMoving extends AbilitySkill {

	public SkillMoving(int id) {
		super("skill.elec.moving", id);
	}

	@Override
	public ResourceLocation getLogo() {
		return APClientProps.ELEC_MOVE;
	}

	@Override
	public int getMaxKeys() {
		return 1;
	}
	
	@Override
	public int getSuggestKey(int skillKeyID) {
		return 3;
	}
	
	@Override
	public boolean onSkillTick(World world, EntityPlayer player, SkillControlStat stat, PlayerControlStat mctrl) {
		NBTTagCompound nbt = player.getEntityData();
		PlayerData data = APDataMain.loadPlayerData(player);
		if(PlayerPositionLock.isPlayerLocked(player)) {
			double velocity = nbt.getDouble("elec_vel");
			int x = nbt.getInteger("elec_x"),
				y = nbt.getInteger("elec_y"),
				z = nbt.getInteger("elec_z");
			velocity += getAccel(player, data.getSkillExp(skillID), x, y, z);
			nbt.setDouble("elec_vel", velocity);
			System.out.println("MOVE " + world.isRemote + " to " + x + ", " + y + ", " + z + " VEL=" + velocity);
			double dx = x + .5 - player.posX,
				   dy = y + .5 - player.posY,
				   dz = z + .5 - player.posZ;
			Vec3 vec3 = Vec3.createVectorHelper(dx, dy, dz).normalize();
			vec3 = GenericUtils.multiply(vec3, velocity);
			if(Math.abs(vec3.xCoord) > Math.abs(dx)) vec3.xCoord = dx;
			if(Math.abs(vec3.yCoord) > Math.abs(dy)) vec3.yCoord = dy;
			if(Math.abs(vec3.zCoord) > Math.abs(dz)) vec3.zCoord = dz;
			
			PlayerPositionLock.applyVelocity(player, vec3);
		}
		return true;
	}
	
	/**
	 * 当按键状况被改变时调用（按下或放开）
	 * @param world
	 * @param player
	 * @param stat
	 * @param kid 状态发生改变的键位id
	 * @param mctrl
	 */
	@Override
	public void onKeyStateChange(World world, EntityPlayer player, SkillControlStat stat, int kid, PlayerControlStat mctrl) {
		if(!world.isRemote) return;
		NBTTagCompound nbt = player.getEntityData();
		PlayerData data = APDataMain.loadPlayerData(player);
		if(stat.isKeyDown(0)) {
			MovingObjectPosition pos = player.rayTrace(calculateMaxDist(data.getSkillExp(skillID)), 1.0F);
			if(pos != null && pos.typeOfHit == MovingObjectType.BLOCK) {
				if(isBlockApplicable(world, pos.blockX, pos.blockY, pos.blockZ)) {
					nbt.setInteger("elec_x", pos.blockX);
					nbt.setInteger("elec_y", pos.blockY);
					nbt.setInteger("elec_z", pos.blockZ);
					nbt.setDouble("elec_vel", 0D);
					PlayerPositionLock.lockPlayer(player);
				}
			}
		} else {
			PlayerPositionLock.unlockPlayer(player);
		}
	}
	
	private boolean isBlockApplicable(World world, int x, int y, int z) {
		return true;
		//Block block = world.getBlock(x, y, z);
		//String str = block.getUnlocalizedName();
		//return str.contains("iron") || str.contains("steel") || str.contains("nik");
	}
	
	private double getAccel(EntityPlayer player, float exp, int x, int y, int z) {
		double dist = player.getDistanceSq(x + .5, y + .5, z + .5);
		return getForceFactor(exp) / dist;
	}
	
	private float calculateMaxDist(float exp) {
		return 10.0F + exp * 3F;
	}
	
	private float getForceFactor(float exp) {
		return 10 * (0.05F + exp * 0.01F);
	}

}
