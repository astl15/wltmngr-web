package ro.astl.wltmngr.model;

public class RegisteredUserDTO {
	
	private String username;
	private String password;
	private String repass;
	private int role;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepass() {
		return repass;
	}
	public void setRepass(String repass) {
		this.repass = repass;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}

}
