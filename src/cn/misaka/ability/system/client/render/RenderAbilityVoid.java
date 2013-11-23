/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.system.client.render;

import cn.misaka.ability.system.AbilityComponent;
import cn.misaka.ability.system.PlayerAbilityData;
import cn.misaka.ability.system.ServerAbilityMain;
import cn.misaka.ability.system.client.system.AbilityRender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

/**
 * @author WeAthFolD
 * 
 */
public class RenderAbilityVoid implements IItemRenderer {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraftforge.client.IItemRenderer#handleRenderType(net.minecraft
	 * .item.ItemStack, net.minecraftforge.client.IItemRenderer.ItemRenderType)
	 */
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch (type) {
		case EQUIPPED:
		case EQUIPPED_FIRST_PERSON:
			return true;
		default:
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraftforge.client.IItemRenderer#shouldUseRenderHelper(net.
	 * minecraftforge.client.IItemRenderer.ItemRenderType,
	 * net.minecraft.item.ItemStack,
	 * net.minecraftforge.client.IItemRenderer.ItemRendererHelper)
	 */
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 */
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if(type == ItemRenderType.EQUIPPED_FIRST_PERSON || type == ItemRenderType.EQUIPPED) {
			EntityLivingBase ent = (EntityLivingBase) data[1];
			if(ent instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) ent;
				PlayerAbilityData data2 = ServerAbilityMain.getAbilityData(player);
				AbilityComponent component = ServerAbilityMain.getActiveComponent(player);
				if(component != null) {
					AbilityRender render = component.getClientRender();
					if(render != null)
						render.onRenderEquipped(player, player.worldObj, data2, type == ItemRenderType.EQUIPPED);
				}
			}
		}
	}
	

}
