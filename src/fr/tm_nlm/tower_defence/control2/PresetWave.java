package fr.tm_nlm.tower_defence.control2;

public abstract class PresetWave {
	public static Wave intro() {
		Wave wave = new Wave();
		
		Monster monster = PresetMonster.annoyingDog();
		monster.setPath(PresetPath.grassland());
		//wave.add(monster, 1);
		monster = PresetMonster.drunkHuman();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 1);
		monster = PresetMonster.ufo();
		monster.setPath(PresetPath.grassland());
		//wave.add(monster, 1);
		
		return wave;
	}
}
