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
		
		//이건 새로할경우! -> 밑에 줄 안쓰면 기존 정보에 추가됨!
		//list.setItems(FXCollections.observableArrayList());
		
		list.getItems().add("냉면");
		list.getItems().add("칭따오");
		list.getItems().add("마파두부");
		
		//Single 처리
	/*	list.setOnMouseClicked(mm->{
			Object obj = list.getSelectionModel().getSelectedItem();
			label.setText(obj.toString());
		});*/
		
		/*list.getSelectionModel().selectedItemProperty().addListener((obb,oldV,newV)->{
			label.setText(oldV+"->"+newV);
		});*/
		
		//Multiple 처리
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //shift누르면 다중선택됨
		list.setOnMouseClicked(mm->{
			items = list.getSelectionModel().getSelectedItems();
			
			for(String str : items) {
				System.out.println(str);
				//list.getItems().remove(str); //선택삭제
			}
		});
		
		btn.setOnAction(ee->{
			if(items!=null) { //선택된게 없을땐 삭제못하게(어차피널이므로)
				for(String str : items) {
					list.getItems().remove(str); //선택삭제
				}
			}
		});
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
