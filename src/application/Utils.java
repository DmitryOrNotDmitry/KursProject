package application;

import javafx.scene.paint.Color;

public class Utils {

	public static String getHexColorString(Color color) {
		return String.format("#%02X%02X%02X%02X", (int) (color.getRed() * 255), (int) (color.getGreen() * 255), (int) (color.getBlue() * 255), (int) (color.getOpacity() * 255));
	}
	
}
