package cn.misaka.ability.category.meltdowner;

import net.minecraft.util.ResourceLocation;
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.core.proxy.APClientProps;

public class SkillMultiple extends AbilitySkill {

	public SkillMultiple(int id) {
		super("skill.md.multiple", id);
	}

	@Override
	public int getSuggestKey(int skillKeyID) {
		return 1;
	}

	@Override
	public ResourceLocation getLogo() {
		return APClientProps.MD_MULTIPLE;
	}

}
