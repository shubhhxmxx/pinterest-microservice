package com.infy.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.infy.dto.BoardDTO;
import com.infy.dto.PinDTO;
import com.infy.exception.ContentException;
import com.infy.service.ContentService;

@RestController
@CrossOrigin
@RequestMapping(value = "")
public class Controller {
	
	private static final Log LOGGER= LogFactory.getLog(Controller.class);

	@Autowired
	ContentService contentService;
	
	@PostMapping(value = "createNewUser/{userId}")
	public ResponseEntity<?> createNewUser(@PathVariable BigInteger userId) throws ContentException{
		
		LOGGER.info("reached user Creation");
		BigInteger x= contentService.addUser(userId);
		Map<Integer , BigInteger> response = new TreeMap<>();
		response.put(1, x);
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	@PostMapping(value="/storePin/{pinId}")
	public ResponseEntity<?> storePin(@PathVariable BigInteger pinId, @RequestParam("image") MultipartFile image) throws ContentException, IllegalStateException, IOException{
		LOGGER.info(pinId+"reaching storepin fron controller");
		BigInteger x=contentService.setPin(pinId , image);
		
		Map<Integer , BigInteger> response = new TreeMap<>();
		response.put(1, x);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping(value = "/createPin")
	public ResponseEntity<PinDTO> createNewPin(@RequestBody PinDTO pinDTO) throws ContentException {
		LOGGER.error("createpin"+pinDTO);
		if(pinDTO.getIsPrivate()==null) {
			pinDTO.setIsPrivate(false);
		}
		pinDTO.setDateCreated(new Date());
		pinDTO=contentService.createPin(pinDTO);
	
		return new ResponseEntity<>(pinDTO,HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value="/createBoard")
	public ResponseEntity<BoardDTO> createNewBoard(@RequestBody BoardDTO boardDTO) throws ContentException{
		LOGGER.error(boardDTO);
		boardDTO=contentService.createBoard(boardDTO);
		
		
		return new ResponseEntity<BoardDTO>(boardDTO,HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value="/getPin/{id}")
	public ResponseEntity<PinDTO> getPinById(@PathVariable BigInteger id) throws ContentException{
		PinDTO pinDTO=contentService.getPinByPinId(id);
		return new ResponseEntity<PinDTO>(pinDTO,HttpStatus.OK);
	}
	
	@GetMapping(value="/getPins/user/{id}")
	public ResponseEntity<List<PinDTO>> getPinsByUserId(@PathVariable BigInteger id) throws ContentException{
		List<PinDTO> list=contentService.getPinsByUserId(id);
		return new ResponseEntity<List<PinDTO>>(list,HttpStatus.OK);
	}
	
	@GetMapping(value="/getPins/board/{id}")
	public ResponseEntity<List<PinDTO>> getPinsByBoardId(@PathVariable BigInteger id) throws ContentException{
		List<PinDTO> list=contentService.getPinsByBoardId(id);
		return new ResponseEntity<List<PinDTO>>(list,HttpStatus.OK);
	}
	
	@GetMapping(value="/getBoards/{userId}")
	public ResponseEntity<List<BoardDTO>> getBoardsByUserId(@PathVariable BigInteger userId) throws ContentException{
		LOGGER.info(userId);
		List<BoardDTO> list=contentService.getBoardsByUserId(userId);
		
		return new ResponseEntity<List<BoardDTO>>(list,HttpStatus.OK);
	}
	
	@GetMapping(value="/homeFeed")
	public ResponseEntity<List<PinDTO>> getPinsByIsPrivate() throws ContentException{
		LOGGER.info("reached Controller for getPinsByIsPrivate()");
		List<PinDTO> list=contentService.getBoardByIsPrivate();
		return new ResponseEntity<List<PinDTO>>(list,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete/{pinId}")
	public ResponseEntity<PinDTO> deletePinById(@PathVariable BigInteger pinId) throws ContentException{
		LOGGER.info("reached Controller for deletePinById()");
		
		PinDTO pinDTO=contentService.deletePinByPinId(pinId);
		return new ResponseEntity<PinDTO>(pinDTO,HttpStatus.OK);
		
		
	}

}
