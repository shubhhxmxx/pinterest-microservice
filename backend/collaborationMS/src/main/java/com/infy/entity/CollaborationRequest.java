package com.infy.entity;

import java.math.BigInteger;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class CollaborationRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger requestId;
	
	private BigInteger followerId;

	private BigInteger userId;
	
	private Date dateCreated;
	
	private BigInteger boardId;
	
	@Override
	public String toString() {
		return "CollaborationRequest [requestId=" + requestId + ", followerId=" + followerId + ", userId=" + userId
				+ ", dateCreated=" + dateCreated + ", boardId=" + boardId + "]";
	}

	public BigInteger getRequestId() {
		return requestId;
	}

	public void setRequestId(BigInteger requestId) {
		this.requestId = requestId;
	}

	public BigInteger getFollowerId() {
		return followerId;
	}

	public void setFollowerId(BigInteger followerId) {
		this.followerId = followerId;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public BigInteger getboardId() {
		return boardId;
	}

	public void setboardId(BigInteger boardId) {
		this.boardId = boardId;
	}

	
	
	
	
	
}
