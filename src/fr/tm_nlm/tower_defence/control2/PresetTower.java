package fr.tm_nlm.tower_defence.control2;

public class PresetTower {
	public static Tower madDummy() {
		Tower tower = new Tower("Mad Dummy");
		tower.addAttack(PresetAttack.magicBullets());
		tower.addAttack(PresetAttack.magicMissiles());
		tower.setMaxHandle(0);
		tower.setImage("mad_dummy_150");
		tower.setEvolution(madMewMew(), 0);
		return tower;
	}
	private static Tower madMewMew() {
		Tower tower = new Tower("Mad Mew Mew");
		tower.addAttack(PresetAttack.kittyCornerShot());
		tower.addAttack(PresetAttack.blueKittyCornerShot());
		tower.addAttack(PresetAttack.redKittyCornerShot());
		tower.setMaxHandle(0);
		tower.setImage("mad_mewmew");
		return tower;
	}
	
	public static Tower undyne() {
		Tower tower = new Tower("Undyne");
		
		tower.addAttack(PresetAttack.spear());
		tower.addAttack(PresetAttack.tinySpear());
		tower.addAttack(PresetAttack.shield());
		tower.setImage("undyne");
		tower.setMaxHandle(4);
		tower.setEvolution(undyneTheUndying(), 0);
		
		return tower;
	}
	private static Tower undyneTheUndying() {
		Tower tower = new Tower("Undyne The Undying");
		
		tower.addAttack(PresetAttack.randomSpear());
		tower.addAttack(PresetAttack.spearJail());
		tower.addAttack(PresetAttack.tinySpear());
		tower.addAttack(PresetAttack.tinySpear());
		tower.setImage("undying");
		tower.setMaxHandle(5);
		
		return tower;
	}
}
