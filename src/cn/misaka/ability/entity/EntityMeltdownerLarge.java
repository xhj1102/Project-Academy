/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.entity;

import cn.liutils.api.util.Motion3D;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * @author WeAthFolD
 *
 */
public class EntityMeltdownerLarge extends EntityMeltdowner {

	/**
	 * @param par1World
	 */
	public EntityMeltdownerLarge(World par1World) {
		super(par1World);
	}

	/**
	 * @param par1World
	 * @param lb
	 * @param damage
	 */
	public EntityMeltdownerLarge(World par1World, EntityLivingBase lb,
			int damage) {
		super(par1World, lb, damage);
		this.lifeTime = 300;
		//TODO：重置offset
	}
	


}
