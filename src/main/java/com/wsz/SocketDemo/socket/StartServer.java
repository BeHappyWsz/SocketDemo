package com.wsz.SocketDemo.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 开启服务器端
 * @author wsz
 * @date 2018年3月3日
 */
public class StartServer {

	public static void main(String[] args) {
		start();
	}
	
	@SuppressWarnings("resource")
	public static void start() {
		try {
			ServerSocket serverSocket = new ServerSocket(8800);
			Socket socket = null;
			System.out.println("服务器正在启动,等待客户端的连接");
			while(true) {
				socket = serverSocket.accept();
				ServerThread thread = new ServerThread(socket);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
