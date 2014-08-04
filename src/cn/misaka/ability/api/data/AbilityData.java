package cn.misaka.ability.api.data;

import net.minecraft.entity.player.EntityPlayer;
/**
 * 创建这个类是为了方便管理技能
 * 以后关于传输和保存读取只需要拿他的实例当参数即可
 * @auther Alan
*/ 
 
public class AbilityData{
	public String player;
	public byte ac_level;
	public byte ac_class;
	public int ac_cp;
	public boolean[] ac_skill_open;
	public float[] ac_skill_exp;
	public AbilityData(EntityPlayer player,byte ac_level,byte ac_class,int ac_cp,boolean[] ac_skill_open,float[] ac_skill_exp){
		this.player = player.getCommandSenderName();
		this.ac_level = ac_level;
		this.ac_class = ac_class;
		this.ac_cp = ac_cp;
		this.ac_skill_open = ac_skill_open;
		this.ac_skill_exp = ac_skill_exp;
	}
}
