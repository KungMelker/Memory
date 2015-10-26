package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {


		GridPane root = new GridPane();
		

		Image cardImage[][] = new Image[10][10];
		for(int i=0;i<10;i++)
			for(int j=0;j<10;j++)
				cardImage[i][j]= new Image("http://mobile-visuals.com/icon.png",30,30, true, true);
		
		ImageView imageView[][] = new ImageView[10][10];
		for(int i=0;i<10;i++)
			for(int j=0;j<10;j++)
				imageView[i][j] = new ImageView(cardImage[i][j]);

		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);

		root.setAlignment(Pos.CENTER);
		for(int i= 0;i<10;i++)
			for(int j = 0;j<10;j++)
				root.addRow(i, imageView[i][j]);

		primaryStage.show();
		primaryStage.setTitle("Memory v0.1");

	}

	public static void main(String[] args) {
		launch(args);
	}
}
