package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		// A simple memory game
		//shgsjöoskfäplsdf
		//asdas
		
	
			GridPane root = new GridPane();

			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			//Adds title to main window
			primaryStage.setTitle("Memory v0.1");
			
			//random comment from EseBring3n
	}
		
	public static void main(String[] args) {
		launch(args);
	}
}
