/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author WeAthFolD
 *
 */
public class EntityPlayerIndicator extends Entity {
	
	protected EntityPlayer associatedPlayer;

	/**
	 * @param par1World
	 */
	public EntityPlayerIndicator(World par1World) {
		super(par1World);
	}
	
	public EntityPlayerIndicator(World par1World, EntityPlayer entity) {
		super(par1World);
		associatedPlayer = entity;
		setPosition(entity.posX, entity.posY, entity.posZ);
	}

	@Override
	protected void entityInit() {
		this.setSize(1.0F, 2.0F);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		//DO NOTHING
		setDead();
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		//DO NOTHING
	}

}
