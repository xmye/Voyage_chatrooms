package ServerItem;

/**
 * 用户信息，创建对象即为用户
 * @author thinkpad
 *
 */
public class UserIn {
	private String name;
	private String password;
	private String loginTime;
	private String address;

	public UserIn() {
	}

	public UserIn(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLogintime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
