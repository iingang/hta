package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControlMain extends Application implements Initializable{
	
	@FXML CheckBox checkBox;
	@FXML RadioButton genM;
	@FXML RadioButton genF;
	@FXML ComboBox<String> email;
	@FXML Slider zoom;
	
	javafx.collections.FXCollections fx;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) { //fxml보다 intialize의 우선순위가 더 높다
		// TODO Auto-generated method stub
		
		//CheckBox ---------------------------------------------
		checkBox.setSelected(false); //체크안되게
		checkBox.setOnAction(ee->{
			CheckBox box = (CheckBox)ee.getSource();
			
			System.out.println("체크박스:"+checkBox.isSelected()+","+box.getText());
		});

		//RadioButton ---------------------------------------------
		EventHandler handler = ee->{
			RadioButton rb = (RadioButton)ee.getSource();
			System.out.println("성별:"+rb.getText());
		};
		
		
		genM.setOnAction(handler);
		genF.setOnAction(handler);		
		
		/*genM.setOnAction(ee->{
			System.out.println("남자");
		});
		genF.setOnAction(ee->{
			System.out.println("여자");
		});*/
		
		//ComboBox ---------------------------------------------
		ObservableList<String> emailData = FXCollections.observableArrayList("구글","네이버","다음");
		email.setItems(emailData);
		email.setValue(emailData.get(1));
		
		/*email.setOnAction(ee->{
			ComboBox box = (ComboBox)ee.getSource();
			System.out.println("email 이벤트:"+box.getValue()); //그냥 email.getValue()로 가져와도 됨!
		});
		*/
		email.getSelectionModel().
		selectedItemProperty().addListener((observable,oldValue,newValue)->
		{
			System.out.println(oldValue+"->"+newValue);
		});
		
		/*email.getSelectionModel().
		selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				System.out.println(oldValue+"->"+newValue);
			}
			
		});*/
		
		//Slider ---------------------------------------------
		zoom.valueProperty().addListener((observable,oldValue,newValue)->
		{
			System.out.println(oldValue+"->"+newValue);
		});
		
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("control.fxml"));
		Scene scene = new Scene(parent);
		
	/*	VBox vBox = (VBox)parent;
		vBox.getChildren().add(new Button());*/
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}


}
