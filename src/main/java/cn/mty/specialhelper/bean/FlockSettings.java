package cn.mty.specialhelper.bean;

import java.io.Serializable;
import java.util.Date;

public class FlockSettings implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4903178327469384628L;
    private int id;
	private int gameType;
    private int gameSubType;
    private int gameBipiao;
    private int gameCount;
    private int payType;
    private int ipLimit;
    private int gpsLimit;
    private int lianzhuangAddGameCount;
    private int mustZiMo;
    private String flockId;
    
    
    private String createdUser;
	private Date createdTime;
	private String modifiedUser;
	private Date modifiedTime;
	public FlockSettings() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FlockSettings(int id, int gameType, int gameSubType, int gameBipiao, int gameCount, int payType, int ipLimit,
			int gpsLimit, int lianzhuangAddGameCount, int mustZiMo, String flockId, String createdUser,
			Date createdTime, String modifiedUser, Date modifiedTime) {
		super();
		this.id = id;
		this.gameType = gameType;
		this.gameSubType = gameSubType;
		this.gameBipiao = gameBipiao;
		this.gameCount = gameCount;
		this.payType = payType;
		this.ipLimit = ipLimit;
		this.gpsLimit = gpsLimit;
		this.lianzhuangAddGameCount = lianzhuangAddGameCount;
		this.mustZiMo = mustZiMo;
		this.flockId = flockId;
		this.createdUser = createdUser;
		this.createdTime = createdTime;
		this.modifiedUser = modifiedUser;
		this.modifiedTime = modifiedTime;
	}
	@Override
	public String toString() {
		return "FlockSettings [id=" + id + ", gameType=" + gameType + ", gameSubType=" + gameSubType + ", gameBipiao="
				+ gameBipiao + ", gameCount=" + gameCount + ", payType=" + payType + ", ipLimit=" + ipLimit
				+ ", gpsLimit=" + gpsLimit + ", lianzhuangAddGameCount=" + lianzhuangAddGameCount + ", mustZiMo="
				+ mustZiMo + ", flockId=" + flockId + ", createdUser=" + createdUser + ", createdTime=" + createdTime
				+ ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGameType() {
		return gameType;
	}
	public void setGameType(int gameType) {
		this.gameType = gameType;
	}
	public int getGameSubType() {
		return gameSubType;
	}
	public void setGameSubType(int gameSubType) {
		this.gameSubType = gameSubType;
	}
	public int getGameBipiao() {
		return gameBipiao;
	}
	public void setGameBipiao(int gameBipiao) {
		this.gameBipiao = gameBipiao;
	}
	public int getGameCount() {
		return gameCount;
	}
	public void setGameCount(int gameCount) {
		this.gameCount = gameCount;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public int getIpLimit() {
		return ipLimit;
	}
	public void setIpLimit(int ipLimit) {
		this.ipLimit = ipLimit;
	}
	public int getGpsLimit() {
		return gpsLimit;
	}
	public void setGpsLimit(int gpsLimit) {
		this.gpsLimit = gpsLimit;
	}
	public int getLianzhuangAddGameCount() {
		return lianzhuangAddGameCount;
	}
	public void setLianzhuangAddGameCount(int lianzhuangAddGameCount) {
		this.lianzhuangAddGameCount = lianzhuangAddGameCount;
	}
	public int getMustZiMo() {
		return mustZiMo;
	}
	public void setMustZiMo(int mustZiMo) {
		this.mustZiMo = mustZiMo;
	}
	public String getFlockId() {
		return flockId;
	}
	public void setFlockId(String flockId) {
		this.flockId = flockId;
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
		result = prime * result + ((flockId == null) ? 0 : flockId.hashCode());
		result = prime * result + gameBipiao;
		result = prime * result + gameCount;
		result = prime * result + gameSubType;
		result = prime * result + gameType;
		result = prime * result + gpsLimit;
		result = prime * result + id;
		result = prime * result + ipLimit;
		result = prime * result + lianzhuangAddGameCount;
		result = prime * result + ((modifiedTime == null) ? 0 : modifiedTime.hashCode());
		result = prime * result + ((modifiedUser == null) ? 0 : modifiedUser.hashCode());
		result = prime * result + mustZiMo;
		result = prime * result + payType;
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
		FlockSettings other = (FlockSettings) obj;
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
		if (flockId == null) {
			if (other.flockId != null)
				return false;
		} else if (!flockId.equals(other.flockId))
			return false;
		if (gameBipiao != other.gameBipiao)
			return false;
		if (gameCount != other.gameCount)
			return false;
		if (gameSubType != other.gameSubType)
			return false;
		if (gameType != other.gameType)
			return false;
		if (gpsLimit != other.gpsLimit)
			return false;
		if (id != other.id)
			return false;
		if (ipLimit != other.ipLimit)
			return false;
		if (lianzhuangAddGameCount != other.lianzhuangAddGameCount)
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
		if (mustZiMo != other.mustZiMo)
			return false;
		if (payType != other.payType)
			return false;
		return true;
	}
	
	
	
	
	
    
}
