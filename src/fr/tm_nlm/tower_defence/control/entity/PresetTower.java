//package fr.tm_nlm.tower_defence.control.entity;
//
//import fr.tm_nlm.tower_defence.control.Field;
//
//public final class PresetTower {
//	private PresetTower() {}
//	
//	public static Tower buildSans(Field field) {
//		Tower sans = new Tower(field, "Sans");
//		sans.setObstacle(true);
//		sans.setCost(0);
//		sans.setMaxHealth(1d);
//		sans.evolveIn(buildInsaneSans(field), 10000);
//		sans.addAttack(PresetAttack.buildLazyBone(field));
//		return sans;
//	}
//	private static Tower buildInsaneSans(Field field) {
//		Tower sans = new Tower(field, "Sans");
//		sans.setObstacle(true);
//		sans.setMaxHealth(1d);
//		sans.addAttack(PresetAttack.buildGhasterBlaster(field));
//		sans.addAttack(PresetAttack.buildGravityControl(field));
//		sans.addAttack(PresetAttack.buildFrozenBone(field));
//		return sans;
//	}
//	
//	public static Tower buildMadDummy(Field field) {
//		Tower madDummy = new Tower(field, "Mad Dummy");
//		madDummy.setObstacle(false);
//		madDummy.setCost(5);
//		madDummy.setMaxHealth(Integer.MAX_VALUE);
//		madDummy.evolveIn(buildMadMewMew(field), 63);
//		madDummy.addAttack(PresetAttack.buildShotGun(field));
//		madDummy.addAttack(PresetAttack.buildRocketLauncher(field));
//		madDummy.getAppareances().setColor(255, 255, 125);
//		return madDummy;
//	}
//	private static Tower buildMadMewMew(Field field) {
//		Tower madMewMew = new Tower(field, "Mad Mew Mew");
//		madMewMew.setObstacle(false);
//		madMewMew.setMaxHealth(Integer.MAX_VALUE);
//		madMewMew.addAttack(PresetAttack.buildTripleShot(field));
//		madMewMew.getAppareances().setColor(255, 150, 255);
//		return madMewMew;
//	}
//	
//	public static Tower buildMettaton(Field field) {
//		Tower mettaton = new Tower(field, "Mettaton");
//		mettaton.setObstacle(true);
//		mettaton.setCost(20);
//		mettaton.setMaxHealth(Integer.MAX_VALUE);
//		mettaton.addAttack(PresetAttack.buildFlameRobe(field));
//		mettaton.getAppareances().setColor(100, 100, 100);
//		mettaton.evolveIn(buildMettatonEX(field), 63);
//		return mettaton;
//	}
//	private static Tower buildMettatonEX(Field field) {
//		Tower mettaton = new Tower(field, "Mettaton EX");
//		mettaton.getAppareances().setColor(255, 100, 255);
//		mettaton.addAttack(PresetAttack.buildHeartBreaker(field));
//		mettaton.addAttack(PresetAttack.buildYellowHearth(field));
//		mettaton.evolveIn(buildMettatonNEO(field), 50);
//		return mettaton;
//	}
//	private static Tower buildMettatonNEO(Field field) {
//		Tower mettaton = new Tower(field, "Mettaton NEO");
//		mettaton.getAppareances().setColor(255, 100, 255);
//		mettaton.addAttack(PresetAttack.buildPinkBeam(field));
//		mettaton.addAttack(PresetAttack.buildYellowBeam(field));
//		mettaton.addAttack(PresetAttack.buildWhiteBeam(field));
//		return mettaton;
//	}
//	
//	public static Tower buildUndyne(Field field) {
//		Tower undyne = new Tower(field, "Undyne");
//		undyne.addAttack(PresetAttack.buildSpire(field));
//		undyne.addAttack(PresetAttack.buildSpireOfJustice(field));
//		undyne.getAppareances().setColor(0, 0, 100);
//		undyne.evolveIn(buildUndyneTheUndying(field), 50);
//		return undyne;
//	}
//	private static Tower buildUndyneTheUndying(Field field) {
//		Tower undyne = new Tower(field, "Undyne The Undying");
//		undyne.addAttack(PresetAttack.buildSpireOfUndying(field));
//		undyne.addAttack(PresetAttack.buildSpireOfJustice(field));
//		undyne.addAttack(PresetAttack.buildShield(field));
//		undyne.getAppareances().setColor(0, 0, 100);
//		return undyne;
//	}
//	
//	public static Tower buildAsriel(Field field) {
//		Tower asriel = new Tower(field, "Asriel Dremurr");
//		asriel.addAttack(PresetAttack.buildRainbowBlaster(field));
//		asriel.addAttack(PresetAttack.buildRainbowShotgun(field));
//		asriel.addAttack(PresetAttack.buildRainbowBlast(field));
//		asriel.getAppareances().setColor(0, 255, 150);
//		return asriel;
//	}
//}