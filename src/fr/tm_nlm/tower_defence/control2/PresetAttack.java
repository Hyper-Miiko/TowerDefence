package fr.tm_nlm.tower_defence.control2;

public class PresetAttack {
	public static Attack magicBullets() {
		Attack attack = new Attack("Magic Bullets");
		
		attack.setBullet(PresetBullet.magicBullet());
		attack.setCooldown(3, 5);
		attack.setSpreadRange(0);
		attack.setStartShotRange(30, 40);
		attack.setConverge(true);
		attack.setStartSpreadRange(Math.PI/3);
		attack.setRandomSpread(true);
		attack.setBulletsByShot(4, 8);
		attack.setNbrOfShot(3, 5);
		attack.setInterval(0.25, 0.4);
		attack.setRange(100);
		attack.setKeepTracking(true);
		
		return attack;
	}
	public static Attack magicMissiles() {
		Attack attack = new Attack("Magic Missiles");
		
		attack.setBullet(PresetBullet.magicMissile());
		attack.setCooldown(15, 25);
		attack.setSpreadRange(Math.PI);
		attack.setRandomSpread(true);
		attack.setBulletsByShot(4, 8);
		attack.setNbrOfShot(2, 5);
		attack.setInterval(2, 5);
		attack.setRange(200);
		attack.setKeepTracking(true);
		attack.setTargetFlying(true);
		attack.setTargetWalking(false);
		
		return attack;
	}
	public static Attack kittyCornerShot() {
		Attack attack = new Attack("Kitty Corner Shot!");
		
		attack.setBullet(PresetBullet.kittyCorner());
		attack.setCooldown(1, 3);
		attack.setNbrOfShot(3);
		attack.setInterval(0.25);
		attack.setRange(200);
		attack.setKeepTracking(true);
		attack.setTargetFlying(true);
		attack.setTargetWalking(true);
		
		return attack;
	}
	public static Attack blueKittyCornerShot() {
		Attack attack = new Attack("Blue Kitty Corner Shot!");
		
		attack.setBullet(PresetBullet.blueKittyCorner());
		attack.setCooldown(1, 3);
		attack.setNbrOfShot(3);
		attack.setInterval(0.25);
		attack.setRange(200);
		attack.setKeepTracking(true);
		attack.setTargetFlying(true);
		attack.setTargetWalking(true);
		
		return attack;
	}
	public static Attack redKittyCornerShot() {
		Attack attack = new Attack("RedKitty Corner Shot!");
		
		attack.setBullet(PresetBullet.redKittyCorner());
		attack.setCooldown(1, 3);
		attack.setNbrOfShot(3);
		attack.setInterval(0.25);
		attack.setRange(200);
		attack.setKeepTracking(true);
		attack.setTargetFlying(true);
		attack.setTargetWalking(true);
		
		return attack;
	}
	public static Attack shield() {
		Attack attack = new Attack("Shield");
		
		attack.setBullet(PresetBullet.shieldFrame());
		attack.setCooldown(15, 20);
		attack.setRange(300);
		attack.setBulletsByShot(100);
		attack.setStartShotRange(80);
		attack.setStartSpreadRange(Math.PI/6);
		
		return attack;
	}
	public static Attack spear() {
		Attack attack = new Attack("Spear");
		
		attack.setBullet(PresetBullet.spear());
		attack.setCooldown(2, 20);
		attack.setRange(300);
		attack.setTargetFlying(true);
		attack.setTargetWalking(false);
		
		return attack;
	}
	public static Attack tinySpear() {
		Attack attack = new Attack("Tiny Spear");
		
		attack.setBullet(PresetBullet.tinySpear());
		attack.setCooldown(1.5, 2);
		attack.setRange(120);
		attack.setInterval(0.25);
		attack.setNbrOfShot(5, 9);
		attack.setBulletsByShot(1);
		attack.setKeepTracking(true);
		
		return attack;
	}
	public static Attack randomSpear() {
		Attack attack = new Attack("Spear Redemption");
		
		attack.setBullet(PresetBullet.redemptingSpear());
		attack.setCooldown(10, 20);
		attack.setNbrOfShot(8, 20);
		attack.setInterval(0.5);
		attack.setStartShotRange(-100, 500);
		attack.setStartSpreadRange(Math.PI/4);
		attack.setConverge(true);
		attack.setKeepTracking(true);
		attack.setRange(200);
		attack.setTargetFlying(true);
		
		return attack;
	}
	public static Attack spearJail() {
		Attack attack = new Attack("Spear Jail");
		
		attack.setBullet(PresetBullet.spearJail());
		attack.setCooldown(20, 30);
		attack.setNbrOfShot(3, 5);
		attack.setInterval(1);
		attack.setKeepTracking(true);
		attack.setRange(150);
		attack.setTargetFlying(true);
		attack.setTargetWalking(false);
		
		return attack;
	}
	public static Attack circleSpear() {
		Attack attack = new Attack("Iron maiden");
		
		attack.setStartShotRange(300);
		attack.setBulletsByShot(7);
		attack.setStartSpreadRange(Math.PI);
		attack.setBullet(PresetBullet.lifeLessSpear());
		attack.setConverge(true);
		
		return attack;
	}
	public static Attack tinyEkusplosion() {
		Attack attack = new Attack("Loli Megumin");
		
		attack.setBullet(PresetBullet.tinyEkusplosionFlame());
		attack.setBulletsByShot(100, 150);
		attack.setSpreadRange(Math.PI);
		attack.setStartShotRange(0, 25);
		attack.setStartSpreadRange(Math.PI);
		attack.setCooldown(0);
		attack.setTargetFlying(true);
		
		return attack;
	}
	public static Attack boneJoke() {
		Attack attack = new Attack("Bone Joke");
		
		attack.setBullet(PresetBullet.bonePunchLine());
		attack.setRange(80);
		attack.setCooldown(9.8);
		
		return attack;
	}
	public static Attack gravityControl() {
		Attack attack = new Attack("Gravity Control");
		
		attack.setBullet(PresetBullet.gravityControl());
		attack.setRange(80);
		attack.setCooldown(0, 15);
		attack.setTargetFlying(true);
		attack.setTargetWalking(false);
		
		return attack;
	}
	public static Attack whiteBone() {
		Attack attack = new Attack("");
		
		attack.setBullet(PresetBullet.whiteBone());
		attack.setRange(150);
		attack.setCooldown(0, 5);
		attack.setBulletsByShot(4, 6);
		attack.setStartShotRange(-100, -200);
		attack.setStartSpreadRange(Math.PI/2);
		attack.setConverge(true);
		
		return attack;
	}
	public static Attack blueBone() {
		Attack attack = new Attack("Ghaster Blaster");
		
		attack.setBullet(PresetBullet.blueBone());
		attack.setRange(150);
		attack.setCooldown(0, 5);
		attack.setBulletsByShot(4, 6);
		attack.setStartShotRange(-100, -200);
		attack.setStartSpreadRange(Math.PI/2);
		attack.setConverge(true);
		
		return attack;
	}
	public static Attack ghasterBlasterCall() {
		Attack attack = new Attack("Ghaster Blaster");
		
		attack.setBullet(PresetBullet.ghasterBlasterCall());
		attack.setRange(200);
		attack.setCooldown(0, 60);
		attack.setTargetFlying(true);
		
		return attack;
	}
	public static Attack ghasterBlaster() {
		Attack attack = new Attack("Ghaster Blaster");
		
		attack.setBullet(PresetBullet.ghasterBlaster());
		attack.setStartShotRange(150, 300);
		attack.setConverge(true);
		attack.setStartSpreadRange(Math.PI);
		attack.setRandomSpread(true);
		attack.setTargetFlying(true);
		
		return attack;
	}
	public static Attack ghasterBlasterBeam() {
		Attack attack = new Attack("Ghaster Blaster");
		
		attack.setBullet(PresetBullet.ghasterBlasterBeam());
		attack.setTargetFlying(true);
		attack.setStartShotRange(600);
		
		return attack;
	}
}
