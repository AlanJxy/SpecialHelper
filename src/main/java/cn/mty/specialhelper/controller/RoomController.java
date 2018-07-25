package cn.mty.specialhelper.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.fasterxml.jackson.databind.util.JSONPObject;


import cn.mty.specialhelper.bean.FlockSettings;
import cn.mty.specialhelper.bean.HttpRequest;
import cn.mty.specialhelper.bean.ResponseResult;
import cn.mty.specialhelper.bean.Room;

import cn.mty.specialhelper.service.IFlockSettingsService;
import cn.mty.specialhelper.service.IRoomService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



@Controller
@RequestMapping("/room")
public class RoomController {

	@Autowired
	private IRoomService roomService;
	@Autowired
	private IFlockSettingsService flockSettingsService;

	//游戏服发送房间状态更新房间状态
	@RequestMapping("/updeteRoom.do")
	@ResponseBody
	public ResponseResult<Void> updeteRoom(String strContext){
		ResponseResult<Void> rr = new ResponseResult<Void>();
		System.out.println("updeteRoom.do");
		//System.out.println(strContext);
		//将json样式的String转化为json


		JSONObject json = JSONObject.fromObject(strContext);
		JSONArray jsonArray =(JSONArray) json.get("playerList");
		System.out.println(jsonArray.size());
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jo = jsonArray.getJSONObject(i);
			System.out.println(jo.getString("vcUnionid")+"......"+i);
		}


		//{"vcMerchantNo":"20171121165200002","vcSerialNo":"KF001","vcChatRoomSerialNo":"Q001",
		//"vcRoomNo":"974655","nStatus":1,
		//"playerList":[{"vcUnionid":"oQFTJ09z1NcqUKR3D003xpqJjg-U","vcHeadImgUrl":"","vcWeixinName":"?"}]}
		System.out.println(json.get("playerList"));

		Room room2 = new Room();
		room2.setVcRoomNo((String)json.get("vcRoomNo"));
		room2.setnStatus((Integer)json.get("nStatus"));
		room2.setNum(jsonArray.size());
		//商户的编号
		room2.setVcMerchatNo((String)json.get("vcMerchantNo"));
		//群编号
		room2.setVcChatRoomSerialNo((String)json.get("vcChatRoomSerialNo"));
		Integer count =  roomService.updateRoomStatus(room2);
		System.out.println(count);

		rr.setMessage("更新成功");

		return rr;

	}

	//游戏结束响应结果给机器人
	@RequestMapping("/playerResult.do")
	@ResponseBody
	public ResponseResult<Void> playerResult(String strContext){
		ResponseResult<Void> rr = new ResponseResult<Void>();
		System.out.println("playerResult.do");
		System.out.println(strContext);
		//{"vcMerchantNo":"20171121165200002","vcSerialNo":"KF001",
//		"vcChatRoomSerialNo":"Q001","vcRoomNo":"046138","vcLogNo":"1530091291351046138",
//		"vcGameType":"qingdao","vcRoomType":"0","vcCardNum":4,"vcPlayerCount":4,"vcIsAARoom":"0",
//		"vcRoomMasterId":"oQFTJ09z1NcqUKR3D003xpqJjg-U",
//		"playerList":[{"vcUnionid":"oQFTJ09z1NcqUKR3D003xpqJjg-U","vcHeadImgUrl":"","vcWeixinName":"?",
//			"nWinCount":"0","nlostCount":"0","nMaxPoint":"0","nTotalPoint":"0"},
//		              {"vcUnionid":"","vcHeadImgUrl":"","vcWeixinName":"?","nWinCount":"0","nlostCount":"0",
//				"nMaxPoint":"0","nTotalPoint":"0"},
//		              {"vcUnionid":"","vcHeadImgUrl":"","vcWeixinName":"?","nWinCount":"0","nlostCount":"0",
//					"nMaxPoint":"0","nTotalPoint":"0"},
//		              {"vcUnionid":"","vcHeadImgUrl":"","vcWeixinName":"?","nWinCount":"0","nlostCount":"0",
//						"nMaxPoint":"0","nTotalPoint":"0"}]}
//
		String param="fangno="+strContext;
		HttpRequest.sendPost("http://192.168.58.216:8088/gamejiesuan", param);

		rr.setState(0);
		rr.setMessage("响应结果给机器人");
		return rr;

	}



	//机器人检测开房查询群里有没有空房
	@RequestMapping("/roomOpening.do")
	@ResponseBody
	public ResponseResult<Room> roomOpening(String flockId){
		System.out.println("机器人检测到开房");

		ResponseResult<Room> rr = new ResponseResult<Room>();
		Room room = roomService.getRoomByNum();
		System.out.println(room);
		if(room==null){
			rr.setState(0);
			//rr.setMessage("没有空闲房间");

			FlockSettings fs = flockSettingsService.selectRoomIdByFlockId(flockId);
			System.out.println(fs);
			//请求参数
//			"gameType":0, //0 青岛麻将 1烟台麻将 2威海麻将
//			"gameSubType":0,
//			"gameBipiao":-1,
//			"gameCount":0,	// 0：4局（4房卡）  1：8局（8房卡）  2：12局（12房卡）  3：16局（16房卡）
//			"payType":1,	// 1：房主支付  2：AA支付   3：赢家支付
//			"ipLimit":0,	// 0/1  相同ip不限制/限制
//			"gpsLimit":0,	// 0/1  GPS不限制/限制
//			"lianzhuangAddGameCount":0, // 0/1  连庄不算局数/算局数
//			"mustZiMo":0
//			String param = "gameType="+fs.getGameType()+"&gameSubType="+fs.getGameSubType()+
//					       "&gameBipiao="+fs.getGameBipiao()+"&gameCount="+fs.getGameCount()+
//					       "&payType="+fs.getPayType()+"&ipLimit="+fs.getIpLimit()+
//					       "&gpsLimit="+fs.getGpsLimit()+"&lianzhuangAddGameCount="+fs.getLianzhuangAddGameCount()+
//					       "&mustZiMo="+fs.getMustZiMo();

//			vcUnionId:"oQFTJ09z1NcqUKR3D003xpqJjg-U",	// 开房用户的unionid
//			vcGameType:"qingdao", //游戏类型
//			vcChatRoomSerialNo:"Q001", //群编号
//			vcIsAA:1, //是否AA开房  0 不是  1 是的
//			vcSerialNo:"KF001", //开房编号
//			gameCount:0,
//			vcJuShu:4
			System.out.println("请求游戏服");

			String param="vcUnionId=oQFTJ09z1NcqUKR3D003xpqJjg-U&vcGameType=qingdao&vcChatRoomSerialNo=Q001"
					+ "&vcIsAA=1&vcSerialNo=KF001&gameCount=0&vcJuShu=4";
			//给游戏服发送 post 请求
			String s=HttpRequest.sendPost("http://192.168.58.83:7001/kaiyiju_create_room", param);

			System.out.println(s);

//	        {"nResult":"1","vcResult":"success","vcRoomId":"908160","vcCallURL":""
//	        	,"vcTitle":"","vcDesc":"青岛玩法,4局好","vcLogoImgURL":"链接的图片地址"}
			//String json = "{\"2\":\"efg\",\"1\":\"abc\"}";
			//   JSONObject json_test = JSONObject.fromObject(json);
			JSONObject json = JSONObject.fromObject(s);
			System.out.println(json.get("nResult"));

			String nResult = (String) json.get("nResult");
			String vcResult = (String) json.get("vcResult");
			String vcRoomId = (String) json.get("vcRoomId");
			if(nResult.equals("1")&&vcResult.equals("success")) {
				Room room2 = new Room();


				room2.setVcRoomNo(vcRoomId);
				room2.setnStatus(0);
				room2.setNum(0);
				//商户的编号
				room2.setVcMerchatNo("20171121165200002");
				//群编号
				room2.setVcChatRoomSerialNo("Q001");
				roomService.register(room2);

			}


			rr.setMessage(vcRoomId);



		}else {
			rr.setState(1);
			rr.setMessage("可以进入房间");
			rr.setData(room);
		}

		return rr;
	}

	//获取所有房间的所有信息
	@RequestMapping("/selectAll.do")
	@ResponseBody
	public JSONPObject selectAll(String callbackparam,String dateTime){
		System.out.println("4444");
		System.out.println(dateTime);


		ResponseResult<List<Room>> rr = new ResponseResult<List<Room>>();
		List<Room> list = roomService.selectAll();

		rr.setState(1);
		rr.setMessage("可以进入房间");
		rr.setData(list);


		return new JSONPObject(callbackparam, rr);
	}
}
