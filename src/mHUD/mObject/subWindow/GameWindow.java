package mHUD.mObject.subWindow;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

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
import mHUD.mObject.IToogleButton;
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
	private IToogleButton buttonDummy;
	private IToogleButton buttonSans;
	private IToogleButton buttonUndyne;
	private IToogleButton buttonAsriel;
	private ILabel life = new ILabel();
	private ILabel temmies = new ILabel();
	private ILabel wave = new ILabel();
	private ILabel voidSpace = new ILabel();
	private ILabel Memory = new ILabel();
	private ILabel FPS = new ILabel();
	
	private FieldToGraphic2 ftg;
	
	long dummyId = 0;
	long undyneId = 0;
	long sansId = 0;
	long asrielId = 0;
	
	int mapId;
	
	boolean gameOver = false;
	
	Clip clip;
	Clip sound;
	
	public GameWindow(int x, int y) {
		super(x,y);
		
		this.setMainFrame(mainFrame);
		
		mainFrame.addObject(gameFrame);
		
		dataFrame.setMinimumSize(980,40);
		dataFrame.setHonrizontalAlignement(Constant.RIGHT);
		mainFrame.addObject(dataFrame);
		
		gameFrame.addObject(buttonsframe);
		
		towerButtons.setMinimumSize(80,674);
		towerButtons.setBackgroundColor(50,50,50);
		buttonsframe.addObject(towerButtons);
		buttonDummy = towerButtons.addButton("");
		buttonSans = towerButtons.addButton("");
		buttonUndyne = towerButtons.addButton("");
		buttonAsriel = towerButtons.addButton("");
		
		upgradeButton.setText("");
		upgradeButton.setSize(240,40);
		dataFrame.addObject(upgradeButton);
		
		life.setText("");
		life.setSize(140,40);
		dataFrame.addObject(life);
		
		
		temmies.setText("");
		temmies.setSize(180,40);
		dataFrame.addObject(temmies);
		
		wave.setText("");
		wave.setSize(140,40);
		dataFrame.addObject(wave);
		
		voidSpace.setText("");
		voidSpace.setSize(120,40);
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
	public void playSound(String m) {
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(m));
			sound = AudioSystem.getClip();
			sound.open(audioIn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sound.start();
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
		wave.setText(Game.getWave());
		time = Game.time();
		
		super.run();
		ftg.run();
		
		ftg.setDisplaySlot(towerButtons.anyActive());
		
		if(upgradeButton.buttonPressed()) {
			if(towerButtons.isPressed(0) && Game.canEvolve(dummyId)) {
				dummyId = Game.evolveTower(dummyId);
				buttonDummy.setText(ExistingTower.get(dummyId).getName());
			} else if(towerButtons.isPressed(2) && Game.canEvolve(undyneId)) {
				undyneId = Game.evolveTower(undyneId);
				buttonUndyne.setText(ExistingTower.get(undyneId).getName());
			} else if(towerButtons.isPressed(1) && Game.canEvolve(sansId)) {
				sansId = Game.evolveTower(sansId);
				buttonSans.setText(ExistingTower.get(sansId).getName());
			} else if(towerButtons.isPressed(3) && Game.canEvolve(asrielId)) {
				asrielId = Game.evolveTower(asrielId);
				buttonAsriel.setText(ExistingTower.get(asrielId).getName());
			}
			towerButtons.setSelect(0,false);
			ftg.setGraphicRange(dummyId,false);
		}
		
		if(towerButtons.isPressed(0)) {
			if(ExistingTower.get(dummyId).isOnScreen()) {
				ftg.setGraphicRange(dummyId,true);
				upgradeButton.setText("Upgrade : " + Game.evolvePrice(dummyId));
			}else upgradeButton.setText("Cost : "+ExistingTower.get(dummyId).getPrice());
		}
		else if(towerButtons.isPressed(1)) {
			if(ExistingTower.get(sansId).isOnScreen()) {
				ftg.setGraphicRange(sansId,true);
				upgradeButton.setText("Upgrade : " + Game.evolvePrice(sansId));
			}else upgradeButton.setText("Cost : "+ExistingTower.get(sansId).getPrice());
		}
		else if(towerButtons.isPressed(2)) {
			if(ExistingTower.get(undyneId).isOnScreen()) {
				ftg.setGraphicRange(undyneId,true);
				upgradeButton.setText("Upgrade : " + Game.evolvePrice(undyneId));
			}else upgradeButton.setText("Cost : "+ExistingTower.get(undyneId).getPrice());
		}
		else if(towerButtons.isPressed(3)) {
			if(ExistingTower.get(asrielId).isOnScreen()) {
				ftg.setGraphicRange(asrielId,true);
				upgradeButton.setText("Upgrade : " + Game.evolvePrice(asrielId));
			}else upgradeButton.setText("Cost : "+ExistingTower.get(asrielId).getPrice());
			
		}
		else {
			upgradeButton.setText("");
			ftg.setGraphicRange(dummyId,false);
			ftg.setGraphicRange(sansId,false);
			ftg.setGraphicRange(undyneId,false);
			ftg.setGraphicRange(asrielId,false);
		}
		
		if(view.mousePressed() && !flag) {
			if(towerButtons.isPressed(0))
				Game.placeTower(dummyId,new Vector(view.mouseX(), view.mouseY()));
			else if(towerButtons.isPressed(1))
				Game.placeTower(sansId,new Vector(view.mouseX(), view.mouseY()));
			else if(towerButtons.isPressed(2))
				Game.placeTower(undyneId,new Vector(view.mouseX(), view.mouseY()));
			else if(towerButtons.isPressed(3))
				Game.placeTower(asrielId, new Vector(view.mouseX(), view.mouseY()));
			
			towerButtons.setSelect(0,false);

			
			flag = true;
		}
		if(!view.mousePressed()) {
			flag = false;
		}
		
		
		if(Game.isOver() && !gameOver) {
			if(clip != null) {
				clip.stop();
			}
			gameOver = true;
			view.setLoseScreen();
		}
		
		HashSet<String> sounds = Game.getSound();
		for(String sound : sounds) {
			playSound(sound);
		}
	}
	public void setMap(int selection) {
		mapId = selection;
		gameOver = false;
		
		if(selection == 0 ) {
			Game.set(PresetMap.grassland1());
			loadMusic("data/music/level1.wav");
		} else if(selection == 1) {
			Game.set(PresetMap.grassland2());
			loadMusic("data/music/level1.wav");
		} else if(selection == 2) {
			Game.set(PresetMap.grassland3());
			loadMusic("data/music/level1.wav");
		} else if (selection == 3){
			Game.set(PresetMap.toundra1());
			loadMusic("data/music/level2.wav");
		} else if (selection == 4){
			Game.set(PresetMap.toundra2());
			loadMusic("data/music/level2.wav");
		} else if (selection == 5){
			Game.set(PresetMap.toundra3());
			loadMusic("data/music/level2.wav");
		} else if (selection == 6){
			Game.set(PresetMap.volcano1());
			loadMusic("data/music/level3.wav");
		} else if (selection == 7){
			Game.set(PresetMap.volcano2());
			loadMusic("data/music/level3.wav");
		} else if (selection == 8){
			Game.set(PresetMap.volcano3());
			loadMusic("data/music/level3.wav");
		} else if (selection == 9){
			Game.set(PresetMap.test());
			//loadMusic("data/music/level3.wav");
		}
		gameFrame.removeObject(view);
		view = new IGraphicView(900, 674,5);
		view.setBackgroundColor(150, 0, 150);
		gameFrame.addObject(view);
		
		ftg = new FieldToGraphic2(view);
		
		dummyId = ExistingTower.add(PresetTower.madDummy());
		undyneId = ExistingTower.add(PresetTower.undyne());
		sansId = ExistingTower.add(PresetTower.chillSans());
		asrielId = ExistingTower.add(PresetTower.asriel());
		buttonDummy.setText(ExistingTower.get(dummyId).getName());
		buttonUndyne.setText(ExistingTower.get(undyneId).getName());
		buttonSans.setText(ExistingTower.get(sansId).getName());
		buttonAsriel.setText(ExistingTower.get(asrielId).getName());
	}
	public boolean isGameOver() {
		return gameOver && view.hasStoped();
	}
}
