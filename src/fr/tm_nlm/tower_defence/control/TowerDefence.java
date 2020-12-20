package fr.tm_nlm.tower_defence.control;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import static fr.tm_nlm.tower_defence.Constant.*;
import fr.tm_nlm.tower_defence.Constant;
import mHUD.FHorizontalFrame;
import mHUD.ITextItem;
import mHUD.MWindow;
import mHUD.FVerticalFrame;
import mHUD.StdDraw;

public class TowerDefence {

	public static void main(String[] args) throws InterruptedException{
		
		MWindow window = new MWindow();
		
		FVerticalFrame mainFrame = new FVerticalFrame();
		window.setMainFrame(mainFrame);
		mainFrame.setMinimumSize(0.45,0.45);
		mainFrame.setVerticalAlignement(TOP);
		
		FHorizontalFrame ressourceTab = new FHorizontalFrame();
		mainFrame.addObject(ressourceTab);
		ressourceTab.setMinimumSize(0.45,0.01);
		ressourceTab.setVerticalAlignement(TOP);
		ressourceTab.setHonrizontalAlignement(RIGHT);
		
		ITextItem gold = new ITextItem();
		ressourceTab.addObject(gold);
		gold.setText("Gold");
		gold.setSize(0.1,0.05);
		
		ITextItem life = new ITextItem();
		ressourceTab.addObject(life);
		life.setText("LIFE");
		life.setSize(0.1,0.05);
		
		mainFrame.setBackgroundColor(150, 150, 150);
		mainFrame.setLineColor(150, 150, 150);
		ressourceTab.setBackgroundColor(50, 50, 50);
		gold.setBackgroundColor(255, 255, 0);
		life.setBackgroundColor(255, 0, 0);
		life.setTextColor(255, 255, 255);
		
		
		while(true) {
			mainFrame.setBackgroundColor((int)(255*StdDraw.mouseX()), 150, (int)(255*StdDraw.mouseY()));
			window.draw();
			StdDraw.pause(100);
		}
	}
}
