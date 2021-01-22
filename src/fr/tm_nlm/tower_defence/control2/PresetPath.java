package fr.tm_nlm.tower_defence.control2;

public abstract class PresetPath {
	public static PathNode grassland() {
		PathNode pathNodeStart = new PathNode(-25, 445);
		PathNode pathNode1 = new PathNode(283, 451);
		PathNode pathNode2 = new PathNode(336, 432);
		PathNode pathNode3 = new PathNode(364, 384);
		PathNode pathNode4 = new PathNode(368, 271);
		PathNode pathNode5 = new PathNode(396, 237);
		PathNode pathNode6 = new PathNode(445, 213);
		PathNode pathNode7 = new PathNode(492, 229);
		PathNode pathNode8 = new PathNode(528, 272);
		PathNode pathNode9 = new PathNode(558, 356);
		PathNode pathNode10 = new PathNode(609, 383);
		PathNode pathNode11 = new PathNode(674, 386);
		PathNode pathNode12 = new PathNode(721, 410);
		PathNode pathNode13 = new PathNode(761, 444);
		PathNode pathNode14 = new PathNode(807, 455);
		PathNode pathNodeEnd = new PathNode(925, 460, true);
		
		pathNodeStart.addWay(pathNode1);
		pathNode1.addWay(pathNode2);
		pathNode2.addWay(pathNode3);
		pathNode3.addWay(pathNode4);
		pathNode4.addWay(pathNode5);
		pathNode5.addWay(pathNode6);
		pathNode6.addWay(pathNode7);
		pathNode7.addWay(pathNode8);
		pathNode8.addWay(pathNode9);
		pathNode9.addWay(pathNode10);
		pathNode10.addWay(pathNode11);
		pathNode11.addWay(pathNode12);
		pathNode12.addWay(pathNode13);
		pathNode13.addWay(pathNode14);
		pathNode14.addWay(pathNodeEnd);
		
		return pathNodeStart;
	}
	
	public static PathNode toundra() {
		PathNode pathNodeStart = new PathNode(-25, 321);
		PathNode pathNode1 = new PathNode(56, 353);
		PathNode pathNode2 = new PathNode(67, 363);
		PathNode pathNode3 = new PathNode(120, 404);
		PathNode pathNode4 = new PathNode(181, 412);
		PathNode pathNode5 = new PathNode(298, 277);
		PathNode pathNode6 = new PathNode(378, 300);
		PathNode pathNode7 = new PathNode(382, 362);
		PathNode pathNode8 = new PathNode(439, 443);
		PathNode pathNode9 = new PathNode(492, 466);
		PathNode pathNode10 = new PathNode(592, 471);
		PathNode pathNode11 = new PathNode(689, 423);
		PathNode pathNode12 = new PathNode(723, 363);
		PathNode pathNode13 = new PathNode(801, 375);
		PathNode pathNode14 = new PathNode(854, 345);
		PathNode pathNode15 = new PathNode(867, 275);
		PathNode pathNodeEnd = new PathNode(925, 265, true);
		
		pathNodeStart.addWay(pathNode1);
		pathNode1.addWay(pathNode2);
		pathNode2.addWay(pathNode3);
		pathNode3.addWay(pathNode4);
		pathNode4.addWay(pathNode5);
		pathNode5.addWay(pathNode6);
		pathNode6.addWay(pathNode7);
		pathNode7.addWay(pathNode8);
		pathNode8.addWay(pathNode9);
		pathNode9.addWay(pathNode10);
		pathNode10.addWay(pathNode11);
		pathNode11.addWay(pathNode12);
		pathNode12.addWay(pathNode13);
		pathNode13.addWay(pathNode14);
		pathNode14.addWay(pathNode15);
		pathNode15.addWay(pathNodeEnd);
		
		return pathNodeStart;
	}
	
	public static PathNode volcano() {
		PathNode pathNodeStart = new PathNode(-25, 159);
		PathNode pathNode1 = new PathNode(73, 147);
		PathNode pathNode2 = new PathNode(140, 159);
		PathNode pathNode3 = new PathNode(188, 203);
		PathNode pathNode4 = new PathNode(241, 215);
		PathNode pathNode5 = new PathNode(320, 203);
		PathNode pathNode6 = new PathNode(378, 177);
		PathNode pathNode7 = new PathNode(439, 163);
		PathNode pathNode8 = new PathNode(508, 159);
		PathNode pathNode9 = new PathNode(552, 179);
		PathNode pathNode10 = new PathNode(602, 218);
		PathNode pathNode11 = new PathNode(655, 262);
		PathNode pathNode12 = new PathNode(720, 295);
		PathNode pathNode13 = new PathNode(776, 298);
		PathNode pathNode14 = new PathNode(836, 284);
		PathNode pathNode15 = new PathNode(925, 274,true);
		
		pathNodeStart.addWay(pathNode1);
		pathNode1.addWay(pathNode2);
		pathNode2.addWay(pathNode3);
		pathNode3.addWay(pathNode4);
		pathNode4.addWay(pathNode5);
		pathNode5.addWay(pathNode6);
		pathNode6.addWay(pathNode7);
		pathNode7.addWay(pathNode8);
		pathNode8.addWay(pathNode9);
		pathNode9.addWay(pathNode10);
		pathNode10.addWay(pathNode11);
		pathNode11.addWay(pathNode12);
		pathNode12.addWay(pathNode13);
		pathNode13.addWay(pathNode14);
		pathNode14.addWay(pathNode15);
		
		return pathNodeStart;
	}
}
