package com.infy.controller;

import java.math.BigInteger;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infy.dto.CollaborationRequestDTO;
import com.infy.dto.FollowActivityDTO;
import com.infy.dto.UserDTO;
import com.infy.entity.Collaboration;
import com.infy.exception.CollaborationException;
import com.infy.service.CollaborationService;

@RequestMapping(value = "")
@RestController
@CrossOrigin
public class Controller {
	
	@Autowired
	CollaborationService collaborationService;
	

	RestTemplate restTemplate=new RestTemplate();

	private final static Log LOGGER=LogFactory.getLog(Controller.class);

	@PostMapping(value="/acceptCollaboration/{boardId}/{followerId}")
	public ResponseEntity<Collaboration> acceptCollaboration(@PathVariable BigInteger boardId,@PathVariable BigInteger followerId) throws CollaborationException{
		
		CollaborationRequestDTO collaborationRequestDTO=new CollaborationRequestDTO();
		collaborationRequestDTO.setboardId(boardId);
		collaborationRequestDTO.setFollowerId(followerId);
		
		LOGGER.info("reached accept Collaboration:"+collaborationRequestDTO);
		
		Collaboration collaboration= collaborationService.acceptCollaborationRequest(collaborationRequestDTO);
		return new ResponseEntity<>(collaboration,HttpStatus.OK);
	}
	
	
	@PostMapping(value="/sendCollaborationRequest/{boardId}/{followerId}/{userId}")
	public ResponseEntity<CollaborationRequestDTO> createCollaborationRequest(@PathVariable BigInteger boardId,@PathVariable BigInteger followerId ,@PathVariable BigInteger userId) throws CollaborationException{
	
		CollaborationRequestDTO collaborationRequestDTO=new CollaborationRequestDTO();
		collaborationRequestDTO.setboardId(boardId);
		collaborationRequestDTO.setFollowerId(followerId);
		collaborationRequestDTO.setUserId(userId);
		
		collaborationRequestDTO=collaborationService.createRequest(collaborationRequestDTO);
		return new ResponseEntity<>(collaborationRequestDTO,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/deleteCollaborationRequest/{requestId}")
	public ResponseEntity<CollaborationRequestDTO> deleteCollaborationRequest(@PathVariable BigInteger requestId) throws CollaborationException{
		
		CollaborationRequestDTO collaborationRequestDTO=new CollaborationRequestDTO();
		collaborationRequestDTO.setRequestId(requestId);
	
		
		LOGGER.info("reached delete CollaborationReq:"+collaborationRequestDTO);
		collaborationService.deleteCollaborationRequest(collaborationRequestDTO.getRequestId());
		return new ResponseEntity<>(collaborationRequestDTO,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/removeFollower/{userId}/{followerId}")
	public ResponseEntity<FollowActivityDTO> removeFollower(@PathVariable BigInteger userId ,@PathVariable BigInteger followerId)throws CollaborationException{
		FollowActivityDTO followActivityDTO=new FollowActivityDTO();
		followActivityDTO.setFollower(followerId);
		followActivityDTO.setUserId(userId);
	
		
		LOGGER.info("reached removeFollower:"+followActivityDTO);
		collaborationService.removeFollower(followActivityDTO);
		return new ResponseEntity<FollowActivityDTO>(followActivityDTO,HttpStatus.OK);
	}
	
	@GetMapping(value="/getBoards/{userId}")
	public ResponseEntity<List<BigInteger>> getUserBoards(@PathVariable BigInteger userId) throws CollaborationException{
		
		LOGGER.error("reached getBoard by userID");
		List<BigInteger> list=collaborationService.fetchBoardsByFollowerId(userId);
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	
	}

	
	@GetMapping(value="/getPendingRequests/{userId}")
	public ResponseEntity<List<CollaborationRequestDTO>> getPendingRequests(@PathVariable BigInteger userId) throws CollaborationException{
		CollaborationRequestDTO collaborationRequestDTO=new CollaborationRequestDTO();
		collaborationRequestDTO.setUserId(userId);
		
		
		LOGGER.info("reached getPendingRequests :"+collaborationRequestDTO);
		
		List<CollaborationRequestDTO> collaborationRequestListDTO=collaborationService.fetchCollaborationRequestByUserId(collaborationRequestDTO.getUserId());
		return new ResponseEntity<>(collaborationRequestListDTO,HttpStatus.OK);
	
	}
	
	@GetMapping(value="/addFollower/{userId}/{followerId}")
	public ResponseEntity<FollowActivityDTO> addFollower(@PathVariable BigInteger userId ,@PathVariable BigInteger followerId) throws CollaborationException{
		LOGGER.error("reaching add follower");
		FollowActivityDTO followActivityDTO=new FollowActivityDTO();
		followActivityDTO.setFollower(followerId);
		followActivityDTO.setUserId(userId);
	
		
		LOGGER.info("reached addFollower: "+ followActivityDTO);
		followActivityDTO=collaborationService.addFollower(followActivityDTO);
		return new ResponseEntity<>(followActivityDTO,HttpStatus.OK);
	
	}
	
	@GetMapping(value="/getCollaborators/{boardId}")
	public ResponseEntity<List<BigInteger>> getCollaborators(@PathVariable BigInteger boardId) throws CollaborationException{
		CollaborationRequestDTO collaborationRequestDTO=new CollaborationRequestDTO();
		collaborationRequestDTO.setboardId(boardId);
	
		
		LOGGER.error(collaborationRequestDTO);
		List<BigInteger> list=collaborationService.fetchFollowersByBoardId(collaborationRequestDTO.getboardId());
		
		
		return new ResponseEntity<List<BigInteger>>(list,HttpStatus.OK);
	}

	@GetMapping(value= "/getFollowers/{userId}")
	public ResponseEntity<List<?>> getFollowers(@PathVariable BigInteger userId) throws CollaborationException{
		
		FollowActivityDTO followActivityDTO=new FollowActivityDTO();
		followActivityDTO.setUserId(userId);
	
		
		LOGGER.info("reached getFollowers : "+ followActivityDTO);
		List<BigInteger> list=collaborationService.followerList(followActivityDTO);
		List<UserDTO> resp=new ArrayList<>();
		String url="http://localhost:8765/user/";
		list.stream().forEach(
			(id)->{
				UserDTO userDTO= restTemplate.getForObject(url+id,UserDTO.class);
				resp.add(userDTO);

			}
		);
		return new ResponseEntity<>(resp,HttpStatus.OK);
	
	}
	
	@GetMapping(value= "/getFollowing/{userId}")
	public ResponseEntity<List<UserDTO>> getFollowing(@PathVariable BigInteger userId) throws CollaborationException{
		FollowActivityDTO followActivityDTO=new FollowActivityDTO();
		followActivityDTO.setUserId(userId);
	
		
		LOGGER.info("reached getFollowers : "+ followActivityDTO);
		List<BigInteger> list=collaborationService.followingList(followActivityDTO);
		List<UserDTO> resp=new ArrayList<>();
		String url="http://localhost:8765/user/";
		list.stream().forEach(
			(id)->{
				UserDTO userDTO= restTemplate.getForObject(url+id,UserDTO.class);
				resp.add(userDTO);

			}
		);
		return new ResponseEntity<>(resp,HttpStatus.OK);

		
	
	}
	
}
