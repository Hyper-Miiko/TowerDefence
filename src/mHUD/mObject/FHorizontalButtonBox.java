package mHUD.mObject;

import java.util.ArrayList;

public class FHorizontalButtonBox extends FHorizontalFrame{
	ArrayList<IToogleButton> buttonList = new ArrayList<IToogleButton>();
	
	public FHorizontalButtonBox(){
		
	}
	public void addButton(String name) {
		IToogleButton b = new IToogleButton();
		b.setText(name);
		addObject(b);
		buttonList.add(b);
		resize();
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
			b.setSize((this.getSize().x*0.9)/buttonList.size(),this.getSize().y*0.9);
		}
	}
}