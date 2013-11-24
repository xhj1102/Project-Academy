/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cn.liutils.api.entity.EntityBullet;
import cn.liutils.api.util.Motion3D;

/**
 * @author WeAthFolD
 *
 */
public class EntityMdRay extends EntityBullet {
	
	/**
	 * @param par1World
	 * @param ent
	 * @param target
	 * @param dmg
	 */
	public EntityMdRay(World par1World, Entity ent, Entity target, int dmg) {
		super(par1World, ent, target, dmg);
	}

	/**
	 * @param world
	 */
	public EntityMdRay(World world) {
		super(world);
	}
	
	public EntityMdRay(World world, Vec3 begin, Vec3 motion, int dmg) {
		super(world, begin, motion, dmg);
	}
	
	@Override
	public void onUpdate() {
		if(!worldObj.isRemote)
			super.onUpdate();
		else if(++ticksExisted > 25) this.setDead();
	}
	
	@Override
	protected float func_70182_d() {
		return 15.0F;
	}
	
	protected void doBlockCollision(MovingObjectPosition result) {
		worldObj.destroyBlock(result.blockX, result.blockY, result.blockZ, rand.nextBoolean());
		setDead();
	}
	
	public void doEntityCollision(MovingObjectPosition result) {
		super.doEntityCollision(result);
		setDead();
	}
}
