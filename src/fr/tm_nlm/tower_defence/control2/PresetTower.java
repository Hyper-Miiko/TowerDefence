package fr.tm_nlm.tower_defence.control2;

public class PresetTower {
	public static Tower madDummy() {
		Tower tower = new Tower("Mad Dummy");
		tower.addAttack(PresetAttack.magicMissiles());
		tower.addAttack(PresetAttack.magicBullets());
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
		tower.addAttack(PresetAttack.shield());
		tower.addAttack(PresetAttack.tinySpear());
		tower.setImage("undyne");
		tower.setMaxHandle(4);
		tower.setEvolution(undyneTheUndying(), 0);
		
		return tower;
	}
	private static Tower undyneTheUndying() {
		Tower tower = new Tower("Undyne The Undying");

		tower.addAttack(PresetAttack.spearJail());
		tower.addAttack(PresetAttack.randomSpear());
		tower.addAttack(PresetAttack.tinySpear());
		tower.addAttack(PresetAttack.tinySpear());
		tower.setImage("undying");
		tower.setMaxHandle(5);
		
		return tower;
	}
	public static Tower chillSans() {
		Tower tower = new Tower("Sans");
		
		tower.addAttack(PresetAttack.boneJoke());
		tower.setImage("Sans1");
		tower.setMaxHandle(0);
		tower.setEvolution(madSans(), 0);
		
		return tower;
	}
	private static Tower madSans() {
		Tower tower = new Tower("Sans");

 		tower.addAttack(PresetAttack.ghasterBlasterCall());
		tower.addAttack(PresetAttack.gravityControl());
		tower.addAttack(PresetAttack.blueBone());
		tower.addAttack(PresetAttack.whiteBone());
		tower.setImage("Sans2");
		tower.setMaxHandle(0);
		
		return tower;
	}
}
