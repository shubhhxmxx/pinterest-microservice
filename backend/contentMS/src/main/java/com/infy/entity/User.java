package com.infy.entity;

import java.math.BigInteger;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
	
	@Id
	private BigInteger userId;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<Pin> pins;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<Board> boards;

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public List<Pin> getPins() {
		return pins;
	}

	public void setPins(List<Pin> pins) {
		this.pins = pins;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", pins=" + pins + ", boards=" + boards + "]";
	}

	public List<Board> getBoards() {
		return boards;
	}

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}
	
	
}
