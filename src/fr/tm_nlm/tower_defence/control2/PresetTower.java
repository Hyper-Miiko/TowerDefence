package fr.tm_nlm.tower_defence.control2;

public class PresetTower {
	public static Tower madDummy() {
		Tower tower = new Tower("Mad\nDummy");
		tower.addAttack(PresetAttack.knife());
		tower.addAttack(PresetAttack.magicMissiles());
		tower.addAttack(PresetAttack.magicBullets());
		tower.setMaxHandle(1);
		tower.setImage("mad_dummy_150");
		tower.setEvolution(madMewMew(), 1000);
		tower.setPrice(30);
		return tower;
	}
	private static Tower madMewMew() {
		Tower tower = new Tower("Mad\nMew Mew");
		tower.addAttack(PresetAttack.kittyCornerShot());
		tower.addAttack(PresetAttack.blueKittyCornerShot());
		tower.addAttack(PresetAttack.redKittyCornerShot());
		tower.setMaxHandle(2);
		tower.setImage("mad_mewmew");
		return tower;
	}
	
	public static Tower undyne() {
		Tower tower = new Tower("Undyne");
		tower.addAttack(PresetAttack.shield());
		tower.addAttack(PresetAttack.spear());
		tower.addAttack(PresetAttack.tinySpear());
		tower.setImage("undyne");
		tower.setMaxHandle(4);
		tower.setEvolution(undyneTheUndying(), 5000);
		tower.setPrice(70);
		
		return tower;
	}
	private static Tower undyneTheUndying() {
		Tower tower = new Tower("Undyne\nThe\nUndying");

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
		tower.setEvolution(madSans(), 50000);
		tower.setPrice(0);
		
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
	public static Tower asriel() {
		Tower tower = new Tower("Asriel\nDreamurr");

 		tower.addAttack(PresetAttack.greatStarCall());
//		tower.addAttack(PresetAttack.rainbowBlaster());
		tower.addAttack(PresetAttack.starGun());
		tower.setImage("asriel");
		tower.setMaxHandle(2);
		tower.setPrice(500);
		tower.setEvolution(truePowerAsriel(), 20000);
		
		return tower;
	}
	private static Tower truePowerAsriel() {
		Tower tower = new Tower("Asriel\nDreamurr");

 		tower.addAttack(PresetAttack.rainbowLight());
		tower.addAttack(PresetAttack.rainbowMissile());
		tower.setImage("asriel_true");
		tower.setMaxHandle(3);
		
		return tower;
	}
}
