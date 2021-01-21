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
		
		bullet.setShape(PresetShape.circle(20));
		bullet.setSpeed(125);
		bullet.setDamage(Math.PI);
		
		return bullet;
	}
	public static Bullet magicMissile() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.triangle(20, 40));
		bullet.setSpeed(200);
		bullet.setDamage(2*Math.PI);
		bullet.setAimingFactor(0.5);
		bullet.setTrack(true);
		bullet.setLifeTime(5);
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
		
		bullet.setDamage(0.01);
		bullet.setSpeed(250);
		bullet.setLifeTime(0, 0.3);
		bullet.setShape(PresetShape.circle(1));
		bullet.setRed(255);
		bullet.setGreen(127, 255);
		bullet.setBlue(0);
		bullet.setFadingTime(0.5, 3);
		
		return bullet;
	}
}
