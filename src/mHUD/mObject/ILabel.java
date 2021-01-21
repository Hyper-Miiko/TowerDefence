package mHUD.mObject;

import java.awt.Color;

import mHUD.StdDraw;

public class ILabel extends MItem{
	private String text = "";
	
	private Color textColor = new Color(0,0,0);
	
	public String getText(){
		return text;
	}
	public void setText(String text){
		if(!text.equals(this.text)) {
			this.text = text;
			setNeedRedraw(true);
		}
	}
	
	public void setTextColor(int r, int g, int b) {
		textColor = new Color(r,g,b);
		setNeedRedraw(true);
	}
	
	protected void refreshObject() {
		//we need a refresh for this???
	}
	
	protected void draw() {
		if(isRedrawNeeded()) {
			drawBackground();
			drawRect();
			
			StdDraw.setPenColor(textColor);
			StdDraw.text(((double)getPos().x/getWindowSize().x), 
						 ((double)getPos().y/getWindowSize().y),
						  text);
			
			this.setNeedRedraw(false);
		}
	}
}
