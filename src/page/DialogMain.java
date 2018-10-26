package page;



import java.io.File;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DialogMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Alert info = new Alert(AlertType.INFORMATION);
		info.setTitle("알림창");
		info.setHeaderText("알림창 제목");
		info.setContentText("알림창 내용");
		info.show();
		
		
		Alert error = new Alert(AlertType.ERROR);
		error.setTitle("에러창");
		error.setHeaderText("에러창 제목");
		error.setContentText("에러창 내용");
		error.show();
		
		Alert waring = new Alert(AlertType.WARNING);
		waring.setTitle("경고창");
		waring.setHeaderText("경고창 제목");
		waring.setContentText("경고창 내용");
		waring.show();
		
		
		Alert custom = new Alert(AlertType.WARNING);
		custom.setTitle("custom창");
		custom.setHeaderText("custom 제목");
		custom.setContentText("custom 내용");
		
		Label label = new Label("구체적 경고 내용");
		TextArea textArea = new TextArea("경고 경고 내용내용 블라블라");
		textArea.setEditable(false);
		textArea.setWrapText(true);
		
		textArea.setMaxHeight(Double.MAX_VALUE);
		textArea.setMaxWidth(Double.MAX_VALUE);
		
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);
		
		GridPane gridPane = new GridPane();
		gridPane.setMaxWidth(Double.MAX_VALUE);
		gridPane.add(label, 0,0);
		gridPane.add(textArea, 0,1);
		
		custom.getDialogPane().setExpandableContent(gridPane);
		
		custom.show();
		
		
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle("선택창");
		confirm.setHeaderText("선택창 제목");
		confirm.setContentText("선택창 내용");
		
		
		Optional<ButtonType>res = confirm.showAndWait();
		//System.out.println(res.get());
		if(res.get()==ButtonType.OK) {
			System.out.println("확인 클릭함");
			
		}else {
			System.out.println("취소 클릭함");
			
			
		}
		
		//confirm.show();  show() 하지 말 것 , 두개 열림
		
		
		
		TextInputDialog textD = new TextInputDialog();
		textD.setTitle("입력창");
		textD.setHeaderText("입력창 제목");
		textD.setContentText("입력:");
		Optional<String>res2 = textD.showAndWait();
		res2.ifPresent(str->System.out.println("입력창 내용:"+str));
		
		
		
		
		////파일 선택
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("img", "*.jpg","*.png","*.gif","*.bmp")
		);
		
								////현재 프로젝트가 위치한 드라이브가 root임 ex) E:\
		fc.setInitialDirectory(new File("\\lee\\public\\study\\teacherWork\\guiProj\\ppp"));
		File res3=fc.showOpenDialog(primaryStage);
		
		if(res3!=null) {
			System.out.println(res3.getName());
		}
		
		
		
		////폴더 선택
		DirectoryChooser dc = new DirectoryChooser();
		
		////현재 프로젝트가 위치한 드라이브가 root임 ex) E:\
		dc.setInitialDirectory(new File("\\lee\\public\\study\\teacherWork\\guiProj\\ppp"));
		File res4=dc.showDialog(primaryStage);
		
		if(res4!=null) {
		System.out.println(res4.getPath());
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
