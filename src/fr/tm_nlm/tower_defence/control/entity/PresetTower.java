package fr.tm_nlm.tower_defence.control.entity;

import fr.tm_nlm.tower_defence.control.Field;

public final class PresetTower {
	private PresetTower() {}
	
	public static Tower buildSans(Field field) {
		Tower sans = new Tower(field, "Sans");
		sans.setObstacle(true);
		sans.setCost(0);
		sans.setMaxHealth(1d);
		sans.evolveIn(buildInsaneSans(field), 10000);
		sans.addAttack(PresetAttack.buildLazyBone(field));
		return sans;
	}
	private static Tower buildInsaneSans(Field field) {
		Tower sans = new Tower(field, "Sans");
		sans.setObstacle(true);
		sans.setMaxHealth(1d);
		sans.addAttack(PresetAttack.buildGhasterBlaster(field));
		return sans;
	}
	
	public static Tower buildMadDummy(Field field) {
		Tower madDummy = new Tower(field, "Mad Dummy");
		madDummy.setObstacle(false);
		madDummy.setCost(5);
		madDummy.setMaxHealth(Integer.MAX_VALUE);
		madDummy.evolveIn(buildMadMewMew(field), 63);
		madDummy.addAttack(PresetAttack.buildShotGun(field));
		madDummy.addAttack(PresetAttack.buildRocketLauncher(field));
		madDummy.getAppareances().setColor(255, 255, 125);
		return madDummy;
	}
	private static Tower buildMadMewMew(Field field) {
		Tower madMewMew = new Tower(field, "Mad Mew Mew");
		madMewMew.setObstacle(false);
		madMewMew.setMaxHealth(Integer.MAX_VALUE);
		madMewMew.addAttack(PresetAttack.buildTripleShot(field));
		madMewMew.getAppareances().setColor(255, 150, 255);
		return madMewMew;
	}
	
	public static Tower buildMetaton(Field field) {
		Tower metaton = new Tower(field, "Mettaton");
		metaton.setObstacle(true);
		metaton.setCost(20);
		metaton.setMaxHealth(Integer.MAX_VALUE);
		metaton.addAttack(PresetAttack.buildFlameRobe(field));
		metaton.getAppareances().setColor(100, 100, 100);
		//mettaton.canEvolveIn(buildMettatonEX(field), 63);
		return metaton;
	}
	/*private Tower buildMettatonEX(Field field) {
		
	}*/
	
	public static Tower buildUndyne(Field field) {
		Tower undyne = new Tower(field, "Undyne");
		undyne.addAttack(PresetAttack.buildSpire(field));
		undyne.addAttack(PresetAttack.buildSpireOfJustice(field));
		undyne.getAppareances().setColor(0, 0, 100);
		return undyne;
	}
	
	public static Tower buildAsriel(Field field) {
		Tower asriel = new Tower(field, "Asriel Dremurr");
		asriel.addAttack(PresetAttack.buildRainbowBlaster(field));
		asriel.addAttack(PresetAttack.buildRainbowShotgun(field));
		asriel.addAttack(PresetAttack.buildStarBlast(field));
		asriel.getAppareances().setColor(0, 255, 150);
		return asriel;
	}
}