package cn.mty.specialhelper.bean;

import java.io.Serializable;
import java.util.Date;

public class Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2918688129018249543L;
	
//	  room_id int(11) auto_increment primary key,
//    vc_room_no varchar(10),
//    room_create_time  timestamp,
//    n_status int(1),
//    vc_merchat_no varchar(10),£®…Ãº“±‡∫≈£©
//    num int(1),
//    vc_chatroom_serial_no varchar(64),»∫±‡∫≈
//    created_user  varchar(50),
//    created_time  date,
//    modified_user varchar(50),
//    modified_time date 
//    )default charset=utf8;

	private int roomId;
	private String vcRoomNo;
	private Date roomCreateTime;
	private int nStatus;
	private String vcMerchatNo;
	private int num;
	private String vcChatRoomSerialNo;
		
	private String createdUser;
	private Date createdTime;
	private String modifiedUser;
	private Date modifiedTime;
	

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Room(int roomId, String vcRoomNo, Date roomCreateTime, int nStatus, String vcMerchatNo, int num,
			String vcChatRoomSerialNo, String createdUser, Date createdTime, String modifiedUser, Date modifiedTime) {
		super();
		this.roomId = roomId;
		this.vcRoomNo = vcRoomNo;
		this.roomCreateTime = roomCreateTime;
		this.nStatus = nStatus;
		this.vcMerchatNo = vcMerchatNo;
		this.num = num;
		this.vcChatRoomSerialNo = vcChatRoomSerialNo;
		this.createdUser = createdUser;
		this.createdTime = createdTime;
		this.modifiedUser = modifiedUser;
		this.modifiedTime = modifiedTime;
	}


	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", vcRoomNo=" + vcRoomNo + ", roomCreateTime=" + roomCreateTime + ", nStatus="
				+ nStatus + ", vcMerchatNo=" + vcMerchatNo + ", num=" + num + ", vcChatRoomSerialNo="
				+ vcChatRoomSerialNo + ", createdUser=" + createdUser + ", createdTime=" + createdTime
				+ ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime + "]";
	}


	public int getRoomId() {
		return roomId;
	}


	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}


	public String getVcRoomNo() {
		return vcRoomNo;
	}


	public void setVcRoomNo(String vcRoomNo) {
		this.vcRoomNo = vcRoomNo;
	}


	public Date getRoomCreateTime() {
		return roomCreateTime;
	}


	public void setRoomCreateTime(Date roomCreateTime) {
		this.roomCreateTime = roomCreateTime;
	}


	public int getnStatus() {
		return nStatus;
	}


	public void setnStatus(int nStatus) {
		this.nStatus = nStatus;
	}


	public String getVcMerchatNo() {
		return vcMerchatNo;
	}


	public void setVcMerchatNo(String vcMerchatNo) {
		this.vcMerchatNo = vcMerchatNo;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getVcChatRoomSerialNo() {
		return vcChatRoomSerialNo;
	}


	public void setVcChatRoomSerialNo(String vcChatRoomSerialNo) {
		this.vcChatRoomSerialNo = vcChatRoomSerialNo;
	}


	public String getCreatedUser() {
		return createdUser;
	}


	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}


	public Date getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}


	public String getModifiedUser() {
		return modifiedUser;
	}


	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}


	public Date getModifiedTime() {
		return modifiedTime;
	}


	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((createdUser == null) ? 0 : createdUser.hashCode());
		result = prime * result + ((modifiedTime == null) ? 0 : modifiedTime.hashCode());
		result = prime * result + ((modifiedUser == null) ? 0 : modifiedUser.hashCode());
		result = prime * result + nStatus;
		result = prime * result + num;
		result = prime * result + ((roomCreateTime == null) ? 0 : roomCreateTime.hashCode());
		result = prime * result + roomId;
		result = prime * result + ((vcChatRoomSerialNo == null) ? 0 : vcChatRoomSerialNo.hashCode());
		result = prime * result + ((vcMerchatNo == null) ? 0 : vcMerchatNo.hashCode());
		result = prime * result + ((vcRoomNo == null) ? 0 : vcRoomNo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (createdTime == null) {
			if (other.createdTime != null)
				return false;
		} else if (!createdTime.equals(other.createdTime))
			return false;
		if (createdUser == null) {
			if (other.createdUser != null)
				return false;
		} else if (!createdUser.equals(other.createdUser))
			return false;
		if (modifiedTime == null) {
			if (other.modifiedTime != null)
				return false;
		} else if (!modifiedTime.equals(other.modifiedTime))
			return false;
		if (modifiedUser == null) {
			if (other.modifiedUser != null)
				return false;
		} else if (!modifiedUser.equals(other.modifiedUser))
			return false;
		if (nStatus != other.nStatus)
			return false;
		if (num != other.num)
			return false;
		if (roomCreateTime == null) {
			if (other.roomCreateTime != null)
				return false;
		} else if (!roomCreateTime.equals(other.roomCreateTime))
			return false;
		if (roomId != other.roomId)
			return false;
		if (vcChatRoomSerialNo == null) {
			if (other.vcChatRoomSerialNo != null)
				return false;
		} else if (!vcChatRoomSerialNo.equals(other.vcChatRoomSerialNo))
			return false;
		if (vcMerchatNo == null) {
			if (other.vcMerchatNo != null)
				return false;
		} else if (!vcMerchatNo.equals(other.vcMerchatNo))
			return false;
		if (vcRoomNo == null) {
			if (other.vcRoomNo != null)
				return false;
		} else if (!vcRoomNo.equals(other.vcRoomNo))
			return false;
		return true;
	}
	
	
	
}