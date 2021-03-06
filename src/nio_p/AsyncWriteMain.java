package nio_p;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class AttachGoGo{
	Path path;
	AsynchronousFileChannel channel;
	
	public AttachGoGo(Path path, AsynchronousFileChannel channel) {
		super();
		this.path = path;
		this.channel = channel;
	}
	
	
}

public class AsyncWriteMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
		ExecutorService executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors());
		
		System.out.println(executorService);
		
		for (int i = 0; i <10; i++) {
				
			
			
			Path  pp = Paths.get("nnn/aaa_"+i+".txt");
			
			Charset chSet = Charset.defaultCharset();
			
			ByteBuffer buf = chSet.encode("시간탐험대 지니");
			
			AsynchronousFileChannel channel = 
					AsynchronousFileChannel.open(pp,
							
							EnumSet.of(StandardOpenOption.CREATE, //없으면 만들자
									   StandardOpenOption.WRITE),
												executorService
					);
			
			
			//completionHandler로 작동시키기위해 Path와 channel을 멤버로 묶어 처리할 클래스로 생성
			AttachGoGo gogo = new AttachGoGo(pp, channel);
			
			
			////쓰레드로 작동  ---> 특정작업을 마치고 나면 자동으로 completed 가 실행됨
			//channel.write(buf, 0);
			CompletionHandler<Integer, AttachGoGo> completionHandler = 
					
					new CompletionHandler<Integer, AttachGoGo>() {

						@Override  //정상 종료시 진입
						public void completed(Integer result, AttachGoGo attachment) {
							// TODO Auto-generated method stub
							//파일 작성후 log 남김
							/// Thread.currentThread().getName() 여러개의 쓰레드가 나누어서 비동기 식으로 처리함
							
							System.out.println(Thread.currentThread().getName()+"->"+attachment.path.getFileName()+":"+result);
							
							try {
								attachment.channel.close();  //다 썼으면 닫기
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						@Override  //메소드 처리 실패시 진입
						public void failed(Throwable exc, AttachGoGo attachment) {
							// TODO Auto-generated method stub
							
						}
					};
			
			///channel 에 작성시 묶고 :gogo, 진행후 결과처리:completionHandler
			channel.write(buf, 0, gogo, completionHandler);
			
			
			///channel.close(); 정상작동되었을때 completionHandler의  completed()로 닫기함
					
		}
	}

}
