package application;

import javafx.scene.image.Image;

public class GameEngine {

	Image cardImage[][] = new Image[2][2];
	{
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				cardImage[i][j] = new Image("http://mobile-visuals.com/icon.png", 40, 40, true, true);
	}
}
