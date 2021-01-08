package fr.tm_nlm.tower_defence.control.entity;

import fr.tm_nlm.tower_defence.control.Field;

public class PresetAttack {
	public static Attack buildShotGun(Field field) {
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
	public static Attack buildFlameRobe(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(10, 30);
		attack.setDamage(0.01, 0.1);
		attack.setAimingFactor(5);
		attack.setInterval(0);
		attack.setRafaleInterval(0);
		attack.setNbrOfBullet(10);
		attack.setPrecisionLoss(40);
		attack.setLifeTime(5, 7);
		attack.setSize(2);
		attack.setRed(255);
		attack.setGreen(0, 150);
		attack.setBlue(0);
		return attack;
	}
	public static Attack buildTripleShot(Field field) {
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
	public static Attack buildSpire(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(200);
		attack.setDamage(50);
		attack.setAimingFactor(0);
		attack.setInterval(10);
		attack.setRafaleInterval(1);
		attack.setNbrOfBullet(1);
		attack.setPrecisionLoss(0);
		attack.setLifeTime(2);
		attack.setSize(10);
		return attack;
	}
}
