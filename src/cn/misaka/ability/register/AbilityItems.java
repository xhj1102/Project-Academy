/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.register;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cn.liutils.core.register.Config;
import cn.liutils.core.register.ConfigHandler;
import cn.misaka.ability.item.ItemAbilityVoid;

/**
 * @author WeAthFolD
 *
 */
public class AbilityItems {

	public static ItemAbilityVoid abilityVoid;
	
	public static void init(Config conf) {
		abilityVoid = new ItemAbilityVoid(ConfigHandler.getItemId(conf, "abilityVoid", 0));
		
		LanguageRegistry.addName(abilityVoid, "Ability Void");
	}

}
