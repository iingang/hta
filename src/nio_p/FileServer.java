package nio_p;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			ServerSocketChannel server = ServerSocketChannel.open();
			server.configureBlocking(true);
			server.bind(new InetSocketAddress(7777));
			
			System.out.println("서버 대기");
			
			Path path = Paths.get("ppp/img.jpg");
			double size;
			//System.out.println(Files.size(path));
			size = Files.size(path)/1024.0;
			//System.out.println(Math.round(size));
			
			SocketChannel client = server.accept(); //2. 연결수락

			IntBuffer ib = IntBuffer.allocate(1024);
			
			ib.put((int)Math.ceil(size));
		
			ByteBuffer buf = ByteBuffer.allocate(1024);
			
			//buf.putInt(ib.get(0)); 
			//buf.putInt((int)Math.round(size));
			
			//System.out.println(buf.get(0));
			FileChannel fc = FileChannel.open(path, 
					StandardOpenOption.READ);
			buf.putInt((int)Math.ceil(size));
			//fc.read(buf);
		
			buf.flip();
			client.write(buf);
			buf.clear();
			
			for(int i=0; i<Math.round(size); i++) {
				//buf.putInt((int)Math.round(size));
				int cnt = fc.read(buf);
				buf.flip();
				client.write(buf);
				buf.clear();
				System.out.println("server:"+cnt);
				/*int cnt = fc.read(buf);
				buf.flip();
				client.write(buf);
				buf.clear();
				System.out.println("server:"+cnt);*/
			}
			
			
			/*fc.read(buf);
			buf.flip();
			client.write(buf);*/
			
			fc.close();
			client.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
