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
	public void initialize(URL location, ResourceBundle resources) { //fxml���� intialize�� �켱������ �� ����
		// TODO Auto-generated method stub
		
		//CheckBox ---------------------------------------------
		checkBox.setSelected(false); //üũ�ȵǰ�
		checkBox.setOnAction(ee->{
			CheckBox box = (CheckBox)ee.getSource();
			
			System.out.println("üũ�ڽ�:"+checkBox.isSelected()+","+box.getText());
		});

		//RadioButton ---------------------------------------------
		EventHandler handler = ee->{
			RadioButton rb = (RadioButton)ee.getSource();
			System.out.println("����:"+rb.getText());
		};
		
		
		genM.setOnAction(handler);
		genF.setOnAction(handler);		
		
		/*genM.setOnAction(ee->{
			System.out.println("����");
		});
		genF.setOnAction(ee->{
			System.out.println("����");
		});*/
		
		//ComboBox ---------------------------------------------
		ObservableList<String> emailData = FXCollections.observableArrayList("����","���̹�","����");
		email.setItems(emailData);
		email.setValue(emailData.get(1));
		
		/*email.setOnAction(ee->{
			ComboBox box = (ComboBox)ee.getSource();
			System.out.println("email �̺�Ʈ:"+box.getValue()); //�׳� email.getValue()�� �����͵� ��!
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
