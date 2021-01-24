package mHUD.mObject;

import java.util.ArrayList;

public class FVerticalButtonBox extends FVerticalFrame {
	ArrayList<IToogleButton> buttonList = new ArrayList<IToogleButton>();
	
	public FVerticalButtonBox(){
		
	}
	public IToogleButton addButton(String name) {
		IToogleButton b = new IToogleButton();
		b.setText(name);
		addObject(b);
		buttonList.add(b);
		resize();
		return b;
	}
	public boolean isPressed(int i) {
		if(i < buttonList.size())return buttonList.get(i).isActive();
		else return false;
	}
	public void refreshObject() {
		
		for(IToogleButton b0 : buttonList) {
			if(b0.buttonReleased()) {
				for(IToogleButton b1 : buttonList) {
					b1.setActive(false);
				}
				b0.setActive(true);
			}
		}
		super.refreshObject();
	}
	private void resize() {
		for(IToogleButton b : buttonList) {
			b.setSize(this.getSize().x*0.9,(this.getSize().y*0.9)/buttonList.size());
		}
	}
	public int getNumberOfButton() {
		return buttonList.size();
	}
	public void setSelect(int i, boolean a) {
		for(IToogleButton b1 : buttonList) {
			b1.setActive(false);
		}
		buttonList.get(i).setActive(a);
	}
	public boolean anyActive() {
		for(IToogleButton b1 : buttonList) {
			if(b1.isActive())return true;
		}
		return false;
	}
	public int getPressed() {
		int i = 0;
		for(IToogleButton b1 : buttonList) {
			if(b1.isActive())return i;
			else i++;
		}
		return -1;
	}
}
