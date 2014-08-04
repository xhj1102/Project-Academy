package cn.misaka.ability.api.ability;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.misaka.ability.api.client.control.ControlIndicator;
import cn.misaka.ability.api.client.render.SkillRender;
import cn.misaka.ability.api.control.PlayerControlData;
import cn.misaka.ability.api.control.SkillControlStat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class AbilitySkill {
	
	protected final String unl_name;
	
	@SideOnly(Side.CLIENT)
	public static SkillRender DEFAULT_SKILL_RENDER = new SkillRender();
	
	@SideOnly(Side.CLIENT)
	protected List<ControlIndicator> indicators = new ArrayList<ControlIndicator>();
	
	public AbilitySkill(String name) {
		unl_name = name;
	}
	
	/**
	 * 如果返回true，那么就会发送该skill的tickUpdate，可在其中进行其他操作。
	 * @param world
	 * @param player
	 * @param stat
	 * @param mctrl
	 * @return
	 */
	public boolean isSkillActivated(World world, EntityPlayer player, SkillControlStat stat, PlayerControlData mctrl) {
		return stat.isKeyDown();
	}
	
	/**
	 * 当按键状况被改变时调用（按下或放开）
	 * @param world
	 * @param player
	 * @param stat
	 * @param kid 状态发生改变的键位id
	 * @param mctrl
	 */
	public void onKeyStateChange(World world, EntityPlayer player, SkillControlStat stat, int kid, PlayerControlData mctrl) {
		
	}
	
	/**
	 * 技能激活时每tick被调用一次。
	 * @param world
	 * @param player
	 * @param stat
	 * @param mctrl
	 * @return 如果返回false，那么ticker将会停止运行，
	 */
	public boolean onSkillTick(World world, EntityPlayer player, SkillControlStat stat, PlayerControlData mctrl) {
		return true;
	}
	
	public int getMaxKeys() {
		return 0;
	}
	
	/**
	 * 该技能是否会“打断”其他技能的动作？
	 * @return
	 */
	public boolean doesInterrupt() {
		return true;
	}
	
	/**
	 * 该技能是否使用手部渲染器？
	 * @return
	 */
	@SideOnly(Side.CLIENT)
	public boolean useRender() {
		return true;
	}
	
	/**
	 * 为当前能力添加操作提示，在GUI的右端显示。
	 */
	@SideOnly(Side.CLIENT)
	protected void addIndications() {
	}
	
	/**
	 * 返回该Skill对应的手部渲染器。注意请不要每次都创建对象，因为该方法时刻被调用。
	 * @return
	 */
	@SideOnly(Side.CLIENT)
	public SkillRender getSkillRender() {
		return DEFAULT_SKILL_RENDER;
	}
	
	@SideOnly(Side.CLIENT)
	public abstract ResourceLocation getLogo();
	
	@SideOnly(Side.CLIENT)
	public final Iterator<ControlIndicator> getControlTips() {
		return indicators.iterator();
	}
	
	public final String getNameForDisplay() {
		return StatCollector.translateToLocal(unl_name);
	}
	
	public final String getUnlocalizedName() {
		return unl_name;
	}

}
