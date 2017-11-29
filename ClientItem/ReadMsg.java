package ClientItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

public class ReadMsg extends Thread{
	private JTextArea jt_input;
	private JTextArea jt_show;
	private String msg;
	private Socket client;
	private InputStream is;
	private BufferedReader brd;
	

	public ReadMsg(Socket client, JTextArea jt_show) { // 构造函数，传入客户端连结对象，和文本区域显示对象
		this.client = client; 
		this.jt_show= jt_show;
		try {
			is=client.getInputStream();
			brd=new BufferedReader(new InputStreamReader(is));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把服务器发送的内容显示到当前连结对象的显示区域
	 */
	public void run() {
		String input = null;
		while (true) {
					try {
						input=brd.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("input="+input);
					jt_show.append(input + "\n"); // 将服务器发送到输入流的内容追加到自己的文本显示区		
		}
	}
}
