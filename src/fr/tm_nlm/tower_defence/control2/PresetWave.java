package fr.tm_nlm.tower_defence.control2;

public abstract class PresetWave {
	public static Wave intro() {
		Wave wave = new Wave("Tutoriel");
		
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
	public static Wave wave1() {
		Wave wave = new Wave("1");
		
		Monster monster;
		for(int i = 0; i < 20; i++) {
			monster = PresetMonster.drunkHuman();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		monster = PresetMonster.ufo();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 10);
		for(int i = 0; i < 4; i++) {
			monster = PresetMonster.ufo();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		
		return wave;
	}
	public static Wave toundraWave1() {
		Wave wave = new Wave("1");
		
		Monster monster;
		for(int i = 0; i < 20; i++) {
			monster = PresetMonster.drunkHuman();
			monster.setPath(PresetPath.toundra());
			wave.add(monster, 1);
		}
		monster = PresetMonster.ufo();
		monster.setPath(PresetPath.toundra());
		wave.add(monster, 10);
		for(int i = 0; i < 4; i++) {
			monster = PresetMonster.ufo();
			monster.setPath(PresetPath.toundra());
			wave.add(monster, 1);
		}
		
		return wave;
	}
	public static Wave trainingNewEnnemy() {
		Wave wave = new Wave("1");
		
		Monster monster;
		monster = PresetMonster.robotCop();
		monster.setPath(PresetPath.toundra());
		wave.add(monster, 1);
		
		monster = PresetMonster.shadyMan();
		monster.setPath(PresetPath.toundra());
		wave.add(monster, 1);
		
		monster = PresetMonster.theBeast();
		monster.setPath(PresetPath.toundra());
		wave.add(monster, 1);
		
		monster = PresetMonster.friendlyPlant();
		monster.setPath(PresetPath.toundra());
		wave.add(monster, 1);
		
		return wave;
	}
}
