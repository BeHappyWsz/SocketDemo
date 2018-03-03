package com.wsz.SocketDemo.socket;
/**
 * 客户端启动类
 * @author wsz
 * @date 2018年3月3日
 */
public class StartClient {

	public static void main(String[] args) {
		SocketClient client = new SocketClient();
		client.showMainMenu();
	}
}
