package mHUD.mGraphicEntity;

import java.awt.Color;

import mHUD.geometric.Shape;

public abstract class GPlainEntity extends MGraphicEntity {
	protected GPlainEntity(Shape s) {
		super(s);
	}
	
	protected Color backgroundColor = new Color(255,255,255);
	protected Color lineColor = new Color(0,0,0);
	
	public void setBackgroundColor(Color c) {
		backgroundColor = c;
	}
	public void setLineColor(Color c) {
		lineColor = c;
	}
	public void setBackgroundColor(int r, int g, int b) {
		backgroundColor = new Color(r,g,b);
	}
	public void setLineColor(int r, int g, int b) {
		lineColor = new Color(r,g,b);
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public Color getLineColor() {
		return lineColor;
	}
}
