package fr.tm_nlm.tower_defence.control2;

public class PresetMonster {
	public static Monster testingDog() {
		Monster monster = new Monster();
		
		monster.setSpeed(10);
		monster.setStrength(1);
		monster.setShape(PresetShape.rect(34, 50));
		monster.setMaxHealth(100000);
		monster.setEliminationWorth(2);
		monster.setStrength(1);
		monster.setFly(true);
		
		return monster;
	}
	
	public static Monster grassland1() {
		Monster monster = new Monster();
		
		monster.setSpeed(50);
		monster.setStrength(1);
		monster.setShape(PresetShape.rect(34, 50));
		monster.setMaxHealth(15);
		monster.setEliminationWorth(2);
		monster.setStrength(1);
		monster.setFly(false);
		
		return monster;
	}
	public static Monster grassland2() {
		Monster monster = new Monster();
		
		monster.setSpeed(40);
		monster.setStrength(1);
		monster.setShape(PresetShape.rect(34, 50));
		monster.setMaxHealth(40);
		monster.setEliminationWorth(5);
		monster.setFly(true);
		
		return monster;
	}
	public static Monster grassland3() {
		Monster monster = new Monster();
		
		monster.setSpeed(80);
		monster.setStrength(1);
		monster.setShape(PresetShape.rect(34, 50));
		monster.setMaxHealth(20);
		monster.setEliminationWorth(10);
		monster.setFly(false);
		
		return monster;
	}
	public static Monster grassland4() {
		Monster monster = new Monster();
		
		monster.setSpeed(30);
		monster.setStrength(2);
		monster.setShape(PresetShape.rect(34, 50));
		monster.setMaxHealth(200);
		monster.setEliminationWorth(15);
		monster.setFly(false);
		
		return monster;
	}
	public static Monster grassland5() {
		Monster monster = new Monster();

		monster.setFly(true);
		monster.setMaxHealth(200);
		monster.setSpeed(30);
		monster.setEliminationWorth(150);
		monster.setStrength(10);
		monster.setShape(PresetShape.rect(51, 75));
		monster.setBoss(true);
		
		return monster;
	}
	public static Monster toundra1() {
		Monster monster = new Monster();

		monster.setFly(false);
		monster.setMaxHealth(40);
		monster.setSpeed(80);
		monster.setEliminationWorth(5);
		monster.setStrength(1);
		monster.setShape(PresetShape.rect(34, 50));
		
		return monster;
	}
	public static Monster toundra2() {
		Monster monster = new Monster();

		monster.setFly(true);
		monster.setMaxHealth(80);
		monster.setSpeed(65);
		monster.setEliminationWorth(12);
		monster.setStrength(1);
		monster.setShape(PresetShape.rect(34, 50));
		
		return monster;
	}
	public static Monster toundra3() {
		Monster monster = new Monster();

		monster.setFly(false);
		monster.setMaxHealth(800);
		monster.setSpeed(40);
		monster.setEliminationWorth(60);
		monster.setStrength(13);
		monster.setShape(PresetShape.rect(34, 50));
		
		return monster;
	}
	public static Monster toundra4() {
		Monster monster = new Monster();

		monster.setFly(true);
		monster.setMaxHealth(800);
		monster.setSpeed(35);
		monster.setEliminationWorth(80);
		monster.setStrength(3);
		monster.setShape(PresetShape.rect(34, 50));
		
		return monster;
	}
	public static Monster toundra5() {
		Monster monster = new Monster();

		monster.setFly(false);
		monster.setMaxHealth(2000);
		monster.setSpeed(30);
		monster.setEliminationWorth(1000);
		monster.setStrength(10);
		monster.setShape(PresetShape.rect(51, 75));
		monster.setBoss(true);
		
		return monster;
	}
	public static Monster Volcano1() {
		Monster monster = new Monster();

		monster.setFly(true);
		monster.setMaxHealth(60);
		monster.setSpeed(80);
		monster.setEliminationWorth(10);
		monster.setStrength(1);
		monster.setShape(PresetShape.rect(34, 50));
		
		return monster;
	}
	public static Monster Volcano2() {
		Monster monster = new Monster();

		monster.setFly(false);
		monster.setMaxHealth(100);
		monster.setSpeed(120);
		monster.setEliminationWorth(15);
		monster.setStrength(1);
		monster.setShape(PresetShape.rect(34, 50));
		
		return monster;
	}
	public static Monster Volcano3() {
		Monster monster = new Monster();

		monster.setFly(true);
		monster.setMaxHealth(600);
		monster.setSpeed(50);
		monster.setEliminationWorth(25);
		monster.setStrength(2);
		monster.setShape(PresetShape.rect(34, 50));
		
		return monster;
	}
	public static Monster Volcano4() {
		Monster monster = new Monster();

		monster.setFly(false);
		monster.setMaxHealth(40);
		monster.setSpeed(150);
		monster.setEliminationWorth(15);
		monster.setStrength(1);
		monster.setShape(PresetShape.rect(34, 50));
		
		return monster;
	}
	public static Monster Volcano5() {
		Monster monster = new Monster();

		monster.setFly(true);
		monster.setMaxHealth(20);
		monster.setSpeed(120);
		monster.setEliminationWorth(10);
		monster.setStrength(1);
		monster.setShape(PresetShape.rect(34, 50));
		
		return monster;
	}
	public static Monster Volcano6() {
		Monster monster = new Monster();

		monster.setFly(false);
		monster.setMaxHealth(20000);
		monster.setSpeed(30);
		monster.setEliminationWorth(10000);
		monster.setStrength(10);
		monster.setShape(PresetShape.rect(51, 75));
		monster.setBoss(true);
		
		return monster;
	}
	public static Monster annoyingDog() {
		Monster monster = new Monster();
		
		monster.setSpeed(200);
		monster.setStrength(0);
		monster.setShape(PresetShape.circle(1));
		monster.setMaxHealth(1000000);
		monster.setEliminationWorth(0);
		
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
	public static Monster robotCop() {
		Monster monster = new Monster();
		monster.setSpeed(50);
		monster.setShape(PresetShape.rect(34, 50));
		monster.setMaxHealth(10);
		monster.setFly(true);
		monster.setImage("roboCop");
		return monster;
	}
	public static Monster shadyMan() {
		Monster monster = new Monster();
		monster.setSpeed(50);
		monster.setShape(PresetShape.rect(34, 50));
		monster.setMaxHealth(10);
		monster.setFly(true);
		monster.setImage("ShadyMan");
		return monster;
	}
	public static Monster theBeast() {
		Monster monster = new Monster();
		monster.setSpeed(50);
		monster.setShape(PresetShape.rect(34, 50));
		monster.setMaxHealth(10);
		monster.setFly(true);
		monster.setImage("theBeast");
		return monster;
	}
	public static Monster friendlyPlant() {
		Monster monster = new Monster();
		monster.setSpeed(50);
		monster.setShape(PresetShape.rect(34, 50));
		monster.setMaxHealth(10);
		monster.setFly(true);
		monster.setImage("friendlyPlant");
		return monster;
	}
}
