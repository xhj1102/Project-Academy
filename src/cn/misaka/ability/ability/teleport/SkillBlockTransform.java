/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.ability.teleport;

import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cn.misaka.system.ability.AbilityClass;
import cn.misaka.system.ability.AbilitySkill;
import cn.misaka.system.client.render.SkillRender;
import cn.misaka.system.control.PlayerControlStat;
import cn.misaka.system.data.PlayerAbilityData;

/**
 * @author WeAthFolD
 *
 */
public class SkillBlockTransform extends AbilitySkill {

	/**
	 * 
	 */
	public SkillBlockTransform(AbilityClass cls, int id) {
		super(cls, id);
	}

	/* (non-Javadoc)
	 * @see cn.misaka.system.ability.AbilitySkill#onButtonTick(net.minecraft.world.World, cn.misaka.system.data.PlayerAbilityData, int, cn.misaka.system.control.PlayerControlStat, boolean)
	 */
	@Override
	public boolean onButtonTick(World world, PlayerAbilityData player,
			int keyID, PlayerControlStat stat, boolean isEmptyHand) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see cn.misaka.system.ability.AbilitySkill#onButtonDown(net.minecraft.world.World, cn.misaka.system.data.PlayerAbilityData, int, cn.misaka.system.control.PlayerControlStat, boolean)
	 */
	@Override
	public boolean onButtonDown(World world, PlayerAbilityData player,
			int keyID, PlayerControlStat stat, boolean isEmptyHand) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see cn.misaka.system.ability.AbilitySkill#onButtonUp(net.minecraft.world.World, cn.misaka.system.data.PlayerAbilityData, int, cn.misaka.system.control.PlayerControlStat, boolean)
	 */
	@Override
	public boolean onButtonUp(World world, PlayerAbilityData player, int keyID,
			PlayerControlStat stat, boolean isEmptyHand) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see cn.misaka.system.ability.AbilitySkill#getMaxKeys()
	 */
	@Override
	public int getMaxKeys() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see cn.misaka.system.ability.AbilitySkill#getDescriptionForKey(int)
	 */
	@Override
	public String getDescriptionForKey(int keyID) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.misaka.system.ability.AbilitySkill#getSkillRenderer()
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public SkillRender getSkillRenderer() {
		// TODO Auto-generated method stub
		return null;
	}

}
