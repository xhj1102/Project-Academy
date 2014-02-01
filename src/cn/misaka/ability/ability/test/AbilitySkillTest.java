/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.ability.test;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cn.misaka.ability.client.render.ability.misc.SkillRenderTest;
import cn.misaka.system.ability.AbilitySkill;
import cn.misaka.system.client.render.SkillRender;
import cn.misaka.system.control.PlayerControlStat;

/**
 * @author WeAthFolD
 *
 */
public class AbilitySkillTest extends AbilitySkill {

	/**
	 * 
	 */
	public AbilitySkillTest() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.misaka.system.ability.AbilitySkill#onButtonTick(net.minecraft.world.World, net.minecraft.entity.player.EntityPlayer, int, cn.misaka.system.control.PlayerControlStat)
	 */
	@Override
	public boolean onButtonTick(World world, EntityPlayer player, int keyID,
			PlayerControlStat stat) {
		// TODO Auto-generated method stub
		System.out.println("OnBtnTick called in " + world.isRemote);
		return true;
	}

	/* (non-Javadoc)
	 * @see cn.misaka.system.ability.AbilitySkill#onButtonDown(net.minecraft.world.World, net.minecraft.entity.player.EntityPlayer, int, cn.misaka.system.control.PlayerControlStat)
	 */
	@Override
	public boolean onButtonDown(World world, EntityPlayer player, int keyID,
			PlayerControlStat stat) {
		// TODO Auto-generated method stub
		System.out.println("OnBtnDn called in " + world.isRemote);
		return true;
	}

	/* (non-Javadoc)
	 * @see cn.misaka.system.ability.AbilitySkill#onButtonUp(net.minecraft.world.World, net.minecraft.entity.player.EntityPlayer, int, cn.misaka.system.control.PlayerControlStat)
	 */
	@Override
	public boolean onButtonUp(World world, EntityPlayer player, int keyID,
			PlayerControlStat stat) {
		System.out.println("OnButtonUp called in " + world.isRemote);
		return true;
	}

	/* (non-Javadoc)
	 * @see cn.misaka.system.ability.AbilitySkill#getMaxKeys()
	 */
	@Override
	public int getMaxKeys() {
		// TODO Auto-generated method stub
		return 2;
	}

	/* (non-Javadoc)
	 * @see cn.misaka.system.ability.AbilitySkill#getDescriptionForKey(int)
	 */
	@Override
	public String getDescriptionForKey(int keyID) {
		// TODO Auto-generated method stub
		return "Test";
	}

	SkillRender renderer = new SkillRenderTest();
	
	@Override
	@SideOnly(Side.CLIENT)
	public SkillRender getSkillRenderer() {
		return renderer; //请不要偷懒写new...
	}

}
