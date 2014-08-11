package cn.misaka.ability.clinet.gui;


/**
 * @author jiangyue
 *
 */
public class GuiPresetSetter extends GuiScreen {
    
    private int tempX, tempY, key；
    
    public GuiPresetSetter() {
        super();
    }

    
    @Override
    public void drawScreen(int par1, int par2, float par3) {
        // Draws the GUI itself and handle yoooo
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
