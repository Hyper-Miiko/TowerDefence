package mHUD.mObject;

import java.awt.Color;

import mHUD.StdDraw;

public class IPushButton extends ITextItem {
	private Color normalColor = new Color(250,250,250);
	private Color pressedColor = new Color(150,150,150);
	private Color mouseOnColor = new Color(230,230,230);
	
	private int mouseStatus = 0; 
	
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
	}
	public void setPressedColor(int r, int g, int b) {
		pressedColor = new Color(r,g,b);
	}
	public void setMouseOnColor(int r, int g, int b) {
		mouseOnColor = new Color(r,g,b);
	}
	public boolean isPressed() {
		return mouseStatus == 2;
	}
	
	protected void setMouseStatus(int s) {
		mouseStatus = s;
	}
	protected int getMouseStatus() {
		return mouseStatus;
	}
	
	protected void draw() {
		if(mouseIsIn()) {
			if(getMouseStatus() != 2 && StdDraw.isMousePressed()) {
				super.setBackgroundColor(getPressedColor());
				setMouseStatus(2);
				setNeedRedraw(true);
			}
			else if(getMouseStatus() != 1 && !StdDraw.isMousePressed()) {
				super.setBackgroundColor(getMouseOnColor());
				setMouseStatus(1);
				setNeedRedraw(true);
			}
		}
		else if(getMouseStatus() != 0) {
			super.setBackgroundColor(getBackgroundColor());
			setMouseStatus(0);
			setNeedRedraw(true);
		}
		super.draw();
	}
}
