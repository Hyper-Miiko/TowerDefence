package fr.tm_nlm.tower_defence.control.entity;

import fr.tm_nlm.tower_defence.control.Field;

public class PresetAttack {
	public Attack buildShotGun(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(45, 55);
		attack.setDamage(5, 10);
		attack.setAimingFactor(0);
		attack.setInterval(0.1, 0.3);
		attack.setRafaleInterval(1, 3);
		attack.setNbrOfBullet(1, 3);
		attack.setPrecisionLoss(30);
		attack.setSize(3);
		return attack;
	}
	public Attack buildFlameRobe(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(10, 30);
		attack.setDamage(0.01, 0.1);
		attack.setAimingFactor(5);
		attack.setInterval(0);
		attack.setRafaleInterval(0);
		attack.setNbrOfBullet(10);
		attack.setPrecisionLoss(40);
		attack.setLifeTime(5, 7);
		attack.setSize(0.1);
		return attack;
	}
	public Attack buildTripleShot(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(100);
		attack.setDamage(10, 20);
		attack.setAimingFactor(1);
		attack.setInterval(1, 3);
		attack.setRafaleInterval(0.1);
		attack.setNbrOfBullet(3);
		attack.setPrecisionLoss(0);
		attack.setLifeTime(3);
		attack.setSize(5);
		return attack;
	}
}
