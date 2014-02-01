/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.client.render.block;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.model.ITileEntityModel;
import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.ability.client.model.ModelAbilityDeveloper;
import cn.misaka.core.client.props.AMClientProps;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

/**
 * @author WeAthFolD
 *
 */
public class RenderAbilityDeveloper extends TileEntitySpecialRenderer {

	private ITileEntityModel model = new ModelAbilityDeveloper();
	
	private final int[] directionMap = { 2, 0, 1, 3 };
	private final float[][] offsetMap = { { 0.5F, -1.0F }, { 0.5F, 2.0F }, { -1.0F, 0.5F }, { 2.0F, 0.5F } };
	
	/**
	 * 
	 */
	public RenderAbilityDeveloper() {
	}

	/*
	 * 位运算狂魔
	 */
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float partialTicks) {
		int meta = te.getBlockMetadata();
		ForgeDirection dir = ForgeDirection.values()[(meta & 3) + 2];
		if((meta >> 2) == 0) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			
			RenderUtils.loadTexture(AMClientProps.TEX_ABILITY_DEVELOPER);
		
			GL11.glTranslated(x + offsetMap[meta & 3][0], y + 3.1, z + offsetMap[meta & 3][1]);
			GL11.glRotatef(-90.0F * (directionMap[meta & 3]), 0F, 1F, 0F);
			GL11.glTranslatef(0.0F, 0.0F, -1.5F);
			GL11.glScalef(-1.0F, -1.0F, 1.0F);
			model.render(te, 0.0, 0.0, 0.0, 1.0F, 0.125F, 0.0F);
			
			GL11.glPopMatrix();
		}
	}

}
