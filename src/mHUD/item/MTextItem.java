package mHUD.item;

import mHUD.StdDraw;
import mHUD.variable.Color;

public class MTextItem extends MItem{
	private String text;
	
	private Color textColor = new Color(0,0,0);
	
	public MTextItem(){
	}
	
	public String getText(){
		return text;
	}
	public void setText(String text){
		this.text = text;
	}
	
	public void setTextColor(int r, int g, int b) {
		textColor = new Color(r,g,b);
	}
	
	public void draw() {
		drawBackground();
		drawRect();
		
		StdDraw.setPenColor(textColor.Red,textColor.Green,textColor.Blue);
		StdDraw.text(getPx(), getPy(), text);
	}
}
