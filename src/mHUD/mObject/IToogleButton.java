package mHUD.mObject;

import mHUD.StdDraw;

public class IToogleButton extends IPushButton {
	private boolean isPressed = false;
	
	public boolean isPressed() {
		return isPressed;
	}
	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}

	protected void draw() {
		//TODO
		
		super.draw();
	}
}
