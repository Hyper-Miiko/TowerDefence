package fr.tm_nlm.tower_defence.control.entity;

import fr.tm_nlm.tower_defence.control.Field;

import static fr.tm_nlm.tower_defence.control.entity.Attack.Option.*;

public class PresetAttack {
	public static Attack buildShotGun(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(195, 205);
		attack.setDamage(10);
		attack.setAimingFactor(0);
		attack.setCooldown(4, 5);
		attack.setInterval(0.25);
		attack.setNbrOfBullet(12, 14);
		attack.setBulletByShot(7);
		attack.setPrecisionLoss(0.1);
		attack.setSize(5);
		attack.setRed(200);
		attack.setGreen(200);
		attack.setBlue(200);
		attack.setLifeTime(3);
		attack.setRange(150);
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_MOB);
		attack.addOption(TARGET_BOSS);
		return attack;
	}
	public static Attack buildRocketLauncher(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(80, 120);
		attack.setDamage(20);
		attack.setAimingFactor(0.1);
		attack.setCooldown(40, 50);
		attack.setInterval(1);
		attack.setNbrOfBullet(25, 30);
		attack.setBulletByShot(5, 6);
		attack.setPrecisionLoss(0.8);
		attack.setSize(10);
		attack.setRed(127);
		attack.setGreen(127);
		attack.setBlue(127);
		attack.setLifeTime(10);
		attack.setRange(250);
		attack.addOption(TARGET_FLYING);
		attack.addOption(TARGET_MOB);
		attack.addOption(TARGET_BOSS);
		attack.setChainAttack(buildSizedEkusplosion(field));
		return attack;
	}
	private static Attack buildSizedEkusplosion(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(0, 200);
		attack.setDamage(2);
		attack.setNbrOfBullet(1000);
		attack.setBulletByShot(1000);
		attack.setPrecisionLoss(1);
		attack.setSize(1);
		attack.setRed(255);
		attack.setGreen(-200, 200);
		attack.setBlue(0);
		attack.setLifeTime(0.25);
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_FLYING);
		attack.addOption(TARGET_MOB);
		attack.addOption(TARGET_BOSS);
		return attack;
	}
	public static Attack buildTripleShot(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(150);
		attack.setDamage(10, 20);
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
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_FLYING);
		attack.addOption(TARGET_MOB);
		attack.addOption(TARGET_BOSS);
		return attack;
	}
	public static Attack buildFlameRobe(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(30, 60);
		attack.setDamage(0.01);
		attack.setAimingFactor(0.01);
		attack.setInterval(0);
		attack.setCooldown(10);
		attack.setNbrOfBullet(16000);
		attack.setBulletByShot(50);
		attack.setPrecisionLoss(1);
		attack.setLifeTime(4, 6);
		attack.setRange(100);
		attack.setSize(1);
		attack.setRed(255);
		attack.setGreen(-200, 200);
		attack.setBlue(0);
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_MOB);
		return attack;
	}
	public static Attack buildYellowHearth(Field field) {
		Attack attack = new Attack(field);
		attack.setRange(500);
		attack.setBulletSpeed(200);
		attack.setInterval(0.25);
		attack.setAimingFactor(0.001);
		attack.setDamage(2.5, 7.5);
		attack.setCooldown(1.5, 2.5);
		attack.setNbrOfBullet(3);
		attack.setSize(5);
		attack.setLifeTime(6);
		attack.setRed(255);
		attack.setGreen(255);
		attack.setBlue(0);
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_FLYING);
		attack.addOption(TARGET_MOB);
		attack.addOption(TARGET_BOSS);
		return attack;
	}
	public static Attack buildHeartBreaker(Field field) {
		Attack attack = new Attack(field);
		attack.setRange(500);
		attack.setBulletSpeed(200);
		attack.setInterval(0.01);
		attack.setAimingFactor(0.005);
		attack.setDamage(0.5);
		attack.setCooldown(3);
		attack.setNbrOfBullet(1);
		attack.setSize(3);
		attack.setLifeTime(6);
		attack.setRed(255);
		attack.setGreen(255);
		attack.setBlue(0);
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_FLYING);
		attack.addOption(TARGET_BOSS);
		attack.addOption(INCREASS_BULLET, 0.8);
		return attack;
	}
	public static Attack buildPinkBeam(Field field) {
		Attack attack = new Attack(field);
		attack.setRange(250);
		attack.setBulletSpeed(50, 100);
		attack.setInterval(0.01);
		attack.setAimingFactor(0.005);
		attack.setPrecisionLoss(0.01);
		attack.setDamage(0.05);
		attack.setCooldown(0.1);
		attack.setNbrOfBullet(100);
		attack.setBulletByShot(10);
		attack.setSize(5);
		attack.setLifeTime(2);
		attack.setRed(255);
		attack.setGreen(150);
		attack.setBlue(255);
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_MOB);
		return attack;
	}
	public static Attack buildYellowBeam(Field field) {
		Attack attack = new Attack(field);
		attack.setRange(250);
		attack.setBulletSpeed(100, 200);
		attack.setInterval(0.01);
		attack.setDamage(0.02);
		attack.setCooldown(0.1);
		attack.setNbrOfBullet(100);
		attack.setBulletByShot(10);
		attack.setPrecisionLoss(0.01);
		attack.setSize(2);
		attack.setLifeTime(2);
		attack.setRed(255);
		attack.setGreen(255);
		attack.setBlue(0);
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_FLYING);
		attack.addOption(TARGET_MOB);
		return attack;
	}
	public static Attack buildWhiteBeam(Field field) {
		Attack attack = new Attack(field);
		attack.setRange(500);
		attack.setBulletSpeed(125, 250);
		attack.setInterval(0.01);
		attack.setDamage(0.00005);
		attack.setCooldown(0.1);
		attack.setNbrOfBullet(300);
		attack.setBulletByShot(30);
		attack.setPrecisionLoss(0.01);
		attack.setSize(1);
		attack.setLifeTime(7);
		attack.setRed(255);
		attack.setGreen(255);
		attack.setBlue(255);
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_FLYING);
		attack.addOption(TARGET_BOSS);
		attack.addOption(INCREASS_DAMAGE, 0.000005);
		attack.addOption(INCREASS_SIZE, 0.001);
		return attack;
	}
	public static Attack buildSpire(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(200);
		attack.setRange(125);
		attack.setDamage(0.5);
		attack.setInterval(0);
		attack.setCooldown(0.1);
		attack.setLifeTime(15);
		attack.setSize(2);
		attack.setRed(255);
		attack.setGreen(0, 1000);
		attack.setBlue(0);
		attack.setNbrOfColor(1);
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_MOB);
		attack.addOption(TARGET_BOSS);
		return attack;
	}
	public static Attack buildSpireOfUndying(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(200);
		attack.setRange(125);
		attack.setDamage(0.5);
		attack.setInterval(0);
		attack.setCooldown(0.1);
		attack.setLifeTime(15);
		attack.setSize(2);
		attack.setRed(255);
		attack.setGreen(0, 1000);
		attack.setBlue(0);
		attack.setNbrOfColor(1);
		attack.addOption(LIFESTEAL, 0.1);
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_MOB);
		attack.addOption(TARGET_BOSS);
		return attack;
	}
	public static Attack buildSpireOfJustice(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(200);
		attack.setRange(500);
		attack.setDamage(50);
		attack.setInterval(0);
		attack.setCooldown(10);
		attack.setLifeTime(15);
		attack.setSize(10);
		attack.setRed(255);
		attack.setGreen(255);
		attack.setBlue(255);
		attack.addQuote("Nyarg!");
		attack.addOption(SLOWING, 2, 0);
		attack.addOption(TARGET_FLYING);
		attack.addOption(TARGET_MOB);
		attack.addOption(TARGET_BOSS);
		return attack;
	}
	public static Attack buildShield(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(25);
		attack.setRange(75);
		attack.setDamage(0);
		attack.setCooldown(0.2);
		attack.setPrecisionLoss(0.25);
		attack.setSize(1);
		attack.setLifeTime(2.5);
		attack.setNbrOfBullet(100);
		attack.setBulletByShot(100);
		attack.setRed(0);
		attack.setGreen(200);
		attack.setBlue(0);
		attack.addOption(SLOWING, 1, 0);
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_FLYING);
		attack.addOption(TARGET_MOB);
		return attack;
	}
	public static Attack buildLazyBone(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(0);
		attack.setRange(0);
		attack.setDamage(0);
		attack.setCooldown(10, 60);
		attack.setLifeTime(0);
		attack.setSize(1);
		attack.setNbrOfBullet(0);
		attack.setRed(255);
		attack.setGreen(255);
		attack.setBlue(255);
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_FLYING);
		attack.addOption(TARGET_MOB);
		attack.addOption(TARGET_BOSS);
		return attack;
	}
	public static Attack buildGhasterBlaster(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(250, 300);
		attack.setRange(10000);
		attack.setDamage(0);
		attack.setInterval(0.02);
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
		attack.addOption(GHOST);
		attack.addOption(BLEEDING, 0.01, 1);
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_FLYING);
		attack.addOption(TARGET_MOB);
		attack.addOption(TARGET_BOSS);
		attack.addOption(LOCK_TARGET);
		return attack;
	}
	public static Attack buildGravityControl(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(100);
		attack.setRange(150);
		attack.setDamage(0);
		attack.setCooldown(0, 20);
		attack.setSize(1);
		attack.setLifeTime(2);
		attack.setRed(0);
		attack.setGreen(0);
		attack.setBlue(1);
		attack.addQuote("There goes nothing!");
		attack.addOption(CONFUSING, 5);
		attack.addOption(SLOWING, 5, 3);
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_FLYING);
		attack.addOption(TARGET_MOB);
		return attack;
	}
	public static Attack buildFrozenBone(Field field ) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(40);
		attack.setRange(100);
		attack.setDamage(1);
		attack.setCooldown(0, 20);
		attack.setInterval(2);
		attack.setNbrOfBullet(40, 200);
		attack.setBulletByShot(40);
		attack.setSize(7);
		attack.setPrecisionLoss(0.25);
		attack.setLifeTime(4);
		attack.setRed(0);
		attack.setGreen(255);
		attack.setBlue(255);
		attack.addOption(PAPYRUS, 5);
		attack.addOption(GHOST);
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_FLYING);
		attack.addOption(TARGET_MOB);
		attack.addOption(TARGET_BOSS);
		return attack;
	}
	public static Attack buildRainbowBlaster(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(200, 400);
		attack.setRange(10000);
		attack.setDamage(0.005);
		attack.setInterval(0);
		attack.setCooldown(200);
		attack.setLifeTime(1, 3);
		attack.setSize(2);
		attack.setPrecisionLoss(0.05);
		attack.setBulletByShot(200);
		attack.setNbrOfBullet(20000);
		attack.setRed(0, 255);
		attack.setGreen(0, 255);
		attack.setBlue(0, 255);
		attack.setNbrOfColor(1);
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_FLYING);
		attack.addOption(TARGET_MOB);
		attack.addOption(TARGET_BOSS);
		attack.addOption(LOCK_TARGET);
		attack.addOption(GHOST);
		return attack;
	}
	public static Attack buildRainbowShotgun(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(200);
		attack.setRange(200);
		attack.setDamage(5);
		attack.setInterval(0.4);
		attack.setCooldown(10, 20);
		attack.setLifeTime(3, 3);
		attack.setSize(10);
		attack.setPrecisionLoss(0.1);
		attack.setBulletByShot(4);
		attack.setNbrOfBullet(20);
		attack.setRed(255);
		attack.setGreen(255);
		attack.setBlue(255);
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_MOB);
		attack.addOption(TARGET_BOSS);
		return attack;
	}
	public static Attack buildRainbowBlast(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(30, 50);
		attack.setRange(200);
		attack.setDamage(0);
		attack.setInterval(2);
		attack.setCooldown(10, 20);
		attack.setLifeTime(60);
		attack.setSize(20);
		attack.setAimingFactor(0.05);
		attack.setPrecisionLoss(1);
		attack.setBulletByShot(3, 5);
		attack.setNbrOfBullet(9, 15);
		attack.setRed(0, 255);
		attack.setGreen(0, 255);
		attack.setBlue(0, 255);
		attack.setNbrOfColor(1);
		attack.addOption(TARGET_FLYING);
		attack.addOption(TARGET_MOB);
		attack.addOption(TARGET_BOSS);
		attack.setChainAttack(buildStarBlast(field));
		return attack;
	}
	private static Attack buildStarBlast(Field field) {
		Attack attack = new Attack(field);
		attack.setBulletSpeed(50, 250);
		attack.setRange(100);
		attack.setDamage(5);
		attack.setLifeTime(5, 10);
		attack.setSize(2);
		attack.setPrecisionLoss(1);
		attack.setBulletByShot(150);
		attack.setNbrOfBullet(150);
		attack.setRed(0, 255);
		attack.setGreen(0, 255);
		attack.setBlue(0, 255);
		attack.setNbrOfColor(1);
		attack.addOption(TARGET_WALKING);
		attack.addOption(TARGET_FLYING);
		attack.addOption(TARGET_MOB);
		attack.addOption(TARGET_BOSS);
		return attack;
	}
}
