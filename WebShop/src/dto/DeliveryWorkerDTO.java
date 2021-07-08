package dto;

import beans.enumerations.Gender;

public class DeliveryWorkerDTO {
	private String firstname;
	private String lastname;
	private String username;
	private String dateOfBirth;
	private Gender gender;

	public DeliveryWorkerDTO() {}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateofBirth) {
		this.dateOfBirth = dateofBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	

}