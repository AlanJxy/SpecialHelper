package cn.mty.specialhelper.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.mty.specialhelper.bean.HttpRequest;
import cn.mty.specialhelper.utils.Flock;
import cn.mty.specialhelper.utils.globalVar;
import cn.zhouyafeng.itchat4j.Wechat;

//import org.apache.log4j.Logger;

import cn.zhouyafeng.itchat4j.api.MessageTools;
import cn.zhouyafeng.itchat4j.api.WechatTools;
import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import cn.zhouyafeng.itchat4j.beans.RecommendInfo;
import cn.zhouyafeng.itchat4j.core.Core;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import cn.zhouyafeng.itchat4j.utils.enums.MsgTypeEnum;
import cn.zhouyafeng.itchat4j.utils.tools.DownloadTools;
import sun.misc.BASE64Decoder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 简单示例程序，收到文本信息自动回复原信息，收到图片、语音、小视频后根据路径自动保存
 * 
 * @author https://github.com/yaphone
 * @date 创建时间：2017年4月25日 上午12:18:09
 * @version 1.0
 *
 */
public class SimpleHandler implements IMsgHandlerFace {
	Logger LOG = Logger.getLogger(SimpleHandler.class);
	List<haoyouInfo> listHaoYou=new ArrayList<haoyouInfo>();
	
	Wechat _manager; 
	public void setManager(Wechat wechat) {
		_manager=wechat;
	}
	
	//@Override
	public String textMsgHandle(BaseMsg msg) throws Exception {
		// String docFilePath = "D:/itchat4j/pic/1.jpg";  这里是需要发送的文件的路径
		if (msg.isGroupMsg()) { // 只处理群消息	
			// String userId = msg.getString("FromUserName");
			// MessageTools.sendFileMsgByUserId(userId, docFilePath); // 发送文件
			// MessageTools.sendPicMsgByUserId(userId, docFilePath);
			String text = msg.getText(); // 发送文本消息，也可调用MessageTools.sendFileMsgByUserId(userId,text);
			LOG.info(text);
//			if (text.equals("111")) {
//				WechatTools.logout();
//			}
//			if (text.equals("222")) {
//				WechatTools.remarkNameByNickName("yaphone", "Hello");
//			}
			
			//先更新
			String mNewQunNiCheng="";
			if(text.equals("激活")) {
				_manager.updateQunList();
				//
				for (JSONObject o : WechatTools.getGroupList()) {
					if (o.getString("UserName").equals(msg.getFromUserName())) {
						mNewQunNiCheng=o.getString("NickName");
						System.out.println("获取到了群的昵称！");
						System.out.println(o.getString("NickName"));
					}
				}
			}
			//获取 发消息的人的 Nick    如果 群没活跃，这儿获取不到群的名字，我日
			String actulNick="";
			JSONArray json2=WechatTools.getMemberListByGroupId(msg.getFromUserName());
			if(json2.size()>0){
				for(int i=0;i<json2.size();i++){
					JSONObject job = json2.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
					if(job.get("UserName").equals(msg.getActualUserName())){
						actulNick=(String)job.get("NickName");
						System.out.println(actulNick) ;  // 得到 每个对象中的属性值
					}
					//System.out.println(job.get("NickName")) ;  // 得到 每个对象中的属性值
				}
			}
			
			if (text.equals("开房")) { // 测试群列表
				System.out.print(msg);
				
				System.out.print(msg.getFromUserName());//这个是 群的 UserName
				System.out.print(msg.getToUserName());	//这个是Jack，就是机器人的UserName
				
				Core core = Core.getInstance();
				Integer mLogerUid=(Integer)core.getUserSelf().get("Uin");
				
				//String param="uid=123456&qunno=Q001&usernike=春雷，张";
				String param="uin="+mLogerUid.toString()+"&qunno=5"+"&usernike="+msg.getActualUserName();
				System.out.println(param);
		        String s=HttpRequest.sendPost(globalVar.getHouTaiAddress()+"room/roomOpening.do", param);
		        System.out.println(s);
		        
		        String sTest="{\"state\":1,\"message\":\"可以进入房间\",\"data\":"+
		        " 	{\"Room\":"+
		        	"{\"createdTime\":null,\"createdUser\":\"\",\"flockId\":12345,\"modifiedTime\":null"+
		        	",\"modifiedUser\":\"\",\"nStatus\":0,\"num\":0,\"players\":[],\"roomCreateTime\":{\"date\":11,\"day\":3,\"hours\":12"+
		        	",\"minutes\":10,\"month\":6,\"seconds\":16,\"time\":1531282216000,\"timezoneOffset\":-480,\"year\":118},\"roomId\":19"+
		        	",\"vcRoomNo\":\"123130\",\"vcSerialNo\":\"1531282212256\"    	}"+
		        	",\"FlockSettings\":"+
		        	"{\"createdTime\":null,\"createdUser\":\"\",\"flockId\":\"5\",\"gameConfig\":\"{\\\"gameCount\\\":\\\"1\\\","+
		        	"\"payType\\\":\\\"2\\\",\\\"ipLimit\\\":\\\"1\\\",\\\"mustZiMo\\\":\\\"1\\\"}\",\"gameType\":1,\"id\":2"+
		        	",\"modifiedTime\":null,\"modifiedUser\":\"\"}"+
		        	"}"+
		        	"}";
		        
		        net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(s);
				System.out.println(json.get("state"));
				System.out.println(json.getString("data"));
				
				net.sf.json.JSONObject jsonData= net.sf.json.JSONObject.fromObject(json.getString("data"));
				//net.sf.json.JSONObject jsonData1= net.sf.json.JSONObject.fromObject(jsonData.getString("FlockSettings"));
//				net.sf.json.JSONObject jsonFlock= net.sf.json.JSONObject.fromObject(jsonData.getString("FlockSettings"));
				
				
				net.sf.json.JSONObject jsonRoom=(net.sf.json.JSONObject) jsonData.get("Room");
				String fangJianHao=(String)jsonRoom.get("vcRoomNo");
				
				net.sf.json.JSONObject jsonFlock=(net.sf.json.JSONObject) jsonData.get("FlockSettings");
				net.sf.json.JSONObject jsonConfig= net.sf.json.JSONObject.fromObject(jsonFlock.getString("gameConfig"));
				
				/*
				  "gameType":0, //0 青岛麻将 1烟台麻将 2威海麻将
			      "gameSubType":0,  
			      "gameBipiao":-1, 
			      "gameCount":0,  // 0：4局（4房卡）  1：8局（8房卡）  2：12局（12房卡）  3：16局（16房卡）
			      "payType":1,  // 1：房主支付  2：AA支付   3：赢家支付
				 */
				String gameType=Integer.toString((Integer)jsonFlock.get("gameType"));
				if(gameType.equals("1")) gameType="青岛麻将";
				if(gameType.equals("2")) gameType="烟台麻将";
				if(gameType.equals("3")) gameType="威海麻将";
				
				String gameCount=(String)jsonConfig.get("gameCount");
				if(gameCount.equals("0")) gameCount="4局（4房卡）";
				if(gameCount.equals("1")) gameCount="8局（8房卡）";
				if(gameCount.equals("2")) gameCount="12局（12房卡）";
				if(gameCount.equals("3")) gameCount="16局（16房卡）";
				 
				String payType=(String)jsonConfig.get("payType");
				if(payType.equals("1")) payType="房主支付";
				if(payType.equals("2")) payType="AA支付";
				if(payType.equals("3")) payType="赢家支付";
				
		        String strSend="@"+actulNick+"\n";
		        strSend+="房间号："+fangJianHao+"\n";
		        strSend+="【缺"+"4"+"人】\n";
		        strSend+=gameType+","+gameCount+","+payType;
                
		        return strSend;
			}
			if (text.equals("激活")) { // 测试群列表
				
				//先更新
				_manager.updateQunList();
				//
				for (JSONObject o : WechatTools.getGroupList()) {
					if (o.getString("UserName").equals(msg.getFromUserName())) {
						mNewQunNiCheng=o.getString("NickName");
						System.out.println("获取到了群的昵称！");
						System.out.println(o.getString("NickName"));
					}
				}
				
//				if(mQunNiCheng.equals("哈尔滨麻将1群")) {
					System.out.println(msg);
					System.out.println(msg.getActualUserName());
					
					System.out.println(msg.getFromUserName());//这个是 群的 UserName
					
			 		System.out.println(msg.getToUserName());	//这个是Jack，就是机器人的UserName
					//System.out.println(WechatTools.getGroupList());
					System.out.println(WechatTools.getMemberListByGroupId(msg.getFromUserName()));
	
					Core core = Core.getInstance();
					Integer mLogerUid=(Integer)core.getUserSelf().get("Uin");
					System.out.println(core.getUserSelf());
					System.out.println(mLogerUid);
					

					String param="uin="+mLogerUid+"&qunnick="+mNewQunNiCheng+"&qunzhunick="+actulNick+"";
					System.out.println(param);
			        String s=HttpRequest.sendPost(globalVar.getHouTaiAddress()+"robot/bindingFlock.do", param);
			        System.out.println(s);
//				}
			}
			//群空间
			if(text.equals("群空间")) {
				//http://47.92.79.186:8080/webclient/qgl_roomList.html?robotname=Jack&qunid=Q001&qunnick=%E5%A4%A7%E5%AE%B6%E5%A5%BD
//				BASE64Encoder encoder = new BASE64Encoder();
//			    String qunnickBm = encoder.encode("哈尔滨麻将1群".getBytes("utf-8"));
//			    System.out.println(qunnickBm);
//			    
//			    String robotnameBm = encoder.encode("机器人".getBytes("utf-8"));
//			    System.out.println(qunnickBm);
			    
//				return "agents.o2odk.com:8080/webclient/qgl_roomList.html?qunid=5";
				
				
//				List<Flock> list=globalVar.getListFlock();
//				String mQunID="";
//				for(Flock flocl : list) {
//					if(flocl.qunNick==);
//				}
				return "http://192.168.43.91/dashboard/qgl_roomList.html?qunid=5";
				//return "http://192.168.43.56/qgl_roomList.html?qunid=5";
			}
		}else {
			//首先看是否是 添加好友后的消息。
			Iterator it = listHaoYou.iterator();
			while(it.hasNext()){
			  haoyouInfo hy=(haoyouInfo)it.next();
			  if(msg.getFromUserName().equals(hy.userName)) {
				  if(hy.yanZhengMa.equals("")) {
					  hy.yanZhengMa=msg.getText();
				  }else {
					  hy.weiXinHao=msg.getText();
					  System.out.print("检测到好友 输入了验证码，发送给 后台吧"+msg.getText());
					  //?robotId=20122334455&verificationCode=343434&agentWxh=666555
					  //param="uin="+mLogerUid.toString()+"&qunno=Q001"+"&usernike="+msg.getActualUserName();
					  //agencySheet/recieveCode.do?wxno=20122334455&verificationCode=343434&agentWxh=666555
					  String param="wxno="+globalVar.mUin+"&verificationCode="+hy.yanZhengMa+"&agentWxh="+hy.weiXinHao;
					  
					  System.out.println(param);
					  System.out.println(globalVar.getHouTaiAddress()); 
							  
					  String s=HttpRequest.sendPost(globalVar.getHouTaiAddress()+"agencySheet/recieveCode.do", param);
				      System.out.println(s);
				  }
			  }
			}
		
			//是否是要注册的。  还没要这个功能。
			if (msg.getText().equals("注册")) { // 测试群列表
				System.out.print(msg);
				
				System.out.print(msg.getFromUserName());//这个是 群的 UserName
				System.out.print(msg.getToUserName());	//这个是Jack，就是机器人的UserName
				//System.out.print(WechatTools.getGroupList());
				//System.out.print(WechatTools.getMemberListByGroupId(msg.getFromUserName()));
				//System.out.print(WechatTools.getGroupIdList());
				//System.out.print(Core.getInstance().getGroupMemeberMap());
			}
		}
		return null;
	}

//	@Override
//	public String picMsgHandle(BaseMsg msg) {
//		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());// 这里使用收到图片的时间作为文件名
//		String picPath = "D://itchat4j/pic" + File.separator + fileName + ".jpg"; // 调用此方法来保存图片
//		DownloadTools.getDownloadFn(msg, MsgTypeEnum.PIC.getType(), picPath); // 保存图片的路径
//		return "图片保存成功";
//	}
//
//	@Override
//	public String voiceMsgHandle(BaseMsg msg) {
//		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
//		String voicePath = "D://itchat4j/voice" + File.separator + fileName + ".mp3";
//		DownloadTools.getDownloadFn(msg, MsgTypeEnum.VOICE.getType(), voicePath);
//		return "声音保存成功";
//	}
//
//	@Override
//	public String viedoMsgHandle(BaseMsg msg) {
//		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
//		String viedoPath = "D://itchat4j/viedo" + File.separator + fileName + ".mp4";
//		DownloadTools.getDownloadFn(msg, MsgTypeEnum.VIEDO.getType(), viedoPath);
//		return "视频保存成功";
//	}

//	@Override
//	public String nameCardMsgHandle(BaseMsg msg) {
//		return "收到名片消息";
//	}

	//@Override
	public void sysMsgHandle(BaseMsg msg) { // 收到系统消息
//		String text = msg.getContent();
//		LOG.info(text);
	}

	//@Override
	public String picMsgHandle(BaseMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public String voiceMsgHandle(BaseMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public String viedoMsgHandle(BaseMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public String nameCardMsgHandle(BaseMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public String verifyAddFriendMsgHandle(BaseMsg msg) {
		System.out.println("有人要添加我为好友！");
		MessageTools.addFriend(msg, true); // 同意好友请求，false为不接受好友请求
		RecommendInfo recommendInfo = msg.getRecommendInfo();
		String nickName = recommendInfo.getNickName();
		String province = recommendInfo.getProvince();
		String mRecommentUserName=recommendInfo.getUserName();
		String city = recommendInfo.getCity();
		String text = "你好，来自" + province + city + "的" + nickName + "， 欢迎添加我为好友！ \n  请输入你的验证码和微信号";
		listHaoYou.add(new haoyouInfo(mRecommentUserName,nickName));
		return text;
	}
	class haoyouInfo{
		String userName;
		String nickName;
		String yanZhengMa="";
		String weiXinHao="";
		haoyouInfo(String pUserName,String pNickName){
			this.userName=pUserName;
			this.nickName=pNickName;
		}
	}

	//@Override
	public String mediaMsgHandle(BaseMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String isExistQunChengYuan(String pQunNick, String pChengYuanNick) {
		String mQunUserName="";
		for (JSONObject o : WechatTools.getGroupList()) {
			if (o.getString("NickName").equals(pQunNick)) {
				mQunUserName=o.getString("UserName");
				break;
			}
		}
		
		if(!mQunUserName.equals("")) {
						
			JSONArray json=WechatTools.getMemberListByGroupId(mQunUserName);
			
			if(json.size()>0){
				for(int i=0;i<json.size();i++){
					JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
					if(job.get("NickName").equals(pChengYuanNick)){
						return "1";
					}
				}
			}
		}
		return "0";
	}

	@Override
	public String sendJieSuanInfo(String pJieSuanInfo,String qunNick,String picPath,String strDaYingJia) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.out.println(qunNick);
		System.out.println(picPath);
		String mQunUserID="";
		for (JSONObject o : WechatTools.getGroupList()) {
			if (o.getString("NickName").equals(qunNick)) {
				mQunUserID=o.getString("UserName");
				System.out.println("获取到了群的UserName！");
				System.out.println(o.getString("NickName"));
				break;
			}
		}
		MessageTools.sendMsgById(pJieSuanInfo, mQunUserID);
		
		
		if(picPath.length()>0) {
			Thread.sleep(200);
			MessageTools.sendMsgById("@"+strDaYingJia +" 大赢家", mQunUserID);
			
			Thread.sleep(200);
			MessageTools.sendPicMsgByUserId(mQunUserID,picPath);
		}
		
		return "执行成功！";
	}

//	@Override
//	public String mediaMsgHandle(BaseMsg msg) {
//		String fileName = msg.getFileName();
//		String filePath = "D://itchat4j/file" + File.separator + fileName; // 这里是需要保存收到的文件路径，文件可以是任何格式如PDF，WORD，EXCEL等。
//		DownloadTools.getDownloadFn(msg, MsgTypeEnum.MEDIA.getType(), filePath);
//		return "文件" + fileName + "保存成功";
//	}

}
