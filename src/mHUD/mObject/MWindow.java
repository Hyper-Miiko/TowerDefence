package mHUD.mObject;

import mHUD.StdDraw;
import mHUD.geometric.Vector;

public class MWindow extends Thread{
	
	private MFrame mainFrame;
	private MItem focusItem;
	private boolean mousePressed;
	
	//Active et running sont des restes de mes tentatives de Threader (?)
	//cette class, l'idée étant de mettre en pause le thread puis de le relancer
	//Une idée stupide qui ralentissais tout le programme
	
	private boolean active = true;
	private boolean running = false;
	
	private final Vector windowSize;
	
	public MWindow(int x, int y) {
		if(x < 1 || y < 1)throw new IllegalArgumentException("The size of the window must be superior or equal to 1");
		windowSize = new Vector(2*x,2*y);
	}
	
	public void setActive(boolean a){
		active = a;
	}
	public boolean isRunning() {
		return running;
	}
	
	public void setMainFrame(MFrame mainFrame) {
		this.mainFrame = mainFrame;
		mainFrame.setWindowSize(windowSize);
	}

	public void run() {
		//while(true) {
				if(active && mainFrame != null) {
					running = true;
					if(mousePressed && !StdDraw.isMousePressed()) {
						focusItem = mainFrame.getFocalisedItem(StdDraw.mouseX(), StdDraw.mouseY());
					}
					
					if(focusItem != null) {
						
					}
					mousePressed = StdDraw.isMousePressed();
					
					mainFrame.refreshObject();
					mainFrame.draw();
					StdDraw.show();
				}
				running = false;
		//}
	}
}
