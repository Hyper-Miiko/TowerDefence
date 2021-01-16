package fr.tm_nlm.tower_defence.control2;

public abstract class PresetMap {
	public static Map empty() {
		return new Map();
	}
	public static Map grasslandIntro() {
		Map map = new Map();
		
		map.setBackground("data/img/grasslang.png");
		map.addWave(PresetWave.intro());
		
		return map;
	}
}
