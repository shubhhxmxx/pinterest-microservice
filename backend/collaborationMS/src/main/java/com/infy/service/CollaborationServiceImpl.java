package com.infy.service;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.CollaborationRequestDTO;
import com.infy.dto.FollowActivityDTO;
import com.infy.entity.Collaboration;
import com.infy.entity.CollaborationRequest;
import com.infy.entity.FollowActivity;
import com.infy.exception.CollaborationException;
import com.infy.repo.CollaborationRepo;
import com.infy.repo.CollaborationReqRepo;
import com.infy.repo.FollowActivityRepo;

import jakarta.transaction.Transactional;

@Service(value = "CollaborationService")
@Transactional
public class CollaborationServiceImpl implements CollaborationService {

	@Autowired
	CollaborationRepo collaborationRepo;
	
	@Autowired
	CollaborationReqRepo collaborationReqRepo;
	
	@Autowired
	FollowActivityRepo followActivityRepo;
	
	private final static Log LOGGER=LogFactory.getLog(CollaborationServiceImpl.class);

	@Override
	public List<CollaborationRequestDTO> fetchCollaborationRequestByUserId(BigInteger userId) throws CollaborationException {
		List<CollaborationRequest> list= collaborationReqRepo.findAllByUserId(userId);
		List<CollaborationRequestDTO> response=new ArrayList<>();
		list.stream().forEach(
				(req)->{
					CollaborationRequestDTO collaborationRequestDTO=new CollaborationRequestDTO();
					collaborationRequestDTO=collaborationRequestDTO.valueOf(req);
					response.add(collaborationRequestDTO);
				}
				);
		return response;
	}	


	@Override
	public void deleteCollaborationRequest(BigInteger requestId) throws CollaborationException {
		collaborationReqRepo.deleteById(requestId);
		
	}

	@Override
	public List<BigInteger> fetchFollowersByBoardId(BigInteger boardId) throws CollaborationException {
		
		if(boardId==null) {
			throw new CollaborationException("Collaboration.NO_BOARD_ID");
		}

		List<Collaboration> list=collaborationRepo.findAllByBoardId(boardId);
		List<BigInteger> followerList=new ArrayList<>();
		list.stream().forEach(
			(req)->{
				followerList.add(req.getFollowerId());
				}
			);
		return followerList;
	}

	
	
	@Override
	public CollaborationRequestDTO createRequest(CollaborationRequestDTO collaborationRequestDTO) throws CollaborationException {

		
		CollaborationRequest optional=collaborationReqRepo.findByBoardIdAndFollowerId(collaborationRequestDTO.getboardId(),collaborationRequestDTO.getFollowerId());
		if(optional!=null) {
			throw new CollaborationException("Collaboration.REQUEST_ALREADY_PRESENT");
		}
		
		LOGGER.info("Reached collaborationService:" + collaborationRequestDTO);
		CollaborationRequest collaborationRequest=new CollaborationRequest();
		collaborationRequest=collaborationRequestDTO.entity(collaborationRequestDTO);
		collaborationRequest.setDateCreated(new Date());
		collaborationReqRepo.save(collaborationRequest);
		collaborationRequestDTO=collaborationRequestDTO.valueOf(collaborationRequest);
		return collaborationRequestDTO;
		
	}

	@Override
	public FollowActivityDTO addFollower(FollowActivityDTO followActivityDTO) throws CollaborationException {
		Optional<FollowActivity> f= followActivityRepo.findByFollowerAndUserId(followActivityDTO.getFollower(),followActivityDTO.getUserId());
		
		if(f.isPresent()) {
			LOGGER.info(f);
			return followActivityDTO;
		}
		
		FollowActivity followActivity=new FollowActivity();
		followActivity=followActivityDTO.value(followActivityDTO);
		followActivity.setDateCreated(new Date());
		followActivityRepo.save(followActivity);
		followActivityDTO=followActivityDTO.value(followActivity);
		return followActivityDTO;
	}


	@Override
	public FollowActivityDTO removeFollower(FollowActivityDTO followActivityDTO) throws CollaborationException {
		LOGGER.info("reached ServiceCLASS"+followActivityDTO);
		Optional<FollowActivity> optional = followActivityRepo.findByFollowerAndUserId(followActivityDTO.getFollower(),followActivityDTO.getUserId());
		FollowActivity followActivity=optional.get();
		LOGGER.info(followActivityDTO);
		followActivityRepo.deleteById(followActivity.getFollowActivityId());
		return followActivityDTO;
		
	}

	
	@Override
	public List<BigInteger> followerList(FollowActivityDTO followActivityDTO) throws CollaborationException {
		List<FollowActivity> list=followActivityRepo.findAllByUserId(followActivityDTO.getUserId());
		List<BigInteger> response=new ArrayList<>();
		list.stream().forEach(
				(curr)->{
					response.add(curr.getFollower());
				}
				);
		return response;
	}

	@Override
	public List<BigInteger> followingList(FollowActivityDTO followActivityDTO) throws CollaborationException {
		List<FollowActivity> list=followActivityRepo.findAllByFollower(followActivityDTO.getUserId());
		List<BigInteger> response=new ArrayList<>();
		list.stream().forEach(
				(curr)->{
					response.add(curr.getUserId());
				}
				);
		return response;
	
	}

	
	
	@Override
	public Collaboration acceptCollaborationRequest(CollaborationRequestDTO collaborationRequestDTO)
			throws CollaborationException {
		
		LOGGER.info("reached acceptCollaborationREQUES FROM SERVICE:"+collaborationRequestDTO);
		Collaboration collaboration=new Collaboration();
		collaboration.setboardId(collaborationRequestDTO.getboardId());
		LOGGER.info("setboardId"+collaboration);
		collaboration.setFollowerId(collaborationRequestDTO.getFollowerId());
		collaborationRepo.save(collaboration);
		return collaboration;
		
	}


	
	@Override
	public List<BigInteger> fetchBoardsByFollowerId(BigInteger followerId) throws CollaborationException {
		List<Collaboration> list=collaborationRepo.findAllByFollowerId(followerId);
		List<BigInteger> resp=new ArrayList<>();
		
		list.stream().forEach(
				(coll)->{
					resp.add(coll.getboardId());
				}
		);
		return resp;
	}
}
