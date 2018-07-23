package cn.mty.specialhelper.bean;

import java.io.Serializable;
import java.util.Date;

public class Robot implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8051728570566336304L;
      private int id;
      private String wxno;
      private String nike;
      private int contactId;//ÉÌ»§ID
      private int flockId;//Èºid
      private int sex;
      private String orURL;
      private String hardURL;
      
      private String createdUser;
  	  private Date createdTime;
  	  private String modifiedUser;
  	  private Date modifiedTime;
	public Robot() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Robot(int id, String wxno, String nike, int contactId, int flockId, int sex, String orURL, String hardURL,
			String createdUser, Date createdTime, String modifiedUser, Date modifiedTime) {
		super();
		this.id = id;
		this.wxno = wxno;
		this.nike = nike;
		this.contactId = contactId;
		this.flockId = flockId;
		this.sex = sex;
		this.orURL = orURL;
		this.hardURL = hardURL;
		this.createdUser = createdUser;
		this.createdTime = createdTime;
		this.modifiedUser = modifiedUser;
		this.modifiedTime = modifiedTime;
	}
	@Override
	public String toString() {
		return "Robot [id=" + id + ", wxno=" + wxno + ", nike=" + nike + ", contactId=" + contactId + ", flockId="
				+ flockId + ", sex=" + sex + ", orURL=" + orURL + ", hardURL=" + hardURL + ", createdUser="
				+ createdUser + ", createdTime=" + createdTime + ", modifiedUser=" + modifiedUser + ", modifiedTime="
				+ modifiedTime + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWxno() {
		return wxno;
	}
	public void setWxno(String wxno) {
		this.wxno = wxno;
	}
	public String getNike() {
		return nike;
	}
	public void setNike(String nike) {
		this.nike = nike;
	}
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	public int getFlockId() {
		return flockId;
	}
	public void setFlockId(int flockId) {
		this.flockId = flockId;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getOrURL() {
		return orURL;
	}
	public void setOrURL(String orURL) {
		this.orURL = orURL;
	}
	public String getHardURL() {
		return hardURL;
	}
	public void setHardURL(String hardURL) {
		this.hardURL = hardURL;
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
		result = prime * result + contactId;
		result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((createdUser == null) ? 0 : createdUser.hashCode());
		result = prime * result + flockId;
		result = prime * result + ((hardURL == null) ? 0 : hardURL.hashCode());
		result = prime * result + id;
		result = prime * result + ((modifiedTime == null) ? 0 : modifiedTime.hashCode());
		result = prime * result + ((modifiedUser == null) ? 0 : modifiedUser.hashCode());
		result = prime * result + ((nike == null) ? 0 : nike.hashCode());
		result = prime * result + ((orURL == null) ? 0 : orURL.hashCode());
		result = prime * result + sex;
		result = prime * result + ((wxno == null) ? 0 : wxno.hashCode());
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
		Robot other = (Robot) obj;
		if (contactId != other.contactId)
			return false;
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
		if (flockId != other.flockId)
			return false;
		if (hardURL == null) {
			if (other.hardURL != null)
				return false;
		} else if (!hardURL.equals(other.hardURL))
			return false;
		if (id != other.id)
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
		if (nike == null) {
			if (other.nike != null)
				return false;
		} else if (!nike.equals(other.nike))
			return false;
		if (orURL == null) {
			if (other.orURL != null)
				return false;
		} else if (!orURL.equals(other.orURL))
			return false;
		if (sex != other.sex)
			return false;
		if (wxno == null) {
			if (other.wxno != null)
				return false;
		} else if (!wxno.equals(other.wxno))
			return false;
		return true;
	}
	
      
}
