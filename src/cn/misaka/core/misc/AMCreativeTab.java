/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.core.misc;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @author WeAthFolD
 *
 */
public class AMCreativeTab extends CreativeTabs {

	/**
	 * @param label
	 */
	public AMCreativeTab(String label) {
		super(label);
	}
	
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem()
    {
    	return Item.blazeRod;
    }

}
