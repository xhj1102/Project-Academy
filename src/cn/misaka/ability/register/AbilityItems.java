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
	public static ItemSwordEnchanted_Fire swordEnch_fire_wood, swordEnch_fire_stone, swordEnch_fire_iron,
	swordEnch_fire_gold, swordEnch_fire_diamond;
	
	public static void init(Config conf) {
		abilityVoid = new ItemAbilityVoid(ConfigHandler.getItemId(conf, "abilityVoid", 0));
		swordEnch_fire_wood = new ItemSwordEnchanted_Fire(ConfigHandler.getItemId(conf, "swordEnch_fire_wood", 1), EnumToolMaterial.WOOD);
		swordEnch_fire_stone = new ItemSwordEnchanted_Fire(ConfigHandler.getItemId(conf, "swordEnch_fire_stone", 1), EnumToolMaterial.STONE);
		swordEnch_fire_iron = new ItemSwordEnchanted_Fire(ConfigHandler.getItemId(conf, "swordEnch_fire_iron", 1), EnumToolMaterial.IRON);
		swordEnch_fire_gold = new ItemSwordEnchanted_Fire(ConfigHandler.getItemId(conf, "swordEnch_fire_gold", 1), EnumToolMaterial.GOLD);
		swordEnch_fire_diamond = new ItemSwordEnchanted_Fire(ConfigHandler.getItemId(conf, "swordEnch_fire_diamond", 1), EnumToolMaterial.EMERALD);
		LanguageRegistry.addName(abilityVoid, "Ability Void");
	}

}
