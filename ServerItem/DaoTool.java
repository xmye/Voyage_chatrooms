package ServerItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Data Access Object ���ݷ��ʶ�����
 * �û���֤�����ݱ������޸�
 */
//�û���Ϣ�洢��
public class DaoTool {
	    /**����HashMap����ʼ����ΪĬ��ֵ��HashMapʵ����map�ӿڣ�����HashMap��Ķ������ڴ洢����
	     StringΪMap��������ͣ�UserInfoΪStringӳ�������* */
		public static Map<String,UserIn > UserDB=new HashMap<String,UserIn>();
		Random rd=new Random();
	
		//�洢���û�
		public static boolean join(String username,String password){
				if(UserDB.get(username)==null){
				//��̬�飬ģ�������ڴ��е��û�����
				//��������ʱ����̬���еĴ�����Զ�ִ�У���UserDB�д������
					UserIn user=new UserIn();
					user.setname(username);
					user.setPassword(password);
					UserDB.put(user.getname(), user);
					return true;
				}else{
					return false;
				}
		}
		
		//��֤�û���Ϣ
		public static  boolean check(UserIn user){
			if(UserDB.containsKey(user.getname())){			//�ж�HashMap���Ƿ����ĳ��user�������򷵻�true
				return true;
			}
			System.out.println("��֤ʧ��"+user.getname());
			return false;
		}
}
