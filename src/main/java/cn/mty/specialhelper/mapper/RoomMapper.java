package cn.mty.specialhelper.mapper;


import java.util.List;


import cn.mty.specialhelper.bean.Room;

public interface RoomMapper {
	/**
	 * ��������
	 * @param Room
	 */
	void insertRoom(Room room);
	/**
	 * ���ݷ�������С��4�Ĳ�ѯ
	 * @return ����Room����
	 */
	Room selectRoomByNum();
	
	
    /**
     * ��ѯ���з���
     * @return list����
     */
	List<Room> selectAll();
	
	/**
	 * ���·���״̬
	 * @param room
	 * @return
	 */
	Integer updateRoomStatus(Room room);
	
	
}
