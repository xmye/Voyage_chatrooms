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
 * �ӷ�������ȡ��Ϣ���߳���
 * 
 * @author thinkpad
 *
 */
public class Neclient extends Thread {
	private String serverIP;
	private int port;
	private OutputStream ous;
	private BufferedReader brd;// ��������������������
	private JTextArea j_recive;// ��ʾ���ܵ�����Ϣ������ӽ����ϴ�����
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
	/** ���Ϸ��������ж��Ƿ�ɹ� */
	public boolean isconnect() {
		try {
			client = new Socket(this.serverIP, this.port);// �������������Ӷ���
			InputStream ins = client.getInputStream(); // �õ��������������
			ous = client.getOutputStream();
			brd = new BufferedReader(new InputStreamReader(ins)); // ���Զ�ȡһ���ַ���
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false; 
	}

	/**
	 * ��¼������
	 * 
	 * @param name
	 * @param psd
	 * @return�Ƿ��¼�ɹ�
	 */
	// public boolean loginserver(String name,String psd){
	// //��ȡ������������һ����Ϣ
	// try {
	// name+="\r\n";
	// ous.write(name.getBytes());
	// ous.flush();
	// System.out.println("�û����ѷ���");
	//
	// psd+="\r\n";
	// ous.write(psd.getBytes());
	// ous.flush();
	// System.out.println("�����ѷ���");
	// return true;
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return false;
	// }

	// �߳��ж�ȡ��������������Ϣ
	public void run() {
		while (true) {
			readFromServer();
		}
	}

	// �ӷ������ж�ȡ��Ϣ,�������������ٶ������߳���
	public void readFromServer() {
		try {
			String input = brd.readLine();
			j_recive.append(input + "\r\n"); // ���յ�����Ϣ��ʾ��������
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �ӷ�������ȡ��Ϣ
	 * @return
	 */
	public String readMsg() {
		try {
			String input = brd.readLine();
			// System.out.println("������˵��"+input);
			// j_recive.append(input+"\r\n"); //���յ�����Ϣ��ʾ��������
			return input;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** ������Ϣ�������� */
	public void sendMsg(String msg) {
		try {
			msg += "\r\n"; // ���лس�
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
	// if(nc.isconnect()){ //���ӷ������ɹ�
	// //����һ���������ж�ȡ�û������ɨ��������
	// Scanner sc=new Scanner(System.in);
	// System.out.println("�������û���");
	// String name=sc.nextLine(); //��ȡ����������
	// System.out.println("����������");
	// String psd=sc.nextLine();
	// if(nc.loginServer(name, psd)){
	// nc.start();//������ȡ�߳�
	// while(true){
	// System.out.println("����Ҫ���͵���Ϣ");
	// String msg=sc.nextLine();
	// nc.sendMsg(msg);
	// }
	// }
	// }
	// }
}