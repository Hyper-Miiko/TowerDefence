package mHUD.mObject;

import mHUD.StdDraw;
import mHUD.geometric.Vector;

public class MWindow {
	
	private MFrame mainFrame;
	private final Vector windowSize;
	
	public MWindow(int x, int y) {
		if(x < 1 || y < 1)throw new IllegalArgumentException("The size of the window must be superior or equal to 1");
		StdDraw.setCanvasSize(x, y);
		windowSize = new Vector(2*x,2*y);
		StdDraw.enableDoubleBuffering();
	}
	
	public void setMainFrame(MFrame mainFrame) {
		this.mainFrame = mainFrame;
		mainFrame.setWindowSize(windowSize);
	}
	
	public void draw() {
		if(mainFrame != null) {
			mainFrame.draw();
		}
		StdDraw.pause(20);
		StdDraw.show();
	}
}
