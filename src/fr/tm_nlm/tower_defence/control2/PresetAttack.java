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
		attack.setNbrOfShot(3, 5);
		attack.setInterval(2, 5);
		attack.setRange(200);
		attack.setKeepTracking(true);
		attack.setTargetFlying(true);
		attack.setTargetWalking(false);
		
		return attack;
	}
	public static Attack knife() {
		Attack attack = new Attack("Kniffe");
		
		attack.setBullet(PresetBullet.knife());
		attack.setCooldown(60, 180);
		attack.setSpreadRange(Math.PI/4);
		attack.setRandomSpread(true);
		attack.setRange(300);
		attack.addQuote("Out of kniffes (\"-_-)");
		
		return attack;
	}
	public static Attack kittyCornerShot() {
		Attack attack = new Attack("Kitty Corner Shot!");
		
		attack.setBullet(PresetBullet.kittyCorner());
		attack.setCooldown(2, 6);
		attack.setNbrOfShot(3);
		attack.setInterval(0.25);
		attack.setRange(200);
		attack.setKeepTracking(true);
		attack.setTargetFlying(true);
		attack.setTargetWalking(true);
		attack.addQuote("ENOUGH!!!");
		
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
		attack.setRange(200);
		attack.setBulletsByShot(100);
		attack.setStartShotRange(80);
		attack.setStartSpreadRange(Math.PI/6);
		attack.addQuote("En guarde!");
		attack.addQuote("STOP RUNNING AWAY!!!");
		attack.addQuote("COME BACK HERE, YOU LITTLE PUNK!!");
		
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
		attack.setCooldown(20, 40);
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
		attack.setNbrOfShot(1);
		attack.setKeepTracking(true);
		attack.setRange(150);
		attack.setTargetFlying(true);
		attack.setTargetWalking(false);
		attack.addQuote("You're gonna have to try a little harder than THAT.");
		
		return attack;
	}
	public static Attack circleSpear() {
		Attack attack = new Attack("Iron maiden");
		
		attack.setStartShotRange(300);
		attack.setBulletsByShot(7);
		attack.setNbrOfShot(3, 5);
		attack.setInterval(1);
		attack.setStartSpreadRange(Math.PI);
		attack.setBullet(PresetBullet.lifeLessSpear());
		attack.setConverge(true);
		attack.setSound("undyne");
		
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
		attack.addQuote("Why are skeletons so calm?\r\n" + 
				"Because nothing gets under their skin!");
		attack.addQuote("What does a skeleton order at a restaurant?\r\n" + 
				"SPARERIBS");
		attack.addQuote("Pretty humerus, right?");
		attack.addQuote("My favorite instrument? the TromBONE, of course.");
		attack.addQuote("My brother truly is a numbSKULL");
		attack.addQuote("What do skeletons hate the most about wind?\r\n" + 
				"Nothing, it goes right through them.");
		attack.addQuote("In the end, Asgore made Papyrus a cool Hedge Skull-ture");
		attack.addQuote("Papyrus doesn't like my hotdogs. probally because he doesn't have the stomach for it!");
		attack.addQuote("I'm not fat. I'm just big boned!");
		attack.addQuote("My brother always works himself down to the bone!");
		attack.setSound("sans_talking");
		
		return attack;
	}
	public static Attack gravityControl() {
		Attack attack = new Attack("Gravity Control");
		
		attack.setBullet(PresetBullet.gravityControl());
		attack.setRange(100);
		attack.setCooldown(0, 15);
		attack.setTargetFlying(true);
		attack.setTargetWalking(false);
		attack.setSound("magical_eye");
		
		return attack;
	}
	public static Attack whiteBone() {
		Attack attack = new Attack("White Bone");
		
		attack.setBullet(PresetBullet.whiteBone());
		attack.setRange(150);
		attack.setCooldown(0, 5);
		attack.setBulletsByShot(4, 6);
		attack.setStartShotRange(-200, -100);
		attack.setStartSpreadRange(Math.PI/20);
		attack.setConverge(false);
		
		return attack;
	}
	public static Attack blueBone() {
		Attack attack = new Attack("Blue Bone");
		
		attack.setBullet(PresetBullet.blueBone());
		attack.setRange(150);
		attack.setCooldown(0, 5);
		attack.setBulletsByShot(4, 6);
		attack.setStartShotRange(-200, -100);
		attack.setStartSpreadRange(Math.PI/20);
		attack.setConverge(false);
		
		return attack;
	}
	public static Attack ghasterBlasterCall() {
		Attack attack = new Attack("Ghaster Blaster");
		
		attack.setBullet(PresetBullet.ghasterBlasterCall());
		attack.setRange(200);
		attack.setCooldown(30, 60);
		attack.setTargetFlying(true);
		attack.addQuote("Never understood why people don't use their best attack first. ¯\\_(ツ)_/¯");
		
		return attack;
	}
	public static Attack ghasterBlaster() {
		Attack attack = new Attack("Ghaster Blaster");
		
		attack.setBullet(PresetBullet.ghasterBlaster());
		attack.setStartShotRange(150, 600);
		attack.setBulletsByShot(6, 12);
		attack.setConverge(true);
		attack.setStartSpreadRange(Math.PI);
		attack.setRandomSpread(true);
		attack.setTargetFlying(true);
		attack.setSound("ghaster_blaster");
		
		return attack;
	}
	public static Attack ghasterBlasterBeam() {
		Attack attack = new Attack("Ghaster Blaster");
		
		attack.setBullet(PresetBullet.ghasterBlasterBeam());
		attack.setTargetFlying(true);
		attack.setStartShotRange(600);
		attack.setConverge(true);
		
		return attack;
	}
	public static Attack greatStarCall() {
		Attack attack = new Attack("Great Star");
		
		attack.setBullet(PresetBullet.greatStar());
		attack.setTargetFlying(true);
		attack.setStartShotRange(600);
		attack.setStartSpreadRange(Math.PI/3);
		attack.setConverge(true);
		attack.setCooldown(30, 45);
		attack.setSound("star_blazing_summon");
		attack.setRange(300);
		
		return attack;
	}
	public static Attack greatStarBurst() {
		Attack attack = new Attack("Great Star Burst");
		
		attack.setBullet(PresetBullet.tinyStar());
		attack.setTargetFlying(true);
		attack.setBulletsByShot(16);
		attack.setSpreadRange(Math.PI);
		attack.setNbrOfShot(3);
		attack.setInterval(0.25);
		attack.setRotationBetweenShot(Math.PI/16);
		
		return attack;
	}
	public static Attack rainbowBlaster() {
		Attack attack = new Attack("Rainbow Blaster");
		
		attack.setBullet(PresetBullet.rainbowBlaster());
		attack.setRange(400);
		attack.setConverge(false);
		attack.setCooldown(15, 20);
		attack.setStartShotRange(30);
		attack.setStartSpreadRange(-Math.PI/4);
		
		return attack;
	}
	public static Attack rainbowBlasterBeam() {
		Attack attack = new Attack("Rainbow Blaster Beam");
		
		attack.setBullet(PresetBullet.rainbowBlasterBeam());
		attack.setConverge(false);
		attack.setStartShotRange(600);
		attack.setNbrOfShot(1);
		
		return attack;
	}
	public static Attack starGun() {
		Attack attack = new Attack("Star Gun");
		
		attack.setBullet(PresetBullet.chaosBullet());
		attack.setTargetFlying(true);
		attack.setCooldown(4, 5);
		attack.setBulletsByShot(4);
		attack.setSpreadRange(Math.PI/8);
		attack.setNbrOfShot(5);
		attack.setInterval(0.25);
		attack.setKeepTracking(true);
		
		return attack;
	}
	
	public static Attack rainbowMissile() {
		Attack attack = new Attack("Rainbow Missiles");
		
		attack.setBullet(PresetBullet.rainbowMissile());
		attack.setCooldown(1, 2);
		attack.setSpreadRange(Math.PI);
		attack.setRandomSpread(true);
		attack.setBulletsByShot(3, 4);
		attack.setNbrOfShot(4, 6);
		attack.setInterval(0.05);
		attack.setRange(200);
		attack.setKeepTracking(true);
		attack.setTargetFlying(true);
		attack.setTargetWalking(false);
		
		return attack;
	}
	public static Attack rainbowLight() {
		Attack attack = new Attack("Rainbow Light");
		
		attack.setBullet(PresetBullet.rainbowLight());
		attack.setCooldown(45, 60);
		attack.setNbrOfShot(1);
		attack.setRange(100);
		attack.setStartShotRange(450);
		attack.setKeepTracking(false);
		attack.setTargetFlying(true);
		
		return attack;
	}
}
