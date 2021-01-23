package fr.tm_nlm.tower_defence.control2;

public class PresetBullet {
	public static Bullet test() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.circle(5));
		bullet.setSpeed(100);
		bullet.setDamage(0);
		
		return bullet;
	}
	public static Bullet magicBullet() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.circle(10));
		bullet.setSpeed(125);
		bullet.setDamage(Math.PI);
		bullet.setImage("mad_magic");
		
		return bullet;
	}
	public static Bullet magicMissile() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.triangle(23, 28));
		bullet.setSpeed(150);
		bullet.setDamage(2*Math.PI);
		bullet.setAimingFactor(0.75);
		bullet.setTrack(true);
		bullet.setLifeTime(5, 10);
		bullet.setOnDeathAttack(PresetAttack.tinyEkusplosion(), true, false);
		bullet.setImage("mad_missile");
		bullet.setCollideFlying(true);
		
		return bullet;
	}
	public static Bullet knife() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.triangle(33, 10));
		bullet.setSpeed(100);
		bullet.setDamage(10*Math.PI);
		bullet.setLifeTime(1.5);
		bullet.setFadingTime(2);
		bullet.setGreen(0);
		bullet.setBlue(0);
		bullet.setImage("mad_knife");
		
		return bullet;
	}
	public static Bullet kittyCorner() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.circle(8));
		bullet.setSpeed(300);
		bullet.setDamage(3*Math.PI);
		bullet.setLifeTime(1);
		bullet.setImage("mew_white_shot");
		bullet.setCollideFlying(true);
		bullet.setCollideWalking(true);
		
		return bullet;
	}
	public static Bullet blueKittyCorner() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.circle(8));
		bullet.setSpeed(300);
		bullet.setDamage(6*Math.PI);
		bullet.setLifeTime(1);
		bullet.setImage("mew_blue_shot");
		bullet.setCollideFlying(true);
		bullet.setCollideWalking(false);
		
		return bullet;
	}
	public static Bullet redKittyCorner() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.circle(8));
		bullet.setSpeed(300);
		bullet.setDamage(6*Math.PI);
		bullet.setLifeTime(1);
		bullet.setImage("mew_red_shot");
		bullet.setCollideFlying(false);
		bullet.setCollideWalking(true);
		
		return bullet;
	}
	public static Bullet shieldFrame() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.circle(5));
		bullet.setSpeed(0);
		bullet.setDamage(0);
		bullet.setRed(0);
		bullet.setGreen(163);
		bullet.setBlue(0);
		bullet.setGhost(true);
		bullet.setLifeTime(5);
		bullet.setSlow(1, 0.1);
		
		return bullet;
	}
	public static Bullet spear() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.rect(119, 18));
		bullet.setSpeed(300);
		bullet.setDamage(15, 20);
		bullet.setFadingTime(1);
		bullet.setSlow(1, 5);
		bullet.setImage("undyne_spear");
		bullet.setCollideWalking(false);
		bullet.setCollideFlying(true);
		
		return bullet;
	}
	public static Bullet redemptingSpear() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.rect(119, 18));
		bullet.setSpeed(300);
		bullet.setDamage(15, 20);
		bullet.setFadingTime(1);
		bullet.setSlow(1, 5);
		bullet.setImage("undyne_spear");
		bullet.setCollideFlying(true);
		
		return bullet;
	}
	public static Bullet lifeLessSpear() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.rect(119, 18));
		bullet.setSpeed(300);
		bullet.setLifeTime(0.9);
		bullet.setGhost(true);
		bullet.setDamage(0.025, 0.033);
		bullet.setFadingTime(1);
		bullet.setSlow(1, 5);
		bullet.setImage("undyne_spear");
		bullet.setCollideWalking(false);
		bullet.setCollideFlying(true);
		
		return bullet;
	}
	public static Bullet tinySpear() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.rect(26, 13));
		bullet.setSpeed(300);
		bullet.setDamage(3, 5);
		bullet.setLifeTime(0.5);
		bullet.setImage("undyne_tiny_spear");
		
		return bullet;
	}
	public static Bullet spearJail() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.circle(1));
		bullet.setSpeed(300);
		bullet.setDamage(0);
		bullet.setAlpha(0);
		bullet.setOnDeathAttack(PresetAttack.circleSpear(), true, false);
		bullet.setAimingFactor(1);
		bullet.setTrack(true);
		bullet.setAttackKeepTarget(true);
		bullet.setCollideWalking(false);
		bullet.setCollideFlying(true);
		
		return bullet;
	}
	public static Bullet tinyEkusplosionFlame() {
		Bullet bullet = new Bullet();
		
		bullet.setDamage(1);
		bullet.setSpeed(15);
		bullet.setLifeTime(0, 2);
		bullet.setShape(PresetShape.circle(3));
		bullet.setRed(255);
		bullet.setGreen(0, 127);
		bullet.setBlue(0);
		bullet.setFadingTime(0.5, 3);
		bullet.setCollideFlying(true);
		
		return bullet;
	}
	public static Bullet bonePunchLine() {
		Bullet bullet = new Bullet();
		
		bullet.setDamage(0);
		bullet.setSpeed(400);
		bullet.setLifeTime(0.5);
		bullet.setAimingFactor(1);
		bullet.setTrack(true);
		bullet.setShape(PresetShape.circle(1));
		bullet.setAlpha(0);
		bullet.setSlow(1, 10);
		
		return bullet;
	}
	public static Bullet gravityControl() {
		Bullet bullet = new Bullet();
		
		bullet.setDamage(0);
		bullet.setSpeed(400);
		bullet.setAimingFactor(1);
		bullet.setTrack(true);
		bullet.setShape(PresetShape.circle(1));
		bullet.setAlpha(0);
		bullet.setSlow(-5, 2);
		bullet.setCollideFlying(true);
		bullet.setCollideWalking(false);
		bullet.setConfuse(2);
		
		return bullet;
	}
	public static Bullet whiteBone() {
		Bullet bullet = new Bullet();
		
		bullet.setDamage(666);
		bullet.setSpeed(300);
		bullet.setShape(PresetShape.rect(86, 14));
		bullet.setImage("WhiteBone");
		
		return bullet;
	}
	public static Bullet blueBone() {
		Bullet bullet = new Bullet();
		
		bullet.setDamage(0.00666);
		bullet.setSpeed(300);
		bullet.setShape(PresetShape.rect(86, 14));
		bullet.setGhost(true);
		bullet.setImage("BlueBone");
		
		return bullet;
	}
	public static Bullet ghasterBlasterCall() {
		Bullet bullet = new Bullet();
		
		bullet.setDamage(0);
		bullet.setSpeed(200);
		bullet.setAimingFactor(100);
		bullet.setTrack(true);
		bullet.setShape(PresetShape.circle(1));
		bullet.setAlpha(0);
		bullet.setOnDeathAttack(PresetAttack.ghasterBlaster(), true, true);
		bullet.setAttackKeepTarget(true);
		bullet.setCollideFlying(true);
		
		return bullet;
	}
	public static Bullet ghasterBlaster() {
		Bullet bullet = new Bullet();
		
		bullet.setDamage(0);
		bullet.setSpeed(0);
		bullet.setShape(PresetShape.circle(5));
		bullet.setGhost(true);
		bullet.setAttackKeepTarget(true);
		bullet.setOnDeathAttack(PresetAttack.ghasterBlasterBeam(), false, true);
		bullet.setLifeTime(1);
		bullet.setFadingTime(2);
		bullet.setImage("GasterBlaster1");
		bullet.setFadingImage("GasterBlaster2");
		
		return bullet;
	}
	public static Bullet ghasterBlasterBeam() {
		Bullet bullet = new Bullet();
		
		bullet.setDamage(0.0666);
		bullet.setSpeed(0);
		bullet.setShape(PresetShape.rect(1200, 1200));
		bullet.setGhost(true);
		bullet.setLifeTime(1);
		bullet.setFadingTime(1);
		bullet.setCollideFlying(true);
		bullet.setImage("ghaster_blaster_beam");
		
		return bullet;
	}
	public static Bullet greatStar() {
		Bullet bullet = new Bullet();
		
		bullet.setDamage(0);
		bullet.setSpeed(100);
		bullet.setShape(PresetShape.circle(100));
		bullet.setLifeTime(3.5);
		bullet.setFadingTime(1);
		bullet.setCollideFlying(false);
		bullet.setCollideWalking(false);
		bullet.setOnDeathAttack(PresetAttack.greatStarBurst(), false, true);
		
		return bullet;
	}
	public static Bullet tinyStar() {
		Bullet bullet = new Bullet();
		
		bullet.setDamage(20, 50);
		bullet.setSpeed(250);
		bullet.setShape(PresetShape.circle(5));
		bullet.setLifeTime(5);
		bullet.setCollideFlying(true);
		bullet.setCollideWalking(true);
		
		return bullet;
	}
	public static Bullet rainbowBlaster() {
		Bullet bullet = new Bullet();
		
		bullet.setDamage(0);
		bullet.setSpeed(0);
		bullet.setShape(PresetShape.circle(5));
		bullet.setGhost(true);
		bullet.setAttackKeepTarget(true);
		bullet.setOnDeathAttack(PresetAttack.rainbowBlasterBeam(), false, true);
		bullet.setLifeTime(1);
		bullet.setFadingTime(2);
//		bullet.setImage("GasterBlaster1");
//		bullet.setFadingImage("GasterBlaster2");
		
		return bullet;
	}
	public static Bullet rainbowBlasterBeam() {
		//FIXME
		Bullet bullet = new Bullet();
		
		bullet.setDamage(10);
		bullet.setSpeed(0);
		bullet.setShape(PresetShape.rect(400, 200));
		bullet.setLifeTime(1);
		bullet.setFadingTime(1);
		bullet.setCollideFlying(true);
		bullet.setImage("ghaster_blaster_beam");
		
		return bullet;
	}
	public static Bullet rainbowMissile() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.circle(10));
		bullet.setSpeed(150);
		bullet.setGhost(true);
		bullet.setDamage(0.01);
		bullet.setAimingFactor(0.80);
		bullet.setTrack(true);
		bullet.setLifeTime(15, 20);
		//bullet.setImage("mad_missile");
		bullet.setCollideFlying(true);
		
		return bullet;
	}
	public static Bullet rainbowLight() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.rect(1800, 900));
		bullet.setSpeed(0);
		bullet.setGhost(true);
		bullet.setDamage(0.002);
		bullet.setLethal(false);
		bullet.setLifeTime(1);
		//bullet.setImage("mad_missile");
		bullet.setCollideFlying(true);
		bullet.setLifeTime(5);
		
		return bullet;
	}
}
