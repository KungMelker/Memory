package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
			
			Label label = new Label("MEMORY");
			root.setAlignment(Pos.CENTER);
			root.addRow(0, label);
			
			primaryStage.show();
			primaryStage.setTitle("Memory v0.1");
			
			//random comment from EseBring3n
	}
		
	public static void main(String[] args) {
		launch(args);
	}
}
