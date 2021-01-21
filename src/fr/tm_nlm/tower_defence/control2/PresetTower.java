package fr.tm_nlm.tower_defence.control2;

public class PresetTower {
	public static Tower madDummy() {
		Tower tower = new Tower("Mad Dummy");
		tower.addAttack(PresetAttack.magicBullets());
		//tower.addAttack(PresetAttack.test());
		tower.setMaxHandle(0);
		tower.setImage("mad_dummy_150");
		return tower;
	}
}
