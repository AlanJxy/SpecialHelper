package cn.mty.specialhelper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mty.specialhelper.bean.HttpRequest;
import cn.mty.specialhelper.bean.ResponseResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/test")
public class TestController {
  
	
	@RequestMapping("/test.do")
	@ResponseBody 
	public ResponseResult<Void> Httprequest(String arg) {
		ResponseResult<Void> rr = new ResponseResult<Void>();
		
		System.out.println("test.do");
		
		//JSONObject json = JSONObject.fromObject(arg);
		//JSONArray jsonArray =(JSONArray) json.get("playerList");
		//System.out.println(jsonArray.size());
		//for (int i = 0; i < jsonArray.size(); i++) {
		//	JSONObject jo = jsonArray.getJSONObject(i);
		//System.out.println(jo.getString("vcUnionid")+"......"+i);
       //}
		System.out.println(arg);
        //发送 GET 请求
//        String s=HttpRequest.sendGet("http://192.168.58.216:8088/userlogin", "usernick=123&qunno=456");
//        System.out.println(s);
        
        //发送 POST 请求
        //String sr=HttpRequest.sendPost("http://192.168.58.216:8088/userlogin", "usernick=123&qunno=456");
        System.out.println("收到了请求！");
        
        rr.setMessage("更新成功");
		
		return rr;

	}
	
	
}
