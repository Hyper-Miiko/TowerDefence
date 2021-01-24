package fr.tm_nlm.tower_defence.control2;

public abstract class PresetMap {
	public static Map test() {
		Map map = new Map();
		
		map.setBackground("toundra");
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
		map.addSlot(new Slot(new Vector(161, 353)));
		map.addSlot(new Slot(new Vector(310, 337)));
		map.addSlot(new Slot(new Vector(355, 226)));
		map.addSlot(new Slot(new Vector(560, 417)));
		map.addSlot(new Slot(new Vector(793, 313)));
		map.addSlot(new Slot(new Vector(764, 426)));
		map.setWave(PresetWave.toundra11());
		
		return map;
	}
	public static Map toundra2() {
		Map map = new Map();
		
		map.setBackground("toundra");
		map.addSlot(new Slot(new Vector(161, 353)));
		map.addSlot(new Slot(new Vector(310, 337)));
		map.addSlot(new Slot(new Vector(355, 226)));
		map.addSlot(new Slot(new Vector(560, 417)));
		map.addSlot(new Slot(new Vector(793, 313)));
		map.addSlot(new Slot(new Vector(764, 426)));
		map.setWave(PresetWave.toundra21());
		
		return map;
	}
	public static Map toundra3() {
		Map map = new Map();
		
		map.setBackground("toundra");
		map.addSlot(new Slot(new Vector(161, 353)));
		map.addSlot(new Slot(new Vector(310, 337)));
		map.addSlot(new Slot(new Vector(355, 226)));
		map.addSlot(new Slot(new Vector(560, 417)));
		map.addSlot(new Slot(new Vector(793, 313)));
		map.addSlot(new Slot(new Vector(764, 426)));
		map.setWave(PresetWave.toundra31());
		
		return map;
	}
	public static Map volcano1() {
		Map map = new Map();
		
		map.setBackground("volcano");
		map.addSlot(new Slot(new Vector(124, 233)));
		map.addSlot(new Slot(new Vector(459, 211)));
		map.addSlot(new Slot(new Vector(456, 452)));
		map.addSlot(new Slot(new Vector(603, 365)));
		map.addSlot(new Slot(new Vector(775, 373)));
		map.setWave(PresetWave.volcano11());
		
		return map;
	}
	public static Map volcano2() {
		Map map = new Map();
		
		map.setBackground("volcano");
		map.addSlot(new Slot(new Vector(124, 233)));
		map.addSlot(new Slot(new Vector(459, 211)));
		map.addSlot(new Slot(new Vector(456, 452)));
		map.addSlot(new Slot(new Vector(603, 365)));
		map.addSlot(new Slot(new Vector(775, 373)));
		map.setWave(PresetWave.volcano21());
		
		return map;
	}
	public static Map volcano3() {
		Map map = new Map();
		
		map.setBackground("volcano");
		map.addSlot(new Slot(new Vector(124, 233)));
		map.addSlot(new Slot(new Vector(459, 211)));
		map.addSlot(new Slot(new Vector(456, 452)));
		map.addSlot(new Slot(new Vector(603, 365)));
		map.addSlot(new Slot(new Vector(775, 373)));
		map.setWave(PresetWave.volcano31());
		
		return map;
	}
}
