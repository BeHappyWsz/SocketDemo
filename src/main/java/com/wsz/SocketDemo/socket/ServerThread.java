package com.wsz.SocketDemo.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.wsz.SocketDemo.entity.Files;
import com.wsz.SocketDemo.entity.User;
import com.wsz.SocketDemo.service.FileService;
import com.wsz.SocketDemo.service.UserService;
import com.wsz.SocketDemo.util.CommandTransfer;
/**
 * 线程辅助类
 * @author wsz
 * @date 2018年3月3日
 */
public class ServerThread extends Thread{

	private Socket socket = null;

	private ObjectInputStream ois = null;
	
	private ObjectOutputStream oos = null;
	
	private UserService userService = new UserService();
	
	private FileService fileService = new FileService();
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			CommandTransfer transfer = (CommandTransfer) ois.readObject();
			transfer = execute(transfer);//执行客户端发送到服务器的指令操作
			oos.writeObject(transfer);//响应客户端
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 执行客户端发送到服务器的指令操作
	 * @param transfer
	 * @return
	 */
	public  CommandTransfer execute(CommandTransfer transfer) {
		String cmd = transfer.getCmd();
		if("login".equals(cmd)) {
			User user = (User) transfer.getData();
			boolean flag = userService.login(user);
			transfer.setFlag(flag);
			if(flag)
				transfer.setResult("登录成功");
			else
				transfer.setResult("用户名或密码不正确,请重新登录");
		}else if("register".equals(cmd)) {
			User user = (User) transfer.getData();
			boolean flag = userService.register(user);
			transfer.setFlag(flag);
			if(flag)
				transfer.setResult("注册成功");
			else
				transfer.setResult("注册失败");
		}else if("uploadFile".equals(cmd)) {
			Files file = (Files) transfer.getData();
			boolean flag = fileService.save(file);
			transfer.setFlag(flag);
			if(flag)
				transfer.setResult("文件上传成功");
			else
				transfer.setResult("文件上传失败");
		}
		return transfer;
	}
}
