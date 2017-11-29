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
		// 读第一个字符，判断消息类型
		try {
			while (true) {
				String s = br.readLine();// 读取发送的第一条消息
				String first = s.substring(0, 1);// 分割出第一个字符
				System.out.println("first = " + first);
				switch (first) {
				case "1":// 注册
					String userinfo = s.substring(1);// 从第二个字符开始读起
					System.out.println("userinfo = " + userinfo);
					String us[] = userinfo.split("#");// 用#号分割
					System.out.println("user = " + us[0] + "    " + us[1]);
					user = new UserIn(us[0], us[1]);//创建用户对象
					boolean flag = DaoTool.join(us[0], us[1]);// 将账号密码存储，并返回存储情况
					System.out.println("flag = " + flag);
					textShow.append("Reg_Log： 账号：" + us[0] + "   密码：" + us[1]
							+ "注册情况：" + flag + "\n");// 在服务器界面上显示日志
					System.out.println(getOwerUser());
					if (flag) {
						os.write("true\r\n".getBytes());// 发送给客户机注册成功与否
					} else {
						os.write("false\r\n".getBytes());
					}
					break;
				case "2":// 登录
					s = s.substring(1);
					System.out.println("s = " + s);
					String str[] = s.split("#");
					System.out.println("str = " + str[0] + "    " + str[1]);
					user = new UserIn(str[0], str[1]);
					flag = DaoTool.check(user);
					System.out.println("flag = " + flag);
					textShow.append("Login_Log： 账号：" + str[0] + "   密码："
							+ str[1] + "登录情况：" + flag + "\n");
					if (flag) {
						os.write("true\r\n".getBytes());
					} else {
						os.write("false\r\n".getBytes());
					}
					break;
				case "3":// 私聊
					 

					break;
				case "4":// 群聊
					s = s.substring(1);
					System.out.println("s = " + s);
					ChatTools.castMsg(user, s);
					textShow.append(user.getname()+"对所有人说"+s);
					break;
				case "5":// 文件

					break;

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 得到当前的用户对象
	 * @return
	 */
	public UserIn getOwerUser() {
		System.out.println("getOwerUser"+this.user.getname());
		return this.user;
	}

	/**
	 * 将发送消息的代码封装到一个类中，发送给客户机
	 * @param msg
	 */
	public void SendMsgToClient(String msg) {
		try {
			msg = msg + "\r\n";
			os.write(msg.getBytes()); // 客户机的输出流对象
			os.flush(); // 强制写入
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void closeMe() {
		// TODO Auto-generated method stub

	}
}