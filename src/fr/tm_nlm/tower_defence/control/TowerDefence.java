package fr.tm_nlm.tower_defence.control;

import java.awt.Color;
import static fr.tm_nlm.tower_defence.Constant.*;
import fr.tm_nlm.tower_defence.Constant;
import mHUD.StdDraw;
import mHUD.frame.MHorizontalFrame;
import mHUD.frame.MVerticalFrame;
import mHUD.item.MTextItem;

public class TowerDefence {

	public static void main(String[] args) {
		MVerticalFrame window = new MVerticalFrame();
		window.setMinimumSize(0.45,0.45);
		window.setVerticalAlignement(TOP);
		
		
		MHorizontalFrame ressourceTab = new MHorizontalFrame();
		window.addObject(ressourceTab);
		ressourceTab.setMinimumSize(0.45,0.01);
		ressourceTab.setVerticalAlignement(TOP);
		ressourceTab.setHonrizontalAlignement(RIGHT);
		
		MTextItem gold = new MTextItem();
		ressourceTab.addObject(gold);
		gold.setText("Gold");
		gold.setSize(0.1,0.05);
		
		MTextItem life = new MTextItem();
		ressourceTab.addObject(life);
		life.setText("LIFE");
		life.setSize(0.1,0.05);
		
		window.setBackgroundColor(150, 150, 150);
		window.setLineColor(150, 150, 150);
		ressourceTab.setBackgroundColor(50, 50, 50);
		gold.setBackgroundColor(255, 255, 0);
		life.setBackgroundColor(255, 0, 0);
		life.setTextColor(255, 255, 255);
		
		window.draw();
	}
}
