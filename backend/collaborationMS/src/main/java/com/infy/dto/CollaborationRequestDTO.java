package com.infy.dto;

import java.math.BigInteger;
import java.util.Date;

import com.infy.entity.CollaborationRequest;

public class CollaborationRequestDTO {

	private BigInteger requestId;
	
	private BigInteger followerId;

	private BigInteger userId;
	
	private Date dateCreated;
	
	@Override
	public String toString() {
		return "CollaborationRequestDTO [requestId=" + requestId + ", followerId=" + followerId + ", userId=" + userId
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

	private BigInteger boardId;

	public CollaborationRequest entity(CollaborationRequestDTO collaborationRequestDTO) {
		CollaborationRequest collaborationRequest=new CollaborationRequest();
		collaborationRequest.setboardId(collaborationRequestDTO.getboardId());
		collaborationRequest.setDateCreated(collaborationRequestDTO.getDateCreated());
		collaborationRequest.setFollowerId(collaborationRequestDTO.getFollowerId());
		collaborationRequest.setRequestId(collaborationRequestDTO.getRequestId());
		collaborationRequest.setUserId(collaborationRequestDTO.getUserId());
		return collaborationRequest;
	}
	
	
	public CollaborationRequestDTO valueOf(CollaborationRequest collaborationRequest) {
		CollaborationRequestDTO collaborationRequestDTO=new CollaborationRequestDTO();
		collaborationRequestDTO.setboardId(collaborationRequest.getboardId());
		collaborationRequestDTO.setDateCreated(collaborationRequest.getDateCreated());
		collaborationRequestDTO.setFollowerId(collaborationRequest.getFollowerId());
		collaborationRequestDTO.setRequestId(collaborationRequest.getRequestId());
		collaborationRequestDTO.setUserId(collaborationRequest.getUserId());
		return collaborationRequestDTO;
	}
	
}
