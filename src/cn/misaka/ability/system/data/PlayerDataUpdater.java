package cn.misaka.ability.system.data;

import net.minecraft.entity.player.EntityPlayer;
/**
 * 创建这个类是为了方便管理技能
 * 以后关于传输和保存读取只需要拿他的实例当参数即可
 * @auther Alan
*/ 
public class PlayerDataUpdater {
	public String player;
	public byte level;
	public byte classid;
	public int maxCP;
	public boolean[] ac_skill_open;
	public float[] ac_skill_exp;
	
	public PlayerDataUpdater(EntityPlayer player,int ac_level,int ac_class,int ac_cp,boolean[] ac_skill_open,float[] ac_skill_exp){
		this.player = player.getCommandSenderName();
		this.level = (byte) ac_level;
		this.classid = (byte) ac_class;
		this.maxCP = ac_cp;
		this.ac_skill_open = ac_skill_open;
		this.ac_skill_exp = ac_skill_exp;
	}
	
	public PlayerDataUpdater() {}
}
