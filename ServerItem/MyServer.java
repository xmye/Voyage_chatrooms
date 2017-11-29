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
	private JTextArea textShow;// �������������ʾ��
	private int port;
	private ArrayList<SerThread> stList;
	
	public MyServer(int port, JTextArea textShow) {
		this.textShow = textShow;
		this.port = port;
	}

	public void run() {
		SetupServer(port);
	}

	// ����������
	private void SetupServer(int port) {
		try {
			ServerSocket server = new ServerSocket(port);// ����ָ���˿ںŵķ�����
			textShow.append("�ɹ������˿ں���" + port + "��ͨ�ŷ�������\n");
			while (true) {
				client = server.accept();// �������ȴ��ͻ������ӣ����Ϻ󷵻�Socket����

				textShow.append(client.getRemoteSocketAddress() + "�����Ϸ�������\n");

				SerThread st = new SerThread(client, textShow);
				ChatTools.addclient(st);//���������ͻ�������
				st.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
