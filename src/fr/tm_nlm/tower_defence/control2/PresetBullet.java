package fr.tm_nlm.tower_defence.control2;

public class PresetBullet {

	public static Bullet magicBullet() {
		Bullet bullet = new Bullet();
		
		bullet.setShape(PresetShape.circle(20));
		bullet.setSpeed(37.5);
		bullet.setDamage(Math.PI);
		bullet.setAimingFactor(0);
		
		return bullet;
	}

}
