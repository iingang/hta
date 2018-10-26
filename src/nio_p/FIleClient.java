package nio_p;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.print.StreamPrintService;

public class FIleClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			SocketChannel channel = SocketChannel.open();
			channel.configureBlocking(true);
			
			channel.connect(new InetSocketAddress("192.168.0.31", 7777));
					//1. 연결요청
			
			
			ByteBuffer buf = ByteBuffer.allocate(1024);
			//Charset charset = Charset.defaultCharset(); 
			int size;
			channel.read(buf);
			buf.flip();
			//buf.getInt();
			size = buf.getInt();
			//System.out.println(charset.decode(buf).toString());
			buf.clear();
			
			Path path = Paths.get("nnn/img2.jpg");
			
			FileChannel fc = FileChannel.open(path, 
					StandardOpenOption.CREATE,
					StandardOpenOption.WRITE);
			
			for(int i=0; i<size; i++) {
				int cnt = channel.read(buf);
				buf.flip();
				fc.write(buf);
				buf.clear();
				
				System.out.println("client:"+cnt);
			}
			fc.close();
		/*	channel.read(buf);
			buf.flip();
			fc.write(buf);*/
			
			channel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
