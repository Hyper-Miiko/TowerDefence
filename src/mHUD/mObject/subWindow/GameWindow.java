package mHUD.mObject.subWindow;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import fr.tm_nlm.tower_defence.Constant;
import fr.tm_nlm.tower_defence.control2.ExistingTower;
import fr.tm_nlm.tower_defence.control2.Game;
import fr.tm_nlm.tower_defence.control2.PresetMap;
import fr.tm_nlm.tower_defence.control2.PresetTower;
import fr.tm_nlm.tower_defence.control2.Tower;
import fr.tm_nlm.tower_defence.control2.Vector;
import fr.tm_nlm.tower_defence.view.mHUDCompat.FieldToGraphic2;
import mHUD.mGraphicEntity.IGraphicView;
import mHUD.mObject.FHorizontalFrame;
import mHUD.mObject.FVerticalButtonBox;
import mHUD.mObject.FVerticalFrame;
import mHUD.mObject.ILabel;
import mHUD.mObject.IPushButton;
import mHUD.mObject.MWindow;

public class GameWindow extends MWindow {

	private double time;
	private ArrayList<Integer> fps = new ArrayList<>();
	private ArrayList<Integer> memory = new ArrayList<>();
	private boolean flag = false;
	
	private FVerticalFrame mainFrame = new FVerticalFrame();
	private FHorizontalFrame gameFrame = new FHorizontalFrame();
	private FHorizontalFrame dataFrame = new FHorizontalFrame();
	private FVerticalFrame buttonsframe = new FVerticalFrame();
	private IGraphicView view = new IGraphicView(900, 674,5);
	
	private FVerticalButtonBox towerButtons = new FVerticalButtonBox();
	private IPushButton upgradeButton = new IPushButton();
	private ILabel life = new ILabel();
	private ILabel temmies = new ILabel();
	private ILabel wave = new ILabel();
	private ILabel voidSpace = new ILabel();
	private ILabel Memory = new ILabel();
	private ILabel FPS = new ILabel();
	
	private FieldToGraphic2 ftg;
	
	long MD = 0;
	long UN = 0;
	
	Clip clip;
	
	public GameWindow(int x, int y) {
		super(x,y);
		
		this.setMainFrame(mainFrame);
		
		mainFrame.addObject(gameFrame);
		
		dataFrame.setMinimumSize(980,40);
		dataFrame.setHonrizontalAlignement(Constant.RIGHT);
		mainFrame.addObject(dataFrame);
		
		gameFrame.addObject(buttonsframe);
		
		view.setBackgroundColor(150, 0, 150);
		gameFrame.addObject(view);
		
		towerButtons.setMinimumSize(80,674);
		towerButtons.setBackgroundColor(50,50,50);
		buttonsframe.addObject(towerButtons);
		towerButtons.addButton("Dummy");
		towerButtons.addButton("Sans");
		towerButtons.addButton("Undyne");
		towerButtons.addButton("Metatton");
		towerButtons.addButton("Asriel");
		
		upgradeButton.setText("Uprgrade");
		upgradeButton.setSize(80,40);
		dataFrame.addObject(upgradeButton);
		
		life.setText("Life : 10");
		life.setSize(140,40);
		dataFrame.addObject(life);
		
		
		temmies.setText("Temmies : 1000");
		temmies.setSize(180,40);
		dataFrame.addObject(temmies);
		
		wave.setText("Wave : 1");
		wave.setSize(140,40);
		dataFrame.addObject(wave);
		
		voidSpace.setText("");
		voidSpace.setSize(280,40);
		voidSpace.setBackgroundColor(50,50,50);
		dataFrame.addObject(voidSpace);
		
		Memory.setText("");
		Memory.setSize(80,40);
		dataFrame.addObject(Memory);
		
		FPS.setText("");
		FPS.setSize(80,40);
		dataFrame.addObject(FPS);
	}
	public void loadMusic(String m) {
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(m));
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void run() {
		if(fps.size() > 100)fps.remove(0);
		fps.add((int)(1/(Game.time() - time)));
		
		Runtime run = Runtime.getRuntime();
		if(memory.size() > 100)memory.remove(0);
		memory.add((int) (run.totalMemory()-run.freeMemory())/1024/1024);
		
		int fpsMedian = 0;
		for(int i = 0; i < fps.size(); i++)fpsMedian+=fps.get(i);
		fpsMedian /=  fps.size();
		
		int memoryMedian = 0;
		for(int i = 0; i < memory.size(); i++)memoryMedian+=memory.get(i);
		memoryMedian /=  memory.size();
		
		FPS.setText(fpsMedian+"fps");
		Memory.setText(memoryMedian +"mb");
		
		temmies.setText("Temmies : "+(int)Game.getTemmies());
		life.setText("Life : "+Game.getLives());
		time = Game.time();
		
		super.run();
		ftg.run();
		
		ftg.setDisplaySlot(towerButtons.anyActive());
		
		if(upgradeButton.buttonPressed()) {
			//TODO la fonction qui teste si une tour PEUT évoluer
			if(towerButtons.isPressed(0) && Game.canEvolve(MD))
				UN = Game.evolveTower(MD);
			else if(towerButtons.isPressed(2) && Game.canEvolve(UN))
				UN = Game.evolveTower(UN);
			
			towerButtons.setSelect(0,false);
		}
		
		if(view.mousePressed() && !flag) {
			System.out.println(view.mouseX()+ " " +view.mouseY());
			if(towerButtons.isPressed(0))
				Game.placeTower(MD,new Vector(view.mouseX(), view.mouseY()));
			else if(towerButtons.isPressed(2))
				Game.placeTower(UN,new Vector(view.mouseX(), view.mouseY()));
			
			towerButtons.setSelect(0,false);
			
			flag = true;
		}
		if(!view.mousePressed()) {
			flag = false;
		}
		
		
	}
	public void setMap(int selection) {
		if(selection == 1) {
			Game.set(PresetMap.grasslandIntro());
			loadMusic("data/music/level1.wav");
		}
		else {
			Game.set(PresetMap.toundra());
			loadMusic("data/music/level2.wav");
		}
		
		//Apparement il n'y a pas plus simple...
		Tower md = PresetTower.madDummy();
		Tower un = PresetTower.undyne();
		ExistingTower.add(md);
		ExistingTower.add(un);
		MD = md.getId();
		UN = un.getId();
				
		ftg = new FieldToGraphic2(view);
	}
}
