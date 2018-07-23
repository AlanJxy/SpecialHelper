package cn.mty.specialhelper.mapper;


import java.util.List;


import cn.mty.specialhelper.bean.Room;

public interface RoomMapper {
	/**
	 * 插入数据
	 * @param Room
	 */
	void insertRoom(Room room);
	/**
	 * 根据房间人数小于4的查询
	 * @return 返回Room对象
	 */
	Room selectRoomByNum();
	
	
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
