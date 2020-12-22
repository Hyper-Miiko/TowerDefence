package fr.tm_nlm.tower_defence.control;

import static fr.tm_nlm.tower_defence.Constant.*;
import mHUD.FHorizontalFrame;
import mHUD.ITextItem;
import mHUD.MWindow;
import mHUD.FVerticalFrame;
import mHUD.IButton;
import mHUD.StdDraw;

public class TowerDefence {

	public static void main(String[] args) throws InterruptedException{
		
		MWindow window = new MWindow(800, 600);
		
		FVerticalFrame mainFrame = new FVerticalFrame();
		window.setMainFrame(mainFrame);
		mainFrame.setMinimumSize(800,600);
		mainFrame.setVerticalAlignement(TOP);
		
		FHorizontalFrame ressourceTab = new FHorizontalFrame();
		mainFrame.addObject(ressourceTab);
		ressourceTab.setMinimumSize(800,40);
		ressourceTab.setVerticalAlignement(TOP);
		ressourceTab.setHonrizontalAlignement(RIGHT);
		
		ITextItem gold = new ITextItem();
		ressourceTab.addObject(gold);
		gold.setText("Gold");
		gold.setSize(80,40);
		
		ITextItem life = new ITextItem();
		ressourceTab.addObject(life);
		life.setText("LIFE");
		life.setSize(80,40);
		
		mainFrame.setBackgroundColor(150, 150, 150);
		mainFrame.setLineColor(150, 150, 150);
		ressourceTab.setBackgroundColor(50, 50, 50);
		gold.setBackgroundColor(255, 255, 0);
		life.setBackgroundColor(255, 0, 0);
		life.setTextColor(255, 255, 255);
		
		IButton buttonTest = new IButton();
		buttonTest.setText("test");
		buttonTest.setSize(120,40);
		ressourceTab.addObject(buttonTest);
		
		while(true) {
			if(!buttonTest.isClickedOn())mainFrame.setBackgroundColor((int)(255*StdDraw.mouseX()), 150, (int)(255*StdDraw.mouseY()));
			else mainFrame.setBackgroundColor(255, 0, 255);
			
			window.draw();
		}
	}
}
