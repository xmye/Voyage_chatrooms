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
	private javax.swing.JTextField jt = new JTextField(14);// �û��� �ı������ȫ�ֵ����ڴ���
	private javax.swing.JTextField jte = new JTextField(14);// ����
	private JTextArea jt_input = new JTextArea(10, 20); // ��ʾ�յ�����Ϣ��

	// private Neclient nc = new Neclient("localhost", 1111, jt_input);;//
	// ����Ҫʹ�õ����Ӷ���

	public static void main(String[] args) {
		ClientUI cu = new ClientUI();
		cu.showUI();
	}

	public void showUI() {
		jf = new javax.swing.JFrame(); // ��������
		jf.setSize(320, 280);
		jf.setTitle("����");
		jf.setResizable(false);
		jf.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); // �رմ���
		jf.setLocationRelativeTo(null); // ���������ʾ
		northPanel();
		southPanel();
		westPanel();
		centerPanel();
		jf.setVisible(true); // ���ô���ɼ�
	}

	public void northPanel() {
		// ������Ĭ������ʽ����
		javax.swing.JPanel northPanel = new javax.swing.JPanel();
		northPanel.setBackground(Color.BLACK);
		northPanel.setPreferredSize(new java.awt.Dimension(0, 130));
		jf.add(northPanel, BorderLayout.NORTH);
		// ͼƬ
		javax.swing.ImageIcon im = new javax.swing.ImageIcon("image/qqͼƬ.PNG");
		// ��ǩ
		javax.swing.JLabel jl = new javax.swing.JLabel(im);
		northPanel.add(jl);
	}

	public void southPanel() {
		javax.swing.JPanel southPanel = new javax.swing.JPanel();
		southPanel.setBackground(Color.white);
		southPanel.setPreferredSize(new java.awt.Dimension(0, 50));
		jf.add(southPanel, BorderLayout.SOUTH);

		javax.swing.JButton jbu = new javax.swing.JButton("��¼"); // ��Ӱ�ť
		jbu.setPreferredSize(new java.awt.Dimension(60, 35)); // ���������С����������JFrame��
		JButton bu_zhuche = new JButton("ע��");
		bu_zhuche.setPreferredSize(new java.awt.Dimension(60, 35));

		LoginRegListener lrl = new LoginRegListener(jt, jte, jf);

		bu_zhuche.addActionListener(lrl); // �Ӽ���
		jbu.addActionListener(lrl);

		southPanel.add(jbu);
		southPanel.add(bu_zhuche);
	}

	public void westPanel() {
		javax.swing.JPanel westPanel = new javax.swing.JPanel();
		westPanel.setBackground(Color.white);
		westPanel.setPreferredSize(new java.awt.Dimension(80, 0));
		javax.swing.ImageIcon ima = new javax.swing.ImageIcon("image/qqͷ��.PNG"); // ͼƬ
		javax.swing.JLabel jla = new javax.swing.JLabel(ima);
		westPanel.add(jla);
		jf.add(westPanel, BorderLayout.WEST);
	}

	public void centerPanel() {
		javax.swing.JPanel centerPanel = new javax.swing.JPanel();
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setPreferredSize(new java.awt.Dimension(0, 0));
		jf.add(centerPanel, BorderLayout.CENTER);
		JLabel jla = new JLabel("�˺ţ�");
		centerPanel.add(jla);
		centerPanel.add(jt); // �ı���
		JLabel jlab = new JLabel("���룺");
		centerPanel.add(jlab);
		centerPanel.add(jte);

		// javax.swing.JLabel jlab=new javax.swing.JLabel("�һ�����");
		// centerPanel.add(jlab);
		// // ��ѡ��
		// javax.swing.JCheckBox jch=new javax.swing.JCheckBox("��ס����");
		// centerPanel.add(jch);
		// javax.swing.JCheckBox jc=new javax.swing.JCheckBox("�Զ���¼");
		// centerPanel.add(jc);
		// ���ı�����������ж�����
	}

}
