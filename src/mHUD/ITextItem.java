package mHUD;

import mHUD.data.Color;

public class ITextItem extends MItem{
	private String text;
	
	private Color textColor = new Color(0,0,0);
	
	public String getText(){
		return text;
	}
	public void setText(String text){
		this.text = text;
	}
	
	public void setTextColor(int r, int g, int b) {
		textColor = new Color(r,g,b);
	}
	
	protected void draw() {
		drawBackground();
		drawRect();
		
		StdDraw.setPenColor(textColor.Red,textColor.Green,textColor.Blue);
		StdDraw.text(((double)getPos().x/getWindowSize().x), 
					 ((double)getPos().y/getWindowSize().y),
					  text);
	}
}
