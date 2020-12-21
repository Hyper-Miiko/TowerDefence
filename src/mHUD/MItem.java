package mHUD;

import mHUD.data.VectorInt;

public abstract class MItem extends MObject{
	
	public void setSize(VectorInt s) {
		super.setSize(s);
		if(mother != null)mother.recalculateUp();
	}
	public void setSize(int x, int y) {
		super.setSize(x,y);
		if(mother != null)mother.recalculateUp();
	}
}
