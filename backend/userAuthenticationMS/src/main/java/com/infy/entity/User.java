package com.infy.entity;

import java.math.BigInteger;

import java.util.Date;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "user_id"),
                @UniqueConstraint(columnNames = "mobile_number"),
                @UniqueConstraint(columnNames = "user_name"),
        }
)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	public BigInteger userId;
	@Column(name="user_name")
	public String userName;
	@Column(name="email")
	public String email;
	public String password;
	public Date signupDate;
	public byte[] profilePic;
	@Column(name="mobile_number")
	public BigInteger mobileNumber;
	
	

	public byte[] getProfilePic(){
		return profilePic;
	}
	public void setProfilePic(byte[] profilePic){
		this.profilePic=profilePic;
	}
	
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
	public void setSignupDate(Date signupDate) {
		this.signupDate = new Date();
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", signupDate=" + signupDate + ", profilePic=" + profilePic + ", mobileNumber=" + mobileNumber
				+ "]";
	}
	
	
	

}
