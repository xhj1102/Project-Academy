/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.client.render;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

/**
 * @author WeAthFolD
 *
 */
public class RenderAbilityVoid implements IItemRenderer {

	/**
	 * 
	 */
	public RenderAbilityVoid() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.minecraftforge.client.IItemRenderer#handleRenderType(net.minecraft.item.ItemStack, net.minecraftforge.client.IItemRenderer.ItemRenderType)
	 */
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see net.minecraftforge.client.IItemRenderer#shouldUseRenderHelper(net.minecraftforge.client.IItemRenderer.ItemRenderType, net.minecraft.item.ItemStack, net.minecraftforge.client.IItemRenderer.ItemRendererHelper)
	 */
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see net.minecraftforge.client.IItemRenderer#renderItem(net.minecraftforge.client.IItemRenderer.ItemRenderType, net.minecraft.item.ItemStack, java.lang.Object[])
	 */
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		// TODO Auto-generated method stub

	}

}
