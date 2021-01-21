package fr.tm_nlm.tower_defence.control2;

public class PresetMonster {
	public static Monster annoyingDog() {
		Monster monster = new Monster();
		monster.setSpeed(50);
		monster.setStrength(0);
		monster.setShape(PresetShape.circle(20));
		monster.setMaxHealth(10000);
		return monster;
	}
	public static Monster drunkHuman() {
		Monster monster = new Monster();
		monster.setSpeed(50);
		monster.setShape(PresetShape.rect(34, 50));
		monster.setMaxHealth(20);
		monster.setImage("drunkHuman");
		return monster;
	}
	public static Monster ufo() {
		Monster monster = new Monster();
		monster.setSpeed(50);
		monster.setShape(PresetShape.rect(34, 50));
		monster.setMaxHealth(10);
		monster.setFly(true);
		monster.setImage("ufo");
		return monster;
	}
}
