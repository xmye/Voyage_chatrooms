package ServerItem;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import communication.ServerThread;
import communication.UserInfo;

public class ChatTools {
	private static List<SerThread> stList = new ArrayList(); // ���洦���̵߳Ķ������

	private ChatTools() {
	}

	/**
	 * ��һ���ͻ���Ӧ�Ĵ����̶߳���ӵ�������
	 * @param ct
	 *            �����̶߳���
	 */
	public static void addclient(SerThread ct) { // ֪ͨ�¿ͻ�������
	// System.out.println("addcliet"+ct.getOwerUser().getname());
	// castMsg(ct.getOwerUser(), "�������ˣ�Ŀǰ����" + stList.size()); // �����̶߳��������û�����
		stList.add(ct); // ����̶߳��󵽶�����
		// ˢ�½����ϵı���Ա���ʾ��Ϣ
		SwingUtilities.updateComponentTreeUI(ServerUI.table_onlineuser);
	}

	/**
	 * ȡ�ñ����̵߳Ķ���
	 * @return
	 */
	public static List<SerThread> getAllThread() {
		return stList;
	}

	/** ��������ĳһ�û�������Ϣ */
	public static void sendMsgToOne(int index, String msg) {
		stList.get(index).SendMsgToClient(msg);
	}

	/** ���ݱ���ѡ��������ȡ�ô����̶߳����Ӧ���û����� */
	public static UserIn getUser(int index) {
		return stList.get(index).getOwerUser();
	}

	/**
	 * �Ƴ�������ָ��λ�õĴ����̶߳�������
	 * @param index
	 */
	public static void removeClient(int index) {
		stList.remove(index).closeMe();
	}

	/**
	 * �Ƴ����������д����̶߳���
	 * @param user
	 */
	public static void removeUser(UserIn user) {
		for (int i = 0; i < stList.size(); i++) {
			SerThread ct = stList.get(i);
			stList.remove(i);
			ct.closeMe();
			ct = null;
			castMsg(user, "��������");
		}
	}

	/** �Ƴ������̶߳��� */
	public static void removeAllClient() {
		for (int i = 0; i < stList.size(); i++) {
			SerThread st = stList.get(i);
			st.SendMsgToClient("�����������ر�");
			st.closeMe();
			stList.remove(i);
			System.out.println("�ر���һ��������");
			st = null;
		}
	}

	/**
	 * ��һ����Ϣ���͸����пͻ���
	 * 
	 * @param sender
	 * @param msg
	 */
	public static void castMsg(UserIn sender, String msg) { // ��һ����Ϣ���͸������ͻ�������
		System.out.println(">>>>." + sender.getname());
		msg = sender.getname() + "��" + msg;
		System.out.println(stList.size() + "++++" + msg);
		for (int i = 0; i < stList.size(); i++) {
			SerThread st = stList.get(i);
			st.SendMsgToClient(msg); // ����Ϣ���͸��ͻ���
		}
	}
}
