package thread_p;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.shape.Arc;
import javafx.util.Duration;

public class TranslateController implements Initializable {
	
	@FXML Arc arc;
	@FXML Button start,pause,stop;
	
	TranslateTransition transition;
	int chk = 1;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		transition = new TranslateTransition();
		
		transition.setToX(400);
		transition.setToY(400);
		transition.setDuration(new Duration(2000)); //����ð�
		transition.setCycleCount(100); //�ݺ�Ƚ��
		transition.setAutoReverse(true);
		transition.setNode(arc); //������ node
		transition.play();
		
		

		pause.setOnAction(ee->{
			if(chk==1) {
				transition.pause();
				System.out.println(transition.getStatus());
				pause.setText("����");
			}
			else {
				transition.play();
				System.out.println(transition.getStatus());
				pause.setText("�Ͻ�����");
			}
				
			chk*=-1;
			});
		
		start.setOnAction(ee->{
			transition.play();
			System.out.println(transition.getStatus());
		});
		
		stop.setOnAction(ee->{
			transition.stop();
			System.out.println(transition.getStatus());
			});
	}

}
