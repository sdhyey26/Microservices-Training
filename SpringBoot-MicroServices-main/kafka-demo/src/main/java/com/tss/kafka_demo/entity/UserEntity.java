package com.tss.kafka_demo.entity;

public class UserEntity {

		public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
		private String firstName;
	    @Override
		public String toString() {
			return "UserEntity [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
		}
		private String lastName;
	    private String email;
}
