package thread_p;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;

public class ThreadAniController implements Initializable {

	@FXML AnchorPane totalP;
	@FXML Pane pp,user;
	@FXML Arc pArc;
	int chk=1,chk2=1;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//new Timer().start();
		Timer timer = new Timer();
		timer.setDaemon(true);
		timer.start();
		
		//이벤트 등록 -> 키 입력 이벤트
		
		totalP.addEventHandler(KeyEvent.KEY_PRESSED, 
				new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent key) {
						// TODO Auto-generated method stub
						//System.out.println(key.getCode());
						if(key.getCode()==KeyCode.UP) {
							double yy = user.getLayoutY()-10;
							if(yy<0)
								yy=0;
							user.setLayoutY(yy);
						}
						if(key.getCode()==KeyCode.DOWN) {
							double yy = user.getLayoutY()+10;
							if(yy>totalP.getPrefHeight()-user.getPrefHeight())
								yy=totalP.getPrefHeight()-user.getPrefHeight();
							user.setLayoutY(yy);
						}
						
						if(key.getCode()==KeyCode.LEFT) {
							double xx = user.getLayoutX()-10;
							if(xx<0)
								xx=0;
							user.setLayoutX(xx);
						}
						
						if(key.getCode()==KeyCode.RIGHT) {
							double xx = user.getLayoutX()+10;
							if(xx>totalP.getPrefWidth()-user.getPrefWidth())
								xx=totalP.getPrefWidth()-user.getPrefWidth();
							user.setLayoutX(xx);
						}
					}
				
			});
		/*totalP.setOnKeyPressed(
				new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent key) {
						// TODO Auto-generated method stub
						System.out.println(key.getCode());
					}
				
			});*/
		
		//pArc.setFocusTraversable(true);
		/*pArc.setOnKeyPressed(
				new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent key) {
						// TODO Auto-generated method stub
						System.out.println(key.getCode());
					}
				
			});
		
		pArc.setFocusTraversable(true);*/
	}
	
	class Timer extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				try {
					Platform.runLater(()->init());
					sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	int disX=5;
	int disY=5;
	void init() {
		double xx = pp.getLayoutX()+disX;
		double yy = pp.getLayoutY()+disY;

		if(pp.getLayoutX()>user.getLayoutX()&&pp.getLayoutX()<user.getLayoutX()+user.getPrefWidth()&&
				pp.getLayoutY()>user.getLayoutY()&&pp.getLayoutY()<user.getLayoutY()+user.getPrefHeight()) 
		{
			
		}
		
		/*if(pp.getLayoutX()>200) {
			
		}*/
		else {
			if(totalP.getPrefWidth()<xx+pp.getPrefWidth()||xx<0) {
				disX*=-1;
				chk2*=-1;
				pArc.setRotate(pArc.getRotate()-45);	
			}
			if(totalP.getPrefHeight()<yy+pp.getPrefHeight()||yy<0) {
				disY*=-1;
				chk2=1;
				pArc.setRotate(pArc.getRotate()-45);
			}
			if(pArc.getLength()==360) 
				chk*=-1;

			if(pArc.getLength()==270&&chk==-1)
				chk=1;
			
			pArc.setLength(pArc.getLength()+10*chk);
			pArc.setRotate(pArc.getRotate()+5*chk);
			
			pp.setLayoutX(xx);
			pp.setLayoutY(yy);
		}
		
		
	}
}
