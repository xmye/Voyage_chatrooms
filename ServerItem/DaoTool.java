package ServerItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Data Access Object 数据访问对象类
 * 用户验证，数据保存与修改
 */
//用户信息存储库
public class DaoTool {
	    /**创建HashMap，初始容量为默认值，HashMap实现了map接口，创建HashMap类的对象用于存储数据
	     String为Map保存的类型，UserInfo为String映射的类型* */
		public static Map<String,UserIn > UserDB=new HashMap<String,UserIn>();
		Random rd=new Random();
	
		//存储新用户
		public static boolean join(String username,String password){
				if(UserDB.get(username)==null){
				//静态块，模拟生成内存中的用户数据
				//程序启动时，静态块中的代码会自动执行，向UserDB中存放数据
					UserIn user=new UserIn();
					user.setname(username);
					user.setPassword(password);
					UserDB.put(user.getname(), user);
					return true;
				}else{
					return false;
				}
		}
		
		//验证用户信息
		public static  boolean check(UserIn user){
			if(UserDB.containsKey(user.getname())){			//判断HashMap中是否存在某个user，存在则返回true
				return true;
			}
			System.out.println("认证失败"+user.getname());
			return false;
		}
}
