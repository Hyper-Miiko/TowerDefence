package fr.tm_nlm.tower_defence.control2;

public abstract class PresetMap {
	public static Map test() {
		Map map = new Map();
		
		map.setBackground("grassland");
		map.addSlot(new Slot(new Vector(284, 380)));
		map.addSlot(new Slot(new Vector(447, 281)));
		map.addSlot(new Slot(new Vector(620, 316)));
		map.addSlot(new Slot(new Vector(655, 442)));
		map.setWave(PresetWave.test());
		
		return map;
	}
	public static Map empty() {
		return new Map();
	}
	public static Map grassland1() {
		Map map = new Map();
		
		map.setBackground("grassland");
		map.addSlot(new Slot(new Vector(284, 380)));
		map.addSlot(new Slot(new Vector(447, 281)));
		map.addSlot(new Slot(new Vector(620, 316)));
		map.addSlot(new Slot(new Vector(655, 442)));
		map.setWave(PresetWave.grassland11());
		
		return map;
	}
	public static Map grassland2() {
		Map map = new Map();
		
		map.setBackground("grassland");
		map.addSlot(new Slot(new Vector(284, 380)));
		map.addSlot(new Slot(new Vector(447, 281)));
		map.addSlot(new Slot(new Vector(620, 316)));
		map.addSlot(new Slot(new Vector(655, 442)));
		map.setWave(PresetWave.grassland21());
		
		return map;
	}
	public static Map grassland3() {
		Map map = new Map();
		
		map.setBackground("grassland");
		map.addSlot(new Slot(new Vector(284, 380)));
		map.addSlot(new Slot(new Vector(447, 281)));
		map.addSlot(new Slot(new Vector(620, 316)));
		map.addSlot(new Slot(new Vector(655, 442)));
		map.setWave(PresetWave.grassland31());
		
		return map;
	}
	
	public static Map toundra1() {
		Map map = new Map();
		map.setBackground("toundra");
		map.setWave(PresetWave.test());
		return map;
	}
	public static Map toundra2() {
		Map map = new Map();
		map.setBackground("toundra");
		map.setWave(PresetWave.test());
		return map;
	}
	public static Map toundra3() {
		Map map = new Map();
		map.setBackground("toundra");
		map.setWave(PresetWave.test());
		return map;
	}
	
	public static Map volcano1() {
		Map map = new Map();
		map.setBackground("volcano");
		map.setWave(PresetWave.test());
		return map;
	}
	public static Map volcano2() {
		Map map = new Map();
		map.setBackground("volcano");
		map.setWave(PresetWave.test());
		return map;
	}
	public static Map volcano3() {
		Map map = new Map();
		map.setBackground("volcano");
		map.setWave(PresetWave.test());
		return map;
	}
}
