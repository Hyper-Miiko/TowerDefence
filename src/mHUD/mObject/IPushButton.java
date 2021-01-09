package mHUD.mObject;

import java.awt.Color;

import mHUD.StdDraw;

public class IPushButton extends ILabel {
	private Color normalColor = new Color(250,250,250);
	private Color pressedColor = new Color(150,150,150);
	private Color mouseOnColor = new Color(230,230,230);
	
	protected boolean isPressed = false;
	
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
		if(buttonPressed())super.setBackgroundColor(getPressedColor());
		else if(mouseIn())super.setBackgroundColor(getMouseOnColor());
		else super.setBackgroundColor(getBackgroundColor());
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
			return true;
		}
		else return false;
	}
}
