package mHUD.mObject;

import mHUD.geometric.Vector;

public abstract class MItem extends MObject{
	public void setSize(Vector s) {
		super.setSize(s);
		if(mother != null)mother.recalculateUp();
	}
	public void setSize(int x, int y) {
		super.setSize(x,y);
		if(mother != null)mother.recalculateUp();
	}
}
