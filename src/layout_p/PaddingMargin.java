package layout_p;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PaddingMargin extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		HBox hbox = new HBox();
		Button btn1 = new Button();
		Button btn2 = new Button();
		//Label lab = new Label();
		btn1.setPrefSize(100, 100);
		btn2.setPrefSize(100, 100);
		//lab.setText("안녕");
		hbox.setPadding(new Insets(50, 10, 100, 200)); //객체를 한번에 잡아서(전후좌우의 공간을 확보)
		hbox.getChildren().add(btn1);
		hbox.getChildren().add(btn2);
		//hbox.getChildren().add(lab);
		hbox.setMargin(btn1, new Insets(20, 20, 20, 20)); //객체 하나당 전후좌우의 공간 확보
		Scene scene = new Scene(hbox);
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
