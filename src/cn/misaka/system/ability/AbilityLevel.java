/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.ability;

import cn.misaka.system.data.AbilityControlData;
import net.minecraft.entity.player.EntityPlayer;

/**
 * 能力等级类。提供了当前等级能力的相关可用信息。
 * @author WeAthFolD
 *
 */
public abstract class AbilityLevel {

	/**
	 * 该能力等级所属能力类。
	 */
	AbilityClass baseClass;
	
	/**
	 * 仅在使用自动分配键位时被使用。这个ControlData代表了默认的该等级能力键位绑定。
	 */
	AbilityControlData preset = null;
	
	/**
	 * 喜闻乐见的构造器。
	 */
	public AbilityLevel(AbilityClass thclass) {
		baseClass = thclass;
	}
	
	/**
	 * 确认该技能是否被激活。
	 * @param player
	 * @param skill
	 * @param id
	 * @return
	 */
	public abstract boolean isSkillAvailable(EntityPlayer player, AbilitySkill skill, int id);
	
	/**
	 * 确认该技能是否可以在该等级被激活。
	 * @param player
	 * @param skill
	 * @param id
	 * @return
	 */
	public abstract boolean isSkillActivatable(EntityPlayer player, AbilitySkill skill, int id);
	
	/**
	 * 是否使用自定义的键位分布。一般在0~3为true，3~5为false.
	 * @return
	 */
	public abstract boolean useCustomKeyset();
	
	/**
	 * 获取一个键位对应的skillID，仅在非自动分配键位时使用。
	 * @param keyID
	 * @return [0]skillID
	 *         [1]映射到的keyID
	 */
	public abstract int[] getSkillForKey(int keyID);
	

}
