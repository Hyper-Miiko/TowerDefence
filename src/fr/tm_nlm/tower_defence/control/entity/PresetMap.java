package fr.tm_nlm.tower_defence.control.entity;

import fr.tm_nlm.tower_defence.control.Field;

public class PresetMap {
	public static Field buildIntroGrassland() {
		Field field = new Field();
		
		PathNode pathNodeStart = new PathNode(field, -25, 445, false);
		
		PathNode pathNode1  = new PathNode(field, 283, 451, false);
		PathNode pathNode2  = new PathNode(field, 336, 432, false);
		PathNode pathNode3  = new PathNode(field, 364, 384, false);
		PathNode pathNode4  = new PathNode(field, 368, 271, false);
		PathNode pathNode5  = new PathNode(field, 396, 237, false);
		PathNode pathNode6  = new PathNode(field, 445, 218, false);
		PathNode pathNode7  = new PathNode(field, 492, 229, false);
		PathNode pathNode8  = new PathNode(field, 528, 272, false);
		PathNode pathNode9  = new PathNode(field, 558, 356, false);
		PathNode pathNode10 = new PathNode(field, 609, 383, false);
		PathNode pathNode11 = new PathNode(field, 674, 386, false);
		PathNode pathNode12 = new PathNode(field, 721, 410, false);
		PathNode pathNode13 = new PathNode(field, 761, 444, false);
		PathNode pathNode14 = new PathNode(field, 807, 455, false);
		
		PathNode pathNodeEnd = new PathNode(field, 925, 460, true);
		
		field.connect(pathNodeStart, pathNode1);
		field.connect(pathNode1, pathNode2);
		field.connect(pathNode2, pathNode3);
		field.connect(pathNode3, pathNode4);
		field.connect(pathNode4, pathNode5);
		field.connect(pathNode5, pathNode6);
		field.connect(pathNode6, pathNode7);
		field.connect(pathNode7, pathNode8);
		field.connect(pathNode8, pathNode9);
		field.connect(pathNode9, pathNode10);
		field.connect(pathNode10, pathNode11);
		field.connect(pathNode11, pathNode12);
		field.connect(pathNode12, pathNode13);
		field.connect(pathNode13, pathNode14);
		field.connect(pathNode14, pathNodeEnd);
		
		pathNodeEnd.calcWay();
		
		Wave wave = new Wave(pathNodeStart, 1);
		for(int i = 0; i < 10; i++) {
			wave.addMonster(PresetMonster.buildKumo(field));
		}
		field.addWave(wave);
		
		wave.setTimer(5);
		//System.out.println(pathNodeStart.getWayToCastle());
		return field;
	}
}
