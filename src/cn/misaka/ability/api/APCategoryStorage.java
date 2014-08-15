package cn.misaka.ability.api;

import java.util.ArrayList;
import java.util.List;

import cn.liutils.api.util.GenericUtils;
import cn.misaka.ability.api.ability.AbilityCategory;
import cn.misaka.ability.system.classes.AbilityCategoryVoid;

/**
 * 存储所有能力类的地方，注册新能力类就在这里进行，实际上就是个List<AbilityCategory> Factory啦
 * @author WeAthFolD
 */
public class APCategoryStorage {

	private static List<AbilityCategory> categoryList = new ArrayList<AbilityCategory>();
	static {
		categoryList.add(new AbilityCategoryVoid(0));
	}
	
	public static int registerAbility(AbilityCategory ab) {
		if(categoryList.add(ab))
			return categoryList.size() - 1;
		return -1;
	}
	
	public static AbilityCategory getAbility(int id) {
		return GenericUtils.safeFetchFrom(categoryList, id);
	}
	
	public static int getMaxAbilities() {
		return categoryList.size() - 1;
	}

}
