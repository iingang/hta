package net_p;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
//cmd -> netstat -an
public class ServerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			ServerSocket server = new ServerSocket(7777); //7777�� ��Ʈ��ȣ
			
			while(true) {
				System.out.println("������");
				Socket client = server.accept(); //Ŭ���̾�Ʈ�� ���;� ��� ����� �� ����
				
				OutputStream os = client.getOutputStream();
				
				DataOutputStream dos = new DataOutputStream(os);
			
				dos.writeUTF("�ű���");
				
				//��������� �������� �ݴ´�
				dos.close();
				os.close();
				client.close();
				
				System.out.println("���ۿϷ�");
			
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
