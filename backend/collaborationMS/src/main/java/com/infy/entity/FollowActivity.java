package com.infy.entity;

import java.math.BigInteger;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FollowActivity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger followActivityId;
	private BigInteger follower;
	private BigInteger userId;
	
	
	public BigInteger getUserId() {
		return userId;
	}


	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}


	private Date dateCreated;


	public BigInteger getFollowActivityId() {
		return followActivityId;
	}


	public void setFollowActivityId(BigInteger followActivityId) {
		this.followActivityId = followActivityId;
	}


	public BigInteger getFollower() {
		return follower;
	}


	public void setFollower(BigInteger follower) {
		this.follower = follower;
	}


	

	public Date getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


	@Override
	public String toString() {
		return "FollowActivity [followActivityId=" + followActivityId + ", follower=" + follower + ", userId=" + userId
				+ ", dateCreated=" + dateCreated + "]";
	}

	

}
