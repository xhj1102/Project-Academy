package cn.misaka.ability.category.meltdowner;

import net.minecraft.util.ResourceLocation;
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.core.proxy.APClientProps;

public class SkillSingle extends AbilitySkill {

	public SkillSingle(int id) {
		super("skill.md.single", id);
	}

	@Override
	public int getSuggestKey(int skillKeyID) {
		return 0;
	}

	@Override
	public ResourceLocation getLogo() {
		return APClientProps.MD_SINGLE;
	}

}
