package cn.mty.specialhelper.websocket;

import cn.zhouyafeng.itchat4j.Wechat;
import org.java_websocket.WebSocket.READYSTATE;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;


import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.ByteBuffer;


public class WebSocketSend {
    public static WebSocketClient client;
    private static Wechat wechat;


    public static void LinkToServer(String uin)//?username=${sessionScope.username}
            throws Exception {
        //ws://localhost:8080/Chapter14/chatSocket?uin="+uin
        client = new WebSocketClient(new URI("ws://192.168.58.179:8080/websocket/" + uin), new Draft_6455()) {

            @Override
            public void onOpen(ServerHandshake arg0) {
                System.out.println("打开链接");
            }

            @Override
            public void onMessage(String msg) {
                System.out.println("收到消息" + msg);
//	            Message message =globalVar.gson.fromJson(msg, Message.class);  
//	    		System.out.println("------->json convert JavaBean:"+message.getMsgType());
//	    		
//	    		switch (message.getMsgType()) {
//	    		case "Client_login":
//	    			System.out.println("是登录消息");
//	    			MsgData_Login_Server msgLogin_client = globalVar.gson.fromJson(msg, MsgData_Login_Server.class);
//	    			globalVar.listFlock=msgLogin_client.listFlock;
//	    			System.out.println("打印当前监听的群列表");
//	    			for(Flock flocl : globalVar.listFlock) {
//	    				System.out.println(flocl.FlockNick);
//	    			}
//	    			System.out.println("登录过程完成！");
//	    			break;
//
//	    		default:
//	    			break;
//	    		}
            }

            @Override
            public void onError(Exception arg0) {
                arg0.printStackTrace();
                System.out.println("发生错误已关闭");
            }

            @Override
            public void onClose(int arg0, String arg1, boolean arg2) {
                System.out.println("链接已关闭");
            }

            @Override
            public void onMessage(ByteBuffer bytes) {
                try {
                    System.out.println(new String(bytes.array(), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }


        };

        client.connect();

        while (!client.getReadyState().equals(READYSTATE.OPEN)) {
            Thread.sleep(300);
            System.out.println("还未连接到服务器！");
        }
        System.out.println("连接服务器成功！");
//	    send("hello 摩天游".getBytes("utf-8"));
//	    client.send("摩天游2");
    }

    public static void send(byte[] bytes) {
        client.send(bytes);
    }
}
