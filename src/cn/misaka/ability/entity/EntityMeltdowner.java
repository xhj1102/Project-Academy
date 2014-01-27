/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.entity;

import cn.liutils.api.util.EntityUtils;
import cn.liutils.api.util.Motion3D;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * 原子崩坏的普通光球，挖矿能力激高哦~~
 * @author WeAthFolD
 * 
 */
public class EntityMeltdowner extends EntityThrowable {

	int lifeTime = rand.nextInt(7) + 40;
	int damage = 5;
	public float alpha = 0;
	public float size = rand.nextFloat() * 0.5F + 0.5F; // 0.5F-1.0F
	protected EntityLivingBase thrower;
	protected boolean isDead = false;

	double xOffset, yOffset, zOffset; // 相对于玩家的xyz偏移量。

	/**
	 * @param par1World
	 */
	public EntityMeltdowner(World par1World) {
		super(par1World);
	}

	@Override
	protected void entityInit() {
		dataWatcher.addObject(10, Integer.valueOf(0));
	}

	/**
	 * @param par1World
	 * @param lb
	 */
	public EntityMeltdowner(World par1World, EntityLivingBase lb, int damage) {
		super(par1World, lb);
		thrower = lb;
		xOffset = rand.nextDouble() * 2.0 - 1.0;
		yOffset = rand.nextDouble() * 1.4 + 0.8;
		zOffset = rand.nextDouble() * 2.0 - 1.0;
		ticksExisted += rand.nextInt(30); //为了位移调整的方便
		setPositionToPlayer();
		this.damage = damage;
	}

	/**
	 * 让原子球围绕在玩家周围，并且在1st Person可以看见。
	 */
	private void setPositionToPlayer() {
		Vec3 lookVec = thrower.getLookVec();
		this.setPosition(thrower.posX + xOffset + lookVec.xCoord * 2.0,
				thrower.posY + yOffset + lookVec.yCoord * 2.0, thrower.posZ
						+ zOffset + lookVec.zCoord * 2.0);
	}

	/**
	 * @param par1World
	 * @param par2
	 * @param par4
	 * @param par6
	 */
	public EntityMeltdowner(World par1World, double par2, double par4,
			double par6) {
		super(par1World, par2, par4, par6);
	}

	@Override
	public void onUpdate() {

		if (!worldObj.isRemote) {
			if (thrower != null) {
				dataWatcher.updateObject(10, Integer.valueOf(thrower.entityId));
				// else setDead();
				setPositionToPlayer();
			} else setDead();
		} else {
			if (thrower == null) {
				Entity e = worldObj.getEntityByID(dataWatcher
						.getWatchableObjectInt(10));
				if (e != null)
					this.thrower = (EntityLivingBase) e;
			}
			
			if (ticksExisted <= lifeTime) {
				this.xOffset += 0.1 * MathHelper.sin(ticksExisted / 7.0F); //要积分的节奏
				this.yOffset += 0.1 * MathHelper.cos(ticksExisted / 8.0F);
				this.zOffset += 0.1 * MathHelper.sin(ticksExisted / 9.0F + (float) Math.PI);
			}
			
		}

		if (ticksExisted > lifeTime) {
			if (thrower != null && !isDead) {
				noticeSetoff();
				isDead = true;
			}
			if (isDead && alpha > 0F)
				alpha -= 0.25F;
			if (ticksExisted > lifeTime + 10)
				setDead();
		} else if (ticksExisted <= 5) {
			alpha = 0.2F * ticksExisted;
		} else
			alpha = 1.0F;

		super.onUpdate();
	}
	
	protected void setOutAttack(Vec3 posVec, Vec3 moVec) {
		worldObj.spawnEntityInWorld(new EntityMdRay(worldObj, posVec, moVec, damage));
	}
	
	public void noticeSetoff() {
		Vec3 posVec = worldObj.getWorldVec3Pool().getVecFromPool(posX,
				posY, posZ);
		Motion3D playerMo = new Motion3D(thrower, true);
		Vec3 vec0 = playerMo.asVec3(worldObj), vec1 = playerMo.move(
				30.0).asVec3(worldObj);
		MovingObjectPosition pos = worldObj.clip(vec0, vec1);
		Vec3 res = pos == null ? vec1 : pos.hitVec;
		Vec3 moVec = worldObj.getWorldVec3Pool().getVecFromPool(
				res.xCoord - posVec.xCoord, res.yCoord - posVec.yCoord,
				res.zCoord - posVec.zCoord);
		setOutAttack(posVec, moVec);
		isDead = true;
	}

	/*
	 * (non-Javadoc)
	 */
	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {

	}

	protected float func_70182_d() {
		return 0.00F;
	}

	protected float getGravityVelocity() {
		return 0.00F;
	}

}
