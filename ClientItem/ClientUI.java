package ClientItem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import communication.ChatTool;
import communication.DaoTool;
import communication.ServerThread;
import communication.UserInfoTableMode;
import Client0726.Netclient;

public class ClientUI {
	private javax.swing.JFrame jf;
	private javax.swing.JTextField jt = new JTextField(14);// 用户名 文本框定义成全局的用于传递
	private javax.swing.JTextField jte = new JTextField(14);// 密码
	private JTextArea jt_input = new JTextArea(10, 20); // 显示收到的消息组

	// private Neclient nc = new Neclient("localhost", 1111, jt_input);;//
	// 界面要使用的连接对象

	public static void main(String[] args) {
		ClientUI cu = new ClientUI();
		cu.showUI();
	}

	public void showUI() {
		jf = new javax.swing.JFrame(); // 创建窗体
		jf.setSize(320, 280);
		jf.setTitle("聊天");
		jf.setResizable(false);
		jf.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); // 关闭窗体
		jf.setLocationRelativeTo(null); // 窗体居中显示
		northPanel();
		southPanel();
		westPanel();
		centerPanel();
		jf.setVisible(true); // 设置窗体可见
	}

	public void northPanel() {
		// 面板对象默认是流式布局
		javax.swing.JPanel northPanel = new javax.swing.JPanel();
		northPanel.setBackground(Color.BLACK);
		northPanel.setPreferredSize(new java.awt.Dimension(0, 130));
		jf.add(northPanel, BorderLayout.NORTH);
		// 图片
		javax.swing.ImageIcon im = new javax.swing.ImageIcon("image/qq图片.PNG");
		// 标签
		javax.swing.JLabel jl = new javax.swing.JLabel(im);
		northPanel.add(jl);
	}

	public void southPanel() {
		javax.swing.JPanel southPanel = new javax.swing.JPanel();
		southPanel.setBackground(Color.white);
		southPanel.setPreferredSize(new java.awt.Dimension(0, 50));
		jf.add(southPanel, BorderLayout.SOUTH);

		javax.swing.JButton jbu = new javax.swing.JButton("登录"); // 添加按钮
		jbu.setPreferredSize(new java.awt.Dimension(60, 35)); // 设置组件大小方法（除了JFrame）
		JButton bu_zhuche = new JButton("注册");
		bu_zhuche.setPreferredSize(new java.awt.Dimension(60, 35));

		LoginRegListener lrl = new LoginRegListener(jt, jte, jf);

		bu_zhuche.addActionListener(lrl); // 加监听
		jbu.addActionListener(lrl);

		southPanel.add(jbu);
		southPanel.add(bu_zhuche);
	}

	public void westPanel() {
		javax.swing.JPanel westPanel = new javax.swing.JPanel();
		westPanel.setBackground(Color.white);
		westPanel.setPreferredSize(new java.awt.Dimension(80, 0));
		javax.swing.ImageIcon ima = new javax.swing.ImageIcon("image/qq头像.PNG"); // 图片
		javax.swing.JLabel jla = new javax.swing.JLabel(ima);
		westPanel.add(jla);
		jf.add(westPanel, BorderLayout.WEST);
	}

	public void centerPanel() {
		javax.swing.JPanel centerPanel = new javax.swing.JPanel();
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setPreferredSize(new java.awt.Dimension(0, 0));
		jf.add(centerPanel, BorderLayout.CENTER);
		JLabel jla = new JLabel("账号：");
		centerPanel.add(jla);
		centerPanel.add(jt); // 文本框
		JLabel jlab = new JLabel("密码：");
		centerPanel.add(jlab);
		centerPanel.add(jte);

		// javax.swing.JLabel jlab=new javax.swing.JLabel("找回密码");
		// centerPanel.add(jlab);
		// // 复选框
		// javax.swing.JCheckBox jch=new javax.swing.JCheckBox("记住密码");
		// centerPanel.add(jch);
		// javax.swing.JCheckBox jc=new javax.swing.JCheckBox("自动登录");
		// centerPanel.add(jc);
		// 传文本框对象用于判断输入
	}

}
