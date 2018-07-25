package cn.mty.specialhelper.whchatbot;

import cn.mty.specialhelper.controller.SimpleHandler;
import cn.mty.specialhelper.utils.MsgData_Login_Client;
import cn.mty.specialhelper.utils.globalVar;
import cn.mty.specialhelper.websocket.WebSocketSend;
import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.core.Core;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.channels.NotYetConnectedException;
import java.util.HashMap;
import java.util.Map;

public class MainApp{

    protected static Map<String,Wechat> wechatMapa = new HashMap<String, Wechat>();
    protected static Wechat wechata;

    public static void initFrame() {
        Frame frame = new Frame("摩天游小助手");
        Button btnEnd = new Button("关闭");
        Button btnStart = new Button("登录");
        Button btnSendsg = new Button("发送消息");
        Label lblUin = new Label("微信唯一编号：");
        TextField txtUin = new TextField(50);

        frame.add(btnEnd);
        frame.add(btnStart);
        frame.add(btnSendsg);
        frame.add(txtUin);
        frame.add(lblUin);

        frame.setLayout(null);
        frame.setVisible(true);//显示窗口

        frame.setBounds(30, 30, 350, 250);
        lblUin.setBounds(20, 50, 90, 25);
        txtUin.setBounds(120, 50, 210, 30);
        btnStart.setBounds(30, 200, 90, 28);
        btnSendsg.setBounds(130, 200, 90, 28);
        btnEnd.setBounds(230, 200, 90, 28);

        txtUin.setEnabled(false);


        btnEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);//退出程序
            }
        });


        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //执行开启微信机器人的代码：
				Wechat wechat;
				String qrPath = "D://itchat4j//login"; // 保存登陆二维码图片的路径，这里需要在本地新建目录
				IMsgHandlerFace msgHandler = new SimpleHandler(); // 实现IMsgHandlerFace接口的类
				wechat = new Wechat(msgHandler, qrPath); //【注入】
				wechat.start(); // 启动服务，会在qrPath下生成一张二维码图片，扫描即可登陆，
								//注意，二维码图片如果超过一定时间未扫描会过期，过期时会自动更新，所以你可能需要重新打开图片

				Core core = Core.getInstance();
				String mLogerUid=core.getUserSelf().get("Uin").toString();
				globalVar.mUin=mLogerUid;

                /*wechata = wechat;
                wechatMapa.put(mLogerUid, wechat);
                wechatMap = wechatMapa;
                *//*globalVar.mUin = 1111111111;*//*
                System.out.println("cn.mty.specialhelper.whchatbot.MainApp.72" + wechatMapa.get(mLogerUid));
                System.out.println("cn.mty.specialhelper.whchatbot.MainApp.73" + wechatMap.toString());*/

                //连接  webSocket客户端
                try {
                    WebSocketSend.LinkToServer(globalVar.mUin);
                } catch (NotYetConnectedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (UnsupportedEncodingException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }

        });


        btnSendsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //发送登录  消息。   然后在收到微信消息时发送SendMsg  以及在收到服务端的消息时，调用本地的wechat方法来执行逻辑。
//        		webSocket.client.send("dddddd");
                MsgData_Login_Client msgLogin = new MsgData_Login_Client();
                msgLogin.setMsgType("Client_login");
                msgLogin.setUin(globalVar.mUin);
                msgLogin.setNickName("测试人员");
                msgLogin.setSex("1");
                msgLogin.setUrl("www.baidu.com");
                String jsonStr = globalVar.gson.toJson(msgLogin);
                System.out.println("---->javabean convert jsonStr:" + jsonStr);

                //解析方法
//		        MsgData_Login_Client msgLogin222 = globalVar.gson.fromJson(jsonStr, MsgData_Login_Client.class)

//				String tmp="{\"uin\":\"1111111111\",\"msgType\":\"Client_login\"}";
                WebSocketSend.client.send(jsonStr);
                System.out.println("消息发送完成！");

            }
        });

    }

    public static void main(String[] args) {
        System.out.println("测试中文乱码问题adbasdads");
        initFrame();

    }
}
