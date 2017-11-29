package ClientItem;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * 注册的页面
 * 
 * @author thinkpad
 *
 */
public class RegisterUI extends JFrame {

	private JTextField textName = new JTextField(10);// 账号文本框
	private JTextField textPassword = new JTextField(10);// 密码

	public void initUI() {
		this.setTitle("注册");
		FlowLayout fl = new FlowLayout();
		this.setLayout(fl);
		this.setSize(200, 200);
		this.setResizable(false);
		JLabel jl_username = new JLabel("账号：");
		JLabel jl_psd = new JLabel("密码：");
		JButton bu_ok = new JButton("注册");
		this.add(jl_username);
		this.add(textName);
		this.add(jl_psd);
		this.add(textPassword);
		this.add(bu_ok);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);
		this.setVisible(true);

		bu_ok.addActionListener(new ActionListener() {// 监听
			public void actionPerformed(ActionEvent e) {
				String name = textName.getText();
				String psd = textPassword.getText();

				Neclient nc = new Neclient("localhost", 1111);
				if (nc.isconnect()) {
					String msg = 1 + name + "#" + psd;
					nc.sendMsg(msg);// 向服务器发送注册消息
					System.out.println(msg);
					msg = nc.readMsg();// 读取服务器发给客户机注册用户是否成功
					boolean flag = Boolean.parseBoolean(msg);
					if (flag) {
						JOptionPane.showMessageDialog(RegisterUI.this, "注册成功");
						RegisterUI.this.dispose();//关闭注册页面
					} else {
						JOptionPane.showMessageDialog(RegisterUI.this, "注册失败");
					}
				} else {
					JOptionPane.showMessageDialog(RegisterUI.this, "连接服务器失败！");
				}
			}
		});
	}

}
