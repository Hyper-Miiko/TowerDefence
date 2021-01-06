package fr.tm_nlm.tower_defence.control.entity;

import fr.tm_nlm.tower_defence.control.Field;

public final class PresetTower {
	private PresetTower() {}
	
	public static Tower buildSans(Field field) {
		Tower sans = new Tower(field, "Sans");
		sans.setObstacle(true);
		sans.setCost(0);
		sans.setRange(10);
		sans.setCooldown(10d);
		sans.setDammage(1d);
		sans.setMaxHealth(1d);
		sans.evolveIn(buildInsaneSans(field), 10000);
		return sans;
	}
	private static Tower buildInsaneSans(Field field) {
		Tower sans = new Tower(field, "Sans");
		sans.setObstacle(true);
		sans.setRange(150);
		sans.setCooldown(0.1d);
		sans.setDammage(1d);
		sans.setMaxHealth(1d);
		return sans;
	}
	
	public static Tower buildMadDummy(Field field) {
		Tower madDummy = new Tower(field, "Mad Dummy");
		madDummy.setObstacle(false);
		madDummy.setCost(5);
		madDummy.setRange(100);
		madDummy.setCooldown(1d);
		madDummy.setDammage(Math.PI);
		madDummy.setMaxHealth(Integer.MAX_VALUE);
		madDummy.evolveIn(buildMadMewMew(field), 63);
		return madDummy;
	}
	private static Tower buildMadMewMew(Field field) {
		Tower madMewMew = new Tower(field, "Mad Mew Mew");
		madMewMew.setObstacle(false);
		madMewMew.setRange(200);
		madMewMew.setCooldown(1.2d);
		madMewMew.setDammage(3*Math.PI);
		madMewMew.setMaxHealth(Integer.MAX_VALUE);
		return madMewMew;
	}
	
	public static Tower buildMetaton(Field field) {
		Tower mettaton = new Tower(field, "Mettaton");
		mettaton.setObstacle(true);
		mettaton.setCost(20);
		mettaton.setRange(20);
		mettaton.setCooldown(1d);
		mettaton.setDammage(Math.PI);
		mettaton.setMaxHealth(Integer.MAX_VALUE);
		//mettaton.canEvolveIn(buildMettatonEX(field), 63);
		return mettaton;
	}
	/*private Tower buildMettatonEX(Field field) {
		
	}*/
}