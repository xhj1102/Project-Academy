/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.ability;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cn.misaka.system.client.render.SkillRender;
import cn.misaka.system.control.PlayerControlStat;
import cn.misaka.system.data.PlayerAbilityData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * 能力技能类。所有实际的产生实体等能力相关动作都在这里进行。
 * 注意skill中所有的keyID都被映射到了局部ID（即0.1.2,...，不是和键盘绑定的ID）
 * @author WeAthFolD
 *
 */
public abstract class AbilitySkill {

	/**
	 * 
	 */
	public AbilitySkill() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 特定操作键被按下过程每一tick被调用。
	 * @param world
	 * @param player
	 * @param keyID
	 * @param stat
	 * @return 返回false则代表终止更新
	 */
	public abstract boolean onButtonTick(World world, PlayerAbilityData player, int keyID, PlayerControlStat stat, boolean isEmptyHand);
	
	/**
	 * 按钮被按下时调用。
	 * @param world
	 * @param player
	 * @param keyID
	 * @param stat
	 * @return 好像暂时没什么特别的意义
	 */
	public abstract boolean onButtonDown(World world, PlayerAbilityData player, int keyID, PlayerControlStat stat, boolean isEmptyHand);
	
	/**
	 * 操作键被松开一刻被调用。
	 * @param world
	 * @param player
	 * @param keyID
	 * @param stat
	 * @return 返回true确认动作成功，进入更新循环，激活渲染器
	 */
	public abstract boolean onButtonUp(World world, PlayerAbilityData player, int keyID, PlayerControlStat stat, boolean isEmptyHand);
	
	/**
	 * 获取最多需要使用的键位数。
	 * @return
	 */
	public abstract int getMaxKeys();
	
	public abstract String getDescriptionForKey(int keyID);
	
	@SideOnly(Side.CLIENT)
	/**
	 * 获取喜闻乐见的渲染器=3=
	 * @return
	 */
	public abstract SkillRender getSkillRenderer();
	
}
