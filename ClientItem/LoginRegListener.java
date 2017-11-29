package ClientItem;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * 监听登录界面的注册，登陆按钮
 * @author thinkpad
 *
 */
public class LoginRegListener implements ActionListener {
	private JTextField textName;// 用户名 文本框定义成全局的用于传递
	private JTextField textPassword;// 密码
	private JFrame frame;// 登录窗体对象
	private Neclient nc;
//	private ArrayList<Neclient> list=new ArrayList<Neclient>();//装客户端线程对象的队列

	public LoginRegListener(JTextField textName, JTextField textPassword,
			JFrame frame) {
		this.textName = textName;
		this.textPassword = textPassword;
		this.frame = frame;
	}

	// 登录事件处理方法
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("登录")) {
			login();
		} else {
			reg();
		}
	}

	private void reg() {
		RegisterUI rui = new RegisterUI();
		rui.initUI();
	}

	private void login() {
		String name = textName.getText();
		String psd = textPassword.getText();
		// 创建连接对象
		nc = new Neclient("localhost", 1111);
		// 连接上服务器
		if (nc.isconnect()) { // 连接上
			String msg = 2 + name + "#" + psd;
			nc.sendMsg(msg);// 向服务器发送登录消息
			System.out.println("  msg = "+msg);
			msg = nc.readMsg();// 读取服务器发给客户机用户登录是否成功
			boolean flag = Boolean.parseBoolean(msg);
			if (flag) {
				System.out.println(nc);
//				JOptionPane.showMessageDialog(frame, "登录成功");
				ChatUI cui = new ChatUI(nc);//显示聊天界面,传入连接对象
				cui.showChatUI();
				frame.dispose();//关闭登陆界面
			} else {
				JOptionPane.showMessageDialog(frame, "登录失败");
			}
		} else {
			JOptionPane.showMessageDialog(frame,
					"客户机无法连接上服务器，请检查是否有网络或者防火墙是否关闭！");
		}
	}

}
