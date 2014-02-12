package cn.misaka.core.client.props;

import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;

public class AMClientProps {

	public static int KEY_MODESWITCH = Keyboard.KEY_V;
	
	private static String NAMESPACE = "misaka:";
	
	public static ResourceLocation
		TEX_OVERLAY_FIRE = getRes("textures/effects/overlay_fire.png"),
		TEX_EFFECT_MELTDOWN = getRes("textures/effects/meltdown_ball.png"),
		TEX_ABILITY_DEVELOPER = getRes("textures/blocks/ability_dev.png"),
		TEX_AIM_INDICATOR = getRes("textures/guis/aim_indicator.png"),
		TEX_EFFECT_STEVE = getRes("textures/effects/steve_indicator.png");
	
	public static ResourceLocation
		FONT_ORATOR_STD = getRes("textures/guis/oratorstd.png");
	
	public static ResourceLocation
		ANIM_SMOKE[] = getResArray("textures/effects/smoke001.png", 
				"textures/effects/smoke002.png", 
				"textures/effects/smoke003.png",
				"textures/effects/smoke004.png", 
				"textures/effects/smoke005.png");
		
	private static ResourceLocation[] getResArray(String... s) {
		ResourceLocation[] arr = new ResourceLocation[s.length];
		for(int i = 0; i < s.length; i++)
			arr[i] = getRes(s[i]);
		return arr;
	}
	
	private static ResourceLocation getRes(String s) {
		return new ResourceLocation(NAMESPACE + s);
	}

}
