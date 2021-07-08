package dto;

import beans.model.User;

public class NewUser extends User {
	private String password;

	public NewUser() {}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}