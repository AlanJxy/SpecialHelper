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
import java.util.Date;
import java.util.List;

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




@Controller
@RequestMapping("/robot")
public class Robot {
	
	private Wechat wechat;
	//http://127.0.0.1:8080/SpecialHelper/robot/start.do?arg=123
	@RequestMapping("/start.do")  
    @ResponseBody  
    public ResponseResult<Void> updeteRoom(String kouling){ 
		ResponseResult<Void> rr = new ResponseResult();
		System.out.println("start.do2222222");
		
		String qrPath = "D://itchat4j//login"; // �����½��ά��ͼƬ��·����������Ҫ�ڱ����½�Ŀ¼
		IMsgHandlerFace msgHandler = new SimpleHandler(); // ʵ��IMsgHandlerFace�ӿڵ���
		wechat = new Wechat(msgHandler, qrPath); //��ע�롿
		wechat.start(); // �������񣬻���qrPath������һ�Ŷ�ά��ͼƬ��ɨ�輴�ɵ�½��
						//ע�⣬��ά��ͼƬ�������һ��ʱ��δɨ�����ڣ�����ʱ���Զ����£������������Ҫ���´�ͼƬ
		
		Core core = Core.getInstance();
		Integer mLogerUid=(Integer)core.getUserSelf().get("Uin");
		globalVar.mUin=mLogerUid;
		
		String param="uid="+globalVar.mUin;
		
		System.out.println(globalVar.getHouTaiAddress()+"robot/selectRobot.do");
    	System.out.println(param);
    	
    	String s=HttpRequest.sendPost(globalVar.getHouTaiAddress()+"robot/selectRobot.do", param);
        System.out.println(s);
		
        JSONObject json = JSONObject.fromObject(s);
		System.out.println(json.get("state"));
		
		JSONArray jsonArray =(JSONArray) json.get("data");
		
		for (int i = 0; i < jsonArray.size(); i++) {
	        JSONObject jo = jsonArray.getJSONObject(i);
	        //���� ���ݣ��� Ⱥд��list����ȥ��
	        Flock flock=new Flock();
	        flock.qunNick=jo.getString("flockName");
	        flock.qunNo=jo.getString("flockId");
	        flock.qunZhuNick=jo.getString("adminName");
	        globalVar.addQunToList(flock);
		}
		rr.setMessage("��¼�ɹ�");
		System.out.println("��ӡ��ǰ������Ⱥ�б�");
		List<Flock> list=globalVar.getListFlock();
		for(Flock flocl : list) {
			System.out.println(flocl.qunNick);
		}
		
		return rr;
		
	}
	
//	//����
//    String pw = "";
//    BASE64Encoder encoder = new BASE64Encoder();
//    String newPw = encoder.encode(pw.getBytes("utf-8"));
//    String string = "8J+Sog==";
//    System.out.println(newPw);
//    //����
//    BASE64Decoder decoder = new BASE64Decoder();
//    String oldpw = new String(decoder.decodeBuffer(string),"utf-8");
//    System.out.println(oldpw);
//    //System.out.println("");
    
	//http://127.0.0.1:8080/SpecialHelper/robot/checkExsist.do?qunno=123&usernick=wzt
	@RequestMapping("/checkExsist.do")  
    @ResponseBody  
    public ResponseResult<String> checkUserLogin(String qunno,String usernick) throws IOException{ 
		ResponseResult<String> rr = new ResponseResult<String>();
		System.out.println("checkExsist.do");
		
		System.out.println(qunno);
		System.out.println(usernick);
		
		BASE64Decoder decoder = new BASE64Decoder();
		String oldpw = new String(decoder.decodeBuffer(usernick),"utf-8");
	    System.out.println(oldpw);
		
	    //��ȡ Ⱥ�ǳ�
		String mQunNick="";
		List<Flock> list=globalVar.getListFlock();
		for(Flock flocl : list) {
			if(flocl.qunNo.equals(qunno)) {
				System.out.println(flocl.qunNick);
				mQunNick=flocl.qunNick;
			}
		}
		
		String sdata="";
		if(wechat.getHandler().isExistQunChengYuan(mQunNick,oldpw).equals("1"))//pQunNick, pChengYuanNick))
		{
			rr.setState(1);
			sdata="{\"errcode\":0,\"result\":\"1\"}";
		}else {
			rr.setState(0);
			sdata="{\"errcode\":0,\"result\":\"0\"}";
		}
		rr.setState(1);
		rr.setData(sdata);
		rr.setMessage("��֤�ɹ���");
		return rr;
		
	}
	//http://127.0.0.1:8080/SpecialHelper/robot/updateRoomStatus.do?fangno=123
	@RequestMapping("/updateRoomStatus.do")  
    @ResponseBody  
    public ResponseResult<String> updateRoomStatus(String fangno) throws InterruptedException, Exception, IOException{ 
		ResponseResult<String> rr = new ResponseResult<String>();
		System.out.println("updateRoomStatus.do");
		
		System.out.println(fangno);
//		{"vcMerchantNo":"20171121165200002","vcSerialNo":"1531292908828","vcChatRoomSerialNo":"5","vcRoomNo":"634388","nStatus":0
//			,"playerList":[{"vcUnionid":"gggg","vcHeadImgUrl":"","vcWeixinName":"5bCP54mb6YC86JuL"}]}
		JSONObject json = JSONObject.fromObject(fangno);
		
		//1 ��ʾ�����ˣ������ˡ�  3��ʾ�Ѿ���ʼ��
		System.out.println(json.get("nStatus"));
		int nStatus=(Integer) json.get("nStatus");
		if(nStatus==1) {
			String sdata="{\"errcode\":0,\"result\":1}";
			rr.setData(sdata);
			rr.setMessage("���·���״̬�ɹ�");
			return rr;
		}
		String mFangJianHao=(String)json.get("vcRoomNo");
		
		//��ȡ Ⱥ�ǳ�
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
	        
		//�̻�����  �����  ȱ���ˣ� �淨��������֧����ʽ��
		
		String mShangHuName="����Ħ����";
		String mQueJiRen=Integer.toString(4-RenShu) ;
		
		String strSend="";
		if(mQueJiRen.equals("0") & nStatus==3) {
        	strSend+="@"+wanJia1 +" @" +wanJia2+ " @"+wanJia3+ " @"+wanJia4+"\n";
        }
		strSend+="/::), "+mShangHuName+"\n";
        strSend+="����ţ�"+mFangJianHao+"\n";//(String)jsonData.get("vcRoomNo")
        if(mQueJiRen.equals("0")) {//& nStatus==3
        	strSend+="���Ѿ���ʼ��\n";
        }else {
        	strSend+="��ȱ"+mQueJiRen+"�ˡ�\n";
        }
		wechat.getHandler().sendJieSuanInfo (strSend,mQunNick,"","");
		
		String sdata="{\"errcode\":0,\"result\":1}";
		rr.setData(sdata);
		rr.setMessage("���·���״̬�ɹ�");
		return rr;
		
	}
	//http://127.0.0.1:8080/SpecialHelper/robot/jiesuan.do?fangno=123
	@RequestMapping("/jiesuan.do")  
    @ResponseBody  
    public ResponseResult<String> jiesuan(String fangno) throws Exception{ 
		ResponseResult<String> rr = new ResponseResult<String>();
		System.out.println("jiesuan.do");
		
		System.out.println(fangno);
		
//		fangno = "{\"vcMerchantNo\":\"20171121165200002\",\"vcSerialNo\":\"KF001\",\"vcChatRoomSerialNo\":\"Q001\",\"vcRoomNo\":\"046138\",\"vcLogNo\":\"1530091291351046138\",\"vcGameType\":\"qingdao\",\"vcRoomType\":\"0\",\"vcCardNum\":4,\"vcPlayerCount\":4,\"vcIsAARoom\":\"0\",\"vcRoomMasterId\":\"oQFTJ09z1NcqUKR3D003xpqJjg-U\","+
//				"\"playerList\":[{\"vcUnionid\":\"aaaa\",\"vcHeadImgUrl\":\"\",\"vcWeixinName\":\"��־��\",\"nWinCount\":\"11\",\"nlostCount\":\"11\",\"nMaxPoint\":\"1\",\"nTotalPoint\":\"11\"},{\"vcUnionid\":\"bbbb\",\"vcHeadImgUrl\":\"\",\"vcWeixinName\":\"������\",\"nWinCount\":\"22\",\"nlostCount\":\"22\",\"nMaxPoint\":\"2\",\"nTotalPoint\":\"22\"},{\"vcUnionid\":\"cccc\",\"vcHeadImgUrl\":\"\",\"vcWeixinName\":\"����\",\"nWinCount\":\"33\",\"nlostCount\":\"33\",\"nMaxPoint\":\"3\",\"nTotalPoint\":\"33\"},{\"vcUnionid\":\"dddd\",\"vcHeadImgUrl\":\"\",\"vcWeixinName\":\"�̻���\",\"nWinCount\":\"44\",\"nlostCount\":\"44\",\"nMaxPoint\":\"4\",\"nTotalPoint\":\"44\"}]}";
		String strSend="";
		System.out.println(fangno);
		//ʱ��
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
		String strDaYingJia="û�д�Ӯ��";
			
		
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
		
		strSend="/::)����š�"+fangjianhao+"��\n  ��Ⱥ�ɡ�Ⱥ��������\n"+nowTime+"\n--------------------\n��Ϸ���";
		strSend +="\n��"+userNick1+"��\n";
		strSend +="\t���֣�"+jifen1+"  ���գ�"+zuizhong1;
		strSend +="\n��"+userNick2+"��\n";
		strSend +="\t���֣�"+jifen2+"  ���գ�"+zuizhong2;
		strSend +="\n��"+userNick3+"��\n";
		strSend +="\t���֣�"+jifen3+"  ���գ�"+zuizhong3;
		strSend +="\n��"+userNick4+"��\n";
		strSend +="\t���֣�"+jifen4+"  ���գ�"+zuizhong4;
	            
		
		
		System.out.println(strSend);
		//����Ⱥ���Ķ�ά�룬�����͵�Ⱥ����
//		String picPath="D:\\itchat4j\\pic\\"+nowTime+".jpg";
//		getErWeiMaPic(picPath);
		String picPath="D:\\itchat4j\\pic\\1111.jpg";
		wechat.getHandler().sendJieSuanInfo (strSend,"�������齫1Ⱥ",picPath,strDaYingJia);
		
		
		String sdata="{\"errcode\":0,\"result\":1}";
		rr.setData(sdata);
		rr.setMessage("���ս������ݳɹ�");
		return rr;
	}
	
	private void getErWeiMaPic(String picPath)throws Exception {
		URL url = new URL("http://192.168.43.246:8080/SpecialHelper/robot/picUrl.do");  
	    //������  
	    HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
	    //��������ʽΪ"GET"  
	    conn.setRequestMethod("GET");  
	    //��ʱ��Ӧʱ��Ϊ5��  
	    conn.setConnectTimeout(5 * 1000);  
	    //ͨ����������ȡͼƬ����  
	    InputStream inStream = conn.getInputStream();  
	    //�õ�ͼƬ�Ķ��������ݣ��Զ����Ʒ�װ�õ����ݣ�����ͨ����  
	    byte[] data = readInputStream(inStream);  
	    //newһ���ļ�������������ͼƬ��Ĭ�ϱ��浱ǰ���̸�Ŀ¼  
	    File imageFile = new File(picPath);  
	    //���������  
	    FileOutputStream outStream = new FileOutputStream(imageFile);  
	    //д������  
	    outStream.write(data);  
	    //�ر������  
	    outStream.close(); 
	}
	public static byte[] readInputStream(InputStream inStream) throws Exception{  
	    ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
	    //����һ��Buffer�ַ���  
	    byte[] buffer = new byte[1024];  
	    //ÿ�ζ�ȡ���ַ������ȣ����Ϊ-1������ȫ����ȡ���  
	    int len = 0;  
	    //ʹ��һ����������buffer������ݶ�ȡ����  
	    while( (len=inStream.read(buffer)) != -1 ){  
	        //���������buffer��д�����ݣ��м����������ĸ�λ�ÿ�ʼ����len�����ȡ�ĳ���  
	        outStream.write(buffer, 0, len);  
	    }  
	    //�ر�������  
	    inStream.close();  
	    //��outStream�������д���ڴ�  
	    return outStream.toByteArray();  
	} 

}
