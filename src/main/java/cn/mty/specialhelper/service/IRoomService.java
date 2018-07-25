package cn.mty.specialhelper.service;

import java.util.List;

import cn.mty.specialhelper.bean.Room;

public interface IRoomService {

	/**
	 * 创建room后保存房间状态
	 * @param user
	 */

	void register(Room room);
	/**
	 * 根据num(人数）<4 获取房间和状态
	 * @return Room 对象
	 */
	Room getRoomByNum();

	/**
	 * 查询所有房间
	 * @return list集合
	 */
	List<Room> selectAll();

	/**
	 * 更新房间状态
	 * @param room
	 * @return
	 */
	Integer updateRoomStatus(Room room);

}
