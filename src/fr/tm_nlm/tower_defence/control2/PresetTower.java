package fr.tm_nlm.tower_defence.control2;

public class PresetTower {
	public static Tower madDummy() {
		Tower tower = new Tower("Mad Dummy");
		tower.addAttack(PresetAttack.magicBullets());
		tower.addAttack(PresetAttack.magicMissiles());
		tower.setMaxHandle(0);
		tower.setImage("mad_dummy_150");
		return tower;
	}
	
	public static Tower undyne() {
		Tower tower = new Tower("Undyne");
		
		tower.addAttack(PresetAttack.spear());
		tower.addAttack(PresetAttack.tinySpear());
		tower.addAttack(PresetAttack.shield());
		tower.setImage("undyne");
		tower.setMaxHandle(4);
		tower.setEvolution(undyneTheUndying());
		
		return tower;
	}
	private static Tower undyneTheUndying() {
		Tower tower = new Tower("Undyne The Undying");
		
		tower.addAttack(PresetAttack.randomSpear());
		tower.addAttack(PresetAttack.spearJail());
		tower.addAttack(PresetAttack.tinySpear());
		tower.addAttack(PresetAttack.tinySpear());
		tower.setImage("undyne");
		tower.setMaxHandle(5);
		
		return tower;
	}
}
