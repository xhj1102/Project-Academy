/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.ability.teleport;

import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cn.misaka.ability.entity.EntityTeleportIndicator;
import cn.misaka.system.ability.AbilityClass;
import cn.misaka.system.ability.AbilitySkill;
import cn.misaka.system.client.render.SkillRender;
import cn.misaka.system.control.PlayerControlStat;
import cn.misaka.system.data.PlayerAbilityData;
import cn.misaka.system.proxy.CommonProxy;

/**
 * @author WeAthFolD
 *
 */
public class SkillPlayerTransform extends AbilitySkill {

	public SkillPlayerTransform(AbilityClass cls, int id) {
		super(cls, id);
	}

	@Override
	public boolean onButtonTick(World world, PlayerAbilityData player,
			int keyID, PlayerControlStat stat, boolean isEmptyHand) {
		return true;
	}

	@Override
	public boolean onButtonDown(World world, PlayerAbilityData player,
			int keyID, PlayerControlStat stat, boolean isEmptyHand) {
		if(world.isRemote) return false;
		
		if(keyID == 0) {
			//TODO:生成辅助实体
			world.spawnEntityInWorld(new EntityTeleportIndicator(world, player));
			return true;
		}
		return false;
	}

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
	/**
	 * key0: 指示此能力已被激活的按键。
	 */
	public int getMaxKeys() {
		return 2;
	}

	/* (non-Javadoc)
	 * @see cn.misaka.system.ability.AbilitySkill#getDescriptionForKey(int)
	 */
	@Override
	public String getDescriptionForKey(int keyID) {
		return StatCollector.translateToLocal("ability.teleport0.desc");
	}

	/* (non-Javadoc)
	 * @see cn.misaka.system.ability.AbilitySkill#getSkillRenderer()
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public SkillRender getSkillRenderer() {
		return null;
	}

}
