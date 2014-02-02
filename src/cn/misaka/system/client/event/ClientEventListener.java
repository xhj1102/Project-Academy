package cn.misaka.system.client.event;

import cn.misaka.system.client.render.RenderAIMIndicator;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.ForgeSubscribe;

/**
 * TODO:补完中。
 * @author WeAthFolD
 *
 */
public class ClientEventListener {

	public ClientEventListener() {
		// TODO Auto-generated constructor stub
	}
	
	@ForgeSubscribe
	public void onRenderGui(RenderGameOverlayEvent event) {
		if(event.type == ElementType.CROSSHAIRS) {
			RenderAIMIndicator.renderIndicator(event.resolution);
		}
 	}

}
