package mHUD.mObject.subWindow;

import mHUD.mObject.FVerticalFrame;
import mHUD.mObject.IPushButton;
import mHUD.mObject.MWindow;

public class MenuWindow extends MWindow{

	private FVerticalFrame mainFrame = new FVerticalFrame();
	private IPushButton map1 = new IPushButton();
	private IPushButton map2 = new IPushButton();
	private IPushButton map3 = new IPushButton();
	private IPushButton map4 = new IPushButton();
	
	public MenuWindow(int i, int j) {
		super(i,j);
		
		super.setMainFrame(mainFrame);
		
		map1.setText("Map 1");
		map1.setSize(80,40);
		mainFrame.addObject(map1);
		
		map2.setText("Map 2");
		map2.setSize(80,40);
		mainFrame.addObject(map2);
		
		map3.setText("Map 3");
		map3.setSize(80,40);
		mainFrame.addObject(map3);
		
		map4.setText("Map 4");
		map4.setSize(80,40);
		mainFrame.addObject(map4);
	}

	public int getSelection() {
		if(map1.buttonPressed())return 1;
		else if(map2.buttonPressed())return 2;
		else if(map3.buttonPressed())return 3;
		else if(map4.buttonPressed())return 4;
		else return 0;
	}
	
	public void run() {
		super.run();
	}
}
