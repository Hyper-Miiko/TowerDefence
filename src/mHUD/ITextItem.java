package mHUD;

public class ITextItem extends MItem{
	private String text;
	
	private MColor textColor = new MColor(0,0,0);
	
	public ITextItem(){
	}
	
	public String getText(){
		return text;
	}
	public void setText(String text){
		this.text = text;
	}
	
	public void setTextColor(int r, int g, int b) {
		textColor = new MColor(r,g,b);
	}
	
	protected void draw() {
		drawBackground();
		drawRect();
		
		StdDraw.setPenColor(textColor.Red,textColor.Green,textColor.Blue);
		StdDraw.text(getPx(), getPy(), text);
	}
}
