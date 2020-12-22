package mHUD;

import mHUD.data.Color;
import mHUD.data.VectorInt;

public class IButton extends ITextItem {
	private Color normalColor = new Color(250,250,250);
	private Color pressedColor = new Color(150,150,150);
	private Color mouseOnColor = new Color(230,230,230);
	
	private short mouseStatus = 0; 
	
	public void setBackgroundColor(int r, int g, int b) {
		normalColor = new Color(r,g,b);
	}
	public void setPressedColor(int r, int g, int b) {
		pressedColor = new Color(r,g,b);
	}
	public void setMouseOnColor(int r, int g, int b) {
		mouseOnColor = new Color(r,g,b);
	}
	public boolean isClickedOn() {
		return mouseStatus == 2;
	}
	
	protected void draw() {
		if(mouseIsIn()) {
			if(StdDraw.isMousePressed()) {
				super.setBackgroundColor(pressedColor);
				mouseStatus = 2;
			}
			else {
				super.setBackgroundColor(mouseOnColor);
				mouseStatus = 1;
			}
		}
		else {
			super.setBackgroundColor(normalColor);
			mouseStatus = 0;
		}
		super.draw();
	}
}
