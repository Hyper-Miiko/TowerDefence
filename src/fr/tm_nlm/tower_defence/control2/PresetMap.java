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
	
	public static Map toundra() {
		Map map = new Map();
		
		map.setBackground("toundra");
		map.addSlot(new Slot(new Vector(162, 355)));
		map.addSlot(new Slot(new Vector(311, 336)));
		map.addSlot(new Slot(new Vector(358, 227)));
		map.addSlot(new Slot(new Vector(563, 414)));
		map.addSlot(new Slot(new Vector(790, 312)));
		map.addSlot(new Slot(new Vector(766, 426)));
		map.addWave(PresetWave.trainingNewEnnemy());
		
		return map;
	}
}
