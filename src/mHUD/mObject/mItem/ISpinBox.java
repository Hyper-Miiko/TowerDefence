package mHUD.mObject.mItem;

import mHUD.mObject.FHorizontalFrame;

public class ISpinBox extends FHorizontalFrame{
	ILabel labelValue = new ILabel();
	
	public ISpinBox() {
		this.addObject(labelValue);
	}
}
