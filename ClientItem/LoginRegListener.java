package ClientItem;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * ������¼�����ע�ᣬ��½��ť
 * @author thinkpad
 *
 */
public class LoginRegListener implements ActionListener {
	private JTextField textName;// �û��� �ı������ȫ�ֵ����ڴ���
	private JTextField textPassword;// ����
	private JFrame frame;// ��¼�������
	private Neclient nc;
//	private ArrayList<Neclient> list=new ArrayList<Neclient>();//װ�ͻ����̶߳���Ķ���

	public LoginRegListener(JTextField textName, JTextField textPassword,
			JFrame frame) {
		this.textName = textName;
		this.textPassword = textPassword;
		this.frame = frame;
	}

	// ��¼�¼�������
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("��¼")) {
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
		// �������Ӷ���
		nc = new Neclient("localhost", 1111);
		// �����Ϸ�����
		if (nc.isconnect()) { // ������
			String msg = 2 + name + "#" + psd;
			nc.sendMsg(msg);// ����������͵�¼��Ϣ
			System.out.println("  msg = "+msg);
			msg = nc.readMsg();// ��ȡ�����������ͻ����û���¼�Ƿ�ɹ�
			boolean flag = Boolean.parseBoolean(msg);
			if (flag) {
				System.out.println(nc);
//				JOptionPane.showMessageDialog(frame, "��¼�ɹ�");
				ChatUI cui = new ChatUI(nc);//��ʾ�������,�������Ӷ���
				cui.showChatUI();
				frame.dispose();//�رյ�½����
			} else {
				JOptionPane.showMessageDialog(frame, "��¼ʧ��");
			}
		} else {
			JOptionPane.showMessageDialog(frame,
					"�ͻ����޷������Ϸ������������Ƿ���������߷���ǽ�Ƿ�رգ�");
		}
	}

}
