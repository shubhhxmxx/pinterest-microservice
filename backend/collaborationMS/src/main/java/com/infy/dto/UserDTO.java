package com.infy.dto;

import java.math.BigInteger;
import java.util.Date;

public class UserDTO {
	public BigInteger userId;
	public String userName;
	public String email;
	public String password;
	public Date signupDate;
	public byte[] profilePic;
	public BigInteger mobileNumber;
	public BigInteger getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(BigInteger mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getSignupDate() {
		return signupDate;
	}
	public void setSignupDate(Date date) {
		this.signupDate = date;
	}
	public byte[] getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}
	
	
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", signupDate=" + signupDate + ", profilePic=" + profilePic + ", mobileNumber=" + mobileNumber
				+ "]";
	}
	
}
