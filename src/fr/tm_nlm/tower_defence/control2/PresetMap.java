package fr.tm_nlm.tower_defence.control2;

public abstract class PresetMap {
	public static Map empty() {
		return new Map();
	}
	public static Map grasslandIntro() {
		Map map = new Map();
		
		map.setBackground("grassland");
		map.addSlot(new Slot(new Vector(284, 380)));
		map.addSlot(new Slot(new Vector(447, 281)));
		map.addSlot(new Slot(new Vector(620, 316)));
		map.addSlot(new Slot(new Vector(655, 442)));
		map.addWave(PresetWave.wave1());
		
		return map;
	}
}
