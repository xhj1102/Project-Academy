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
 * @author WeAthFolD
 * 
 */
public class EntityMeltdowner extends EntityThrowable {

	int lifeTime = rand.nextInt(7) + 20;
	int damage = 5;
	public float alpha = 0;
	public float size = rand.nextFloat() * 0.5F + 0.5F; // 0.5F-1.0F
	protected EntityLivingBase thrower;
	protected boolean isDead = false;

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
		Vec3 lookVec = lb.getLookVec();
		this.setPosition(posX + rand.nextDouble() * 3.0 - 1.5 + lookVec.xCoord
				* 2.0, posY + rand.nextDouble() * 3.0 - 1.5 + lookVec.yCoord
				* 2.0, posZ + rand.nextDouble() * 3.0 - 1.5 + lookVec.zCoord
				* 2.0);
		this.damage = damage;
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
			if(thrower != null)
				dataWatcher.updateObject(10, Integer.valueOf(thrower.entityId));
			//else setDead();
			
			if (ticksExisted <= lifeTime) {
				this.posX += 0.05 * MathHelper.sin(ticksExisted / 20.0F);
				this.posY += 0.05 * MathHelper.cos(ticksExisted / 20.0F);
				this.posZ += 0.05 * MathHelper.sin(ticksExisted / 30.0F
						+ (float) Math.PI);
			}
		} else {
			if (thrower == null) {
				Entity e = worldObj.getEntityByID(dataWatcher
						.getWatchableObjectInt(10));
				if (e != null)
					this.thrower = (EntityLivingBase) e;
			}
		}
		
	
		
		if(ticksExisted > lifeTime) {
			if (thrower != null && !isDead) {
				Vec3 posVec = worldObj.getWorldVec3Pool().getVecFromPool(posX, posY, posZ);
				Motion3D playerMo = new Motion3D(thrower, true);
				Vec3 vec0 = playerMo.asVec3(worldObj), vec1 = playerMo.move(30.0).asVec3(worldObj);
				MovingObjectPosition pos = worldObj.clip(vec0, vec1);
				Vec3 res = pos == null ? vec1 : pos.hitVec;
				Vec3 moVec = worldObj.getWorldVec3Pool().getVecFromPool(res.xCoord - posVec.xCoord, res.yCoord - posVec.yCoord, res.zCoord - posVec.zCoord);
				worldObj.spawnEntityInWorld(new EntityMdRay(worldObj, posVec,  moVec, damage));
				isDead = true;
			}
			if(isDead && alpha > 0F)
				alpha -= 0.25F;
			if(ticksExisted > lifeTime + 10)
				setDead();
		} else if(ticksExisted <= 5) {
			alpha = 0.2F * ticksExisted;
		} else alpha = 1.0F;
		 
		super.onUpdate();
	}

	/*
	 * (non-Javadoc)
	 */
	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {

	}

	protected float func_70182_d() {
		return 0.05F;
	}

	protected float getGravityVelocity() {
		return 0.00F;
	}

}
