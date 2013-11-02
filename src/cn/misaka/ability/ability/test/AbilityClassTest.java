/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.ability.test;

import cn.misaka.ability.system.AbilityClass;

/**
 * @author WeAthFolD
 *
 */
public class AbilityClassTest extends AbilityClass {

	/**
	 * 
	 */
	public AbilityClassTest() {
		for(int i = 0; i < 5; i++)
			components.add(new AbilityComponentTest1(this));
	}

	@Override
	public String getAbilityName() {
		return "Test Ability";
	}

}
