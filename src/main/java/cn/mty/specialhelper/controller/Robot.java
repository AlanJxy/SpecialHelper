package cn.mty.specialhelper.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.mty.specialhelper.websocket.WebSocket;
import javafx.scene.chart.PieChart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mty.specialhelper.bean.HttpRequest;
import cn.mty.specialhelper.bean.ResponseResult;
import cn.mty.specialhelper.utils.Flock;
import cn.mty.specialhelper.utils.globalVar;
import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.api.MessageTools;
import cn.zhouyafeng.itchat4j.core.Core;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;


@Controller
@RequestMapping("/robot")
public class Robot extends WebSocket{

	//http://127.0.0.1:8080/robot/botTime.do?uin=1633000544

	@RequestMapping("/botTime.do")
	@ResponseBody
	public ResponseResult<Void> getBotTime(String uin){
		ResponseResult<Void> rr = new ResponseResult();
		Date date = new Date();

		/*//todo 测试用，用完记得删掉*/
		List<Flock> listf = new ArrayList<Flock>();
		Flock flock = new Flock();
		flock.qunNick = "asdasdasdas";
		flock.qunNo = "213465";
		flock.qunZhuNick = String.valueOf(date.getTime());
		listf.add(flock);

		boolean botType = this.sendAll(globalVar.gson.toJson(listf), uin);


		rr.setMessage(String.valueOf(date.getTime()));
		rr.setState(botType ? 1 : 0);
		return rr;
	}


	@RequestMapping("/start.do")
	@ResponseBody
	public ResponseResult<Void> updeteRoom(String kouling){
		ResponseResult<Void> rr = new ResponseResult();
		System.out.println("start.do2222222");

		String qrPath = "D://itchat4j//login"; // 保存登陆二维码图片的路径，这里需要在本地新建目录
		IMsgHandlerFace msgHandler = new SimpleHandler(); // 实现IMsgHandlerFace接口的类
		/*wechat = new Wechat(msgHandler, qrPath); //【注入】
		wechat.start(); // 启动服务，会在qrPath下生成一张二维码图片，扫描即可登陆，*/
		//注意，二维码图片如果超过一定时间未扫描会过期，过期时会自动更新，所以你可能需要重新打开图片

		Core core = Core.getInstance();
		String mLogerUid=core.getUserSelf().get("Uin").toString();
		globalVar.mUin=mLogerUid;

		String param="uin="+globalVar.mUin;

		System.out.println(globalVar.getHouTaiAddress()+"robot/selectRobot.do");
		System.out.println(param);

		String s=HttpRequest.sendGet(globalVar.getHouTaiAddress()+"robot/selectRobot.do", param);
		System.out.println(s);

		JSONObject json = JSONObject.fromObject(s);
		System.out.println(json.get("state"));

		JSONArray jsonArray =(JSONArray) json.get("data");

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jo = jsonArray.getJSONObject(i);
			//根据 数据，把 群写到list里面去。
			Flock flock=new Flock();
			flock.qunNick=jo.getString("flockName");
			flock.qunNo=jo.getString("flockId");
			flock.qunZhuNick=jo.getString("adminName");
			globalVar.addQunToList(flock);
		}
		rr.setMessage("登录成功");
		System.out.println("打印当前监听的群列表");
		List<Flock> list=globalVar.getListFlock();
		for(Flock flocl : list) {
			System.out.println(flocl.qunNick);
		}

		return rr;

	}

//	//编码
//    String pw = "";
//    BASE64Encoder encoder = new BASE64Encoder();
//    String newPw = encoder.encode(pw.getBytes("utf-8"));
//    String string = "8J+Sog==";
//    System.out.println(newPw);
//    //解码
//    BASE64Decoder decoder = new BASE64Decoder();
//    String oldpw = new String(decoder.decodeBuffer(string),"utf-8");
//    System.out.println(oldpw);
//    //System.out.println("");

	//http://127.0.0.1:8080/robot/checkExsist.do?qunno=123&usernick=wzt&uin=1633000544
	@RequestMapping("/checkExsist.do")
	@ResponseBody
	public ResponseResult<String> checkUserLogin(String qunno, String usernick, String uin) throws IOException {
		ResponseResult<String> rr = new ResponseResult<String>();
		System.out.println("checkExsist.do");

		System.out.println(qunno);
		System.out.println(usernick);
		System.out.println(uin);

		BASE64Decoder decoder = new BASE64Decoder();
		String oldpw = new String(decoder.decodeBuffer(usernick), "utf-8");
		System.out.println("BASE64Decoder加密后的结果为==>" + oldpw);

		//获取 群昵称
        String mQunNick = "";
        List<Flock> list = globalVar.getListFlock();
        for (Flock flocl : list) {
            if (flocl.qunNo.equals(qunno)) {
                System.out.println(flocl.qunNick);
                mQunNick = flocl.qunNick;
            }
        }

		String sdata="";
		Wechat wechat;
		String qrPath = "D://itchat4j//login"; // 保存登陆二维码图片的路径，这里需要在本地新建目录
		IMsgHandlerFace msgHandler = new SimpleHandler(); // 实现IMsgHandlerFace接口的类
		wechat = new Wechat(msgHandler, qrPath); //【注入】
		if(wechat.getHandler().isExistQunChengYuan(mQunNick,oldpw).equals("1"))//pQunNick, pChengYuanNick))
		{
			rr.setState(1);
			/*sdata="{\"errcode\":0,\"result\":\"1\"}";*/
		}else {
			rr.setState(0);
			/*sdata="{\"errcode\":0,\"result\":\"0\"}";*/
		}

		/*this.sendAll(globalVar.gson.toJson(listf), uin);*/
		Date date = new Date();
		rr.setMessage(String.valueOf(date.getTime()));
		rr.setData("DataTest");
		return rr;

	}
	//http://127.0.0.1:8080/SpecialHelper/robot/updateRoomStatus.do?fangno=123
	@RequestMapping("/updateRoomStatus.do")
	@ResponseBody
	public ResponseResult<String> updateRoomStatus(String fangno, String uin) throws InterruptedException, Exception, IOException{
		ResponseResult<String> rr = new ResponseResult<String>();
		System.out.println("updateRoomStatus.do");

		System.out.println(fangno);
//		{"vcMerchantNo":"20171121165200002","vcSerialNo":"1531292908828","vcChatRoomSerialNo":"5","vcRoomNo":"634388","nStatus":0
//			,"playerList":[{"vcUnionid":"gggg","vcHeadImgUrl":"","vcWeixinName":"5bCP54mb6YC86JuL"}]}
		JSONObject json = JSONObject.fromObject(fangno);

		//1 表示结束了，不发了。  3表示已经开始了
		System.out.println(json.get("nStatus"));
		int nStatus=(Integer) json.get("nStatus");
		if(nStatus==1) {
			String sdata="{\"errcode\":0,\"result\":1}";
			rr.setData(sdata);
			rr.setMessage("更新房间状态成功");
			return rr;
		}
		String mFangJianHao=(String)json.get("vcRoomNo");

		//获取 群昵称
		String mQunNick="";
		String mQunNo=(String)json.get("vcChatRoomSerialNo");
		List<Flock> list=globalVar.getListFlock();
		for(Flock flocl : list) {
			if(flocl.qunNo.equals(mQunNo)) {
				System.out.println(flocl.qunNick);
				mQunNick=flocl.qunNick;
			}
		}

		JSONArray jsonArray =(JSONArray) json.get("playerList");
		int RenShu=jsonArray.size();
		String wanJia1="";
		String wanJia2="";
		String wanJia3="";
		String wanJia4="";
		if(jsonArray.size()==4) {
			BASE64Decoder decoder = new BASE64Decoder();

			JSONObject jo = jsonArray.getJSONObject(0);
			String strTmp = new String(decoder.decodeBuffer((String)jo.get("vcWeixinName")),"utf-8");
			wanJia1=strTmp;
			System.out.println(strTmp);

			jo = jsonArray.getJSONObject(1);
			strTmp =new String(decoder.decodeBuffer((String)jo.get("vcWeixinName")),"utf-8");
			wanJia2=strTmp;

			jo = jsonArray.getJSONObject(2);
			strTmp = new String(decoder.decodeBuffer((String)jo.get("vcWeixinName")),"utf-8");
			wanJia3=strTmp;

			jo = jsonArray.getJSONObject(3);
			strTmp =new String(decoder.decodeBuffer((String)jo.get("vcWeixinName")),"utf-8");
			wanJia4=strTmp;
		}

		//商户名称  房间号  缺几人， 玩法，局数，支付方式。

		String mShangHuName="深圳摩天游";
		String mQueJiRen=Integer.toString(4-RenShu) ;

		String strSend="";
		if(mQueJiRen.equals("0") & nStatus==3) {
			strSend+="@"+wanJia1 +" @" +wanJia2+ " @"+wanJia3+ " @"+wanJia4+"\n";
		}
		strSend+="/::), "+mShangHuName+"\n";
		strSend+="房间号："+mFangJianHao+"\n";//(String)jsonData.get("vcRoomNo")
		if(mQueJiRen.equals("0")) {//& nStatus==3
			strSend+="【已经开始】\n";
		}else {
			strSend+="【缺"+mQueJiRen+"人】\n";
		}
		//todo
		/*globalVar.wechatMap.get(uin).getHandler().sendJieSuanInfo (strSend,mQunNick,"","");*/

		String sdata="{\"errcode\":0,\"result\":1}";
		rr.setData(sdata);
		rr.setMessage("更新房间状态成功");
		return rr;

	}
	//http://127.0.0.1:8080/SpecialHelper/robot/jiesuan.do?fangno=123
	@RequestMapping("/jiesuan.do")
	@ResponseBody
	public ResponseResult<String> jiesuan(String fangno, String uin) throws Exception{
		ResponseResult<String> rr = new ResponseResult<String>();
		System.out.println("jiesuan.do");

		System.out.println(fangno);

//		fangno = "{\"vcMerchantNo\":\"20171121165200002\",\"vcSerialNo\":\"KF001\",\"vcChatRoomSerialNo\":\"Q001\",\"vcRoomNo\":\"046138\",\"vcLogNo\":\"1530091291351046138\",\"vcGameType\":\"qingdao\",\"vcRoomType\":\"0\",\"vcCardNum\":4,\"vcPlayerCount\":4,\"vcIsAARoom\":\"0\",\"vcRoomMasterId\":\"oQFTJ09z1NcqUKR3D003xpqJjg-U\","+
//				"\"playerList\":[{\"vcUnionid\":\"aaaa\",\"vcHeadImgUrl\":\"\",\"vcWeixinName\":\"张志国\",\"nWinCount\":\"11\",\"nlostCount\":\"11\",\"nMaxPoint\":\"1\",\"nTotalPoint\":\"11\"},{\"vcUnionid\":\"bbbb\",\"vcHeadImgUrl\":\"\",\"vcWeixinName\":\"商永琪\",\"nWinCount\":\"22\",\"nlostCount\":\"22\",\"nMaxPoint\":\"2\",\"nTotalPoint\":\"22\"},{\"vcUnionid\":\"cccc\",\"vcHeadImgUrl\":\"\",\"vcWeixinName\":\"刘磊\",\"nWinCount\":\"33\",\"nlostCount\":\"33\",\"nMaxPoint\":\"3\",\"nTotalPoint\":\"33\"},{\"vcUnionid\":\"dddd\",\"vcHeadImgUrl\":\"\",\"vcWeixinName\":\"程焕焕\",\"nWinCount\":\"44\",\"nlostCount\":\"44\",\"nMaxPoint\":\"4\",\"nTotalPoint\":\"44\"}]}";
		String strSend="";
		System.out.println(fangno);
		//时间
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime=sdf.format(d);
		String fangjianhao="";

		String userNick1="";
		String jifen1="";
		String zuizhong1="";

		String userNick2="";
		String jifen2="";
		String zuizhong2="";

		String userNick3="";
		String jifen3="";
		String zuizhong3="";

		String userNick4="";
		String jifen4="";
		String zuizhong4="";

		JSONObject json = JSONObject.fromObject(fangno);
		System.out.println(json.get("vcChatRoomSerialNo"));
		fangjianhao=(String) json.get("vcRoomNo");

		JSONArray jsonArray =(JSONArray) json.get("playerList");

		int maxScore=0;
		int tmpScore=0;
		String strDaYingJia="没有大赢家";


		if(jsonArray.size()==4) {
			JSONObject jo = jsonArray.getJSONObject(0);
			userNick1 =jo.getString("vcWeixinName");
			jifen1 =jo.getString("nMaxPoint");
			zuizhong1 =jo.getString("nTotalPoint");

			jo = jsonArray.getJSONObject(1);
			userNick2 =jo.getString("vcWeixinName");
			jifen2 =jo.getString("nMaxPoint");
			zuizhong2 =jo.getString("nTotalPoint");

			jo = jsonArray.getJSONObject(2);
			userNick3 =jo.getString("vcWeixinName");
			jifen3 =jo.getString("nMaxPoint");
			zuizhong3 =jo.getString("nTotalPoint");

			jo = jsonArray.getJSONObject(3);
			userNick4 =jo.getString("vcWeixinName");
			jifen4 =jo.getString("nMaxPoint");
			zuizhong4 =jo.getString("nTotalPoint");


			if(!zuizhong1.substring(0,1).equals("-")) {
				tmpScore=Integer.parseInt(zuizhong1);
				if(tmpScore>maxScore) {
					strDaYingJia=userNick1;
				}
			}

			if(!zuizhong2.substring(0,1).equals("-")) {
				tmpScore=Integer.parseInt(zuizhong2);
				if(tmpScore>maxScore) {
					strDaYingJia=userNick2;
				}
			}


			if(!zuizhong3.substring(0,1).equals("-")) {
				tmpScore=Integer.parseInt(zuizhong3);
				if(tmpScore>maxScore) {
					strDaYingJia=userNick3;
				}
			}


			if(!zuizhong4.substring(0,1).equals("-")) {
				tmpScore=Integer.parseInt(zuizhong4);
				if(tmpScore>maxScore) {
					strDaYingJia=userNick4;
				}
			}
		}

		strSend="/::)房间号【"+fangjianhao+"】\n  本群由【群主】创建\n"+nowTime+"\n--------------------\n游戏结果";
		strSend +="\n【"+userNick1+"】\n";
		strSend +="\t积分："+jifen1+"  最终："+zuizhong1;
		strSend +="\n【"+userNick2+"】\n";
		strSend +="\t积分："+jifen2+"  最终："+zuizhong2;
		strSend +="\n【"+userNick3+"】\n";
		strSend +="\t积分："+jifen3+"  最终："+zuizhong3;
		strSend +="\n【"+userNick4+"】\n";
		strSend +="\t积分："+jifen4+"  最终："+zuizhong4;



		System.out.println(strSend);
		//下载群主的二维码，并发送到群里面
//		String picPath="D:\\itchat4j\\pic\\"+nowTime+".jpg";
//		getErWeiMaPic(picPath);
		String picPath="D:\\itchat4j\\pic\\1111.jpg";
		//todo
		/*globalVar.wechatMap.get(uin).getHandler().sendJieSuanInfo (strSend,"哈尔滨麻将1群",picPath,strDaYingJia);*/


		String sdata="{\"errcode\":0,\"result\":1}";
		rr.setData(sdata);
		rr.setMessage("接收结算数据成功");
		return rr;
	}

	private void getErWeiMaPic(String picPath)throws Exception {
		URL url = new URL("http://192.168.43.246:8080/SpecialHelper/robot/picUrl.do");
		//打开链接
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		//设置请求方式为"GET"
		conn.setRequestMethod("GET");
		//超时响应时间为5秒
		conn.setConnectTimeout(5 * 1000);
		//通过输入流获取图片数据
		InputStream inStream = conn.getInputStream();
		//得到图片的二进制数据，以二进制封装得到数据，具有通用性
		byte[] data = readInputStream(inStream);
		//new一个文件对象用来保存图片，默认保存当前工程根目录
		File imageFile = new File(picPath);
		//创建输出流
		FileOutputStream outStream = new FileOutputStream(imageFile);
		//写入数据
		outStream.write(data);
		//关闭输出流
		outStream.close();
	}
	public static byte[] readInputStream(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		//创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		//每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		//使用一个输入流从buffer里把数据读取出来
		while( (len=inStream.read(buffer)) != -1 ){
			//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		//关闭输入流
		inStream.close();
		//把outStream里的数据写入内存
		return outStream.toByteArray();
	}

}
