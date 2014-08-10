public class GuiPresetSetter {
	private int tempX, tempY, key;

	public GuiPresetSetter() {
		// TODO Add codes.
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
