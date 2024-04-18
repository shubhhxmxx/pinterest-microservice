package com.infy.service;

import java.math.BigInteger;

import java.util.List;

import com.infy.dto.CollaborationRequestDTO;
import com.infy.dto.FollowActivityDTO;
import com.infy.entity.Collaboration;
import com.infy.exception.CollaborationException;

public interface CollaborationService {
	
	//collaboration req methods
	public CollaborationRequestDTO createRequest(CollaborationRequestDTO collaborationRequestDTO) throws CollaborationException;
	public List<CollaborationRequestDTO> fetchCollaborationRequestByUserId(BigInteger userId) throws CollaborationException;
	public Collaboration acceptCollaborationRequest(CollaborationRequestDTO collaborationRequestDTO) throws CollaborationException;
	public void deleteCollaborationRequest(BigInteger requestId) throws CollaborationException;
	
	//Collaboration Methods
	
	public List<BigInteger> fetchFollowersByBoardId(BigInteger BoardId) throws CollaborationException;
	public List<BigInteger> fetchBoardsByFollowerId(BigInteger follwerId) throws CollaborationException;
	
	
	//follow acitvity methods
	
	public FollowActivityDTO addFollower(FollowActivityDTO followActivityDTO) throws CollaborationException;
	public FollowActivityDTO removeFollower(FollowActivityDTO followActivityDTO) throws CollaborationException;
	public List<BigInteger> followerList(FollowActivityDTO followActivityDTO) throws CollaborationException;
	public List<BigInteger> followingList(FollowActivityDTO followActivityDTO) throws CollaborationException;
	
	
	
	
}
