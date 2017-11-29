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
 * �������������
 * 
 * @author thinkpad
 *
 */
public class ServerUI extends JFrame {

	public static JTable table_onlineuser;// �����û���

	public static void main(String[] args) {
		ServerUI su = new ServerUI();
		su.init();
	}

	private void init() {
		this.setTitle("����������");
		this.setSize(new Dimension(500, 500));
		this.setDefaultCloseOperation(2);
		this.setLocationRelativeTo(null);
		this.setResizable(false);// �̶�����
		FlowLayout fl = new FlowLayout();
		this.setLayout(fl);
		JLabel jl = new JLabel("�˿ں�");
		this.add(jl);
		final JTextField jt_port = new JTextField(10);
		jt_port.setText("1111");
		this.add(jt_port);
		JButton bu_setup = new JButton("����");

		this.add(bu_setup);

		table_onlineuser = new JTable();// ������������ʾ�û��б�ı��

		List<SerThread> sts = ChatTools.getAllThread();// ���������Լ���modle���󣬴���ʱ�����̴߳������
		UserTableMode utm = new UserTableMode(sts);
		table_onlineuser.setModel(utm); // ��ģ�ͼӸ����

		JScrollPane scroll = new JScrollPane(table_onlineuser);// ��������ŵ����������

		table_onlineuser.setPreferredScrollableViewportSize(new Dimension(380, // �趨���������ϵĴ�С
				100));

		scroll.setAutoscrolls(true);// �Զ����ֹ�����
		this.add(scroll);

		// JPopupMenu pop = getTablePop();// ȡ�ñ���ϵĵ����˵����󣬼ӵ������
		// table_onlineuser.setComponentPopupMenu(pop);

		// ��ʾ��Ϣ���ı�����
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
					JOptionPane.showMessageDialog(ServerUI.this, "������˿ںţ�");
				}
			}
		});
		this.setDefaultCloseOperation(3); // ����ر�ʱ�������˳�
		this.setVisible(true);
	}
}
