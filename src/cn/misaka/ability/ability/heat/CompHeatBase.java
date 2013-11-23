/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.ability.heat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cn.misaka.ability.item.ItemSwordEnchanted_Fire;
import cn.misaka.ability.system.AbilityClass;
import cn.misaka.ability.system.AbilityClass.ControlStat;
import cn.misaka.ability.system.AbilityComponent;
import cn.misaka.ability.system.PlayerAbilityData;
import cn.misaka.ability.system.client.system.AbilityRender;

/**
 * @author WeAthFolD
 *
 */
public abstract class CompHeatBase extends AbilityComponent {

	final int level;
	
	/**
	 * @param base
	 */
	public CompHeatBase(AbilityClass base, int lvl) {
		super(base);
		level = lvl;
	}
	
	/**
	 * 关于武器攻击力提升的代码。
	 * @param player
	 * @param world
	 * @param data
	 * @param stat
	 * @param dir
	 */
	protected void onEnchantStateChange(EntityPlayer player, World world, PlayerAbilityData data, ControlStat stat, boolean dir) {
		stat.tag.setBoolean("enchant", dir);
		ItemStack currentItem = player.getCurrentEquippedItem();
		if(dir && (currentItem != null && currentItem.getItem() instanceof ItemSword)) { //activate
			ItemSwordEnchanted_Fire.createEnchantedSword(currentItem, player, level);
		} else { //deactivate
			stat.tag.setBoolean("enchant", false);
			ItemSwordEnchanted_Fire.restoreItem(currentItem);
		}
	}
}
