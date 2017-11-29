package ClientItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JTextArea;

public class SendLister implements ActionListener{

	private OutputStream os;
	private JTextArea jt_input;
	private JTextArea jt_show;
	private Socket client;
	
	public SendLister(Socket client,JTextArea jt_input,JTextArea jt_show){
		this.client=client;
		this.jt_input=jt_input;
		this.jt_show=jt_show;
		try {
			os=client.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ȡ�������ı����ڵ����ݣ����ͷ�����
	 */
	public void actionPerformed(ActionEvent e) {
		String msg = jt_input.getText();    //ȡ���ı����ڵ�����
//		jt_show.append(msg+"\n");		//׷�ӵ���ʾ���ݵ��ı�����
		
		if(e.getActionCommand().equals("����")){
			sendMsg(4+msg);
		}else {
			sendMsg(4+msg);					//����Ϣ���͸�������
		}
	}
	/** ������Ϣ�������� */
	public void sendMsg(String msg) {
		try {
			msg += "\r\n";
			os.write(msg.getBytes());			//Ϊʲôʹ�ÿͻ��˵���������������д��Ϣ
			this.os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
