package nio_p;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardProtocolFamily;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

public class UDPSender {
//받는애없어도 잘만된다~
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			DatagramChannel channel = 
					DatagramChannel.open(StandardProtocolFamily.INET);
										//IPV4 사용-StandardProtocolFamily.INET
			
			System.out.println("data 전송시작");
			
			Charset charset = Charset.defaultCharset();
			ByteBuffer buf = charset.encode("저예요");
			
			//String ip = "192.168.0.31"; //UniCast
			//String ip = "192.168.0.8";
			//ip = "192.168.0.255"; //BroadCast
			int cnt = channel.send(buf,new InetSocketAddress("192.168.0.8",7777));
			
			System.out.println("data 전송완료:"+cnt);
			
			channel.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
