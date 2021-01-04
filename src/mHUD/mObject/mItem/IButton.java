package mHUD.mObject.mItem;

import java.awt.Color;

import mHUD.StdDraw;

public class IButton extends ILabel {
	private Color normalColor = new Color(250,250,250);
	private Color pressedColor = new Color(150,150,150);
	private Color mouseOnColor = new Color(230,230,230);
	
	private boolean toogle = false; 
	private boolean isPressed = false;
	private boolean isButtonPressed = false;
	
	
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
		buttonReleased();
		
		if(isButtonPressed)super.setBackgroundColor(getPressedColor());
		else if(mouseIn())super.setBackgroundColor(getMouseOnColor());
		else super.setBackgroundColor(getBackgroundColor());
	}
	public void setToggle(boolean t) {
		toogle = t;
	}
	
	public boolean buttonPressed() {
			if(mouseIn() && StdDraw.isMousePressed() && !isPressed) {
				isPressed = true;
				if(!toogle) isButtonPressed = true;
				return isButtonPressed;
			}
			else return isButtonPressed;
	}
	public boolean buttonReleased() {
		
		if(mouseIn() && !StdDraw.isMousePressed() && isPressed) {
			isPressed = false;
			isButtonPressed = !isButtonPressed;
			return !toogle || (toogle && !isButtonPressed);
		}
		else return false;
	}
}
