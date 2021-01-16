package fr.tm_nlm.tower_defence.control2;

public class PresetAttack {
	public static Attack magicBullets() {
		Attack attack = new Attack();
		
		attack.setBullet(PresetBullet.magicBullet());
		attack.setCooldown(7.5, 10);
		attack.setSpreadRange(0.25);
		attack.setRandomSpread(false);
		attack.setBulletByShot(4, 8);
		attack.setNbrOfShot(4, 6);
		attack.setInterval(0.75, 1);
		
		return attack;
	}
}
