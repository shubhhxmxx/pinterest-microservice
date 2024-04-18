package com.infy.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.infy.entity.Board;
import com.infy.entity.Pin;

public class BoardDTO {
	
	private BigInteger boardId;
	private List<PinDTO> pins;
	private Date dateCreated;
	private Date lastUpdated;
	private String boardName;
	private BigInteger userId;
	private byte[] boardPic;
	
	
	
	
	
	public void setBoardPic(byte[] boardPic) {
		this.boardPic = boardPic;
	}



	public BigInteger getUserId() {
		return userId;
	}



	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}



	public String getBoardName() {
		return boardName;
	}



	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}



	@Override
	public String toString() {
		return "BoardDTO [boardId=" + boardId + ", pins=" + pins + ", dateCreated=" + dateCreated + ", lastUpdated="
				+ lastUpdated + ", boardName=" + boardName + ", userId=" + userId + ", boardPic="
				+ Arrays.toString(boardPic) + "]";
	}



	public byte[] getBoardPic() {
		return boardPic;
	}



	public BigInteger getBoardId() {
		return boardId;
	}



	public void setBoardId(BigInteger boardId) {
		this.boardId = boardId;
	}



	public List<PinDTO> getPins() {
		return pins;
	}



	public void setPins(List<PinDTO> pins) {
		this.pins = pins;
	}



	public Date getDateCreated() {
		return dateCreated;
	}



	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}



	public Date getLastUpdated() {
		return lastUpdated;
	}



	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}



	public BoardDTO valueOf(Board board) {
		BoardDTO boardDTO=new BoardDTO();
		
		boardDTO.setBoardId(board.getBoardId());
		boardDTO.setDateCreated(board.getDateCreated());
		boardDTO.setLastUpdated(board.getLastUpdatedDate());
		boardDTO.setBoardName(board.getBoardName());
		boardDTO.setUserId(board.getUserId());
		boardDTO.setBoardPic(board.getBoardPic());
		//creating pinDTO list
		List<PinDTO> list=new ArrayList();
		
		board.getPins().stream().
		forEach((pin)->{
			PinDTO pinDTO=new PinDTO();
			pinDTO=pinDTO.valueOf(pin);
			list.add(pinDTO);
		});
		
		boardDTO.setPins(list);
		
		return boardDTO;
	}

	public Board entity(BoardDTO boardDTO) {
		Board board = new Board();
		
		board.setBoardId(boardDTO.getBoardId());
		board.setDateCreated(boardDTO.getDateCreated());
		board.setLastUpdatedDate(boardDTO.getLastUpdated());
		board.setBoardName(boardDTO.getBoardName());
		board.setUserId(boardDTO.getUserId());
		board.setBoardPic(boardDTO.getBoardPic());
		
		List<Pin> list=new ArrayList<>();
		
		if(boardDTO.getPins()!=null){
			System.out.println(87);
			boardDTO.getPins().stream()
			.forEach(pinDTO->{
				Pin pin =pinDTO.entity(pinDTO);
				list.add(pin);
			});
		}
		System.out.println(7);
		board.setPins(list);
		
		return board;
	}

}
