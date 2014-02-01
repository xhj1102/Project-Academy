/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.register;

import cpw.mods.fml.common.registry.GameRegistry;
import cn.liutils.core.register.Config;
import cn.liutils.core.register.ConfigHandler;
import cn.misaka.ability.block.BlockAbilityDeveloper;
import cn.misaka.ability.tileentity.TileAbilityDeveloper;
import net.minecraft.block.Block;

/**
 * @author WeAthFolD
 *
 */
public class AbilityBlocks {

	public static Block abilityDeveloper;
	
	public static void init(Config conf) {
		abilityDeveloper = new BlockAbilityDeveloper(3999);
		GameRegistry.registerBlock(abilityDeveloper, "ability_developer");
		GameRegistry.registerTileEntity(TileAbilityDeveloper.class, "tile_ability_developer");
		
	}
}
