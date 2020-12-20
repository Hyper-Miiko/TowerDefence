package mHUD;

public class MWindow {
	
	private MFrame mainFrame;
	
	public MWindow() {
		StdDraw.enableDoubleBuffering();
	}
	
	public void setMainFrame(MFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	public void draw() {
		mainFrame.draw();
		StdDraw.show();
	}
}
