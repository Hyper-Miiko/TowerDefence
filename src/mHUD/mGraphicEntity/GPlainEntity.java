package mHUD.mGraphicEntity;

import java.awt.Color;

public abstract class GPlainEntity extends MGraphicEntity {
	
	protected Color backgroundColor = new Color(255,255,255);
	protected Color lineColor = new Color(0,0,0,0);
	
	public void setBackgroundColor(Color c) {
		backgroundColor = c;
		reloadCanvas();
	}
	public void setLineColor(Color c) {
		lineColor = c;
		reloadCanvas();
	}
	public void setBackgroundColor(int r, int g, int b) {
		backgroundColor = new Color(r,g,b);
		reloadCanvas();
	}
	public void setLineColor(int r, int g, int b) {
		lineColor = new Color(r,g,b);
		reloadCanvas();
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public Color getLineColor() {
		return lineColor;
	}
}
