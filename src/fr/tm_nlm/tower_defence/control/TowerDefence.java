package fr.tm_nlm.tower_defence.control;

import mHUD.mGraphicEntity.*;
import mHUD.mObject.*;
import fr.tm_nlm.tower_defence.Constant;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle;
import fr.tm_nlm.tower_defence.control.entity.Monster;
import fr.tm_nlm.tower_defence.control.entity.Tower;
import fr.tm_nlm.tower_defence.control.entity.PresetTower;
import fr.tm_nlm.tower_defence.view.mHUDCompat.FieldToGraphic;

public class TowerDefence {

	public static void main(String[] args) throws InterruptedException{
		
		MWindow window = new MWindow(800, 600);
		
		FVerticalFrame mainFrame = new FVerticalFrame();
		mainFrame.setMinimumSize(800, 600);
		mainFrame.setBackgroundColor(100,0,100);
		window.setMainFrame(mainFrame);
		
		IGraphicView view = new IGraphicView(600, 600);
		view.setBackgroundColor(0, 0, 0);
		mainFrame.addObject(view);
		
		Field field = new Field(600, 600);
		
		FieldToGraphic fTg = new FieldToGraphic(field,view);
		
		field.start();
		window.start();
		fTg.start();
		
		boolean flag = false;
		while(true) {
			if(view.mousePressed() && !flag) {
				System.out.println("new Tower");
				Tower m = PresetTower.buildMadDummy(field);
				flag = true;
			}
			if(!view.mousePressed()) {
				flag = false;
			}
		}
	}
}
