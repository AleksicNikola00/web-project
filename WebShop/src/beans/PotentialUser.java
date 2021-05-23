package beans;

public class PotentialUser {

	private String username;
	private String password;
	private Role role;
	
	public PotentialUser() {
		
	}
	
	public PotentialUser(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public PotentialUser(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Role getRole() {
		return role;
	}
	
	public String format() {
		String out = "";
		out += this.username + ":" + this.password + ":" + this.role;
		return out;
	}
}
