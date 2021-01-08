package fr.tm_nlm.tower_defence.control.entity;

import fr.tm_nlm.tower_defence.control.Field;

public class PresetAttack {
	public static Attack buildShotGun(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(95, 105);
		attack.setDamage(10);
		attack.setAimingFactor(0);
		attack.setCooldown(4, 5);
		attack.setInterval(0.25);
		attack.setNbrOfBullet(12, 14);
		attack.setBulletByShot(7);
		attack.setPrecisionLoss(0.1);
		attack.setSize(3);
		attack.setRed(127);
		attack.setGreen(127);
		attack.setBlue(127);
		attack.setLifeTime(3);
		attack.setRange(150);
		return attack;
	}
	public static Attack buildRocketLauncher(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(95, 105);
		attack.setDamage(20);
		attack.setAimingFactor(0.2);
		attack.setCooldown(40, 50);
		attack.setInterval(1, 2);
		attack.setNbrOfBullet(25, 30);
		attack.setBulletByShot(5, 6);
		attack.setPrecisionLoss(0.2);
		attack.setSize(3);
		attack.setRed(127);
		attack.setGreen(127);
		attack.setBlue(127);
		attack.setLifeTime(10);
		attack.setRange(250);
		return attack;
	}
	public static Attack buildFlameRobe(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(10, 30);
		attack.setDamage(0.01, 0.1);
		attack.setAimingFactor(0.05);
		attack.setInterval(0);
		attack.setCooldown(0);
		attack.setNbrOfBullet(100);
		attack.setBulletByShot(100);
		attack.setPrecisionLoss(1);
		attack.setLifeTime(10, 12);
		attack.setRange(100);
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
		attack.setCooldown(0.1);
		attack.setNbrOfBullet(3);
		attack.setPrecisionLoss(0);
		attack.setLifeTime(3);
		attack.setSize(5);
		return attack;
	}
	public static Attack buildSpire(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(200);
		attack.setRange(200);
		attack.setDamage(0.5);
		attack.setInterval(0);
		attack.setCooldown(0.1);
		attack.setLifeTime(15);
		attack.setSize(2);
		return attack;
	}
	public static Attack buildSpireOfJustice(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(200);
		attack.setRange(400);
		attack.setDamage(50);
		attack.setInterval(0);
		attack.setCooldown(10);
		attack.setLifeTime(15);
		attack.setSize(10);
		return attack;
	}
}
