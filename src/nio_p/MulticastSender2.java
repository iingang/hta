package nio_p;

import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

public class MulticastSender2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		DatagramChannel channel = DatagramChannel.open();
		channel.bind(null);
		NetworkInterface inter = NetworkInterface.getByName("eth1");
		
		channel.setOption(StandardSocketOptions.IP_MULTICAST_IF, inter);
		
		Charset charset = Charset.defaultCharset();
		ByteBuffer buf = charset.encode("우리그룹이에요");
		
		InetSocketAddress group = new InetSocketAddress("239.1.1.1", 7777);
		
		channel.send(buf, group);
		
		System.out.println("송신성공");
		
	}

}
