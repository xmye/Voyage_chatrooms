package ServerItem;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import communication.ServerThread;
import communication.UserInfo;

public class ChatTools {
	private static List<SerThread> stList = new ArrayList(); // 保存处理线程的对象队列

	private ChatTools() {
	}

	/**
	 * 将一个客户对应的处理线程对象加到队列中
	 * @param ct
	 *            处理线程对象
	 */
	public static void addclient(SerThread ct) { // 通知新客户机上线
	// System.out.println("addcliet"+ct.getOwerUser().getname());
	// castMsg(ct.getOwerUser(), "我上线了，目前人数" + stList.size()); // 返回线程对象代表的用户对象
		stList.add(ct); // 添加线程对象到队列中
		// 刷新界面上的表格，以便显示信息
		SwingUtilities.updateComponentTreeUI(ServerUI.table_onlineuser);
	}

	/**
	 * 取得保存线程的队列
	 * @return
	 */
	public static List<SerThread> getAllThread() {
		return stList;
	}

	/** 给队列中某一用户发送消息 */
	public static void sendMsgToOne(int index, String msg) {
		stList.get(index).SendMsgToClient(msg);
	}

	/** 根据表中选中索引，取得处理线程对象对应的用户对象 */
	public static UserIn getUser(int index) {
		return stList.get(index).getOwerUser();
	}

	/**
	 * 移除队列中指定位置的处理线程对象，踢人
	 * @param index
	 */
	public static void removeClient(int index) {
		stList.remove(index).closeMe();
	}

	/**
	 * 移除队列中所有处理线程对象
	 * @param user
	 */
	public static void removeUser(UserIn user) {
		for (int i = 0; i < stList.size(); i++) {
			SerThread ct = stList.get(i);
			stList.remove(i);
			ct.closeMe();
			ct = null;
			castMsg(user, "我下线了");
		}
	}

	/** 移除所有线程对象 */
	public static void removeAllClient() {
		for (int i = 0; i < stList.size(); i++) {
			SerThread st = stList.get(i);
			st.SendMsgToClient("服务器即将关闭");
			st.closeMe();
			stList.remove(i);
			System.out.println("关闭了一个服务器");
			st = null;
		}
	}

	/**
	 * 将一条消息发送给所有客户机
	 * 
	 * @param sender
	 * @param msg
	 */
	public static void castMsg(UserIn sender, String msg) { // 将一条消息发送给其他客户机对象
		System.out.println(">>>>." + sender.getname());
		msg = sender.getname() + "：" + msg;
		System.out.println(stList.size() + "++++" + msg);
		for (int i = 0; i < stList.size(); i++) {
			SerThread st = stList.get(i);
			st.SendMsgToClient(msg); // 将消息发送给客户机
		}
	}
}
