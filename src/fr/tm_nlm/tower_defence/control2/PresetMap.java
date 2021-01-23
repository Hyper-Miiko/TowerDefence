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
	
//	public static Map toundra() {
//		Map map = new Map();
//		
//		map.setBackground("toundra");
//		map.addSlot(new Slot(new Vector(162, 355)));
//		map.addSlot(new Slot(new Vector(311, 336)));
//		map.addSlot(new Slot(new Vector(358, 227)));
//		map.addSlot(new Slot(new Vector(563, 414)));
//		map.addSlot(new Slot(new Vector(790, 312)));
//		map.addSlot(new Slot(new Vector(766, 426)));
//		
//		return map;
//	}
//	
//	public static Map volcano() {
//		Map map = new Map();
//		
//		map.setBackground("volcano");
//		map.addSlot(new Slot(new Vector(123, 237)));
//		map.addSlot(new Slot(new Vector(460, 217)));
//		map.addSlot(new Slot(new Vector(455, 456)));
//		map.addSlot(new Slot(new Vector(605, 369)));
//		map.addSlot(new Slot(new Vector(774, 377)));
//		map.addWave(PresetWave.volcanoWave1());
//		
//		return map;
//	}
}
