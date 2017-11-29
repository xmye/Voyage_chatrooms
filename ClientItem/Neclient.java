package ClientItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JTextArea;

import ServerItem.SerThread;

/**
 * 从服务器读取消息的线程类
 * 
 * @author thinkpad
 *
 */
public class Neclient extends Thread {
	private String serverIP;
	private int port;
	private OutputStream ous;
	private BufferedReader brd;// 到服务器的输入流对象
	private JTextArea j_recive;// 显示接受到的消息组件，从界面上传过来
	private Socket client;

	public Neclient(String serverIP, int port) {
		this.serverIP = serverIP;
		this.port = port;
	}

	public Socket getClient() {
		return this.client;
	}

	// public Neclient(String serverIP,int port,JTextArea j_recive){
	// this.serverIP=serverIP;
	// this.port=port;
	// this.j_recive=j_recive;
	// }
	/** 连上服务器，判断是否成功 */
	public boolean isconnect() {
		try {
			client = new Socket(this.serverIP, this.port);// 创建服务器连接对象
			InputStream ins = client.getInputStream(); // 得到输入输出流对象
			ous = client.getOutputStream();
			brd = new BufferedReader(new InputStreamReader(ins)); // 可以读取一行字符串
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false; 
	}

	/**
	 * 登录服务器
	 * 
	 * @param name
	 * @param psd
	 * @return是否登录成功
	 */
	// public boolean loginserver(String name,String psd){
	// //读取服务器发来的一条消息
	// try {
	// name+="\r\n";
	// ous.write(name.getBytes());
	// ous.flush();
	// System.out.println("用户名已发送");
	//
	// psd+="\r\n";
	// ous.write(psd.getBytes());
	// ous.flush();
	// System.out.println("密码已发送");
	// return true;
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return false;
	// }

	// 线程中读取服务器发来的消息
	public void run() {
		while (true) {
			readFromServer();
		}
	}

	// 从服务器中读取消息,会阻塞，运行再独立的线程中
	public void readFromServer() {
		try {
			String input = brd.readLine();
			j_recive.append(input + "\r\n"); // 将收到的消息显示到界面上
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从服务器读取消息
	 * @return
	 */
	public String readMsg() {
		try {
			String input = brd.readLine();
			// System.out.println("服务器说："+input);
			// j_recive.append(input+"\r\n"); //将收到的消息显示到界面上
			return input;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 发送消息到服务器 */
	public void sendMsg(String msg) {
		try {
			msg += "\r\n"; // 换行回车
			System.out.println(ous);
			this.ous.write(msg.getBytes());
			this.ous.flush();
		} catch (IOException e) {
			System.out.println(ous);
			e.printStackTrace();
		}
	}

	// public static void main(String[] args){
	// Netclient nc=new Netclient("localhost", 9099);
	// if(nc.isconnect()){ //连接服务器成功
	// //创建一个从命令行读取用户输入的扫描器对象
	// Scanner sc=new Scanner(System.in);
	// System.out.println("请输入用户名");
	// String name=sc.nextLine(); //读取命令行输入
	// System.out.println("请输入密码");
	// String psd=sc.nextLine();
	// if(nc.loginServer(name, psd)){
	// nc.start();//启动读取线程
	// while(true){
	// System.out.println("输入要发送的消息");
	// String msg=sc.nextLine();
	// nc.sendMsg(msg);
	// }
	// }
	// }
	// }
}