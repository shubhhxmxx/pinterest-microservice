package com.infy.service;


import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.infy.exception.UserException;
import com.infy.dto.UserDTO;
import com.infy.entity.User;
import com.infy.repo.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.transaction.UserTransaction;

@Service(value = "UserService")
@Transactional
public class UserServiceImpl implements UserService {

	private static final Log LOGGER=LogFactory.getLog(UserServiceImpl.class);
	@Autowired
	UserRepository userRepository;
	
	
	
	@Override
	public UserDTO findUserById(BigInteger id) throws UserException {
		Optional<User> optionalUser=userRepository.findByUserId(id);
		if(optionalUser.isEmpty()) {
			LOGGER.info("Error for controller , Cannot find error");
			throw new UserException("User Not Found.");
		}
		User user=optionalUser.get();
		UserDTO userDTO=new UserDTO();
		userDTO=userDTO.valueOf(user);
		return userDTO;
	}
	
	@Override
	public UserDTO registerUser(UserDTO userDTO) throws UserException {
		LOGGER.info(userDTO);
		if(userDTO.getUserId()!=null) {
			LOGGER.info("ID is provided , which is automatically generated,Wrong input Error");
			throw new UserException("USER.ID_EXISTS");
		}
		Optional<User> repoUserOptional=userRepository.findByEmail(userDTO.getEmail());
		if(repoUserOptional.isPresent()) {
			throw new UserException("USER.ALREADY_EXISTS");
		}
		User newUser=userDTO.entity(userDTO);
		User localUser=userRepository.save(newUser);
		userDTO.valueOf(newUser);
		userDTO.setUserId(localUser.getUserId());
		return userDTO;
	
	}

	@Override
	public List<UserDTO> findUsers() throws UserException {
		List<User> userList=userRepository.findAll();
		List<UserDTO> userDTOList=new ArrayList<>();
		userList.stream()
		.forEach(curr->{
			UserDTO userDTO=new UserDTO();
			userDTO=userDTO.valueOf(curr);
			userDTOList.add(userDTO);
		});
		return userDTOList;
	}

	@Override
	public UserDTO findUserByEmail(String email) throws UserException {
		Optional<User> optional=userRepository.findByEmail(email);
		if(optional.isEmpty()) {
			throw new UserException("USER.DOES_NOT_EXISTS");
		}
		User user=optional.get();
		LOGGER.info("UserService User:"+user);
		UserDTO userDTO=new UserDTO();
		userDTO=userDTO.valueOf(user);
		LOGGER.info("UserService UserDTO:"+userDTO);
		
		return userDTO;
	}

	@Override
	public UserDTO login(UserDTO userDTO) throws UserException {
		LOGGER.info("Reached user service");
		Optional<User> optionalRepoUser=userRepository.findByEmail(userDTO.getEmail());
		if(optionalRepoUser.isPresent()!=true) {
			throw new UserException("USER.DOES_NOT_EXISTS");
		}
		User repoUser=optionalRepoUser.get();
		LOGGER.info("repoUser:"+repoUser);
		LOGGER.info("Received Used"+userDTO);
		if(repoUser.getPassword().equals(userDTO.getPassword())) {
			userDTO=userDTO.valueOf(repoUser);
			LOGGER.info("User info for login:"+ userDTO);
			return userDTO;
		}else {
			throw new UserException("USER.WRONG_PASSWORD");
		}
		
	}

	@Override
	public UserDTO resetPassword(UserDTO userDTO) throws UserException {
	
		Optional<User> optionalUser=userRepository.findByEmail(userDTO.getEmail());
		LOGGER.info("Reset Password Optional: "+optionalUser);
		if(optionalUser.isEmpty()) {
			throw new UserException("USER.DOES_NOT_EXISTS");
		}
		User repoUser=optionalUser.get();
		if(userDTO.mobileNumber.equals(repoUser.getMobileNumber())) {
			userDTO=changePassword(userDTO);
			LOGGER.info("change password returned user:"+userDTO);
			return userDTO;
		}else {
			throw new UserException("USER.WRONG_MOBILE_NUMBER");
		}
	}

	@Override
	public boolean validateEmail(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

	@Override
	public UserDTO changePassword(UserDTO userDTO) {
		User user=userRepository.findByEmail(userDTO.getEmail()).get();
		LOGGER.info("changePassword:"+user);
		user.setPassword(userDTO.getPassword());
		user =userRepository.save(user);
		LOGGER.info("Post Saving:"+user);
		userDTO=userDTO.valueOf(user);
		return userDTO;
	}

	@Override
	public BigInteger setProfilePic(BigInteger userId, MultipartFile image) throws UserException, IOException {
		
		System.out.println("in service setProfilePic()");
		Optional<User> optional=userRepository.findByUserId(userId);
		User user= optional.orElseThrow(()-> new UserException("USER.NOT_FOUND"));
		user.setProfilePic(image.getBytes());
		userRepository.save(user);
		return user.getUserId();
	}

	
}
