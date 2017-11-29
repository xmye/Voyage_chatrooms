package ClientItem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.sql.NClob;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import ServerItem.ChatTools;
import ServerItem.SerThread;
import ServerItem.UserIn;
import ServerItem.UserTableMode;
import communication.ChatTool;
import communication.ServerThread;
import communication.UserInfoTableMode;

public class ChatUI extends JFrame {
	private JTextArea jt_show;
	private JTextArea jt_input;
	private Neclient nc;
	private Socket client;

	public ChatUI(Neclient nc) {
		this.nc = nc;
	}

	/**
	 * 聊天界面
	 */
	public void showChatUI() {
		this.setTitle("客户端");
		this.setSize(600, 450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
		// 北边面板
		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(0, 300));
		jt_show = new JTextArea(30, 50);
		JScrollPane jsc = new JScrollPane(jt_show);// 将文本区域传给滚动面板，否则文本区域显示不出来
		jp.add(jsc);
		this.add(jp, BorderLayout.NORTH);// 将面板添加给frame，边框布局的北边
		// 南边面板
		JPanel sp = new JPanel();
		sp.setPreferredSize(new Dimension(0, 100));
		jt_input = new JTextArea(4, 30);
		JScrollPane js = new JScrollPane(jt_input);
		sp.add(js);
		JButton jb = new JButton("发送");
		jb.setPreferredSize(new Dimension(60, 40));
		sp.add(jb);
//		east();
		JButton sendToAll = new JButton("群发");
		sendToAll.setPreferredSize(new Dimension(60, 40));

		client = nc.getClient();// 得到线程对象对应的连接对象
		System.out.println("client=" + client);
		SendLister seli = new SendLister(client, jt_input, jt_show);// 监听器对象
		jb.addActionListener(seli);
		sendToAll.addActionListener(seli);
		sp.add(sendToAll);
		this.add(sp, BorderLayout.SOUTH);
		this.setVisible(true);

		ReadMsg rm = new ReadMsg(client, jt_show);// 使读写消息分离，能随时读取消息
		rm.start();
	}

	/**
	 * 显示用户列表
	 */
	private void east() {
		javax.swing.JPanel ep = new javax.swing.JPanel();
		ep.setBackground(Color.white);
		ep.setPreferredSize(new java.awt.Dimension(100, 100));
		JTable ta_user=new JTable();// 显示用户列表

		ClientTableMode utm = new ClientTableMode();
		ta_user.setModel(utm); // 将模型加给表格

		JScrollPane scroll = new JScrollPane(ta_user);// 将表格对象放到滚动面板上

		ta_user.setPreferredScrollableViewportSize(new Dimension(130, // 设定表格在面板上的大小
				100));

		scroll.setAutoscrolls(true);// 自动出现滚动条
//		jl_user.addListSelectionListener();
		ep.add(ta_user);
		this.add(ep, BorderLayout.EAST);
	}
}
