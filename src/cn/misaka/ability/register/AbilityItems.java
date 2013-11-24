/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.register;

import net.minecraft.item.EnumToolMaterial;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cn.liutils.core.register.Config;
import cn.liutils.core.register.ConfigHandler;
import cn.misaka.ability.item.ItemSwordEnchanted_Fire;
import cn.misaka.ability.system.item.ItemAbilityVoid;

/**
 * @author WeAthFolD
 *
 */
public class AbilityItems {

	public static ItemAbilityVoid abilityVoid;
	public static ItemSwordEnchanted_Fire swordEnch_fire;
	
	public static void init(Config conf) {
		abilityVoid = new ItemAbilityVoid(ConfigHandler.getItemId(conf, "abilityVoid", 0));
		swordEnch_fire = new ItemSwordEnchanted_Fire(ConfigHandler.getItemId(conf, "swordEnch_fire", 1));
		LanguageRegistry.addName(abilityVoid, "Ability Void");
	}

}
