package mHUD.mObject;

import java.awt.Color;

import mHUD.StdDraw;

public class IToogleButton extends ILabel {
	
	private Color normalColor = new Color(250,250,250);
	private Color pressedColor = new Color(150,150,150);
	private Color mouseOnColor = new Color(230,230,230);
	protected boolean isPressed = false;
	private boolean isActive = false;
	
	protected Color getBackgroundColor() {
		return normalColor;
	}
	protected Color getPressedColor() {
		return pressedColor;
	}
	protected Color getMouseOnColor() {
		return mouseOnColor;
	}
	
	public void setBackgroundColor(int r, int g, int b) {
		normalColor = new Color(r,g,b);
		setNeedRedraw(true);
	}
	public void setPressedColor(int r, int g, int b) {
		pressedColor = new Color(r,g,b);
		setNeedRedraw(true);
	}
	public void setMouseOnColor(int r, int g, int b) {
		mouseOnColor = new Color(r,g,b);
		setNeedRedraw(true);
	}
	
	protected void refreshObject() {
		buttonPressed();
		
		if(isActive)setBackgroundColor(pressedColor);
		else if(mouseIn())setBackgroundColor(mouseOnColor);
		else setBackgroundColor(normalColor);
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
