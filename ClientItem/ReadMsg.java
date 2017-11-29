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
	

	public ReadMsg(Socket client, JTextArea jt_show) { // ���캯��������ͻ���������󣬺��ı�������ʾ����
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
	 * �ѷ��������͵�������ʾ����ǰ����������ʾ����
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
					jt_show.append(input + "\n"); // �����������͵�������������׷�ӵ��Լ����ı���ʾ��		
		}
	}
}
