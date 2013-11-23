/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.client.render;

import cn.liutils.api.client.util.RenderUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

/**
 * @author WeAthFolD
 *
 */
public class RenderSwordEnchanted_Fire implements IItemRenderer {

	/**
	 * 
	 */
	public RenderSwordEnchanted_Fire() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.minecraftforge.client.IItemRenderer#handleRenderType(net.minecraft.item.ItemStack, net.minecraftforge.client.IItemRenderer.ItemRenderType)
	 */
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type) {
		case EQUIPPED:
		case EQUIPPED_FIRST_PERSON:
		case INVENTORY:
			return true;
		case ENTITY:
		case FIRST_PERSON_MAP:
			return false;
		default:
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see net.minecraftforge.client.IItemRenderer#shouldUseRenderHelper(net.minecraftforge.client.IItemRenderer.ItemRenderType, net.minecraft.item.ItemStack, net.minecraftforge.client.IItemRenderer.ItemRendererHelper)
	 */
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return false;
	}

	/* (non-Javadoc)
	 * @see net.minecraftforge.client.IItemRenderer#renderItem(net.minecraftforge.client.IItemRenderer.ItemRenderType, net.minecraft.item.ItemStack, java.lang.Object[])
	 */
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if(type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON)
			doRenderEquipped(item);
		else doRenderInventory(item);
	}
	
	private void doRenderEquipped(ItemStack stack) {
		RenderUtils.renderItemIn2d(stack, 0.0625F);
	}
	
	private void doRenderInventory(ItemStack stack) {
		RenderUtils.renderItemInventory(stack);
	}

}
