package fr.tm_nlm.tower_defence.control2;

import java.util.HashSet;

public class Map {
	private int lives;
	private String background;
	private HashSet<Wave> waves;
	private Game game;
	
	{
		lives = 10;
		waves = new HashSet<>();
	}
	
	public void place(Monster monster) {
		monster.setPosition(monster.getPath().getPosition());
		monster.setGame(game);
		monster.setMap(this);
		game.add(monster);
		monster.resetMove();
	}
	public void removeLives(int nbrOfLivesLost) {
		lives = (nbrOfLivesLost > lives) ? 0 : lives - nbrOfLivesLost;
	}
	public void run() {
		for(Wave wave : waves) {
			wave.run();
		}
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
