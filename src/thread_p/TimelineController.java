package thread_p;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.shape.Arc;
import javafx.util.Duration;

public class TimelineController implements Initializable {

	@FXML Arc arc;
	@FXML Button btn1,btn2;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		btn1.setOnAction(ee->{
			Timeline t1 = new Timeline();
			
			KeyFrame x_0  = new KeyFrame(
					new Duration(0),
					new KeyValue(arc.layoutXProperty(),50)
					);
			
			KeyFrame y_0  = new KeyFrame(
					new Duration(0),
					new KeyValue(arc.layoutYProperty(),50)
					);
			
			KeyFrame x_1  = new KeyFrame(
					new Duration(1000),
					new KeyValue(arc.layoutXProperty(),100)
					);
			KeyFrame y_1  = new KeyFrame(
					new Duration(1000),
					new KeyValue(arc.layoutYProperty(),50)
					);
			
		/*	KeyFrame x_2  = new KeyFrame(
					new Duration(2000),
					new KeyValue(arc.layoutXProperty(),200)
					);*/
			
			KeyFrame y_2 = new KeyFrame(
					new Duration(2000), //////Duration 시간이 매우 중요함
					new KeyValue(arc.layoutYProperty(), 200)  //상태값으로 처리
					);
			
			////add 순서와 keyframe 순서는 상관없음
			t1.getKeyFrames().add(x_0);
			t1.getKeyFrames().add(x_1);
			t1.getKeyFrames().add(y_0);
			t1.getKeyFrames().add(y_1);
			t1.getKeyFrames().add(y_2);
			
			//t1.setCycleCount(3);
			t1.setCycleCount(-1); //무한반복
			t1.setAutoReverse(true);
			
			t1.play();
			
			
			
		});
	}

}
