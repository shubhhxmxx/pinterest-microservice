package com.infy.entity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger pinId;

	private byte[] src;
	
	private Date dateCreated;
	
	private Boolean isPrivate;
	
	private String description;
	private String title;
	
	
	@Column(name="board_id")
	private BigInteger boardId;
	
	
	@Column(name="user_id")
	private BigInteger userId;

	public BigInteger getBoardId() {
		return boardId;
	}

	public void setBoardId(BigInteger boardId) {
		this.boardId = boardId;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
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

	public Boolean getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public BigInteger getPinId() {
		return pinId;
	}

	public void setPinId(BigInteger pinId) {
		this.pinId = pinId;
	}



	public byte[] getSrc() {
		return src;
	}

	public void setSrc(byte[] src) {
		this.src = src;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "Pin [pinId=" + pinId + ", src=" + Arrays.toString(src) + ", dateCreated=" + dateCreated + ", isPrivate="
				+ isPrivate + ", description=" + description + ", title=" + title + ", boardId=" + boardId + ", userId="
				+ userId + "]";
	}
	
	
}
