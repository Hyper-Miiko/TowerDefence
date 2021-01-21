package fr.tm_nlm.tower_defence.control2;

import fr.tm_nlm.tower_defence.Couple;

public class PresetAttack {
	public static Attack test() {
		Attack attack = new Attack("Test");
		
		attack.setBullet(PresetBullet.test());
		attack.setCooldown(1);
		attack.setSpreadRange(0);
		attack.setRandomSpread(true);
		attack.setBulletsByShot(1);
		attack.setNbrOfShot(300);
		attack.setInterval(0.25);
		attack.setRange(300);
		
		return attack;
	}
	public static Attack magicBullets() {
		Attack attack = new Attack("Magic Bullet");
		
		attack.setBullet(PresetBullet.magicBullet());
		attack.setCooldown(7, 9);
		attack.setSpreadRange(0);
		attack.setStartShotRange(30, 40);
		attack.setConverge(true);
		attack.setStartSpreadRange(Math.PI/3);
		attack.setRandomSpread(true);
		attack.setBulletsByShot(4, 8);
		attack.setNbrOfShot(3, 5);
		attack.setInterval(0.75, 1);
		attack.setRange(300);
		attack.setKeepTracking(true);
		
		return attack;
	}
	public static Attack spear() {
		Attack attack = new Attack("Spear");
		
		attack.setBullet(PresetBullet.spear());
		attack.setCooldown(2, 20);
		attack.setRange(300);
		
		return attack;
	}
}
