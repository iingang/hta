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
			ServerSocket server = new ServerSocket(7777); //7777은 포트번호
			
			while(true) {
				System.out.println("연결대기");
				Socket client = server.accept(); //클라이언트가 들어와야 계속 진행될 수 있음
				
				OutputStream os = client.getOutputStream();
				
				DataOutputStream dos = new DataOutputStream(os);
			
				dos.writeUTF("신기방기");
				
				//열어놓은것 역순으로 닫는다
				dos.close();
				os.close();
				client.close();
				
				System.out.println("전송완료");
			
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
