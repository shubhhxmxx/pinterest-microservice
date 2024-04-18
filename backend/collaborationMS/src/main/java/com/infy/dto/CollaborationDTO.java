package com.infy.dto;

import java.math.BigInteger;

import com.infy.entity.Collaboration;

public class CollaborationDTO {
	
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
	
	public CollaborationDTO valueOf(Collaboration collaboration) {
		CollaborationDTO collaborationDTO=new CollaborationDTO();
		collaborationDTO.setboardId(collaboration.getboardId());
		collaborationDTO.setCollaborationId(collaboration.getCollaborationId());
		collaborationDTO.setFollowerId(collaboration.getFollowerId());
		return collaborationDTO;
	}
	public Collaboration entity(CollaborationDTO collaborationDTO) {
		Collaboration collaboration=new Collaboration();
		collaboration.setboardId(collaborationDTO.getboardId());
		collaboration.setCollaborationId(collaborationDTO.getCollaborationId());
		collaboration.setFollowerId(collaborationDTO.getFollowerId());
		return collaboration;
	}

	
	

}
