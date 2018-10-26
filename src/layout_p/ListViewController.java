package layout_p;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class ListViewController implements Initializable {

	@FXML Label label;
	@FXML Button btn;
	@FXML ListView<String> list;
	@FXML ObservableList<String> items;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		//�̰� �����Ұ��! -> �ؿ� �� �Ⱦ��� ���� ������ �߰���!
		//list.setItems(FXCollections.observableArrayList());
		
		list.getItems().add("�ø�");
		list.getItems().add("Ī����");
		list.getItems().add("���ĵκ�");
		
		//Single ó��
	/*	list.setOnMouseClicked(mm->{
			Object obj = list.getSelectionModel().getSelectedItem();
			label.setText(obj.toString());
		});*/
		
		/*list.getSelectionModel().selectedItemProperty().addListener((obb,oldV,newV)->{
			label.setText(oldV+"->"+newV);
		});*/
		
		//Multiple ó��
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //shift������ ���߼��õ�
		list.setOnMouseClicked(mm->{
			items = list.getSelectionModel().getSelectedItems();
			
			for(String str : items) {
				System.out.println(str);
				//list.getItems().remove(str); //���û���
			}
		});
		
		btn.setOnAction(ee->{
			if(items!=null) { //���õȰ� ������ �������ϰ�(�����ǳ��̹Ƿ�)
				for(String str : items) {
					list.getItems().remove(str); //���û���
				}
			}
		});
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
