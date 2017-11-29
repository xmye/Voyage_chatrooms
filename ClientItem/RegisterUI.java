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
 * ע���ҳ��
 * 
 * @author thinkpad
 *
 */
public class RegisterUI extends JFrame {

	private JTextField textName = new JTextField(10);// �˺��ı���
	private JTextField textPassword = new JTextField(10);// ����

	public void initUI() {
		this.setTitle("ע��");
		FlowLayout fl = new FlowLayout();
		this.setLayout(fl);
		this.setSize(200, 200);
		this.setResizable(false);
		JLabel jl_username = new JLabel("�˺ţ�");
		JLabel jl_psd = new JLabel("���룺");
		JButton bu_ok = new JButton("ע��");
		this.add(jl_username);
		this.add(textName);
		this.add(jl_psd);
		this.add(textPassword);
		this.add(bu_ok);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);
		this.setVisible(true);

		bu_ok.addActionListener(new ActionListener() {// ����
			public void actionPerformed(ActionEvent e) {
				String name = textName.getText();
				String psd = textPassword.getText();

				Neclient nc = new Neclient("localhost", 1111);
				if (nc.isconnect()) {
					String msg = 1 + name + "#" + psd;
					nc.sendMsg(msg);// �����������ע����Ϣ
					System.out.println(msg);
					msg = nc.readMsg();// ��ȡ�����������ͻ���ע���û��Ƿ�ɹ�
					boolean flag = Boolean.parseBoolean(msg);
					if (flag) {
						JOptionPane.showMessageDialog(RegisterUI.this, "ע��ɹ�");
						RegisterUI.this.dispose();//�ر�ע��ҳ��
					} else {
						JOptionPane.showMessageDialog(RegisterUI.this, "ע��ʧ��");
					}
				} else {
					JOptionPane.showMessageDialog(RegisterUI.this, "���ӷ�����ʧ�ܣ�");
				}
			}
		});
	}

}
