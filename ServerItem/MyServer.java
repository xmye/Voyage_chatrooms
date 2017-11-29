package ServerItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;

import javax.swing.JTextArea;

import communication.DaoTool;

public class MyServer extends Thread {
	private Socket client;
	private JTextArea textShow;// 服务器界面的显示框
	private int port;
	private ArrayList<SerThread> stList;
	
	public MyServer(int port, JTextArea textShow) {
		this.textShow = textShow;
		this.port = port;
	}

	public void run() {
		SetupServer(port);
	}

	// 创建服务器
	private void SetupServer(int port) {
		try {
			ServerSocket server = new ServerSocket(port);// 绑定在指定端口号的服务器
			textShow.append("成功启动端口号是" + port + "的通信服务器。\n");
			while (true) {
				client = server.accept();// 阻塞，等待客户机连接，连上后返回Socket对象

				textShow.append(client.getRemoteSocketAddress() + "连接上服务器。\n");

				SerThread st = new SerThread(client, textShow);
				ChatTools.addclient(st);//将对象加入客户机队列
				st.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
