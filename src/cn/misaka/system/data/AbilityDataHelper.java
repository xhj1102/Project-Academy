/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.data;

import net.minecraft.nbt.NBTTagCompound;

/**
 * @author WeAthFolD
 *
 */
public class AbilityDataHelper {

	
	private static final String PREFIX = "aim";
	private static final String BASE = "b", SKILLS = "s", PROPS = "p", CNTRL = "cn";
	private static final String KEYSET = "k";


	public static final String 
		PRF_BASE = prefix(BASE),
		PRF_SKILLS = prefix(SKILLS),
		PRF_PROPS = prefix(PROPS),
		PRF_CNTRL = prefix(CNTRL),
		PRF_KEYSET = prefix(KEYSET);
	
	
	public static boolean isSkillAvailable(NBTTagCompound tag, int abclass, int skill) {
		return tag.getBoolean(PRF_SKILLS + abclass + "_" + skill);
	}
	
	public static void setSkillAvailable(NBTTagCompound tag, int abclass, int skill, boolean isAvailable) {
		tag.setBoolean(PRF_SKILLS + abclass + "_" + skill, isAvailable);
	}
	
	
	protected static  short getShort(NBTTagCompound nbt, String str, String... prefix) {
		return nbt.getShort(prefix(prefix) + str);
	}
	
	protected static  String prefix(String... strs) {
		StringBuilder sb = new StringBuilder(PREFIX).append("_");
		for(String str : strs) 
			sb.append(str).append("_");
		return sb.toString();
	}

}
