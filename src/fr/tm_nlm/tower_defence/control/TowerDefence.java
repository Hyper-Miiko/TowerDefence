package fr.tm_nlm.tower_defence.control;

import mHUD.StdDraw;
import mHUD.mObject.subWindow.GameWindow;
import mHUD.mObject.subWindow.MenuWindow;

public class TowerDefence {

	public static void main(String[] args) throws InterruptedException {
		boolean cheat = false;
		if(args.length > 0) {
			for(String a : args) {
				if(a.equals("cheat")) {
					cheat = true;
					break;
				}
			}
		}
		MenuWindow menuWindow = new MenuWindow(980,714,cheat);
		GameWindow gameWindow = new GameWindow(980,714);
		
		StdDraw.setCanvasSize(980,714);
		StdDraw.enableDoubleBuffering();
		
		boolean inMenu = true;
		while(true) {
			if(inMenu) {
				menuWindow.run();
				int i = menuWindow.getSelection();
				if(i != -1) {
					gameWindow = new GameWindow(980,714);
					gameWindow.setMap(i);
					inMenu = false;
				} 
			}
			else {
				gameWindow.run();
				if(gameWindow.isGameOver()) {
					inMenu = true;
					menuWindow = new MenuWindow(980,714,cheat);
				}
			}
		}
	}
}
