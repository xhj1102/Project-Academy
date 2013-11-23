/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.system.client;

import java.util.ArrayList;
import java.util.List;

import cn.liutils.api.client.util.RenderUtils;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.ForgeSubscribe;

/**
 * @author WeAthFolD
 *
 */
public class AbilityClientEventHandler {

	public static List<String> hoveringText = new ArrayList();
	
	Minecraft mc = Minecraft.getMinecraft();
	
	static {
		hoveringText.add("Testing...");
	}
	
	@ForgeSubscribe
	public void onRenderHud(RenderGameOverlayEvent event){
		if(event.type == ElementType.CROSSHAIRS) {
			int width, height;
			width = event.resolution.getScaledWidth();
			height = event.resolution.getScaledHeight();
			RenderUtils.drawHoveringText(hoveringText, 15, 45, mc.fontRenderer, width, height);
		}
	}
	
	public static void setHoveringText(String... texts) {
		hoveringText.clear();
		for(String s : texts)
			hoveringText.add(s);
	}

}
