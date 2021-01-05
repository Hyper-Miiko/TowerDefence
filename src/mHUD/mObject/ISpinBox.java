package mHUD.mObject;

import mHUD.geometric.Vector;

public class ISpinBox extends FHorizontalFrame{
	
	FVerticalFrame buttonFrame = new FVerticalFrame();
	IButton plus = new IButton();
	IButton minus = new IButton();
	ILabel label = new ILabel();
	
	int value = 0;
	int min = 0;
	int max = 9000;
	int step = 1;
	
	public ISpinBox() {
		this.addObject(buttonFrame);
		this.addObject(label);
		
		buttonFrame.addObject(minus);
		buttonFrame.addObject(plus);
		
		minus.setText("-");
		plus.setText("+");
		label.setText(""+value);
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int v) {
		value = v;
	}
	public void setStep(int s) {
		step = s;
	}
	public void setMin(int m) {
		min = m;
	}
	public void setMax(int m) {
		max = m;
	}
	
 	public void setMinimumSize(double x, double y) {
 		label.setSize(x*0.7,y);
 		minus.setSize(x*0.3,y*0.5);
 		plus.setSize(x*0.3,y*0.5);
		recalculateUp();
	}
	public void refreshObject() {
		
		if(minus.buttonReleased() && value-step >= min) {
			value-=step;
			label.setText(""+value);
		}
		if(plus.buttonReleased() && value+step <= max) {
			value+=step;
			label.setText(""+value);
		}
		
		minus.refreshObject();
		plus.refreshObject();
	}
}
