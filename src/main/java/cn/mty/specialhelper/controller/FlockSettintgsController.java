package cn.mty.specialhelper.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;

import cn.mty.specialhelper.bean.FlockSettings;
import cn.mty.specialhelper.bean.ResponseResult;
import cn.mty.specialhelper.service.FlockSettingsService;

@Controller
@RequestMapping("/flock")
public class FlockSettintgsController {
	@Autowired
	private FlockSettingsService flockSettingsService;
	//向房间里面存或修改游戏配置
	@RequestMapping("/flockSettings.do")
	@ResponseBody
	public JSONPObject insertFlockSettings( String callbackparam,FlockSettings flockSettings){
		
		
//		String[] str = request.getParameterValues("ipLimit");
//		
//		for(int i= 0;i<str.length;i++) {
//			
//				if(Integer.parseInt(str[i])==1){
//					flockSettings.setIpLimit(1);
//				}else if(Integer.parseInt(str[i])==2) {
//					flockSettings.setGpsLimit(1);
//				}else if(Integer.parseInt(str[i])==3) {
//					flockSettings.setLianzhuangAddGameCount(1);
//				}else if(Integer.parseInt(str[i])==4) {
//					flockSettings.setMustZiMo(1);
//				}
//		}
		
		System.out.println(flockSettings);
		System.out.println("保存");
		
		ResponseResult<Void> rr = new ResponseResult<Void>();
		
		System.out.println(flockSettings);
		//flockSettings.setFlockId("123456");
		flockSettings.setGameBipiao(-1);
		
		flockSettingsService.insertFlockSettings(flockSettings);
		rr.setState(1);
		rr.setMessage("成功");
		
		return new JSONPObject(callbackparam, rr);
	}
	
	//请求返回群的玩法设置
	@RequestMapping("/infoflockSettings.do")
	@ResponseBody
	public JSONPObject infoFlockSettings( String callbackparam,String flockId){
		if(flockId==null||flockId=="") {
			return null;
			
		}
		System.out.println("infoflockSettings.do");
		ResponseResult<FlockSettings> rr = new ResponseResult<FlockSettings>();
		
		FlockSettings flockSettings = flockSettingsService.selectRoomIdByFlockId(flockId);
		rr.setState(1);
		rr.setMessage("haha成功");
		rr.setData(flockSettings);
		
		return new JSONPObject(callbackparam, rr);
	}
	
	
	//将玩法设置的多选框的数据遍历赋值
	
	
	
}
