package fr.tm_nlm.tower_defence.view.mHUDCompat;

import static fr.tm_nlm.tower_defence.view.mHUDCompat.VectorToVector.*;

public class ShapeToShape {
	public static mHUD.geometric.Circle fromTDCircle(fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle from) {
		mHUD.geometric.Circle to = new mHUD.geometric.Circle(fromTDVector(from.getPosition()), from.getRadius());
		return to;
	}

	public static mHUD.geometric.Rect fromTDRect(fr.tm_nlm.tower_defence.control.data.geometric.shape.Rect from) {
		mHUD.geometric.Rect to = new mHUD.geometric.Rect(fromTDVector(from.getPosition()), fromTDVector(from.getSize()));
		return to;
	}
}
