package fr.tm_nlm.tower_defence.control;

import mHUD.StdDraw;
import mHUD.mGraphicEntity.GCircleEntity;
import mHUD.mGraphicEntity.GRectEntity;
import mHUD.mGraphicEntity.IGraphicView;
import mHUD.mObject.FVerticalFrame;
import mHUD.mObject.MWindow;

public class TowerDefence {

	public static void main(String[] args) throws InterruptedException{
		
		MWindow window = new MWindow(800, 600);
		
		FVerticalFrame mainFrame = new FVerticalFrame();
		window.setMainFrame(mainFrame);
		mainFrame.setMinimumSize(800,600);
		
		IGraphicView graphic = new IGraphicView(800,600);
		mainFrame.addObject(graphic);
		
		GRectEntity rectEntity = new GRectEntity(0,0,80,80);
		graphic.addGraphicEntity(rectEntity);

		GCircleEntity circleEntity = new GCircleEntity(0,0,60);
		graphic.addGraphicEntity(circleEntity);
		
		while(true) {
			window.draw();
			
			rectEntity.setBackgroundColor((int)(255*StdDraw.mouseX()), 150, (int)(255*StdDraw.mouseY()));
			rectEntity.setPosition(StdDraw.mouseX()*800,600-StdDraw.mouseY()*600);
			
		}
	}
}
