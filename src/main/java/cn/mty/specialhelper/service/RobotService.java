package cn.mty.specialhelper.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mty.specialhelper.bean.Robot;
import cn.mty.specialhelper.mapper.RobotMapper;
import cn.mty.specialhelper.service.IRobotService;
import cn.mty.specialhelper.service.ex.NikeNotFoundException;

@Service
public class RobotService implements IRobotService {
	@Autowired
	private RobotMapper robotMapper;

	public List<Robot> selectRobotByNike(String nike) {

		Integer count = robotMapper.selectCountByNike(nike);
		List<Robot> list  = new ArrayList<Robot>();
		if(count==0) {
			throw new NikeNotFoundException("˻Ѳ");
		}else {
			list = robotMapper.selectRobotByNike(nike);
		}

		return list;
	}

}
