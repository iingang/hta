package nio_p;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;
import java.nio.charset.Charset;

public class MulticastReceiver2 {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			DatagramChannel channel = DatagramChannel.open(StandardProtocolFamily.INET);
			
			NetworkInterface inter = NetworkInterface.getByName("eth1");
			
			channel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
			channel.setOption(StandardSocketOptions.IP_MULTICAST_IF, inter);
			
			channel.bind(new InetSocketAddress(7777));
			InetAddress group = InetAddress.getByName("239.1.1.1");
			MembershipKey key = channel.join(group, inter);
			
			System.out.println("수신대기...");
			
			ByteBuffer buf = ByteBuffer.allocate(1024);
			channel.receive(buf);
			buf.flip();
			
			Charset charset = Charset.defaultCharset();
			String msg = charset.decode(buf).toString();
			
			System.out.println("수신메시지:"+msg);
			
			
			key.drop();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
