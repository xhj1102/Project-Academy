/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.ability.test;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
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
		System.out.println("Component onButtonDown in " + world.isRemote);
	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.system.AbilityComponent#onButtonUp(net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, cn.misaka.ability.system.PlayerAbilityData, int, cn.misaka.ability.system.AbilityClass.ControlStat)
	 */
	@Override
	protected void onButtonUp(EntityPlayer player, World world,
			PlayerAbilityData data, int keyID, ControlStat stat) {
		System.out.println("Component onButtonUp in " + world.isRemote);
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
		return null;
	}

	@Override
	public String getComponentName() {
		// TODO Auto-generated method stub
		return "Test Component";
	}

}
