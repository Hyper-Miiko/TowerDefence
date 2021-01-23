package fr.tm_nlm.tower_defence.control2;

public abstract class PresetWave {
	public static Wave test() {
		Monster monster;
		Wave wave = new Wave("Test");

		monster = PresetMonster.testingDog();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 10);
		return wave;
	}
	
	public static Wave grassland11() {
		Monster monster;
		Wave wave = new Wave("Grassland 1:1");

		monster = PresetMonster.grassland1();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 10);
		for(int i = 0; i < 19; i++) {
			monster = PresetMonster.grassland1();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		
		wave.setNextWave(grassland12());
		return wave;
	}
	public static Wave grassland12() {
		Monster monster;
		Wave wave = new Wave("Grassland 1:2");

		monster = PresetMonster.grassland2();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 30);
		for(int i = 0; i < 9; i++) {
			monster = PresetMonster.grassland2();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}

		wave.setNextWave(grassland13());
		return wave;
	}
	public static Wave grassland13() {
		Monster monster;
		Wave wave = new Wave("Grassland 1:3");

		monster = PresetMonster.grassland1();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 30);
		for(int i = 0; i < 14; i++) {
			monster = PresetMonster.grassland1();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		
		monster = PresetMonster.grassland2();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 5);
		for(int i = 0; i < 4; i++) {
			monster = PresetMonster.grassland2();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		
		return wave;
	}

	public static Wave grassland21() {
		Monster monster;
		Wave wave = new Wave("Grassland 2:1");

		monster = PresetMonster.grassland1();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 10);
		for(int i = 0; i < 14; i++) {
			monster = PresetMonster.grassland1();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		
		monster = PresetMonster.grassland2();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 5);
		for(int i = 0; i < 4; i++) {
			monster = PresetMonster.grassland2();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}

		wave.setNextWave(grassland22());
		return wave;
	}
	public static Wave grassland22() {
		Monster monster;
		Wave wave = new Wave("Grassland 2:2");

		monster = PresetMonster.grassland2();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 30);
		for(int i = 0; i < 4; i++) {
			monster = PresetMonster.grassland2();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		
		monster = PresetMonster.grassland3();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 5);
		for(int i = 0; i < 9; i++) {
			monster = PresetMonster.grassland3();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}

		wave.setNextWave(grassland23());
		return wave;
	}
	public static Wave grassland23() {
		Monster monster;
		Wave wave = new Wave("Grassland 2:3");

		monster = PresetMonster.grassland1();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 30);
		for(int i = 0; i < 14; i++) {
			monster = PresetMonster.grassland1();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		
		monster = PresetMonster.grassland2();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 5);
		for(int i = 0; i < 7; i++) {
			monster = PresetMonster.grassland2();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		
		monster = PresetMonster.grassland3();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 5);
		for(int i = 0; i < 9; i++) {
			monster = PresetMonster.grassland3();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		
		return wave;
	}
	
	public static Wave grassland31() {
		Monster monster;
		Wave wave = new Wave("Grassland 3:1");

		monster = PresetMonster.grassland1();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 10);
		for(int i = 0; i < 9; i++) {
			monster = PresetMonster.grassland1();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		
		monster = PresetMonster.grassland2();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 5);
		for(int i = 0; i < 3; i++) {
			monster = PresetMonster.grassland2();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		
		monster = PresetMonster.grassland3();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 5);
		for(int i = 0; i < 5; i++) {
			monster = PresetMonster.grassland3();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}

		wave.setNextWave(grassland32());
		return wave;
	}
	public static Wave grassland32() {
		Monster monster;
		Wave wave = new Wave("Grassland 3:2");

		monster = PresetMonster.grassland4();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 10);
		for(int i = 0; i < 7; i++) {
			monster = PresetMonster.grassland4();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}

		wave.setNextWave(grassland33());
		return wave;
	}
	public static Wave grassland33() {
		Monster monster;
		Wave wave = new Wave("Grassland 3:3");

		monster = PresetMonster.grassland1();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 10);
		for(int i = 0; i < 6; i++) {
			monster = PresetMonster.grassland1();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		
		monster = PresetMonster.grassland2();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 5);
		for(int i = 0; i < 2; i++) {
			monster = PresetMonster.grassland2();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		
		monster = PresetMonster.grassland3();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 5);
		for(int i = 0; i < 1; i++) {
			monster = PresetMonster.grassland3();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		
		monster = PresetMonster.grassland4();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 5);
		for(int i = 0; i < 5; i++) {
			monster = PresetMonster.grassland4();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}

		wave.setNextWave(grassland34());
		return wave;
	}
	public static Wave grassland34() {
		Monster monster;
		Wave wave = new Wave("Grassland 3:BOSS");

		monster = PresetMonster.grassland1();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 10);
		for(int i = 0; i < 6; i++) {
			monster = PresetMonster.grassland1();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		
		monster = PresetMonster.grassland2();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 5);
		for(int i = 0; i < 2; i++) {
			monster = PresetMonster.grassland2();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		
		monster = PresetMonster.grassland3();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 5);
		for(int i = 0; i < 1; i++) {
			monster = PresetMonster.grassland3();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		
		monster = PresetMonster.grassland4();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 5);
		for(int i = 0; i < 5; i++) {
			monster = PresetMonster.grassland4();
			monster.setPath(PresetPath.grassland());
			wave.add(monster, 1);
		}
		
		monster = PresetMonster.grassland5();
		monster.setPath(PresetPath.grassland());
		wave.add(monster, 10);
		
		return wave;
	}
}
