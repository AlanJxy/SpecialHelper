package cn.mty.specialhelper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mty.specialhelper.bean.Room;
import cn.mty.specialhelper.mapper.RoomMapper;
@Service
public class RoomService implements IRoomService{
     
	@Autowired
	private RoomMapper roomMapper;
	
	public void register(Room room) {
		roomMapper.insertRoom(room);
		
	}

	public Room getRoomByNum() {
		Room room =roomMapper.selectRoomByNum();
		
		
		return room;
	}

	public List<Room> selectAll() {
		List<Room> list = roomMapper.selectAll();
		return list;
	}

	public Integer updateRoomStatus(Room room) {
		Integer  count = roomMapper.updateRoomStatus(room);
		return count;
	}

}
