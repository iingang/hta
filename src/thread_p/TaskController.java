package thread_p;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TaskController implements Initializable {
	
	@FXML Label label;
	@FXML Button restart, suspend;
	
	NumberGo numberGo;
	
	boolean chk = true, reChk = true;

	HashMap<Boolean, String>chkMap, restartMap;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		chkMap = new HashMap<>();
		chkMap.put(true, "||");
		chkMap.put(false, "��");
		
		restartMap = new HashMap<>();
		restartMap.put(true, "��");
		restartMap.put(false, "restart");
		
		numberGo = new NumberGo();
		numberGo.start();
		

		restart.setOnAction(ee->{
			
			if(!reChk) {
				
				numberGo = new NumberGo();
				numberGo.start();
			}

			reChk = !reChk;
			restart.setText(restartMap.get(reChk));
			
			
		});
		
		suspend.setOnAction(ee->{
			chk = !chk;
			suspend.setText(chkMap.get(chk));
		});
	}
	

	class NumberGo extends Thread{
		
		int i =0;
		
		@Override
		public void run() {
			// TODO Auto-generated method stub		
			
			while(reChk) {
				if(chk) {
					i++;
					Platform.runLater(()->label.setText(i+""));

					/*Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							label.setText(i+"");
						}
					});
					*/
				}
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}		
	}

}


/*package thread_p;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

//��ž��ġ ��
public class TaskController implements Initializable {
	
	@FXML Label label;
	@FXML Button restart, suspend;
	
	NumberGo numberGo;
	
	boolean chk = true, reChk = true;

	HashMap<Boolean, String>chkMap, restartMap;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		chkMap = new HashMap<>();
		chkMap.put(true, "||");
		chkMap.put(false, "��");
		
		restartMap = new HashMap<>();
		restartMap.put(true, "��");
		restartMap.put(false, "restart");
		
		numberGo = new NumberGo();
		numberGo.start();
		
		restart.setOnAction(ee->{
			
			///reChk ::: > true -> ������
			if(reChk) {
				numberGo.cancel();	// ���߱� , ���θ޼ҵ�
			}else {
				numberGo.restart();	// �ٽý���, ���θ޼ҵ�
			}
			
			reChk = !reChk;
			restart.setText(restartMap.get(reChk));
			
			
		});
		
		suspend.setOnAction(ee->{
			chk = !chk;
			suspend.setText(chkMap.get(chk));
		});
	}
	
	class NumberGo extends Service<Void>{

		@Override
		protected Task<Void> createTask() {
			// TODO Auto-generated method stub
			
			Task<Void> task = new Task<Void>() {

				@Override
				protected Void call() throws Exception {
					// TODO Auto-generated method stub
					
					int i =0;
					
					while(true) {
						if(chk) {
							i++;
							System.out.println("���´�");
							//label.setText(i+"");  ���� gui ��ü ���� �Ұ�!!!!
							
							updateMessage(i+"");  ///Task ���� �޼ҵ�� ������ ����
						}
						Thread.sleep(1000);
						
						if(!reChk)
							break;
						
						/////numberGo.cancel() ; �� �޼ҵ�� ��������
					}
					//return null;
				}
			};
			
			label.textProperty().bind(task.messageProperty());
			///label �� task�� �����ش�
			
			return task;
		}
		
	}

}*/

/*package thread_p;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TaskController implements Initializable {
	
	NumberGo numberGo;
	@FXML Label label;
	@FXML Button restart,suspend;
	String tmp="0";
	boolean chk=false,chk2=false, reChk = false;
	HashMap<Boolean, String>chkMap, restartMap;
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		chkMap = new HashMap<>();
		chkMap.put(true, "||");
		chkMap.put(false, "��");
		
		restartMap = new HashMap<>();
		restartMap.put(true, "��");
		restartMap.put(false, "�����");
		
		numberGo = new NumberGo();
		numberGo.start();
		restartGO();
		
		
		restart.setOnAction(ee->{
			if(reChk) {
				numberGo.cancel();	// ���߱� , ���θ޼ҵ�
			}else {
				numberGo.restart();	// �ٽý���, ���θ޼ҵ�
			}
			reChk = !reChk;
			restart.setText(restartMap.get(reChk));
		});
		restart.setOnAction(ee->{
			reChk = !reChk;
			restart.setText(restartMap.get(reChk));
			
			if(reChk) {
				numberGo = new NumberGo();
				numberGo.start();
			}
		});
		
		suspend.setOnAction(ee->{
			chk = !chk;
			suspend.setText(chkMap.get(chk));
		});
		

		resume.setOnAction(ee->{
			chk=true;
			if(chk) {
				chk=false;
				resume.setText("��");
			}
			else {
				chk=true;
				resume.setText("||");
			}	
			//numbergo.restart();
		});
		
		suspend.setOnAction(ee->{
			chk=!chk;
			if(chk2) {
				numbergo.cancel();
				suspend.setText("��");
				chk2=false;
			}
			else {
				numbergo.restart();
				suspend.setText("��");
				chk2=true;
			}
			//chk=false;
			//numbergo.cancel();
			//tmp = label.getText();
		});
	}
	
	
	void restartGO() {
		reChk = !reChk;
		restart.setText(restartMap.get(reChk));
		
		if(reChk) {
			numberGo = new NumberGo();
			numberGo.start();
		}
	}

	class NumberGo extends Service<Void>{
		@Override
		protected Task<Void> createTask() {
			// TODO Auto-generated method stub
			Task<Void> task = new Task<Void>() {
				protected Void call() throws Exception{
					//int i=Integer.parseInt(tmp)+1;
					int i=0;
					while(true) {
						//label.setText(i+""); //������ ���ο��� ���� gui ��ü ����Ұ�!! -> �޼ҵ� ���ؼ� �����ؾ���
						if(chk) {
							updateMessage(i+""); //Task ���� �޼ҵ�� ������ ����
							i++;
						}
						Thread.sleep(1000);

						if(!reChk)
							break;
						
						/////numberGo.cancel() ; �� �޼ҵ�� ��������
					}
					//return null;
				}
			};
			
			label.textProperty().bind(task.messageProperty());
			return task;
		}
		
	}
	

}
*/