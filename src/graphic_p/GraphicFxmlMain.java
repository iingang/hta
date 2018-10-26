package graphic_p;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GraphicFxmlMain extends Application implements Initializable{

	@FXML Rectangle rec;
	@FXML Button btnRec, btnMouth;
	@FXML Arc pack;
	int chk=1,chk2=1;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = 
				FXMLLoader.load(getClass().getResource("graphicFxml.fxml"));
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources){
		// TODO Auto-generated method stub
		btnRec.setOnAction(ee->{
			rec.setRotate(rec.getRotate()*-1);
		});
		
		btnMouth.setOnAction(ee->{
			if(pack.getLength()==360) 
				chk*=-1;

			if(pack.getLength()==270&&chk==-1)
				chk=1;
			
			pack.setLength(pack.getLength()+10*chk);
			pack.setRotate(pack.getRotate()+5*chk);
			
			pack.setLayoutX(pack.getLayoutX()+15*chk2);
			
			if(pack.getLayoutX()>600) {
				chk2*=-1;
				pack.setRotate(pack.getRotate()+180);	
			}
			
			if(pack.getLayoutX()<200&&chk2==-1) {
				chk2=1;
				pack.setRotate(pack.getRotate()+180);
			}
			
		});
		
		
	}

}

/*package graphic_p;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GraphicFxmlMain extends Application implements Initializable{

	@FXML Rectangle rec;
	@FXML Button btnRec;
	@FXML Arc arc;
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = 
				FXMLLoader.load(getClass().getResource("graphicFxml.fxml"));
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	int maxAngle=45;
	
	int oneAngle = 3;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		btnRec.setOnAction(ee->{
			System.out.println("Å¬¸¯");
			rec.setRotate(rec.getRotate()*-1);
			
			
			
			double startAngle = arc.getStartAngle();
			
			startAngle+=oneAngle;
			
			if(startAngle>=maxAngle || startAngle<0) {
				oneAngle*=-1;
				
				if(startAngle>=maxAngle)
					startAngle=maxAngle;
				else if(startAngle<0)
					startAngle=0;
			}
			
			arc.setStartAngle(startAngle);
			
			arc.setLength(359-(startAngle*2));
			
			
		});
		
	}

}

*/

