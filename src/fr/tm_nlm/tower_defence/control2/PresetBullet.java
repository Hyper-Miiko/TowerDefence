package fr.tm_nlm.tower_defence.control2;

public class PresetBullet {
	public static Bullet test() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.circle(5));
		bullet.setSpeed(100);
		bullet.setDamage(0);
		bullet.setAimingFactor(0);
		
		return bullet;
	}
	public static Bullet magicBullet() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.circle(20));
		bullet.setSpeed(75);
		bullet.setDamage(Math.PI);
		bullet.setAimingFactor(0);
		
		return bullet;
	}

}
