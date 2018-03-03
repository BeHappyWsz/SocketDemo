package com.wsz.SocketDemo.util;

import java.io.Serializable;
/**
 * 传输数据辅助类
 * @author wsz
 * @date 2018年3月3日
 */
public class CommandTransfer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String cmd;
	private Object data;
	private boolean flag;
	private String result;
	
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
