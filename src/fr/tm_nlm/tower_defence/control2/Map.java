package fr.tm_nlm.tower_defence.control2;

import java.util.HashSet;

public class Map {
	private boolean over;
	private int lives;
	private HashSet<Slot> slots;
	private Wave wave;
	private Game game;
	private String background;
	
	{
		lives = 10;
		over = false;
		slots = new HashSet<>();
	}

	public void place(Bullet bullet, Vector position) {
		bullet.setPosition(position);
		bullet.setGame(game);
		game.add(bullet);
		bullet.resetMove();
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
		if(wave != null) {
			if(wave.isOver()) {
				if(wave.isLast()) {
					over = true;
				} else {
					wave = wave.getNextWave();
				}
			}
			wave.run();
		}
	}
	
	public void addSlot(Slot slot) {
		slots.add(slot);
	}
	public void setWave(Wave wave) {
		wave.setMap(this);
		this.wave = wave;
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
	public HashSet<Slot> getSlots() {
		return slots;
	}
	public String getBackground() {
		return "data/img/" + background + ".png";
	}
	public int getLives() {
		return lives;
	}
	public String getWaveName() {
		if(over) {
			return "No more pain!";
		}
		return wave.getName();
	}
	public boolean isOver() {
		return over;
	}
}
