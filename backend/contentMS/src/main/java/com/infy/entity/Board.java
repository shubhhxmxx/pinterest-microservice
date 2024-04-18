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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;


@Entity
public class Board {
		
		
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE )
		private BigInteger boardId;

		@OneToMany
		@JoinColumn(name="board_id")
		private List<Pin> pins;
		
		@Column(name="user_id")
		private BigInteger userId;
		
		private Date dateCreated;
		
		private Date lastUpdatedDate;
		
		private String boardName;
	
		private byte[] boardPic;
		
		
				


		@Override
		public String toString() {
			return "Board [boardId=" + boardId + ", pins=" + pins + ", userId=" + userId + ", dateCreated="
					+ dateCreated + ", lastUpdatedDate=" + lastUpdatedDate + ", boardName=" + boardName + ", boardPic="
					+ Arrays.toString(boardPic) + "]";
		}

		public byte[] getBoardPic() {
			return boardPic;
		}

		public void setBoardPic(byte[] boardPic) {
			this.boardPic = boardPic;
		}

		public String getBoardName() {
			return boardName;
		}

		public void setBoardName(String boardName) {
			this.boardName = boardName;
		}



		public BigInteger getUserId() {
			return userId;
		}

		public void setUserId(BigInteger userId) {
			this.userId = userId;
		}

		public BigInteger getBoardId() {
			return boardId;
		}

		public void setBoardId(BigInteger boardId) {
			this.boardId = boardId;
		}

		public List<Pin> getPins() {
			return pins;
		}

		public void setPins(List<Pin> pins) {
			this.pins = pins;
		}

		public Date getDateCreated() {
			return dateCreated;
		}

		public void setDateCreated(Date dateCreated) {
			this.dateCreated = dateCreated;
		}

		public Date getLastUpdatedDate() {
			return lastUpdatedDate;
		}

		public void setLastUpdatedDate(Date lastUpdatedDate) {
			this.lastUpdatedDate = lastUpdatedDate;
		}

		
		
		
		
}
