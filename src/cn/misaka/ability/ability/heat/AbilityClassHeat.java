/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.ability.heat;

import cn.misaka.ability.system.AbilityClass;

/**
 * @author WeAthFolD
 *
 */
public class AbilityClassHeat extends AbilityClass {

	/**
	 * 
	 */
	public AbilityClassHeat() {
		for(int i = 0; i < 5; i++)
			components.add(new CompHeat1(this, 0));
	}

	/* (non-Javadoc)
	 * @see cn.misaka.ability.system.AbilityClass#getAbilityName()
	 */
	@Override
	public String getAbilityName() {
		return "Heat";
	}

}
