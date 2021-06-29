package dto;

import beans.model.User;

public class NewShopper extends User {
	private String password;

	public NewShopper() {}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}