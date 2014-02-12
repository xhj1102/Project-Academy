/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.client.render;

import cn.misaka.system.ability.AbilityClass;
import cn.misaka.system.ability.AbilitySkill;
import cn.misaka.system.data.PlayerAbilityData;
import cn.misaka.system.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

/**
 * 能力虚空的渲染类，纯底层内容吧。
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
		switch(type) {
		case EQUIPPED:
		case EQUIPPED_FIRST_PERSON:
			return true;
		case FIRST_PERSON_MAP:
		case ENTITY:
		case INVENTORY:
			return false;
		}
		return false;
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
		//if(type == ItemRenderType.INVENTORY) return;
		
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		renderInv(player, type == ItemRenderType.EQUIPPED_FIRST_PERSON);
		//狼：如果有人用到左手渲染告诉我一声
	}
	
	private void renderInv(EntityPlayer player, boolean b) {
		PlayerAbilityData data = CommonProxy.abilityMain.getAbilityData(player);
		if(data != null) {
			AbilitySkill skl = data.lastActiveSkill;
			if(skl != null) {
				SkillRender render = skl.getSkillRenderer();
				if(render != null)
					render.onRenderEquipped(player, player.worldObj, data, CommonProxy.abilityMain.getControlStat(player), b);
			} else SkillRender.renderHand(player);
		}
	}

}
