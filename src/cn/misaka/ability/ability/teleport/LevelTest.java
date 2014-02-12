/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.ability.teleport;

import net.minecraft.entity.player.EntityPlayer;
import cn.misaka.system.ability.AbilityClass;
import cn.misaka.system.ability.AbilityLevel;
import cn.misaka.system.ability.AbilitySkill;

/**
 * @author WeAthFolD
 *
 */
public class LevelTest extends AbilityLevel {

	/**
	 * @param thclass
	 */
	public LevelTest(AbilityClass thclass) {
		super(thclass);
	}

	@Override
	public boolean isSkillAvailable(EntityPlayer player, AbilitySkill skill,
			int id) {
		return true;
	}

	@Override
	public boolean isSkillActivatable(EntityPlayer player, AbilitySkill skill,
			int id) {
		return true;
	}

	@Override
	public boolean useCustomKeyset() {
		return true;
	}

	@Override
	public int[] getSkillForKey(int keyID) {
		switch(keyID) {
		case 0:
			return new int[] { 0, 1 };
		case 1:
			return new int[] { 0, 0 };
		default:
			return null;
		}
	}

}
