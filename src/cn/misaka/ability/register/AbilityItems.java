/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.register;

import cn.liutils.core.register.Config;
import cn.liutils.core.register.ConfigHandler;
import cn.misaka.ability.item.ItemSwordEnchanted_Fire;

/**
 * @author WeAthFolD
 *
 */
public class AbilityItems {

	public static ItemSwordEnchanted_Fire swordEnch_fire;
	
	public static void init(Config conf) {
		swordEnch_fire = new ItemSwordEnchanted_Fire(ConfigHandler.getItemId(conf, "swordEnch_fire", 1));
	}

}
