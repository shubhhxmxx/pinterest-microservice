
package com.infy.service;

import java.io.IOException;
import java.math.BigInteger;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.infy.exception.UserException;
import com.infy.dto.UserDTO;

public interface UserService {

	public UserDTO findUserById(BigInteger id) throws UserException;

	public UserDTO registerUser(UserDTO userDTO) throws UserException;

	public UserDTO findUserByEmail(String email) throws UserException;

	public List<UserDTO> findUsers() throws UserException;

	public UserDTO login(UserDTO userDTO) throws UserException;

	public UserDTO resetPassword(UserDTO userDTO) throws UserException;

	public boolean validateEmail(String email);

	public UserDTO changePassword(UserDTO userDTO);

	public BigInteger setProfilePic(BigInteger userId,MultipartFile image) throws UserException,IOException;
}
