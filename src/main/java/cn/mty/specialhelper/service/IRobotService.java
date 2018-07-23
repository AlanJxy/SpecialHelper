package cn.mty.specialhelper.service;

import java.util.List;

import cn.mty.specialhelper.bean.Robot;

public interface IRobotService {
	/**
	 * 通过机器人的nike查询机器人的所有信息
	 * @param nike
	 * @return 
	 */
	List<Robot> selectRobotByNike(String nike);
	

}
