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
		
		return bullet;
	}
}
