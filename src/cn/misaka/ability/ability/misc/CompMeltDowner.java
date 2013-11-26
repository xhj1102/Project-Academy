/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.ability.misc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cn.misaka.ability.ability.test.AbilityRenderDefault;
import cn.misaka.ability.client.render.ability.misc.RenderMeltdowner;
import cn.misaka.ability.entity.EntityMeltdowner;
import cn.misaka.ability.system.AbilityClass;
import cn.misaka.ability.system.AbilityClass.ControlStat;
import cn.misaka.ability.system.AbilityComponent;
import cn.misaka.ability.system.PlayerAbilityData;
import cn.misaka.ability.system.client.system.AbilityRender;

/**
 * @author WeAthFolD
 *
 */
public class CompMeltDowner extends AbilityComponent {

	/**
	 * @param base
	 * @param lvl
	 */
	public CompMeltDowner(AbilityClass base, int lvl) {
		super(base, lvl);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.system.AbilityComponent#onAbilityTick(net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, cn.misaka.ability.system.PlayerAbilityData, cn.misaka.ability.system.AbilityClass.ControlStat)
	 */
	@Override
	protected void onAbilityTick(EntityPlayer player, World world,
			PlayerAbilityData data, ControlStat stat) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.system.AbilityComponent#onButtonDown(net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, cn.misaka.ability.system.PlayerAbilityData, int, cn.misaka.ability.system.AbilityClass.ControlStat)
	 */
	@Override
	protected void onButtonDown(EntityPlayer player, World world,
			PlayerAbilityData data, int keyID, ControlStat stat) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.system.AbilityComponent#onButtonUp(net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, cn.misaka.ability.system.PlayerAbilityData, int, cn.misaka.ability.system.AbilityClass.ControlStat)
	 */
	@Override
	protected void onButtonUp(EntityPlayer player, World world,
			PlayerAbilityData data, int keyID, ControlStat stat) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.system.AbilityComponent#onButtonTick(net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, cn.misaka.ability.system.PlayerAbilityData, int, int)
	 */
	@Override
	protected void onButtonTick(EntityPlayer player, World world,
			PlayerAbilityData data, int keyID, int ticks) {
		if(keyID == 0) {
			if(!world.isRemote) {
				if(ticks % 15 == 0) {
					world.spawnEntityInWorld(new EntityMeltdowner(world, player, 10));
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.system.AbilityComponent#getClientRender()
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public AbilityRender getClientRender() {
		return new RenderMeltdowner();
	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.system.AbilityComponent#getComponentName()
	 */
	@Override
	public String getComponentName() {
		return "acamod.comp.melt_downer.name";
	}

}
