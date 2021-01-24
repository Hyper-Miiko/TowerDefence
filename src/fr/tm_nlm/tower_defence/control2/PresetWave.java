package fr.tm_nlm.tower_defence.control2;

public abstract class PresetWave {
	public static Wave test() {
		Monster monster;
		Wave wave = new Wave("TestWave");

		monster = PresetMonster.testingDog();
		add(wave, monster, PresetPath.toundraLeft(), 4, 1);
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

	
	public static Wave toundra11() {
		Wave wave = new Wave("Icefield 1:1");
		
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 1, 10);
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 15, 1);
		
		wave.setNextWave(toundra12());
		return wave;
	}
	private static Wave toundra12() {
		Wave wave = new Wave("Icefield 1:2");
		
		add(wave, PresetMonster.toundra2(), PresetPath.toundraLeft(), 1, 30);
		add(wave, PresetMonster.toundra2(), PresetPath.toundraLeft(), 7, 1);
		
		wave.setNextWave(toundra13());
		return wave;
	}
	private static Wave toundra13() {
		Wave wave = new Wave("Icefield 1:3");
		
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 1, 30);
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 11, 1);
		
		add(wave, PresetMonster.toundra2(), PresetPath.toundraTop(), 1, 5);
		add(wave, PresetMonster.toundra2(), PresetPath.toundraTop(), 5, 1);
		
		wave.setNextWave(toundra14());
		return wave;
	}
	private static Wave toundra14() {
		Wave wave = new Wave("Icefield 1:4");
		
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 1, 30);
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 19, 1);
		
		add(wave, PresetMonster.toundra2(), PresetPath.toundraTop(), 1, 5);
		add(wave, PresetMonster.toundra2(), PresetPath.toundraTop(), 9, 1);
		
		return wave;
	}
	
	public static Wave toundra21() {
		Wave wave = new Wave("Icefield 2:1");
		
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 1, 10);
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 11, 1);
		
		add(wave, PresetMonster.toundra2(), PresetPath.toundraTop(), 1, 5);
		add(wave, PresetMonster.toundra2(), PresetPath.toundraTop(), 5, 1);
		
		wave.setNextWave(toundra22());
		return wave;
	}
	private static Wave toundra22() {
		Wave wave = new Wave("Icefield 2:2");
		
		add(wave, PresetMonster.toundra3(), PresetPath.toundraLeft(), 1, 30);
		add(wave, PresetMonster.toundra3(), PresetPath.toundraLeft(), 2, 1);
		
		wave.setNextWave(toundra23());
		return wave;
	}
	private static Wave toundra23() {
		Wave wave = new Wave("Icefield 2:3");
		
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 1, 30);
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 7, 1);
		
		add(wave, PresetMonster.toundra2(), PresetPath.toundraTop(), 1, 5);
		add(wave, PresetMonster.toundra2(), PresetPath.toundraTop(), 3, 1);
		
		add(wave, PresetMonster.toundra3(), PresetPath.toundraLeft(), 1, 5);
		add(wave, PresetMonster.toundra3(), PresetPath.toundraLeft(), 1, 1);
		
		wave.setNextWave(toundra24());
		return wave;
	}
	private static Wave toundra24() {
		Wave wave = new Wave("Icefield 2:4");
		
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 1, 30);
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 11, 1);
		
		add(wave, PresetMonster.toundra2(), PresetPath.toundraTop(), 1, 5);
		add(wave, PresetMonster.toundra2(), PresetPath.toundraTop(), 5, 1);
		
		add(wave, PresetMonster.toundra3(), PresetPath.toundraLeft(), 1, 5);
		add(wave, PresetMonster.toundra3(), PresetPath.toundraLeft(), 2, 1);
		
		return wave;
	}
	
	public static Wave toundra31() {
		Wave wave = new Wave("Icefield 3:1");
		
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 1, 10);
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 5, 1);
		
		add(wave, PresetMonster.toundra2(), PresetPath.toundraTop(), 1, 5);
		add(wave, PresetMonster.toundra2(), PresetPath.toundraTop(), 2, 1);
		
		add(wave, PresetMonster.toundra3(), PresetPath.toundraLeft(), 1, 5);
		add(wave, PresetMonster.toundra3(), PresetPath.toundraLeft(), 1, 1);
		
		wave.setNextWave(toundra32());
		return wave;
	}
	private static Wave toundra32() {
		Wave wave = new Wave("Icefield 3:2");
		
		add(wave, PresetMonster.toundra4(), PresetPath.toundraTop(), 1, 30);
		add(wave, PresetMonster.toundra4(), PresetPath.toundraTop(), 3, 1);

		wave.setNextWave(toundra33());
		return wave;
	}
	private static Wave toundra33() {
		Wave wave = new Wave("Icefield 3:3");
		
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 1, 30);
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 3, 1);
		
		add(wave, PresetMonster.toundra2(), PresetPath.toundraTop(), 1, 5);
		add(wave, PresetMonster.toundra2(), PresetPath.toundraTop(), 1, 1);
		
		add(wave, PresetMonster.toundra3(), PresetPath.toundraLeft(), 1, 5);
		
		add(wave, PresetMonster.toundra4(), PresetPath.toundraTop(), 1, 5);
		add(wave, PresetMonster.toundra4(), PresetPath.toundraTop(), 1, 1);

		wave.setNextWave(toundra34());
		return wave;
	}
	private static Wave toundra34() {
		Wave wave = new Wave("Icefield 3:4");
		
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 1, 30);
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 5, 1);
		
		add(wave, PresetMonster.toundra2(), PresetPath.toundraTop(), 1, 5);
		add(wave, PresetMonster.toundra2(), PresetPath.toundraTop(), 2, 1);
		
		add(wave, PresetMonster.toundra3(), PresetPath.toundraLeft(), 1, 5);
		add(wave, PresetMonster.toundra3(), PresetPath.toundraLeft(), 1, 1);
		
		add(wave, PresetMonster.toundra4(), PresetPath.toundraTop(), 1, 5);
		add(wave, PresetMonster.toundra4(), PresetPath.toundraTop(), 2, 1);

		wave.setNextWave(toundra35());
		return wave;
	}
	private static Wave toundra35() {
		Wave wave = new Wave("Icefield 3:BOSS");
		
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 1, 30);
		add(wave, PresetMonster.toundra1(), PresetPath.toundraLeft(), 5, 1);
		
		add(wave, PresetMonster.toundra2(), PresetPath.toundraTop(), 1, 5);
		add(wave, PresetMonster.toundra2(), PresetPath.toundraTop(), 2, 1);
		
		add(wave, PresetMonster.toundra3(), PresetPath.toundraLeft(), 1, 5);
		add(wave, PresetMonster.toundra3(), PresetPath.toundraLeft(), 1, 1);
		
		add(wave, PresetMonster.toundra4(), PresetPath.toundraTop(), 1, 5);
		add(wave, PresetMonster.toundra4(), PresetPath.toundraTop(), 2, 1);

		add(wave, PresetMonster.toundra5(), PresetPath.toundraLeft(), 1, 10);
		
		return wave;
	}
	
	
	public static Wave volcano11() {
		Wave wave = new Wave("Volcano 1:1");
		
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 1, 10);
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 19, 1);
		
		wave.setNextWave(volcano12());
		return wave;
	}
	private static Wave volcano12() {
		Wave wave = new Wave("Volcano 1:2");
		
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 1, 30);
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 14, 1);
		
		wave.setNextWave(volcano13());
		return wave;
	}
	private static Wave volcano13() {
		Wave wave = new Wave("Volcano 1:3");
		
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 1, 30);
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 15, 1);
		
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 11, 1);
		
		wave.setNextWave(volcano14());
		return wave;
	}
	private static Wave volcano14() {
		Wave wave = new Wave("Volcano 1:4");
		
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 1, 30);
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 9, 1);
		
		wave.setNextWave(volcano15());
		return wave;
	}
	private static Wave volcano15() {
		Wave wave = new Wave("Volcano 1:5");
		
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 1, 30);
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 19, 1);
		
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 14, 1);
		
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 9, 1);
		
		return wave;
	}

	public static Wave volcano21() {
		Wave wave = new Wave("Volcano 2:1");
		
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 1, 10);
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 19, 1);
		
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 14, 1);
		
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 9, 1);
		
		wave.setNextWave(volcano22());
		return wave;
	}
	private static Wave volcano22() {
		Wave wave = new Wave("Volcano 2:2");
		
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 1, 30);
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 29, 1);
		
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 22, 1);
		
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 14, 1);
		
		wave.setNextWave(volcano23());
		return wave;
	}
	private static Wave volcano23() {
		Wave wave = new Wave("Volcano 2:3");
		
		add(wave, PresetMonster.volcano4(), PresetPath.volcanoLeft(), 1, 30);
		add(wave, PresetMonster.volcano4(), PresetPath.volcanoLeft(), 59, 1);
		
		wave.setNextWave(volcano24());
		return wave;
	}
	private static Wave volcano24() {
		Wave wave = new Wave("Volcano 2:4");
		
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 1, 30);
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 29, 1);
		
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 22, 1);
		
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 14, 1);
		
		add(wave, PresetMonster.volcano4(), PresetPath.volcanoLeft(), 1, 3);
		add(wave, PresetMonster.volcano4(), PresetPath.volcanoLeft(), 59, 1);
		
		wave.setNextWave(volcano25());
		return wave;
	}
	private static Wave volcano25() {
		Wave wave = new Wave("Volcano 2:5");
		
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 1, 30);
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 44, 1);
		
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 29, 1);
		
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 22, 1);
		
		add(wave, PresetMonster.volcano4(), PresetPath.volcanoLeft(), 1, 3);
		add(wave, PresetMonster.volcano4(), PresetPath.volcanoLeft(), 99, 1);
		
		return wave;
	}

	public static Wave volcano31() {
		Wave wave = new Wave("Volcano 3:1");
		
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 1, 10);
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 44, 1);
		
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 29, 1);
		
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 22, 1);
		
		add(wave, PresetMonster.volcano4(), PresetPath.volcanoLeft(), 1, 3);
		add(wave, PresetMonster.volcano4(), PresetPath.volcanoLeft(), 99, 1);
		
		wave.setNextWave(volcano32());
		return wave;
	}
	private static Wave volcano32() {
		Wave wave = new Wave("Volcano 3:2");
		
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 1, 30);
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 59, 1);
		
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 44, 1);
		
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 29, 1);
		
		add(wave, PresetMonster.volcano4(), PresetPath.volcanoLeft(), 1, 3);
		add(wave, PresetMonster.volcano4(), PresetPath.volcanoLeft(), 149, 1);

		wave.setNextWave(volcano33());
		return wave;
	}
	private static Wave volcano33() {
		Wave wave = new Wave("Volcano 3:3");
		
		add(wave, PresetMonster.volcano5(), PresetPath.volcanoLeft(), 1, 30);
		add(wave, PresetMonster.volcano5(), PresetPath.volcanoLeft(), 199, 1);

		wave.setNextWave(volcano34());
		return wave;
	}
	private static Wave volcano34() {
		Wave wave = new Wave("Volcano 3:4");
		
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 1, 30);
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 59, 1);
		
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 44, 1);
		
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 29, 1);
		
		add(wave, PresetMonster.volcano4(), PresetPath.volcanoLeft(), 1, 3);
		add(wave, PresetMonster.volcano4(), PresetPath.volcanoLeft(), 149, 1);
		
		add(wave, PresetMonster.volcano5(), PresetPath.volcanoLeft(), 1, 3);
		add(wave, PresetMonster.volcano5(), PresetPath.volcanoLeft(), 199, 1);

		wave.setNextWave(volcano35());
		return wave;
	}
	private static Wave volcano35() {
		Wave wave = new Wave("Volcano 3:5");
		
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 1, 30);
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 99, 1);
		
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 59, 1);
		
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 44, 1);
		
		add(wave, PresetMonster.volcano4(), PresetPath.volcanoLeft(), 1, 3);
		add(wave, PresetMonster.volcano4(), PresetPath.volcanoLeft(), 199, 1);
		
		add(wave, PresetMonster.volcano5(), PresetPath.volcanoLeft(), 1, 3);
		add(wave, PresetMonster.volcano5(), PresetPath.volcanoLeft(), 299, 1);

		wave.setNextWave(volcano36());
		return wave;
	}
	private static Wave volcano36() {
		Wave wave = new Wave("Volcano 3:BOSS");
		
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 1, 30);
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 199, 1);
		
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 149, 1);
		
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 99, 1);
		
		add(wave, PresetMonster.volcano4(), PresetPath.volcanoLeft(), 1, 3);
		add(wave, PresetMonster.volcano4(), PresetPath.volcanoLeft(), 399, 1);
		
		add(wave, PresetMonster.volcano5(), PresetPath.volcanoLeft(), 1, 3);
		add(wave, PresetMonster.volcano5(), PresetPath.volcanoLeft(), 499, 1);

		wave.setNextWave(volcano37());
		return wave;
	}
	private static Wave volcano37() {
		Wave wave = new Wave("Woof");
		
		add(wave, PresetMonster.annoyingDog(), PresetPath.volcanoAnnoyingPath(), 1, 60);

		wave.setNextWave(volcano38());
		return wave;
	}
	private static Wave volcano38() {
		Wave wave = new Wave("Final Boss");
		
		add(wave, PresetMonster.volcano6(), PresetPath.volcanoLeft(), 1, 3);
		
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 1, 10);
		add(wave, PresetMonster.volcano1(), PresetPath.volcanoBottom(), 59, 1);
		
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano2(), PresetPath.volcanoTop(), 44, 1);
		
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 1, 3);
		add(wave, PresetMonster.volcano3(), PresetPath.volcanoTop(), 29, 1);
		
		add(wave, PresetMonster.volcano4(), PresetPath.volcanoLeft(), 1, 3);
		add(wave, PresetMonster.volcano4(), PresetPath.volcanoLeft(), 149, 1);
		
		add(wave, PresetMonster.volcano5(), PresetPath.volcanoLeft(), 1, 3);
		add(wave, PresetMonster.volcano5(), PresetPath.volcanoLeft(), 199, 1);
		return wave;
	}
	
	private static void add(Wave wave, Monster monster, PathNode pathNode, int number, double time) {
		Monster clone;
		for(int i = 0; i < number; i++) {
			clone = (Monster) monster.clone();
			clone.setPath(pathNode);
			wave.add(clone, time);
		}
	}
}
