package ServerItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;

import communication.UserInfo;

public class SerThread extends Thread {

	private Socket client;
	private OutputStream os;
	private InputStream is;
	private BufferedReader br;
	private JTextArea textShow;
	private UserIn user;

	public SerThread(Socket client, JTextArea textShow) {
		this.client = client;
		this.textShow = textShow;
		try {
			os = client.getOutputStream();
			is = client.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		ChatProcess();
	}

	public void ChatProcess(){
		// ����һ���ַ����ж���Ϣ����
		try {
			while (true) {
				String s = br.readLine();// ��ȡ���͵ĵ�һ����Ϣ
				String first = s.substring(0, 1);// �ָ����һ���ַ�
				System.out.println("first = " + first);
				switch (first) {
				case "1":// ע��
					String userinfo = s.substring(1);// �ӵڶ����ַ���ʼ����
					System.out.println("userinfo = " + userinfo);
					String us[] = userinfo.split("#");// ��#�ŷָ�
					System.out.println("user = " + us[0] + "    " + us[1]);
					user = new UserIn(us[0], us[1]);//�����û�����
					boolean flag = DaoTool.join(us[0], us[1]);// ���˺�����洢�������ش洢���
					System.out.println("flag = " + flag);
					textShow.append("Reg_Log�� �˺ţ�" + us[0] + "   ���룺" + us[1]
							+ "ע�������" + flag + "\n");// �ڷ�������������ʾ��־
					System.out.println(getOwerUser());
					if (flag) {
						os.write("true\r\n".getBytes());// ���͸��ͻ���ע��ɹ����
					} else {
						os.write("false\r\n".getBytes());
					}
					break;
				case "2":// ��¼
					s = s.substring(1);
					System.out.println("s = " + s);
					String str[] = s.split("#");
					System.out.println("str = " + str[0] + "    " + str[1]);
					user = new UserIn(str[0], str[1]);
					flag = DaoTool.check(user);
					System.out.println("flag = " + flag);
					textShow.append("Login_Log�� �˺ţ�" + str[0] + "   ���룺"
							+ str[1] + "��¼�����" + flag + "\n");
					if (flag) {
						os.write("true\r\n".getBytes());
					} else {
						os.write("false\r\n".getBytes());
					}
					break;
				case "3":// ˽��
					 

					break;
				case "4":// Ⱥ��
					s = s.substring(1);
					System.out.println("s = " + s);
					ChatTools.castMsg(user, s);
					textShow.append(user.getname()+"��������˵"+s);
					break;
				case "5":// �ļ�

					break;

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �õ���ǰ���û�����
	 * @return
	 */
	public UserIn getOwerUser() {
		System.out.println("getOwerUser"+this.user.getname());
		return this.user;
	}

	/**
	 * ��������Ϣ�Ĵ����װ��һ�����У����͸��ͻ���
	 * @param msg
	 */
	public void SendMsgToClient(String msg) {
		try {
			msg = msg + "\r\n";
			os.write(msg.getBytes()); // �ͻ��������������
			os.flush(); // ǿ��д��
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void closeMe() {
		// TODO Auto-generated method stub

	}
}