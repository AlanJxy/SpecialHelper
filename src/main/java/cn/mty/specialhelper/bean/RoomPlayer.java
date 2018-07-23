package cn.mty.specialhelper.bean;

import java.io.Serializable;
import java.util.Date;


public class RoomPlayer implements Serializable {

			/**
			 * 
			 */
			private static final long serialVersionUID = -5852091411143558826L;
		//	room_id int(11) ,
		//    vc_unionid varchar(64),
		//         Vc_weixin_name varchar(64),
		//    vc_head_img_Url varchar(64),
		//	
			private int roomId;
			private String vcUnionId;
			private String vcWeixinName;
			private String vcHeadimgURL;
			
			private String createdUser;
			private Date createdTime;
			private String modifiedUser;
			private Date modifiedTime;
			
			public RoomPlayer() {
				super();
				// TODO Auto-generated constructor stub
			}

			public RoomPlayer(int roomId, String vcUnionId, String vcWeixinName, String vcHeadimgURL,
					String createdUser, Date createdTime, String modifiedUser, Date modifiedTime) {
				super();
				this.roomId = roomId;
				this.vcUnionId = vcUnionId;
				this.vcWeixinName = vcWeixinName;
				this.vcHeadimgURL = vcHeadimgURL;
				this.createdUser = createdUser;
				this.createdTime = createdTime;
				this.modifiedUser = modifiedUser;
				this.modifiedTime = modifiedTime;
			}

			@Override
			public String toString() {
				return "RoomPlayer [roomId=" + roomId + ", vcUnionId=" + vcUnionId + ", vcWeixinName=" + vcWeixinName
						+ ", vcHeadimgURL=" + vcHeadimgURL + ", createdUser=" + createdUser + ", createdTime="
						+ createdTime + ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime + "]";
			}

			public int getRoomId() {
				return roomId;
			}

			public void setRoomId(int roomId) {
				this.roomId = roomId;
			}

			public String getVcUnionId() {
				return vcUnionId;
			}

			public void setVcUnionId(String vcUnionId) {
				this.vcUnionId = vcUnionId;
			}

			public String getVcWeixinName() {
				return vcWeixinName;
			}

			public void setVcWeixinName(String vcWeixinName) {
				this.vcWeixinName = vcWeixinName;
			}

			public String getVcHeadimgURL() {
				return vcHeadimgURL;
			}

			public void setVcHeadimgURL(String vcHeadimgURL) {
				this.vcHeadimgURL = vcHeadimgURL;
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
				result = prime * result + roomId;
				result = prime * result + ((vcHeadimgURL == null) ? 0 : vcHeadimgURL.hashCode());
				result = prime * result + ((vcUnionId == null) ? 0 : vcUnionId.hashCode());
				result = prime * result + ((vcWeixinName == null) ? 0 : vcWeixinName.hashCode());
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
				RoomPlayer other = (RoomPlayer) obj;
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
				if (roomId != other.roomId)
					return false;
				if (vcHeadimgURL == null) {
					if (other.vcHeadimgURL != null)
						return false;
				} else if (!vcHeadimgURL.equals(other.vcHeadimgURL))
					return false;
				if (vcUnionId == null) {
					if (other.vcUnionId != null)
						return false;
				} else if (!vcUnionId.equals(other.vcUnionId))
					return false;
				if (vcWeixinName == null) {
					if (other.vcWeixinName != null)
						return false;
				} else if (!vcWeixinName.equals(other.vcWeixinName))
					return false;
				return true;
			}
			
			
			
			
	

}
