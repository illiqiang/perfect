package cn.perfect.netty.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String args[]) throws Exception {
		// 为了简单起见，所有的异常都直接往外抛
		String host = "192.168.0.60"; // 要连接的服务端IP地址
		int port = 8081; // 要连接的服务端对应的监听端口
		// 与服务端建立连接
		Socket client = null;
		Writer writer = null;
		try {
			client = new Socket(host, port);
			// 建立连接后就可以往服务端写数据了
			writer = new OutputStreamWriter(client.getOutputStream());
			writer.write("Hello Server.");
			writer.write("\n");
			writer.flush();// 写完后要记得flush
			//Thread.sleep(1000);
			for(int i=0; i<100000000; i++) {
				writer.write(i+"ok! \n");
				writer.flush();
				Thread.sleep(3000);
			}
			//Thread.sleep(1000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(writer != null) {
				writer.close();
			}
			
			if(client != null) {
				client.close();
			}
			
			
		}
	
	}

	
}
