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
	 * 取得输入文本框内的内容，发送服务器
	 */
	public void actionPerformed(ActionEvent e) {
		String msg = jt_input.getText();    //取得文本框内的内容
//		jt_show.append(msg+"\n");		//追加到显示内容的文本区域
		
		if(e.getActionCommand().equals("发送")){
			sendMsg(4+msg);
		}else {
			sendMsg(4+msg);					//将信息发送给服务器
		}
	}
	/** 发送消息到服务器 */
	public void sendMsg(String msg) {
		try {
			msg += "\r\n";
			os.write(msg.getBytes());			//为什么使用客户端的输出流能向服务器写消息
			this.os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
