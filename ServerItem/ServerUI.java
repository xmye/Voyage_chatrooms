package ServerItem;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import communication.ChatTool;
import communication.ServerThread;
import communication.UserInfoTableMode;

/**
 * 服务器管理界面
 * 
 * @author thinkpad
 *
 */
public class ServerUI extends JFrame {

	public static JTable table_onlineuser;// 在线用户表

	public static void main(String[] args) {
		ServerUI su = new ServerUI();
		su.init();
	}

	private void init() {
		this.setTitle("服务管理界面");
		this.setSize(new Dimension(500, 500));
		this.setDefaultCloseOperation(2);
		this.setLocationRelativeTo(null);
		this.setResizable(false);// 固定界面
		FlowLayout fl = new FlowLayout();
		this.setLayout(fl);
		JLabel jl = new JLabel("端口号");
		this.add(jl);
		final JTextField jt_port = new JTextField(10);
		jt_port.setText("1111");
		this.add(jt_port);
		JButton bu_setup = new JButton("启动");

		this.add(bu_setup);

		table_onlineuser = new JTable();// 界面上用以显示用户列表的表格

		List<SerThread> sts = ChatTools.getAllThread();// 创建我们自己的modle对象，创建时传入线程处理对象
		UserTableMode utm = new UserTableMode(sts);
		table_onlineuser.setModel(utm); // 将模型加给表格

		JScrollPane scroll = new JScrollPane(table_onlineuser);// 将表格对象放到滚动面板上

		table_onlineuser.setPreferredScrollableViewportSize(new Dimension(380, // 设定表格在面板上的大小
				100));

		scroll.setAutoscrolls(true);// 自动出现滚动条
		this.add(scroll);

		// JPopupMenu pop = getTablePop();// 取得表格上的弹出菜单对象，加到表格上
		// table_onlineuser.setComponentPopupMenu(pop);

		// 显示消息的文本区域
		final JTextArea textShow = new JTextArea();
		textShow.setPreferredSize(new Dimension(380, 300));
		textShow.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textShow);
		scrollPane.setAutoscrolls(true);

		this.add(scrollPane);

		bu_setup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String text = jt_port.getText();
				if (text != null && !text.equals("")) {
					int port = Integer.parseInt(text);
					MyServer ms = new MyServer(port, textShow);
					ms.start();
				} else {
					JOptionPane.showMessageDialog(ServerUI.this, "请输入端口号！");
				}
			}
		});
		this.setDefaultCloseOperation(3); // 界面关闭时，程序退出
		this.setVisible(true);
	}
}
