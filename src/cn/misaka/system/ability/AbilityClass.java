/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.ability;

import java.util.ArrayList;
import java.util.List;

import cn.liutils.api.util.GenericUtils;

/**
 * 能力系类。
 * 具体内容在AbilitySkill实现，而AbilityLevel则提供技能是否可用的信息。
 * @author WeAthFolD
 *
 */
public abstract class AbilityClass {

	/**
	 * 所有的技能列表。
	 */
	protected List<AbilitySkill> skillList = new ArrayList();
	
	/**
	 * 所有的等级列表。
	 */
	protected List<AbilityLevel> levelList = new ArrayList();
	
	public AbilityClass() { }
	
	/**
	 * 获得当前能力的名称。
	 * @return
	 */
	public abstract String getAbilityName();
	
	/**
	 * 获取该能力类对应的等级类。
	 * @param level
	 * @return
	 */
	public final AbilityLevel getAbilityLevel(int level) {
		return GenericUtils.safeFetchFrom(levelList, level);
	}
	
	/**
	 * 获得skillID对应的技能类。
	 * @param id 技能ID
	 * @return
	 */
	public final AbilitySkill getAbilitySkill(int id) {
		return GenericUtils.safeFetchFrom(skillList, id);
	}
	
}
