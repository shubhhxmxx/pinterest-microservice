package com.infy.service;

import java.io.Console;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.infy.controller.Controller;
import com.infy.dto.BoardDTO;
import com.infy.dto.PinDTO;
import com.infy.entity.Board;
import com.infy.entity.Pin;
import com.infy.entity.User;
import com.infy.exception.ContentException;
import com.infy.repo.BoardRepository;
import com.infy.repo.PinRepository;
import com.infy.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service(value = "ContentService")
@Transactional
public class ContentServiceImpl implements ContentService{
	
	private static final Log LOGGER= LogFactory.getLog(ContentService.class);
	
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	PinRepository pinRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	
	@Override
	public PinDTO createPin(PinDTO pinDTO) throws ContentException {
		if(pinDTO.getUserId()==null) {
			throw new ContentException("Content.NOT_LOGGED_IN");
		}
		Pin pin = pinDTO.entity(pinDTO);
		LOGGER.error(pin);
		if(pinDTO.getBoardId()!=null) {
			Board optional=boardRepository.getById(pinDTO.getBoardId());
			LOGGER.info(optional);
			if(optional.getBoardPic()==null) {
				optional.setBoardPic(pinDTO.getSrc());
				boardRepository.save(optional);
			}
			optional.setLastUpdatedDate(new Date());
		} 
		
		
		pinRepository.save(pin);	
		pinDTO.setPinId(pin.getPinId());
		
		
		return pinDTO;
	}
		
	@Override
	public BigInteger setPin(BigInteger pinId, MultipartFile pinUrl) throws ContentException, IOException {
		System.out.println("in service setpin()");
		Optional<Pin> optional=pinRepository.findById(pinId);
		Pin pin= optional.orElseThrow(()-> new ContentException("Content.PIN_NOT_FOUND"));
		pin.setSrc(pinUrl.getBytes());
		LOGGER.info("FOR BOARD ID"+pin.getBoardId());
		if(pin.getBoardId()!=null){
				Board board=boardRepository.findById(pin.getBoardId()).get();
				board.setBoardPic(pinUrl.getBytes());
				boardRepository.save(board);
				LOGGER.error("REACHED BOARDPIC");
				LOGGER.error(board.getBoardPic());
			
		}
		pinRepository.save(pin);
		return pin.getPinId();
		
	}


	
	
	public BoardDTO createBoard(BoardDTO boardDTO)  throws ContentException {
		if(boardDTO.getUserId()==null) {
			throw new ContentException("Content.NOT_LOGGED_IN");
		}
		
		Board board=new Board();
		board =boardDTO.entity(boardDTO);
		
		
		boardRepository.save(board);	
		boardDTO.setBoardId(board.getBoardId());
		
		return boardDTO;
	}


	@Override
	public PinDTO getPinByPinId(BigInteger pinId) throws ContentException{
		Optional<Pin> optionalPin=pinRepository.findById(pinId);
		if(optionalPin.isEmpty()) {
			throw new ContentException("Content.PIN_NOT_FOUND");
		}
		PinDTO pinDTO=new PinDTO();
		pinDTO=pinDTO.valueOf(optionalPin.get());
		return pinDTO;
	}

	@Override
	public List<PinDTO> getPinsByUserId(BigInteger userId) throws ContentException {
		List<Pin> pinList=pinRepository.findAllByUserId(userId);
		if(pinList.isEmpty()) {
			throw new ContentException("Content.USER_PINS_NOT_FOUND");
		}
		List<PinDTO> list=new ArrayList<>();
		
		pinList.stream().forEach((pin)->{
			PinDTO pinDTO=new PinDTO();
			pinDTO=pinDTO.valueOf(pin);
			list.add(pinDTO);
		});
		return list;
	}

	@Override
	public List<PinDTO> getPinsByBoardId(BigInteger boardId) throws ContentException {
		List<Pin> pinList=pinRepository.findAllByBoardId(boardId);
		if(pinList.isEmpty()) {
			throw new ContentException("Content.BOARD_EMPTY");
		}
		List<PinDTO> list=new ArrayList<>();
		
		pinList.stream().forEach((pin)->{
			PinDTO pinDTO=new PinDTO();
			pinDTO=pinDTO.valueOf(pin);
			list.add(pinDTO);
		});
		return list;
	
	}

	@Override
	public List<BoardDTO> getBoardsByUserId(BigInteger userId) throws ContentException {
		List<Board> boardList=boardRepository.findAllByUserId(userId);
		if(boardList.isEmpty()) {
			throw new ContentException("Content.NO_BOARDS_FOUND");
		}
		List<BoardDTO> list=new ArrayList<>();
		
		boardList.stream().forEach(board->{
			BoardDTO boardDTO=new BoardDTO();
			boardDTO=boardDTO.valueOf(board);
			list.add(boardDTO);
		});
		return list;
	}



	@Override
	public List<PinDTO> getBoardByIsPrivate() throws ContentException {
		List<Pin> list=pinRepository.findAllByIsPrivate(false);
		
		List<PinDTO> responseList=new ArrayList<>();
		list.stream().forEach(
				(pin)->{
					PinDTO pinDTO=new PinDTO();
					pinDTO=pinDTO.valueOf(pin);
					responseList.add(pinDTO);
				}
		);
		return responseList;
	}




	@Override
	public PinDTO deletePinByPinId(BigInteger pinId) throws ContentException {
		Optional<Pin> optional=pinRepository.findById(pinId);
		
		if(optional.isEmpty()) {
			throw new ContentException("Content.PIN_NOT_FOUND");
		}
		
		Pin pin=optional.get();
		PinDTO pinDTO=new PinDTO();
		pinDTO=pinDTO.valueOf(pin);
		pinRepository.deleteById(pinId);
		
		return pinDTO;
		
	}




	@Override
	public BigInteger addUser(BigInteger userId) throws ContentException {
		
		User user=new User();
		user.setUserId(userId);
		
		User user2=userRepository.save(user);
		return userId;
	}		
}
