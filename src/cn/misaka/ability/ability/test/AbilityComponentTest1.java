/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.ability.test;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cn.misaka.ability.client.system.AbilityRender;
import cn.misaka.ability.system.AbilityClass;
import cn.misaka.ability.system.AbilityClass.ControlStat;
import cn.misaka.ability.system.AbilityComponent;
import cn.misaka.ability.system.PlayerAbilityData;

/**
 * @author WeAthFolD
 *
 */
public class AbilityComponentTest1 extends AbilityComponent {
	
	/**
	 * @param base
	 */
	public AbilityComponentTest1(AbilityClass base) {
		super(base);
	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.system.AbilityComponent#onAbilityTick(net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, cn.misaka.ability.system.PlayerAbilityData, cn.misaka.ability.system.AbilityClass.ControlStat)
	 */
	@Override
	protected void onAbilityTick(EntityPlayer player, World world,
			PlayerAbilityData data, ControlStat stat) {

	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.system.AbilityComponent#onButtonDown(net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, cn.misaka.ability.system.PlayerAbilityData, int, cn.misaka.ability.system.AbilityClass.ControlStat)
	 */
	@Override
	protected void onButtonDown(EntityPlayer player, World world,
			PlayerAbilityData data, int keyID, ControlStat stat) {
		if(!world.isRemote) {
			if(keyID == 0) {
				Vec3 lookVec = player.getLookVec();
				double s = 4.0;
				EntityLargeFireball entity = new EntityLargeFireball(world, player, 
						s * lookVec.xCoord, s * lookVec.yCoord, s * lookVec.zCoord);
				entity.setPosition(player.posX + lookVec.xCoord, player.posY + lookVec.yCoord + 0.2,
						player.posZ + lookVec.zCoord);
				world.spawnEntityInWorld(entity);
			} else {}
		}
		if(keyID == 1) { //愉快的瞬移
			MovingObjectPosition mop = player.rayTrace(30D, 1.0F);
			if(mop != null) {
				double x = mop.hitVec.xCoord, y = mop.hitVec.yCoord + 1.5, z = mop.hitVec.zCoord;
				if(mop.typeOfHit == EnumMovingObjectType.TILE) {
					ForgeDirection dir = ForgeDirection.values()[mop.sideHit];
					x += dir.offsetX * 1.0;
					y += dir.offsetY * 1.5;
					z += dir.offsetZ * 1.0;
				}
				player.setPosition(x, y, z);
			}
		}
	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.system.AbilityComponent#onButtonUp(net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, cn.misaka.ability.system.PlayerAbilityData, int, cn.misaka.ability.system.AbilityClass.ControlStat)
	 */
	@Override
	protected void onButtonUp(EntityPlayer player, World world,
			PlayerAbilityData data, int keyID, ControlStat stat) {
	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.system.AbilityComponent#onButtonTick(net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, cn.misaka.ability.system.PlayerAbilityData, int, int)
	 */
	@Override
	protected void onButtonTick(EntityPlayer player, World world,
			PlayerAbilityData data, int keyID, int ticks) {
	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.system.AbilityComponent#getClientRender()
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public AbilityRender getClientRender() {
		return new AbilityRenderTest1();
	}

	@Override
	public String getComponentName() {
		return "Test Component";
	}

}
