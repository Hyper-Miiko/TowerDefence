package mHUD;

import fr.tm_nlm.tower_defence.control.data.geometric.Vector;

public abstract class MItem extends MObject{


	public void setSx(double sx) {
		super.setSx(sx);
		if(mother != null)mother.recalculateUp();
	}
	public void setSy(double sy) {
		super.setSy(sy);
		if(mother != null)mother.recalculateUp();
	}
	
	public void setSize(Vector s) {
		super.setSize(s);
		if(mother != null)mother.recalculateUp();
	}
	public void setSize(double sx, double sy) {
		super.setSize(sx,sy);
		if(mother != null)mother.recalculateUp();
	}
	
	public void setRect(double x, double y, double tx, double ty) {
		super.setRect(x,y,tx,ty);
		if(mother != null)mother.recalculateUp();
	}
}
