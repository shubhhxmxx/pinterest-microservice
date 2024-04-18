package com.infy.controller;

import java.io.Console;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.infy.exception.UserException;
import com.infy.repo.UserRepository;
import com.infy.dto.UserDTO;
import com.infy.entity.User;
import com.infy.service.UserService;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(path ="")
public class Controller {
	
	private static final Log LOGGER=LogFactory.getLog(Controller.class);
	@Autowired
	UserService userService;
	
	RestTemplate restTemplate=new RestTemplate();
	
	
	@PostMapping(value="/register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) throws UserException{
		UserDTO serviceUserDTO=userService.registerUser(userDTO);
		LOGGER.info(serviceUserDTO);		
		restTemplate.postForObject("http://localhost:8766/createNewUser/"+serviceUserDTO.getUserId(),null,User.class);
		return new ResponseEntity<UserDTO>(serviceUserDTO,HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value="/test")
	public ResponseEntity<String> Test() {
		return new ResponseEntity<String>("Get Mapping Works",HttpStatus.OK);
	}
	
	@GetMapping(value="/user/{id}")
	public ResponseEntity<UserDTO> findByUserId(@PathVariable BigInteger id) throws UserException{
		UserDTO userDTO=userService.findUserById(id);
		return new ResponseEntity<UserDTO>(userDTO,HttpStatus.OK);
	}
	
	@GetMapping(value="/users")
	public ResponseEntity<List<UserDTO>> findAllUsers() throws UserException{
	    List<UserDTO> userList=userService.findUsers();
		return new ResponseEntity<>(userList,HttpStatus.OK);
	}
	
	@PostMapping(value="/login")
	public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) throws UserException{
		LOGGER.info(userDTO);
		UserDTO userDTO2=userService.login(userDTO);
		return new ResponseEntity<>(userDTO2,HttpStatus.OK);
	}
	
	@PostMapping(value="/forgotPassword")
	public ResponseEntity<UserDTO> validateMobileNumber(@RequestBody UserDTO userDTO) throws UserException{
		LOGGER.info(userService.validateEmail(userDTO.getEmail()));
		if(!userService.validateEmail(userDTO.getEmail())) {
			throw new UserException("USER.DOES_NOT_EXISTS");
		}
		UserDTO userDTO2=userService.findUserByEmail(userDTO.getEmail());
		LOGGER.info(userDTO2);
		LOGGER.info("ResponseEntity : "+userDTO2);
		if(userDTO.getMobileNumber().equals(userDTO2.getMobileNumber())) {
			userDTO2=userService.resetPassword(userDTO);
			LOGGER.info("Reset Password userDTO:"+userDTO2);
			return new ResponseEntity<UserDTO>(userDTO2,HttpStatus.OK);
		}
		else {
		throw new UserException("USER.WRONG_MOBILE_NUMBER");
			}		

		}

	
	
	@PostMapping(value="/storeProfilePic/{userId}")
	public ResponseEntity<?> storeProfilePic(@PathVariable BigInteger userId, @RequestParam("image") MultipartFile image) throws UserException, IllegalStateException, IOException{
		LOGGER.info(userId+"reaching storepin fron controller");
		BigInteger x=userService.setProfilePic(userId , image);
		Map<Integer , BigInteger> response = new TreeMap<>();
		response.put(1, x);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
