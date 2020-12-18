package mHUD.item;

import mHUD.StdDraw;

public class MTextItem extends MItem{
	private String text;
	
	public MTextItem(){
	}
	
	public String getText(){
		return text;
	}
	public void setText(String text){
		this.text = text;
	}
	
	public void draw() {
		StdDraw.rectangle(getPx(),getPy(),getSx(),getSy());
		StdDraw.text(getPx(), getPy(), text);
	}
}
