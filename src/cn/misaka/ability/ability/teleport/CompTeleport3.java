/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.ability.teleport;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cn.liutils.api.util.BlockPos;
import cn.misaka.ability.system.AbilityClass;
import cn.misaka.ability.system.AbilityClass.ControlStat;
import cn.misaka.ability.system.AbilityComponent;
import cn.misaka.ability.system.PlayerAbilityData;
import cn.misaka.ability.system.client.system.AbilityRender;

/**
 * @author WeAthFolD
 *
 */
public class CompTeleport3 extends AbilityComponent {

	public static final int KEY_SELECT = 3, KEY_FIREBLOCK = 0;
	
	private HashMap<EntityPlayer, BlockPos> selects = new HashMap(); //玩家的当前方块选择对应表
	
	/**
	 * @param base
	 */
	public CompTeleport3(AbilityClass base) {
		super(base);
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
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.system.AbilityComponent#getClientRender()
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public AbilityRender getClientRender() {
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.system.AbilityComponent#getComponentName()
	 */
	@Override
	public String getComponentName() {
		// TODO Auto-generated method stub
		return null;
	}

}
