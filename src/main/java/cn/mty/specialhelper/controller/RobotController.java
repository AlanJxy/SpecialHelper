package cn.mty.specialhelper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;

import cn.mty.specialhelper.bean.ResponseResult;
import cn.mty.specialhelper.bean.Robot;
import cn.mty.specialhelper.service.IRobotService;

import cn.mty.specialhelper.service.ex.NikeNotFoundException;


@Controller
@RequestMapping
public class RobotController {
	@Autowired
	private IRobotService robotService;

	//机器人登录查询返回机器人的管理信息
	@RequestMapping("/selectRobot.do")
	@ResponseBody
	public ResponseResult<List<Robot>> selectRobot(String nike){
		System.out.println("robot");
		System.out.println(nike);

		ResponseResult<List<Robot>> rr = new ResponseResult<List<Robot>>();

		try {
			List<Robot> list = robotService.selectRobotByNike(nike);


			rr.setState(0);
			rr.setMessage("找的机器人");
			rr.setData(list);

		} catch (NikeNotFoundException e) {
			rr.setState(-1);
			rr.setMessage(e.getMessage());
		}


		return rr;
	}

}
