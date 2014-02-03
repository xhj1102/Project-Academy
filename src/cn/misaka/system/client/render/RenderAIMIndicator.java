/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.core.client.props.AMClientProps;
import cn.misaka.system.ability.AbilityClass;
import cn.misaka.system.data.PlayerAbilityData;
import cn.misaka.system.proxy.CommonProxy;

/**
 * TODO:补完中。
 * @author WeAthFolD
 *
 */
public class RenderAIMIndicator {

	private double zDepth = -90;
	
	public static void renderIndicator(ScaledResolution res) {
		int k = res.getScaledWidth(), l = res.getScaledHeight();
		
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		PlayerAbilityData data = CommonProxy.abilityMain.getAbilityData(player);
		int tickTime = player.ticksExisted;
		GL11.glPushMatrix();
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glTranslatef(k - 40F, 48F, 0.0F);
		RenderUtils.loadTexture(AMClientProps.TEX_AIM_INDICATOR);
		if(data != null) {
			if(data.isDeveloped) {
				if(data.isActivated) {
					renderIndications(data, tickTime);
				}
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.7F);
				renderCircle(data, tickTime);
			}
		}
		GL11.glPopMatrix();
	}
	
	private static void renderCircle(PlayerAbilityData data, int tickTime) {
		
		drawTexturedModalRect(-32, -32, 1, 1, 64, 64, 164, 164);
		GL11.glPushMatrix();
		GL11.glRotated(tickTime * 0.3, 0.0F, 0.0F, 1.0F);
		drawTexturedModalRect(-32, -32, 165, 1, 64, 64, 164, 164);
		GL11.glPopMatrix();
		drawTexturedModalRect(-32, -32, 165, 259, 64, 64, 164, 164);
		
		RenderUtils.loadTexture(AMClientProps.FONT_ORATOR_STD);
		drawString("This is just a test 1122334455 000", -200, 20, .16F);
		
		float fontScale = 0.18F;
		int hFontHeight = (int) (24 * fontScale),
				hFontLength = getStringLength(data.player.getEntityName(), fontScale) / 2;
		drawString(data.player.getEntityName(), -hFontLength, -hFontHeight, fontScale);
		RenderUtils.loadTexture(AMClientProps.TEX_AIM_INDICATOR);
	}
	
	private static void renderIndications(PlayerAbilityData data, int tickTime) {
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
		drawTexturedModalRect(-141, -24, 114, 184, 121, 22, 312, 54); //主要信息
		drawTexturedModalRect(-193, -1, 6, 239, 173, 9, 446, 20); //计算力槽
		drawTexturedModalRect(-71, 9, 335, 259, 51, 10, 132, 25); //计算力数值
		
		GL11.glColor4f(1.0F,1.0F,1.0F,.7F);
		if(data.calcPoint != 0) {
			drawTexturedModalRect(-189, 1, 15, 437, 173, 5, 446, 10);
			if(data.currentCalcPoint != 0) {
				int len = (int) (157 * (data.currentCalcPoint / (double)data.calcPoint) + 5);
				drawTexturedModalRect(-27 - len, 1, 15, 448, len, 5, (int) (len / 0.388), 10);
			}
		}
		
		RenderUtils.loadTexture(AMClientProps.FONT_ORATOR_STD);
		float fontScale = 0.20F;
		String str1 = "Player Level:" + data.ability_level;
		String str2 = "";
		AbilityClass abc = CommonProxy.abilityMain.getAbilityClass(data);
		if(abc != null)
			str2 = StatCollector.translateToLocal(abc.getAbilityName());
		int hFontHeight = (int) (24 * fontScale),
				hFontLength = getStringLength(str1, fontScale) / 2;
		
		drawString(str1, -115, -24, fontScale);
		if(str2 != "") {
			hFontLength = getStringLength(str2, fontScale) / 2;
			drawString(str2, -120, -14, fontScale);
		}
		
		drawString(data.currentCalcPoint + "/" + data.calcPoint, -63, 10, 0.15F);
		RenderUtils.loadTexture(AMClientProps.TEX_AIM_INDICATOR);
	}

	private static  int TEX_WIDTH = 512, TEX_HEIGHT = 512;
	private static final float SCALE_X = 1.0F / TEX_WIDTH, SCALE_Y = 1.0F / TEX_HEIGHT;
	
    /**
     * Draws a textured rectangle at the stored z-value. Args: x, y, u, v, width, height
     */
    public static void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        float f = SCALE_X;
        float f1 = SCALE_Y;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(par1 + 0, par2 + par6, -90, (par3 + 0) * f, (par4 + par6) * f1);
        tessellator.addVertexWithUV(par1 + par5, par2 + par6, -90, (par3 + par5) * f, (par4 + par6) * f1);
        tessellator.addVertexWithUV(par1 + par5, par2 + 0, -90, (par3 + par5) * f, (par4 + 0) * f1);
        tessellator.addVertexWithUV(par1 + 0, par2 + 0, -90, (par3 + 0) * f, (par4 + 0) * f1);
        tessellator.draw();
    }
    
    /**
     * Draws a textured rectangle at the stored z-value. Args: x, y, u, v, width, height, texWidth, texHeight
     */
    public static void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6, int par7, int par8)
    {
    	float f = SCALE_X;
        float f1 = SCALE_Y;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(par1 + 0, par2 + par6, -90, (par3 + 0) * f, (par4 + par8) * f1);
        tessellator.addVertexWithUV(par1 + par5, par2 + par6, -90, (par3 + par7) * f, (par4 + par8) * f1);
        tessellator.addVertexWithUV(par1 + par5, par2 + 0, -90, (par3 + par7) * f, (par4 + 0) * f1);
        tessellator.addVertexWithUV(par1 + 0, par2 + 0, -90, (par3 + 0) * f, (par4 + 0) * f1);
        tessellator.draw();
    }
    
    public static void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6, int par7, int par8, double f, double f1)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(par1 + 0, par2 + par6, -90, (par3 + 0) * f, (par4 + par8) * f1);
        tessellator.addVertexWithUV(par1 + par5, par2 + par6, -90, (par3 + par7) * f, (par4 + par8) * f1);
        tessellator.addVertexWithUV(par1 + par5, par2 + 0, -90, (par3 + par7) * f, (par4 + 0) * f1);
        tessellator.addVertexWithUV(par1 + 0, par2 + 0, -90, (par3 + 0) * f, (par4 + 0) * f1);
        tessellator.draw();
    }
    
    private static int getStringLength(String str, float scl) {
    	return (int) (27 * str.length() * scl);
    }
    
    public static void drawString(String str, int x, int y, float scale) {
    	GL11.glPushMatrix();
    	GL11.glTranslatef(x, y, 0.0F);
    	GL11.glScalef(scale, scale, 1.0F);
    	int offset = 0;
    	for(int i = 0; i < str.length(); i++) {
    		char ch = str.charAt(i);
    		if(Character.isAlphabetic(ch) || Character.isDigit(ch) || ch == ' ' || ch == ':' || ch == '/') {
    			if(ch != ' ')
    				drawOratorChar(ch, offset, 0, 48);
    			offset += 27; //字符间距
    		}
    	}
    	
    	GL11.glPopMatrix();
    }
    
    public static void drawOratorChar(char ch, int x, int y, int size) {
    	final double SCALE_X = 0.00130208, SCALE_Y = 0.0041666667;
    	int u, v;
    	if(!Character.isDigit(ch)) {
    		if(ch == ':') {
    			u = 480;
    			v = 193;
    		} else if(ch == '/') {
    			u = 528;
    			v = 193;
    		} else {
    			boolean b = Character.isLowerCase(ch);
    			char cmp = b ? 'a' : 'A';
        		int i = (ch - cmp) % 16;
        		u = i * 48;
        		v = (((ch - cmp) - i) / 16) * 48 + 2 + (b ? 0 : 96); 
    		}
    	} else {
    		u = Integer.valueOf(ch) * 48;
    		v = 194;
    	}
    	drawTexturedModalRect(x, y, u, v, size, size, 48, 48, SCALE_X, SCALE_Y);
    	TEX_WIDTH = TEX_HEIGHT = 512;
    }
	
}
