package mHUD.mObject.subWindow;

import mHUD.mObject.FVerticalButtonBox;
import mHUD.mObject.FVerticalFrame;
import mHUD.mObject.IPushButton;
import mHUD.mObject.MWindow;

public class MenuWindow extends MWindow{

	private FVerticalFrame mainFrame = new FVerticalFrame();
	private FVerticalButtonBox bBox = new FVerticalButtonBox();
	private IPushButton map1 = new IPushButton();
	private IPushButton map2 = new IPushButton();
	private IPushButton map3 = new IPushButton();
	private IPushButton map4 = new IPushButton();
	
	public MenuWindow(int i, int j, boolean cheat) {
		super(i,j);
		
		super.setMainFrame(mainFrame);
		mainFrame.addObject(bBox);
		mainFrame.setMinimumSize(i,j);
		mainFrame.setBackgroundColor(100,100,100);
		
		bBox.setMinimumSize(120,600);
		bBox.setBackgroundColor(50,50,50);
				
		bBox.addButton("Lavaland 3");
		bBox.addButton("Lavaland 2");
		bBox.addButton("Lavaland 1");
		
		bBox.addButton("Snowland 3");
		bBox.addButton("Snowland 2");
		bBox.addButton("Snowland 1");
		
		bBox.addButton("Grassland 3");
		bBox.addButton("Grassland 2");
		bBox.addButton("Grassland 1");
	}

	public int getSelection() {
		if(bBox.anyActive()) {
			int i = bBox.getPressed();
			bBox.setSelect(i, false);
			return bBox.getNumberOfButton()-i-1;	
		}
		else return -1;
	}
	
	public void run() {
		super.run();
	}
}
