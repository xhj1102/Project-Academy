package cn.misaka.ability.system;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cn.misaka.ability.system.AbilityClass.ControlStat;
import cn.misaka.ability.system.client.system.AbilityRender;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 代表每一系能力的一种级别，在其主AbilityClass中用一个ArrayList统一存储。
 * @author WeAthFolD
 *
 */
public abstract class AbilityComponent {
	
	AbilityClass baseClass;

	public AbilityComponent(AbilityClass base) {
		baseClass = base;
	}
	
	/**
	 * 当玩家Ability被激活时时刻调用，用于进行综合判断。
	 * @param player
	 * @param world
	 * @param data
	 * @param stat
	 */
	protected abstract void onAbilityTick(EntityPlayer player, World world, PlayerAbilityData data, ControlStat stat);
	
	protected abstract void onButtonDown(EntityPlayer player, World world, PlayerAbilityData data, int keyID, ControlStat stat);
	
	protected abstract void onButtonUp(EntityPlayer player, World world, PlayerAbilityData data, int keyID, ControlStat stat);
	
	/**
	 * 当一个按键被持续按下的时候调用。只执行单按键的简单操作，所以不提供ControlStat.
	 * @param player
	 * @param world
	 * @param data
	 * @param keyID
	 * @param ticks 按下的时间
	 */
	protected abstract void onButtonTick(EntityPlayer player, World world, PlayerAbilityData data, int keyID, int ticks);
	
	@SideOnly(Side.CLIENT)
	/**
	 * 获取辅助渲染器。
	 * @return
	 */
	public abstract AbilityRender getClientRender();
	
	public abstract String getComponentName();

}
