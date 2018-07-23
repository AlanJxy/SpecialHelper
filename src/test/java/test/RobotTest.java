package test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.mty.specialhelper.bean.Robot;
import cn.mty.specialhelper.mapper.RobotMapper;
import cn.mty.specialhelper.service.IRobotService;
import cn.mty.specialhelper.service.RobotService;
import sun.nio.cs.US_ASCII;

public class RobotTest {
	@Test
	public void testselect(){
		AbstractApplicationContext ac  = 
				new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
	
	IRobotService us = 
				ac.getBean("robotService",RobotService.class);
	String str = "wx111";
	try {
		List<Robot> list = us.selectRobotByNike(str);
		System.out.println(list.size());
	} catch (Exception e) {
		
	}
    
    
    
//    String str = "wx111";
//     List<Robot> list = us.selectRobotByNike(str);
//     System.out.println(list.size());
//    
   }
}
