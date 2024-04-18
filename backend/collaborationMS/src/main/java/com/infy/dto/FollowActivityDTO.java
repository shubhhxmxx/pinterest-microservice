package com.infy.dto;

import java.math.BigInteger;

import java.util.Date;

import com.infy.entity.FollowActivity;

public class FollowActivityDTO {
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

	
	public FollowActivityDTO value(FollowActivity followActivity) {
		FollowActivityDTO followActivityDTO=new FollowActivityDTO();
		followActivityDTO.setDateCreated(followActivity.getDateCreated());
		followActivityDTO.setFollowActivityId(followActivity.getFollowActivityId());
		followActivityDTO.setUserId(followActivity.getUserId());
		followActivityDTO.setFollower(followActivity.getFollower());
		return followActivityDTO;
	}
	
	public FollowActivity value(FollowActivityDTO followActivityDTO) {
		FollowActivity followActivity=new FollowActivity();
		followActivity.setDateCreated(followActivityDTO.getDateCreated());
		followActivity.setFollowActivityId(followActivityDTO.getFollowActivityId());
		followActivity.setUserId(followActivityDTO.getUserId());
		followActivity.setFollower(followActivityDTO.getFollower());
		return followActivity;
	}


	
	
	
}
