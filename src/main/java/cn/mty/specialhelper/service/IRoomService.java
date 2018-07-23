package cn.mty.specialhelper.service;

import java.util.List;

import cn.mty.specialhelper.bean.Room;

public interface IRoomService {
	
	/**
	 * ����room�󱣴淿��״̬
	 * @param user
	 */
	
	void register(Room room);
	/**
	 * ����num(������<4 ��ȡ�����״̬
	 * @return Room ����
	 */
	Room getRoomByNum();
	
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
