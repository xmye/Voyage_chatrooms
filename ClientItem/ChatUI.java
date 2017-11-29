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
	 * �������
	 */
	public void showChatUI() {
		this.setTitle("�ͻ���");
		this.setSize(600, 450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
		// �������
		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(0, 300));
		jt_show = new JTextArea(30, 50);
		JScrollPane jsc = new JScrollPane(jt_show);// ���ı����򴫸�������壬�����ı�������ʾ������
		jp.add(jsc);
		this.add(jp, BorderLayout.NORTH);// �������Ӹ�frame���߿򲼾ֵı���
		// �ϱ����
		JPanel sp = new JPanel();
		sp.setPreferredSize(new Dimension(0, 100));
		jt_input = new JTextArea(4, 30);
		JScrollPane js = new JScrollPane(jt_input);
		sp.add(js);
		JButton jb = new JButton("����");
		jb.setPreferredSize(new Dimension(60, 40));
		sp.add(jb);
//		east();
		JButton sendToAll = new JButton("Ⱥ��");
		sendToAll.setPreferredSize(new Dimension(60, 40));

		client = nc.getClient();// �õ��̶߳����Ӧ�����Ӷ���
		System.out.println("client=" + client);
		SendLister seli = new SendLister(client, jt_input, jt_show);// ����������
		jb.addActionListener(seli);
		sendToAll.addActionListener(seli);
		sp.add(sendToAll);
		this.add(sp, BorderLayout.SOUTH);
		this.setVisible(true);

		ReadMsg rm = new ReadMsg(client, jt_show);// ʹ��д��Ϣ���룬����ʱ��ȡ��Ϣ
		rm.start();
	}

	/**
	 * ��ʾ�û��б�
	 */
	private void east() {
		javax.swing.JPanel ep = new javax.swing.JPanel();
		ep.setBackground(Color.white);
		ep.setPreferredSize(new java.awt.Dimension(100, 100));
		JTable ta_user=new JTable();// ��ʾ�û��б�

		ClientTableMode utm = new ClientTableMode();
		ta_user.setModel(utm); // ��ģ�ͼӸ����

		JScrollPane scroll = new JScrollPane(ta_user);// ��������ŵ����������

		ta_user.setPreferredScrollableViewportSize(new Dimension(130, // �趨���������ϵĴ�С
				100));

		scroll.setAutoscrolls(true);// �Զ����ֹ�����
//		jl_user.addListSelectionListener();
		ep.add(ta_user);
		this.add(ep, BorderLayout.EAST);
	}
}
