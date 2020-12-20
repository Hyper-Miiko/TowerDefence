package mHUD.item;

import mHUD.ColorSet;
import mHUD.StdDraw;

public class MTextItem extends MItem{
	private String text;
	
	private ColorSet textColor = new ColorSet(0,0,0);
	
	public MTextItem(){
	}
	
	public String getText(){
		return text;
	}
	public void setText(String text){
		this.text = text;
	}
	
	public void setTextColor(int r, int g, int b) {
		textColor = new ColorSet(r,g,b);
	}
	
	public void draw() {
		drawBackground();
		drawRect();
		
		StdDraw.setPenColor(textColor.Red,textColor.Green,textColor.Blue);
		StdDraw.text(getPx(), getPy(), text);
	}
}
