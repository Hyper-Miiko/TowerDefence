package TEST;

import fr.tm_nlm.tower_defence.Constant;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.Field.Action;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.entity.Monster;
import fr.tm_nlm.tower_defence.control.entity.PathNode;
import fr.tm_nlm.tower_defence.control.entity.PresetMonster;
import fr.tm_nlm.tower_defence.control.entity.PresetTower;
import fr.tm_nlm.tower_defence.control.entity.Tower;
import fr.tm_nlm.tower_defence.view.mHUDCompat.FieldToGraphic;
import mHUD.mGraphicEntity.GCircleEntity;
import mHUD.mGraphicEntity.GPictureEntity;
import mHUD.mGraphicEntity.IGraphicView;
import mHUD.mObject.FHorizontalFrame;
import mHUD.mObject.FVerticalFrame;
import mHUD.mObject.FHorizontalButtonBox;
import mHUD.mObject.IPushButton;
import mHUD.mObject.FSpinBox;
import mHUD.mObject.ILabel;
import mHUD.mObject.MWindow;

public class Test {
	private static final int width = 520;
	private static final int height = 640;
	private static boolean end;
	public static void main(String[] args) throws InterruptedException {
		end = false;
		
		MWindow window = new MWindow(640,640);
		
		FVerticalFrame mainFrame = new FVerticalFrame();
		mainFrame.setMinimumSize(640,640);
		mainFrame.setBackgroundColor(100,0,100);
		window.setMainFrame(mainFrame);
		
		FHorizontalFrame towerFrame = new FHorizontalFrame();
		mainFrame.addObject(towerFrame);
		
		FHorizontalButtonBox bBoxTower = new FHorizontalButtonBox();
		bBoxTower.setMinimumSize(500,80);
		towerFrame.addObject(bBoxTower);
		
		IPushButton upgrade = new IPushButton();
		upgrade.setText("Upgrade Tower");
		upgrade.setSize(140,80);
		towerFrame.addObject(upgrade);
		
		IGraphicView view = new IGraphicView(height, width,5);
		view.setBackgroundColor(0, 0, 0);
		mainFrame.addObject(view);
		
		FHorizontalFrame menu = new FHorizontalFrame();
		menu.setMinimumSize(640,40);
		menu.setHonrizontalAlignement(Constant.RIGHT);
		mainFrame.addObject(menu);
		
		ILabel life = new ILabel();
		life.setSize(80,40);
		menu.addObject(life);
		
		ILabel gold = new ILabel();
		gold.setSize(120,40);
		menu.addObject(gold);
		
		//FSpinBox spin = new FSpinBox();
		//menu.addObject(spin);
		//spin.setMinimumSize(100,40);
		
		IPushButton spawnKumo = new IPushButton();
		spawnKumo.setSize(140,40);
		spawnKumo.setText("Spawn");
		menu.addObject(spawnKumo);
		
		FHorizontalButtonBox bBoxMonster = new FHorizontalButtonBox();
		bBoxMonster.setMinimumSize(240,40);
		menu.addObject(bBoxMonster);

		Field field = new Field();

		PathNode pathNodeCenter  = field.createPathNode(height/2, width/2, false);
		
		PathNode pathNodeN  = field.createPathNode(height*0.5, width*0.2, false);
		PathNode pathNodeNO = field.createPathNode(height*0.8, width*0.2, false);
		PathNode pathNodeO  = field.createPathNode(height*0.8, width*0.5, false);
		PathNode pathNodeSO = field.createPathNode(height*0.8, width*0.8, false);
		PathNode pathNodeS  = field.createPathNode(height*0.5, width*0.8, false);
		PathNode pathNodeSE = field.createPathNode(height*0.2, width*0.8, true);
		PathNode pathNodeE  = field.createPathNode(height*0.2, width*0.5, false);
		PathNode pathNodeNE = field.createPathNode(height*0.2, width*0.2, false);
		
		pathNodeCenter.link(pathNodeN);
		pathNodeCenter.link(pathNodeNO);
		pathNodeN.link(pathNodeNE);
		pathNodeN.link(pathNodeNO);
		pathNodeNO.link(pathNodeO);
		pathNodeO.link(pathNodeS);
		pathNodeSO.link(pathNodeE);
		pathNodeSO.link(pathNodeS);
		pathNodeS.link(pathNodeSE);
		pathNodeE.link(pathNodeNE);
		
		//DirectoLinko
		//pathNodeCenter.link(pathNodeSE);
		pathNodeSE.calcWay();

		FieldToGraphic fieldToGraphic = new FieldToGraphic(field,view);
		
		/*field.setName("Field");
		field.start();
		window.setPriority(2);
		Thread.sleep(20);
		window.setName("Window");
		window.start();
		window.setPriority(2);
		Thread.sleep(20);
		fieldToGraphic.setName("Glassyland");
		fieldToGraphic.start();
		window.setPriority(1);
		Thread.sleep(20);*/
		
		bBoxMonster.addButton("Kumo");
		bBoxMonster.addButton("Airplan Chan");
		
		bBoxTower.addButton("Mad Dummy");
		bBoxTower.addButton("Mettaton");
		bBoxTower.addButton("Asriel");
		bBoxTower.addButton("Sans");
		bBoxTower.addButton("Undyne");
		
		Tower madDummy = PresetTower.buildMadDummy(field);
		Tower mettaton = PresetTower.buildMettaton(field);
		Tower asriel = PresetTower.buildAsriel(field);
		Tower sans = PresetTower.buildSans(field);
		Tower undyne = PresetTower.buildUndyne(field);
		

		
		boolean flag = false;
		while(true) {
			//System.out.println(field);
			
			field.run();
			fieldToGraphic.run();
			window.run();
			
			life.setText("Life : " + field.getLives());
			gold.setText("Temmies : " + field.getTemmies());
			
			
			if(spawnKumo.buttonReleased()) {
				if(bBoxMonster.isPressed(0))field.placeMonster(PresetMonster.buildKumo(field), pathNodeCenter);
				if(bBoxMonster.isPressed(1))field.placeMonster(PresetMonster.buildAirplanChan(field), pathNodeCenter);
			}
			if(upgrade.buttonReleased()) {
				if(bBoxTower.isPressed(0) && madDummy.canEvolve())field.evolveTower(madDummy);
				if(bBoxTower.isPressed(1) && mettaton.canEvolve())field.evolveTower(mettaton);
				if(bBoxTower.isPressed(2) && asriel.canEvolve())field.evolveTower(asriel);
				if(bBoxTower.isPressed(3) && sans.canEvolve())field.evolveTower(sans);
				if(bBoxTower.isPressed(4) && undyne.canEvolve())field.evolveTower(undyne);
			}
			
			if(view.mousePressed() && !flag) {
				if(bBoxTower.isPressed(0))field.placeTower(madDummy,view.mouseX(), view.mouseY());
				if(bBoxTower.isPressed(1))field.placeTower(mettaton,view.mouseX(), view.mouseY());
				if(bBoxTower.isPressed(2))field.placeTower(asriel,view.mouseX(), view.mouseY());
				if(bBoxTower.isPressed(3))field.placeTower(sans,view.mouseX(), view.mouseY());
				if(bBoxTower.isPressed(4))field.placeTower(undyne,view.mouseX(), view.mouseY());
				
				flag = true;
			}
			if(!view.mousePressed()) {
				flag = false;
			}
			Thread.sleep(10);
		}
	}
}
