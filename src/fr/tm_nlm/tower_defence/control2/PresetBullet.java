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
	public static Bullet spear() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.rect(119, 18));
		bullet.setSpeed(300);
		bullet.setDamage(15, 20);
		bullet.setImage("undyne_spear");
		
		return bullet;
	}
	public static Bullet tinyEkusplosionFlame() {
		Bullet bullet = new Bullet();
		
		bullet.setDamage(0.1);
		bullet.setSpeed(100);
		bullet.setLifeTime(0, 0.3);
		bullet.setShape(PresetShape.circle(3));
		bullet.setRed(255);
		bullet.setGreen(0, 127);
		bullet.setBlue(0);
		bullet.setFadingTime(3, 3);
		
		return bullet;
	}
}
