package cn.zhouyafeng.itchat4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhouyafeng.itchat4j.controller.LoginController;
import cn.zhouyafeng.itchat4j.core.MsgCenter;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;

public class Wechat {
	private static final Logger LOG = LoggerFactory.getLogger(Wechat.class);
	private IMsgHandlerFace msgHandler;

	private LoginController login;
	public void updateQunList() {
		login.updateQunList();
	}
	
	public Wechat(IMsgHandlerFace msgHandler, String qrPath) {
		System.setProperty("jsse.enableSNIExtension", "false"); // 防止SSL错误
		this.msgHandler = msgHandler;
		msgHandler.setManager(this);
		// 登陆
		login = new LoginController();
		login.login(qrPath);
	}
	
	public IMsgHandlerFace getHandler() {
		return msgHandler;
	}
	
	public void start() {
		LOG.info("+++++++++++++++++++开始消息处理+++++++++++++++++++++");
		new Thread(new Runnable() {
			public void run() {
				MsgCenter.handleMsg(msgHandler);
			}
		}).start();
	}

}
