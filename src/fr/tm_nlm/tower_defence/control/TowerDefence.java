package fr.tm_nlm.tower_defence.control;

import mHUD.frame.MHorizontalFrame;
import mHUD.item.MTextItem;

public class TowerDefence {

	public static void main(String[] args) {
		MHorizontalFrame hf = new MHorizontalFrame();
		MTextItem txt = new MTextItem();
		hf.addObject(txt);
		txt.setText("test");
		txt.setSize(0.2,0.2);
		hf.draw();
	}
}
