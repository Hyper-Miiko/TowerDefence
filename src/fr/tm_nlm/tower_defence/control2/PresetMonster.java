package fr.tm_nlm.tower_defence.control2;

public class PresetMonster {
	public static Monster annoyingDog() {
		Monster monster = new Monster();
		monster.setSpeed(30);
		monster.setStrength(0);
		monster.setShape(PresetShape.usualMonsterShape());
		return monster;
	}
}
