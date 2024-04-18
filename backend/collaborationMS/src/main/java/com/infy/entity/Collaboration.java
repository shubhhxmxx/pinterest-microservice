package com.infy.entity;

import java.math.BigInteger;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Collaboration {
	

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private BigInteger collaborationId;
	
	private BigInteger followerId;
	private BigInteger boardId;
	public BigInteger getCollaborationId() {
		return collaborationId;
	}
	public void setCollaborationId(BigInteger collaborationId) {
		this.collaborationId = collaborationId;
	}
	public BigInteger getFollowerId() {
		return followerId;
	}
	public void setFollowerId(BigInteger followerId) {
		this.followerId = followerId;
	}
	public BigInteger getboardId() {
		return boardId;
	}
	public void setboardId(BigInteger boardId) {
		this.boardId = boardId;
	}
	@Override
	public String toString() {
		return "Collaboration [collaborationId=" + collaborationId + ", followerId=" + followerId + ", boardId="
				+ boardId + "]";
	}

	
	
	
}
