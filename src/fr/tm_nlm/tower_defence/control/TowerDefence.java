package fr.tm_nlm.tower_defence.control;

import static fr.tm_nlm.tower_defence.Constant.*;

import java.awt.Color;

import fr.tm_nlm.tower_defence.control.entity.Monster;
import mHUD.StdDraw;
import mHUD.geometric.Circle;
import mHUD.geometric.Rect;
import mHUD.mGraphicEntity.GCircleEntity;
import mHUD.mGraphicEntity.GRectEntity;
import mHUD.mGraphicEntity.IGraphicView;
import mHUD.mObject.FHorizontalFrame;
import mHUD.mObject.FVerticalFrame;
import mHUD.mObject.IPushButton;
import mHUD.mObject.ITextItem;
import mHUD.mObject.MWindow;

public class TowerDefence {


	
	public static void main(String[] args) throws InterruptedException{
		
		
		
		MWindow window = new MWindow(800, 600);
		
		FVerticalFrame mainFrame = new FVerticalFrame();
		window.setMainFrame(mainFrame);
		mainFrame.setMinimumSize(800,600);
		mainFrame.setVerticalAlignement(TOP);
		mainFrame.setBackgroundColor(150, 150, 150);
		mainFrame.setLineColor(150, 150, 150);
		
		IGraphicView graphic = new IGraphicView();
		mainFrame.addObject(graphic);
		graphic.setSize(800,560);
		
		FHorizontalFrame ressourceTab = new FHorizontalFrame();
		mainFrame.addObject(ressourceTab);
		ressourceTab.setMinimumSize(800,40);
		ressourceTab.setVerticalAlignement(TOP);
		ressourceTab.setHonrizontalAlignement(RIGHT);
		ressourceTab.setBackgroundColor(50, 50, 50);
		
		ITextItem gold = new ITextItem();
		ressourceTab.addObject(gold);
		gold.setText("Gold");
		gold.setSize(80,40);
		gold.setBackgroundColor(255, 255, 0);
		
		
		ITextItem life = new ITextItem();
		ressourceTab.addObject(life);
		life.setText("LIFE");
		life.setSize(80,40);
		life.setBackgroundColor(255, 0, 0);
		life.setTextColor(255, 255, 255);
		
		IPushButton buttonTest = new IPushButton();
		buttonTest.setText("test");
		buttonTest.setSize(120,40);
		ressourceTab.addObject(buttonTest);
		
		Rect rect = new Rect(50,50,50,50);
		GRectEntity rectEntity = new GRectEntity(rect);
		graphic.addGraphicEntity(rectEntity);
		
		Circle circle = new Circle(50,50,50);
		GCircleEntity circleEntity = new GCircleEntity(circle);
		graphic.addGraphicEntity(circleEntity);
		
		while(true) {
			window.draw();
			
			circleEntity.setBackgroundColor((int)(255*StdDraw.mouseX()), 150, (int)(255*StdDraw.mouseY()));
			circle.setPosition(StdDraw.mouseX()*800-25.,StdDraw.mouseY()*600+25.);
			
		}
	}
}
