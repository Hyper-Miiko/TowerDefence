//package fr.tm_nlm.tower_defence.control.entity;
//
//import fr.tm_nlm.tower_defence.control.Field;
//
//public class PresetMap {
//	public static Field buildIntroGrassland() {
//		Field field = new Field();
//		
//		PathNode pathNodeStart = field.createPathNode(-25, 445, false);
//		
//		PathNode pathNode1  = field.createPathNode(283, 451, false);
//		PathNode pathNode2  = field.createPathNode(336, 432, false);
//		PathNode pathNode3  = field.createPathNode(364, 384, false);
//		PathNode pathNode4  = field.createPathNode(368, 271, false);
//		PathNode pathNode5  = field.createPathNode(396, 237, false);
//		PathNode pathNode6  = field.createPathNode(445, 218, false);
//		PathNode pathNode7  = field.createPathNode(492, 229, false);
//		PathNode pathNode8  = field.createPathNode(528, 272, false);
//		PathNode pathNode9  = field.createPathNode(558, 356, false);
//		PathNode pathNode10 = field.createPathNode(609, 383, false);
//		PathNode pathNode11 = field.createPathNode(674, 386, false);
//		PathNode pathNode12 = field.createPathNode(721, 410, false);
//		PathNode pathNode13 = field.createPathNode(761, 444, false);
//		PathNode pathNode14 = field.createPathNode(807, 455, false);
//		
//		PathNode pathNodeEnd = field.createPathNode(925, 460, true);
//		
//		field.connect(pathNodeStart, pathNode1);
//		field.connect(pathNode1, pathNode2);
//		field.connect(pathNode2, pathNode3);
//		field.connect(pathNode3, pathNode4);
//		field.connect(pathNode4, pathNode5);
//		field.connect(pathNode5, pathNode6);
//		field.connect(pathNode6, pathNode7);
//		field.connect(pathNode7, pathNode8);
//		field.connect(pathNode8, pathNode9);
//		field.connect(pathNode9, pathNode10);
//		field.connect(pathNode10, pathNode11);
//		field.connect(pathNode11, pathNode12);
//		field.connect(pathNode12, pathNode13);
//		field.connect(pathNode13, pathNode14);
//		field.connect(pathNode14, pathNodeEnd);
//		
//		pathNodeEnd.calcWay();
//
//		Wave wave = new Wave(pathNodeStart, 1);
//		for(int i = 0; i < 3; i++) {
//			wave.addMonster(PresetMonster.buildKumo(field));
//		}
//		field.addWave(wave);
//		
//		wave = new Wave(pathNodeStart, 1);
//		for(int i = 0; i < 5; i++) {
//			wave.addMonster(PresetMonster.buildKumo(field));
//		}
//		field.addWave(wave);
//		
//		return field;
//	}
//}
