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
	
	//��Ϸ�����ͷ���״̬���·���״̬
	@RequestMapping("/updeteRoom.do")  
    @ResponseBody  
    public ResponseResult<Void> updeteRoom(String strContext){ 
		ResponseResult<Void> rr = new ResponseResult<Void>();
		System.out.println("updeteRoom.do");
		//System.out.println(strContext);
		//��json��ʽ��Stringת��Ϊjson
		
		
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
         //�̻��ı��
         room2.setVcMerchatNo((String)json.get("vcMerchantNo"));
         //Ⱥ���
         room2.setVcChatRoomSerialNo((String)json.get("vcChatRoomSerialNo"));
        Integer count =  roomService.updateRoomStatus(room2);
         System.out.println(count);
		
		rr.setMessage("���³ɹ�");
		
		return rr;
		
	}
	
	//��Ϸ������Ӧ�����������
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
	    rr.setMessage("��Ӧ�����������");
		return rr;
		
	}
	
	

	//�����˼�⿪����ѯȺ����û�пշ�
	@RequestMapping("/roomOpening.do")  
    @ResponseBody  
    public ResponseResult<Room> roomOpening(String flockId){ 
		System.out.println("�����˼�⵽����");
         
		ResponseResult<Room> rr = new ResponseResult<Room>();
		Room room = roomService.getRoomByNum();
		System.out.println(room);
		if(room==null){
			rr.setState(0);
			//rr.setMessage("û�п��з���");
			
			FlockSettings fs = flockSettingsService.selectRoomIdByFlockId(flockId);
			System.out.println(fs);
			//�������
//			"gameType":0, //0 �ൺ�齫 1��̨�齫 2�����齫
//			"gameSubType":0,  
//			"gameBipiao":-1, 
//			"gameCount":0,	// 0��4�֣�4������  1��8�֣�8������  2��12�֣�12������  3��16�֣�16������
//			"payType":1,	// 1������֧��  2��AA֧��   3��Ӯ��֧��
//			"ipLimit":0,	// 0/1  ��ͬip������/����
//			"gpsLimit":0,	// 0/1  GPS������/����
//			"lianzhuangAddGameCount":0, // 0/1  ��ׯ�������/�����
//			"mustZiMo":0	
//			String param = "gameType="+fs.getGameType()+"&gameSubType="+fs.getGameSubType()+
//					       "&gameBipiao="+fs.getGameBipiao()+"&gameCount="+fs.getGameCount()+
//					       "&payType="+fs.getPayType()+"&ipLimit="+fs.getIpLimit()+
//					       "&gpsLimit="+fs.getGpsLimit()+"&lianzhuangAddGameCount="+fs.getLianzhuangAddGameCount()+
//					       "&mustZiMo="+fs.getMustZiMo();
			
//			vcUnionId:"oQFTJ09z1NcqUKR3D003xpqJjg-U",	// �����û���unionid
//			vcGameType:"qingdao", //��Ϸ����
//			vcChatRoomSerialNo:"Q001", //Ⱥ���
//			vcIsAA:1, //�Ƿ�AA����  0 ����  1 �ǵ�
//			vcSerialNo:"KF001", //�������
//			gameCount:0,
//			vcJuShu:4
			System.out.println("������Ϸ��");
			
			String param="vcUnionId=oQFTJ09z1NcqUKR3D003xpqJjg-U&vcGameType=qingdao&vcChatRoomSerialNo=Q001"
					+ "&vcIsAA=1&vcSerialNo=KF001&gameCount=0&vcJuShu=4";
			//����Ϸ������ post ����            
	        String s=HttpRequest.sendPost("http://192.168.58.83:7001/kaiyiju_create_room", param);
	        
	        System.out.println(s);
	        
//	        {"nResult":"1","vcResult":"success","vcRoomId":"908160","vcCallURL":""
//	        	,"vcTitle":"","vcDesc":"�ൺ�淨,4�ֺ�","vcLogoImgURL":"���ӵ�ͼƬ��ַ"}
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
	                 //�̻��ı��
	                 room2.setVcMerchatNo("20171121165200002");
	                 //Ⱥ���
	                 room2.setVcChatRoomSerialNo("Q001");
	                 roomService.register(room2);
	             
	        }
	        
	        
	        rr.setMessage(vcRoomId);
	        
			
			
		}else {
			rr.setState(1);
			rr.setMessage("���Խ��뷿��");
			rr.setData(room);
		}
		
     return rr;   
    }  
 
	//��ȡ���з����������Ϣ
	@RequestMapping("/selectAll.do")  
    @ResponseBody  
    public JSONPObject selectAll(String callbackparam,String dateTime){ 
		System.out.println("4444");
		System.out.println(dateTime);
	
   
		ResponseResult<List<Room>> rr = new ResponseResult<List<Room>>();
		List<Room> list = roomService.selectAll();
		
			rr.setState(1);
			rr.setMessage("���Խ��뷿��");
			rr.setData(list);
		
		
     return new JSONPObject(callbackparam, rr);   
    }  
}
