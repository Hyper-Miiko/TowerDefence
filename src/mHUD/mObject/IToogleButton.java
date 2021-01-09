package mHUD.mObject;

import mHUD.StdDraw;

public class IToogleButton extends IPushButton {
	
	private boolean isActive = false;
	
	protected void refreshObject() {
		buttonPressed();
		
		if(isActive)super.setBackgroundColor(getPressedColor());
		else if(mouseIn())super.setBackgroundColor(getMouseOnColor());
		else super.setBackgroundColor(getBackgroundColor());
	}
	
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean active) {
		isActive = active;
	}
	
	public boolean buttonPressed() {
		if(mouseIn() && StdDraw.isMousePressed()) {
			isPressed = true;
			return true;
		}
		else return false;
	}
	public boolean buttonReleased() {
		if(mouseIn() && !StdDraw.isMousePressed() && isPressed) {
			isPressed = false;
			isActive = !isActive;
			return true;
		}
		else return false;
	}
}
