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

	public static PathNode toundraTop() {
		PathNode pathNode1 = new PathNode(new Vector(464, -50));
		PathNode pathNode2 = new PathNode(new Vector(417, 72));
		PathNode pathNode3 = new PathNode(new Vector(323, 171));
		PathNode pathNode4 = new PathNode(new Vector(278, 214));
		PathNode pathNode5 = new PathNode(new Vector(288, 255));

		pathNode1.addWay(pathNode2);
		pathNode2.addWay(pathNode3);
		pathNode3.addWay(pathNode4);
		pathNode4.addWay(pathNode5);
		pathNode5.addWay(toundraMiddle());
		
		return pathNode1;
	}
	public static PathNode toundraLeft() {
		PathNode pathNode1 = new PathNode(new Vector(-50, 296));
		PathNode pathNode2 = new PathNode(new Vector(42, 341));
		PathNode pathNode3 = new PathNode(new Vector(120, 406));
		PathNode pathNode4 = new PathNode(new Vector(212, 401));
		PathNode pathNode5 = new PathNode(new Vector(256, 297));

		pathNode1.addWay(pathNode2);
		pathNode2.addWay(pathNode3);
		pathNode3.addWay(pathNode4);
		pathNode4.addWay(pathNode5);
		pathNode5.addWay(toundraMiddle());
		
		return pathNode1;
	}
	private static PathNode toundraMiddle() {
		PathNode pathNode1 = new PathNode(new Vector(291, 273));
		PathNode pathNode2 = new PathNode(new Vector(358, 292));

		pathNode1.addWay(pathNode2);
		pathNode2.addWay(toundraTopMiddle());
		pathNode2.addWay(toundraBottomMiddle());
		
		return pathNode1;
	}
	private static PathNode toundraTopMiddle() {
		PathNode pathNode1 = new PathNode(new Vector(393, 286));
		PathNode pathNode2 = new PathNode(new Vector(471, 223));
		PathNode pathNode3 = new PathNode(new Vector(567, 207));
		PathNode pathNode4 = new PathNode(new Vector(678, 248));
		PathNode pathNode5 = new PathNode(new Vector(712, 296));

		pathNode1.addWay(pathNode2);
		pathNode2.addWay(pathNode3);
		pathNode3.addWay(pathNode4);
		pathNode4.addWay(pathNode5);
		pathNode5.addWay(toundraEnd());
		
		return pathNode1;
	}
	private static PathNode toundraBottomMiddle() {
		PathNode pathNode1 = new PathNode(new Vector(379, 311));
		PathNode pathNode2 = new PathNode(new Vector(405, 405));
		PathNode pathNode3 = new PathNode(new Vector(477, 463));
		PathNode pathNode4 = new PathNode(new Vector(589, 479));
		PathNode pathNode5 = new PathNode(new Vector(685, 433));

		pathNode1.addWay(pathNode2);
		pathNode2.addWay(pathNode3);
		pathNode3.addWay(pathNode4);
		pathNode4.addWay(pathNode5);
		pathNode5.addWay(toundraEnd());
		
		return pathNode1;
	}
	private static PathNode toundraEnd() {
		PathNode pathNode1 = new PathNode(new Vector(735, 369));
		PathNode pathNode2 = new PathNode(new Vector(820, 374));
		PathNode pathNode3 = new PathNode(new Vector(855, 346));
		PathNode pathNode4 = new PathNode(new Vector(862, 290));
		PathNode pathNode5 = new PathNode(new Vector(896, 265));
		PathNode pathNode6 = new PathNode(new Vector(950, 265), true);

		pathNode1.addWay(pathNode2);
		pathNode2.addWay(pathNode3);
		pathNode3.addWay(pathNode4);
		pathNode4.addWay(pathNode5);
		pathNode5.addWay(pathNode6);
		
		return pathNode1;
	}
	
	public static PathNode volcanoTop() {
		PathNode pathNode1 = new PathNode(450, -50);
		PathNode pathNode2 = new PathNode(465, 69);
		
		pathNode1.addWay(pathNode2);
		pathNode2.addWay(volcanoTopEnd());
		
		return pathNode1;
	}
	public static PathNode volcanoLeft() {
		PathNode pathNode1 = new PathNode(-50, 175);
		PathNode pathNode2 = new PathNode(190, 147);
		PathNode pathNode3 = new PathNode(168, 177);
		
		pathNode1.addWay(pathNode2);
		pathNode2.addWay(pathNode3);
		pathNode3.addWay(volcanoTopBegin());
		pathNode3.addWay(volcanoBottomBegin());
		
		return pathNode1;
	}
	public static PathNode volcanoBottom() {
		PathNode pathNode1 = new PathNode(445, 724);
		PathNode pathNode2 = new PathNode(473, 610);
		
		pathNode1.addWay(pathNode2);
		pathNode2.addWay(volcanoBottomEnd());
		
		return pathNode1;
	}
	private static PathNode volcanoTopBegin() {
		PathNode pathNode1 = new PathNode(204, 214);
		PathNode pathNode2 = new PathNode(280, 217);
		PathNode pathNode3 = new PathNode(450, 157);
		
		pathNode1.addWay(pathNode2);
		pathNode2.addWay(pathNode3);
		pathNode3.addWay(volcanoTopEnd());
		
		return pathNode1;
	}
	private static PathNode volcanoTopEnd() {
		PathNode pathNode1 = new PathNode(517, 164);
		
		pathNode1.addWay(volcanoEnd());
		
		return pathNode1;
	}
	private static PathNode volcanoBottomBegin() {
		PathNode pathNode1 = new PathNode(219, 352);
		PathNode pathNode2 = new PathNode(291, 467);
		PathNode pathNode3 = new PathNode(387, 511);
		
		pathNode1.addWay(pathNode2);
		pathNode2.addWay(pathNode3);
		pathNode3.addWay(volcanoBottomEnd());
		
		return pathNode1;
	}
	private static PathNode volcanoBottomEnd() {
		PathNode pathNode1 = new PathNode(530, 511);
		PathNode pathNode2 = new PathNode(670, 464);
		
		pathNode1.addWay(pathNode2);
		pathNode2.addWay(volcanoEnd());
		
		return pathNode1;
	}
	private static PathNode volcanoEnd() {
		PathNode pathNode1 = new PathNode(742, 309);
		PathNode pathNode2 = new PathNode(878, 280);
		PathNode pathNode3 = new PathNode(950, 280, true);
		
		pathNode1.addWay(pathNode2);
		pathNode2.addWay(pathNode3);
		
		return pathNode1;
	}

	public static PathNode volcanoAnnoyingPath() {
		PathNode pathNode1 = new PathNode(450, -50);
		PathNode pathNode2 = new PathNode(466, 76);
		PathNode pathNode3 = new PathNode(534, 163);
		PathNode pathNode4 = new PathNode(430, 159);
		PathNode pathNode5 = new PathNode(273, 216);
		PathNode pathNode6 = new PathNode(210, 211);
		PathNode pathNode7 = new PathNode(227, 379);
		PathNode pathNode8 = new PathNode(321, 482);
		PathNode pathNode9 = new PathNode(409, 514);
		PathNode pathNode10 = new PathNode(511, 515);
		PathNode pathNode11 = new PathNode(614, 461);
		PathNode pathNode12 = new PathNode(710, 346);
		PathNode pathNode13 = new PathNode(773, 296);
		PathNode pathNode14 = new PathNode(950, 281, true);
		
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
		
		return pathNode1;
	}
}
