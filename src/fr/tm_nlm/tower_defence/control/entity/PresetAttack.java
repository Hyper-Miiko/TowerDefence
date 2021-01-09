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
		attack.setSize(10);
		attack.setRed(200);
		attack.setGreen(200);
		attack.setBlue(200);
		attack.setLifeTime(3);
		attack.setRange(150);
		return attack;
	}
	public static Attack buildRocketLauncher(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(45, 55);
		attack.setDamage(20);
		attack.setAimingFactor(0.2);
		attack.setCooldown(40, 50);
		attack.setInterval(0.8);
		attack.setNbrOfBullet(25, 30);
		attack.setBulletByShot(5, 6);
		attack.setPrecisionLoss(0.8);
		attack.setSize(10);
		attack.setRed(127);
		attack.setGreen(127);
		attack.setBlue(127);
		attack.setLifeTime(10);
		attack.setRange(250);
		return attack;
	}
	public static Attack buildFlameRobe(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(20, 40);
		attack.setDamage(0.01, 0.1);
		attack.setAimingFactor(0.05);
		attack.setInterval(0.025);
		attack.setCooldown(15);
		attack.setNbrOfBullet(8000);
		attack.setBulletByShot(50);
		attack.setPrecisionLoss(1);
		attack.setLifeTime(4, 6);
		attack.setRange(100);
		attack.setSize(1);
		attack.setRed(255);
		attack.setGreen(-200, 200);
		attack.setBlue(0);
		return attack;
	}
	public static Attack buildTripleShot(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(150);
		attack.setDamage(10, 20);
		attack.setAimingFactor(0);
		attack.setInterval(0.1);
		attack.setCooldown(1, 3);
		attack.setNbrOfBullet(3);
		attack.setPrecisionLoss(0);
		attack.setLifeTime(3);
		attack.setSize(7);
		attack.setRed(0, 255);
		attack.setGreen(0);
		attack.setBlue(0, 255);
		attack.setNbrOfColor(1);
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
		attack.addQuote("Nyarg!");
		return attack;
	}
	public static Attack buildGhasterBlaster(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(250, 300);
		attack.setRange(10000);
		attack.setDamage(1);
		attack.setInterval(0);
		attack.setCooldown(20);
		attack.setLifeTime(3);
		attack.setSize(30);
		attack.setPrecisionLoss(0);
		attack.setBulletByShot(20);
		attack.setNbrOfBullet(2000);
		attack.setRed(255);
		attack.setGreen(255);
		attack.setBlue(255);
		attack.addQuote("Never understood why people don't use their best attack first.");
		return attack;
	}
	public static Attack buildLazyBone(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(0);
		attack.setRange(0);
		attack.setDamage(0);
		attack.setCooldown(10000);
		attack.setLifeTime(0);
		attack.setSize(1);
		attack.setNbrOfBullet(0);
		attack.setRed(255);
		attack.setGreen(255);
		attack.setBlue(255);
		return attack;
	}
	public static Attack buildRainbowBlaster(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(200, 400);
		attack.setRange(10000);
		attack.setDamage(0.05);
		attack.setInterval(0);
		attack.setCooldown(200);
		attack.setLifeTime(1, 3);
		attack.setSize(5);
		attack.setPrecisionLoss(0.05);
		attack.setBulletByShot(200);
		attack.setNbrOfBullet(20000);
		attack.setRed(0, 255);
		attack.setGreen(0, 255);
		attack.setBlue(0, 255);
		attack.setNbrOfColor(1);
		return attack;
	}
	public static Attack buildRainbowShotgun(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(200);
		attack.setRange(200);
		attack.setDamage(5);
		attack.setInterval(0.2);
		attack.setCooldown(10, 20);
		attack.setLifeTime(2.5, 3);
		attack.setSize(10);
		attack.setPrecisionLoss(0.1);
		attack.setBulletByShot(4);
		attack.setNbrOfBullet(20);
		attack.setRed(0, 255);
		attack.setGreen(0, 255);
		attack.setBlue(0, 255);
		attack.setNbrOfColor(1);
		return attack;
	}
	public static Attack buildStarBlast(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(170, 340);
		attack.setRange(200);
		attack.setDamage(2);
		attack.setInterval(0.5);
		attack.setCooldown(10, 20);
		attack.setLifeTime(10, 20);
		attack.setSize(5);
		attack.setAimingFactor(0.1);
		attack.setPrecisionLoss(1);
		attack.setBulletByShot(15);
		attack.setNbrOfBullet(60);
		attack.setRed(0, 255);
		attack.setGreen(0, 255);
		attack.setBlue(0, 255);
		attack.setNbrOfColor(1);
		return attack;
	}
}
