package cn.misaka.ability.clinet.gui;


/**
 * @author jiangyue
 *
 */
public class GuiPresetSetter extends GuiScreen {
	
    private int tempX, tempY, key；
	public final int xSizeOfTexture = 176;
	public final int ySizeOfTexture = 88;
    
    public GuiPresetSetter() {
		
        super();
    }

	@Override
	public void initGui() {
		is.controlList.clear();

		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;

		this.controlList.add(new GuiButton(0, posX+ 40, posY + 40, 100, 20, "placeholder"));
	}
    
    @Override
    public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
        // Draws the GUI itself and handle yoooo
		
		int var4 = this.mc.renderEngine.getTexture(null); // To be completed!
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var4);

		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;

		drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);
		
		super.drawScreen(par1, par2, par3);
    }
    
    
    
    public void onMenuChanged(int i) {
	    this.tempX = i;
    }

	public void onChildMenuChanged(int i) {
		this.tempY = i;
	}

	public void onPresetKeyChanged(int i) {
		this.key = i;
	}

	public void confirmPresetKeyChange() {
		invokePresetChange(this.tempX, this.tempY, this.key);
	}

	private void invokePresetChange(int x, int y, int key) {
		// TODO Implement to the API. yoooooooooooo
	}
	
}
