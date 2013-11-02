/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.client.system;

import cn.misaka.ability.system.PlayerAbilityData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * 和一种AbilityComponent对应 ，和其配合执行Client端的渲染等各种操作。
 * @author WeAthFolD
 *
 */
public abstract class AbilityRender {

	/**
	 * 在特定能力级被激活的时候调用。进行判断并进行能力动作和特效的渲染。
	 * 目前由voidItem的ItemRender调用，所以坐标轴和渲染位置参考IItemRender.
	 * @param player
	 * @param world
	 * @param data
	 */
	public abstract void onRenderEquipped(EntityPlayer player, World world, PlayerAbilityData data);
	
	private final void renderHand() {
		//TODO:求写……
	}
}
