/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.ability.test;

import net.minecraft.entity.player.EntityPlayer;
import cn.misaka.system.ability.AbilityClass;
import cn.misaka.system.ability.AbilityLevel;
import cn.misaka.system.ability.AbilitySkill;

/**
 * @author WeAthFolD
 *
 */
public class AbilityLevelTest extends AbilityLevel {

	/**
	 * @param thclass
	 */
	public AbilityLevelTest(AbilityClass thclass) {
		super(thclass);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.misaka.system.ability.AbilityLevel#isSkillAvailable(net.minecraft.entity.player.EntityPlayer, cn.misaka.system.ability.AbilitySkill, int)
	 */
	@Override
	public boolean isSkillAvailable(EntityPlayer player, AbilitySkill skill,
			int id) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see cn.misaka.system.ability.AbilityLevel#useCustomKeyset()
	 */
	@Override
	public boolean useCustomKeyset() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see cn.misaka.system.ability.AbilityLevel#getSkillForKey(int)
	 */
	@Override
	public int[] getSkillForKey(int keyID) {
		// TODO Auto-generated method stub
		return new int[] { 0, keyID == 0 ? 0 : 1 };
	}

	@Override
	public boolean isSkillActivatable(EntityPlayer player, AbilitySkill skill,
			int id) {
		return true;
	}

}
