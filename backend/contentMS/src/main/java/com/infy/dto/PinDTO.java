package com.infy.dto;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

import com.infy.entity.Pin;

public class PinDTO {

	private BigInteger pinId;
	private byte[] src;
	
	
	private Date dateCreated;
	private Boolean isPrivate;
	private BigInteger userId;
	private BigInteger boardId;
	
	
	public byte[] getSrc() {
		return src;
	}
	public void setSrc(byte[] src) {
		this.src = src;
	}

	public BigInteger getBoardId() {
		return boardId;
	}
	public void setBoardId(BigInteger boardId) {
		this.boardId = boardId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	private String description;
	private String title;
	
	
	public BigInteger getPinId() {
		return pinId;
	}
	public void setPinId(BigInteger pinId) {
		this.pinId = pinId;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Boolean getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	@Override
	public String toString() {
		return "PinDTO [pinId=" + pinId + ", src=" + Arrays.toString(src) + ", dateCreated=" + dateCreated
				+ ", isPrivate=" + isPrivate + ", userId=" + userId + ", boardId=" + boardId + ", description="
				+ description + ", title=" + title + "]";
	}
	
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public PinDTO valueOf(Pin pin) {
		
		PinDTO pinDTO=new PinDTO();
		
		pinDTO.setDateCreated(pin.getDateCreated());
		pinDTO.setIsPrivate(pin.getIsPrivate());
		pinDTO.setPinId(pin.getPinId());
		pinDTO.setSrc(pin.getSrc());
		pinDTO.setTitle(pin.getTitle());
		pinDTO.setDescription(pin.getDescription());
		pinDTO.setBoardId(pin.getBoardId());
		pinDTO.setUserId(pin.getUserId());
		
		System.out.println(5.2);
		return pinDTO;
	}
	
	public Pin entity(PinDTO pinDTO) {
		Pin pin = new Pin();
		
		pin.setDateCreated(pinDTO.getDateCreated());
		pin.setIsPrivate(pinDTO.getIsPrivate());
		pin.setPinId(pinDTO.getPinId());
		pin.setSrc(pinDTO.getSrc());
		pin.setTitle(pinDTO.getTitle());
		pin.setDescription(pinDTO.getDescription());
		pin.setBoardId(pinDTO.getBoardId());
		pin.setUserId(pinDTO.getUserId());
		
		return pin;
	}
	
}	
