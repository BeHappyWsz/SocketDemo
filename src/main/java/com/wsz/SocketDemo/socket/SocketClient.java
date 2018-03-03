package com.wsz.SocketDemo.socket;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.wsz.SocketDemo.entity.Files;
import com.wsz.SocketDemo.entity.User;
import com.wsz.SocketDemo.util.CommandTransfer;
/**
 * 客户端实现类
 * @author wsz
 * @date 2018年3月3日
 */
public class SocketClient {

	private Scanner input = new Scanner(System.in);
	
	private Socket socket = null;
	
	public void showMainMenu() {
		System.out.println("1.登录 2.注册 3.退出");
		int choice = input.nextInt();
		switch(choice) {
			case 1:
				showLogin();
				break;
			case 2:
				showRegister();
				break;
			case 3 : 
				System.out.println("再见");
				System.exit(0);
			default:
				System.out.println("输入有误");
				System.exit(0);
		}
	}
	/**
	 * 用户登录
	 */
	private void showLogin() {
		User user = new User();
		CommandTransfer transfer = new CommandTransfer();
		int count = 0;
		while(true) {
			count++;
			if(count > 3) {
				System.out.println("已连续登录失败三次,退出程序");
				System.exit(0);
			}
			System.out.println("输入用户名:");
			user.setUsername(input.next());
			System.out.println("输入密码:");
			user.setPassword(input.next());
			transfer.setCmd("login");
			transfer.setData(user);
			
			try {
				socket = new Socket("localhost", 8800);
				
				sendData(transfer);
				transfer = getData();
				
				System.out.println(transfer.getResult());
				if(transfer.isFlag()) {
					break;//登录成功
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				closeAll();
			}
		}
		showUploadFile();
	}
	
	private void showRegister() {
		User user = new User();
		CommandTransfer transfer = new CommandTransfer();
		
		while(true) {
			System.out.println("输入用户名");
			user.setUsername(input.next());
			System.out.println("输入密码");
			user.setPassword(input.next());
			System.out.println("重新输入密码");
			String rPwd = input.next();
			if(!user.getPassword().equals(rPwd)) {
				System.out.println("两次输入的密码不一致");
				System.out.println("****************");
				continue;
			}
			transfer.setCmd("register");
			transfer.setData(user);
			
			try {
				socket = new Socket("localhost", 8800);
				sendData(transfer);
				transfer = getData();
				System.out.println(transfer.getResult());
				if(transfer.isFlag()) {
					break;
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		showLogin();
	}
	
	private void showUploadFile() {
		System.out.println("请输入上传文件的绝对路径(c:/aa/aa.txt)");
		String path = input.next();
		Files file = null;
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		String fname = path.substring(path.lastIndexOf("/")+1);
		try {
			fis = new FileInputStream(path);
			byte[] fcontent = new byte[fis.available()];
			bis = new BufferedInputStream(fis);
			bis.read(fcontent);
			file = new Files(fname,fcontent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		CommandTransfer transfer = new CommandTransfer();
		transfer.setCmd("uploadFile");
		transfer.setData(file);
		
		try {
			socket = new Socket("localhost", 8800);
			sendData(transfer);
			transfer = getData();
			System.out.println(transfer.getResult());//输出显示结果
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		
	}
	
	private void closeAll() {
		try {
			if(socket != null)
				socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendData(CommandTransfer transfer) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(transfer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private CommandTransfer getData() {
		CommandTransfer transfer = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			transfer = (CommandTransfer) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return transfer;
	}
}
