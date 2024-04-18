package com.infy.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.infy.dto.BoardDTO;
import com.infy.dto.PinDTO;
import com.infy.exception.ContentException;
import com.infy.repo.PinRepository;

public interface ContentService {
	
	//creation
	public BoardDTO createBoard(BoardDTO boardDTO) throws ContentException;
	public PinDTO createPin(PinDTO pinDTO) throws ContentException;
	public BigInteger setPin(BigInteger pinId,MultipartFile pinUrl) throws ContentException,IOException;
	public BigInteger addUser(BigInteger userId) throws ContentException;
	
	//fetching
	public PinDTO getPinByPinId(BigInteger pinId) throws ContentException;
	public List<PinDTO> getPinsByUserId(BigInteger userId) throws ContentException;
	public List<PinDTO> getPinsByBoardId(BigInteger boardId) throws ContentException;
	public List<BoardDTO> getBoardsByUserId(BigInteger userId) throws ContentException;
	public List<PinDTO> getBoardByIsPrivate()throws ContentException;
	public PinDTO deletePinByPinId(BigInteger pinId)throws ContentException;
	
	
	
}
