package fr.tm_nlm.tower_defence.control2;

import java.util.HashSet;

public class Map {
	private String background;
	private HashSet<Wave> waves;
	private Game game;
	
	{
		waves= new HashSet<>();
	}
	
	public void run() {
		for(Wave wave : waves) {
			wave.run();
		}
	}
	public void place(Monster monster) {
		monster.setPosition(monster.getPath().getPosition());
		monster.setGame(game);
		game.add(monster);
		monster.resetMove();
	}
	
	public void addWave(Wave wave) {
		wave.setMap(this);
		waves.add(wave);
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public void setGame(Game game) {
		this.game = game;
	}
//	public void setSize(int width, int height) {
//		if(width <= 0 || height <= 0) {
//			throw new IllegalArgumentException("Width and height must be over 0.");
//		}
//		this.width = width;
//		this.height = height;
//	}
}
