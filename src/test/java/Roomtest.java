import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import cn.mty.specialhelper.bean.Room;
import cn.mty.specialhelper.mapper.RoomMapper;
import cn.mty.specialhelper.service.RoomService;



public class Roomtest {
	@Test
	public void testRegister(){
		AbstractApplicationContext ac  = 
				new ClassPathXmlApplicationContext("spring-dao.xml");
	RoomMapper us = 
				ac.getBean("roomMapper",RoomMapper.class);
		Room room = new Room();
//		private int roomId;
//		private String vcRoomNo;
//		private Date roomCreateTime;
//		private int nStatus;
//		private String vcMerchatNo;
//		private int num;
//		private String vcChatRoomSerialNo;
        room.setVcRoomNo("111111");
        room.setnStatus(1);
        room.setVcMerchatNo("sh123");
        room.setNum(4);
        room.setVcChatRoomSerialNo("qun123");
		System.out.println(us.updateRoomStatus(room));
//		List<Room> list = us.selectAll();
//		System.out.println(list.size());
	       // Room room =us.selectRoomByNum();
           // System.out.println(room);
		ac.close();
		
	}
	
	@Test
	public void testRegister1(){
		AbstractApplicationContext ac  = 
				new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		RoomService us = 
				ac.getBean("roomService",RoomService.class);

	
	        Room room =us.getRoomByNum();
            System.out.println(room);
		
		ac.close();
		
	}

}
