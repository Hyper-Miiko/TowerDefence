package TEST;

import java.util.LinkedList;

import mHUD.mGraphicEntity.GPictureEntity;
import mHUD.mGraphicEntity.GRectEntity;
import mHUD.mGraphicEntity.IGraphicView;
import mHUD.mGraphicEntity.MGraphicEntity;
import mHUD.mObject.FVerticalFrame;
import mHUD.mObject.MWindow;

public class TEST2 {

	public static void main(String[] args) {
		MWindow window = new MWindow(640,640);
		
		FVerticalFrame mainFrame = new FVerticalFrame();
		window.setMainFrame(mainFrame);
		
		IGraphicView view = new IGraphicView(640, 640,1);
		view.setBackgroundColor(20, 0, 20);
		mainFrame.addObject(view);
		
		GPictureEntity test = new GPictureEntity();
		test.setPicture("data/img/plane_chan.png");
		test.setPosition(320,320);
		view.addGraphicEntityAt(0,test);
		
		while(true) {

			window.run();
		}

	}

}
