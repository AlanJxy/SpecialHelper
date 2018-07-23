package test;



import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.mty.specialhelper.bean.FlockSettings;

import cn.mty.specialhelper.mapper.FlockSettingsMapper;


public class FlockSettingsTest {
	@Test
	public void testRegister(){
		AbstractApplicationContext ac  = 
				new ClassPathXmlApplicationContext("spring-dao.xml");
	FlockSettingsMapper us = 
				ac.getBean("flockSettingsMapper",FlockSettingsMapper.class);
    FlockSettings fs = new FlockSettings();
    fs.setFlockId("123456");
    fs.setGameType(0);
    fs.setGameSubType(0);
    fs.setGameBipiao(-1);
    fs.setGameCount(0);
    fs.setPayType(1);
    fs.setIpLimit(0);
    fs.setGpsLimit(0);
    fs.setLianzhuangAddGameCount(0);
    fs.setMustZiMo(0);
    us.insertFlockSettings(fs);
		ac.close();
		
	}
	
	@Test
	public void testselect(){
		AbstractApplicationContext ac  = 
				new ClassPathXmlApplicationContext("spring-dao.xml");
	FlockSettingsMapper us = 
				ac.getBean("flockSettingsMapper",FlockSettingsMapper.class);
//	FlockSettings fSettings = us.selectRoomIdByFlockId("123456");
//	System.out.println(fSettings);
    
   
    FlockSettings fs = new FlockSettings();
    fs.setFlockId("456123");
    fs.setGameType(9);
    fs.setGameSubType(0);
    fs.setGameBipiao(-1);
    fs.setGameCount(0);
    fs.setPayType(1);
    fs.setIpLimit(0);
    fs.setGpsLimit(0);
    fs.setLianzhuangAddGameCount(0);
    fs.setMustZiMo(0);
    Integer i = us.selectCunotByFlockId(fs.getFlockId());
    System.out.println(i);
    if(i==1) {
    	us.updeteFlockSettings(fs);
    	System.out.println("ÐÞ¸Ä");
    }else {
    	us.insertFlockSettings(fs);
    	System.out.println("Ìí¼Ó");
    }
	
    
		ac.close();
		
	}
}
