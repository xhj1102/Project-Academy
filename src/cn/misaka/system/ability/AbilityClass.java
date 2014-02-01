/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.ability;

import java.util.ArrayList;
import java.util.List;

import cn.liutils.api.util.GenericUtils;

/**
 * 纯纯的存放哟*具体内容在AbilitySkill实现，而AbilityLevel则提供技能是否可用的信息。
 * @author WeAthFolD
 *
 */
public abstract class AbilityClass {

	protected List<AbilitySkill> skillList = new ArrayList();
	protected List<AbilityLevel> levelList = new ArrayList();
	
	/**
	 * 
	 */
	public AbilityClass() {
	}
	
	
	public abstract String getAbilityName();
	
	public final AbilityLevel getAbilityLevel(int level) {
		return GenericUtils.safeFetchFrom(levelList, level);
	}
	
	public final AbilitySkill getAbilitySkill(int id) {
		return GenericUtils.safeFetchFrom(skillList, id);
	}
	
}
