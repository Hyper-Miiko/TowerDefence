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
		return sans;
	}
	private static Tower buildInsaneSans(Field field) {
		Tower sans = new Tower(field, "Sans");
		sans.setObstacle(true);
		sans.setMaxHealth(1d);
		return sans;
	}
	
	public static Tower buildMadDummy(Field field) {
		Tower madDummy = new Tower(field, "Mad Dummy");
		madDummy.setObstacle(false);
		madDummy.setCost(5);
		madDummy.setMaxHealth(Integer.MAX_VALUE);
		madDummy.evolveIn(buildMadMewMew(field), 63);
		madDummy.setAttack(PresetAttack.buildShotGun(field));
		return madDummy;
	}
	private static Tower buildMadMewMew(Field field) {
		Tower madMewMew = new Tower(field, "Mad Mew Mew");
		madMewMew.setObstacle(false);
		madMewMew.setMaxHealth(Integer.MAX_VALUE);
		madMewMew.setAttack(PresetAttack.buildTripleShot(field));
		return madMewMew;
	}
	
	public static Tower buildMetaton(Field field) {
		Tower metaton = new Tower(field, "Mettaton");
		metaton.setObstacle(true);
		metaton.setCost(20);
		metaton.setMaxHealth(Integer.MAX_VALUE);
		metaton.setAttack(PresetAttack.buildFlameRobe(field));
		metaton.getAppareances().setColor(100, 100, 100);
		//mettaton.canEvolveIn(buildMettatonEX(field), 63);
		return metaton;
	}
	/*private Tower buildMettatonEX(Field field) {
		
	}*/
	
	public static Tower buildUndyne(Field field) {
		Tower undyne = new Tower(field, "Undyne");
		undyne.setAttack(PresetAttack.buildSpire(field));
		undyne.getAppareances().setColor(0, 0, 100);
		return undyne;
	}
}