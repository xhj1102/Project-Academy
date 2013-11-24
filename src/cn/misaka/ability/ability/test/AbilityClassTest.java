/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.ability.test;

import cn.misaka.ability.ability.misc.CompMeltDowner;
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
		components.add(new CompMeltDowner(this, 0));
		for(int i = 1; i < 5; i++)
			components.add(new AbilityComponentTest1(this, i));
	}

	@Override
	public String getAbilityName() {
		return "Test";
	}

}
