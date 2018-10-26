package nio_p;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class TCPSelectorServer {
	
	Selector selector;
	ArrayList<Client> list;
	
	public TCPSelectorServer() {
		// TODO Auto-generated constructor stub
		
		try {
			
			selector = Selector.open();
			list = new ArrayList<>();
			
			ServerSocketChannel server = ServerSocketChannel.open();
			server.configureBlocking(false);
			server.bind(new InetSocketAddress(7777));
			
			server.register(selector, SelectionKey.OP_ACCEPT);
			
			while(true) {
				int cnt = selector.select();

				System.out.println(cnt);
				if(cnt==0)
					continue;
				
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey>it = keys.iterator();
				
				while(it.hasNext()) {
					
					SelectionKey key=it.next();
					//System.out.println(key);
					
					if(key.isAcceptable()) {
						accept(key);
						
					}else if(key.isReadable()) {///클라이언트로부터 data를 읽을때
						//System.out.println("isReadable");
						Client client = (Client)key.attachment();
						client.recieve();
						
					}else if(key.isWritable()) {
						//System.out.println("isWritable");
						Client client = (Client)key.attachment();
						client.send(key);
					}
					it.remove();
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		
	}
	
	void accept(SelectionKey key) {  ///접속시 실행
		
		try {
			ServerSocketChannel server = (ServerSocketChannel)key.channel();
			SocketChannel socket = server.accept();  //실제 서버로 접근한 클라이언트 가져옴
			Client client = new Client(socket);
			
			list.add(client);
			String msg = socket.getRemoteAddress()+"연결:"+list.size();
			System.out.println(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	class Client{
		SocketChannel socket;
		Charset charset = Charset.defaultCharset();

		String msg; ///send 시 사용할 변수
		public Client(SocketChannel socket) {
			super();
			this.socket = socket;
			try {
				socket.configureBlocking(false);
				SelectionKey key = socket.register(selector, SelectionKey.OP_READ);
								///평상시 socket은 READ 상태로 대기
				
				key.attach(this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		void recieve() {
			
			
			
			try {
				ByteBuffer buf = ByteBuffer.allocate(1024);
				int cnt = socket.read(buf);
				
				buf.flip();
				
				String data = charset.decode(buf).toString();
				System.out.println("isReadable:"+data);
				
				///글 내용을 읽어와 모든 socket에게 글쓰기 실행
				sendToAll(data);
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				////socket 이 소멸 돼었을 경우 삭제 및 종료
				list.remove(this);
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		void send(SelectionKey key) {
			System.out.println("send:"+msg);
			ByteBuffer buf = charset.encode(msg);
			try {
				socket.write(buf);
				
				key.interestOps(SelectionKey.OP_READ);
				//interestOps()
				//SelectionKey의 작업 유형을 OP_READ로 변경
				///글을 보내고 난후 내용을 받기 위한 모드로 변경
				
				selector.wakeup();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	void sendToAll(String msg) {//전체에게 보내기 위한 메소드
		for (Client client : list) {
			client.msg = msg;
			SelectionKey key = client.socket.keyFor(selector);
			//keyFor(selector);
			///selector로 부터 socket에 해당하는 SelectionKey를 리턴받는다.
			
			key.interestOps(SelectionKey.OP_WRITE);
			//interestOps()
			//SelectionKey의 작업 유형을 OP_WRITE로 변경
			///평상시 OP_READ 였다가 글내용을 단 하나의 socket이라도 읽어오면
			///모든 socket에 글을 써야 하므로 OP_WRITE로 변경하여 각 client에 글쓰게 함 
		}
		selector.wakeup();
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new TCPSelectorServer();
	}

}
