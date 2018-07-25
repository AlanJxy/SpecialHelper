package cn.mty.specialhelper.mapper;

import java.util.List;

import cn.mty.specialhelper.bean.Robot;



public interface RobotMapper {

	/**
	 * 通过机器人的nike查询机器人的所有信息
	 * @param nike
	 * @return
	 */
	List<Robot> selectRobotByNike(String nike);
	/**
	 * 通过Nike查询机器人是否存在
	 * @param nike
	 * @return 0就是机器人不存在，大于0就是机器人存在
	 */
	Integer selectCountByNike(String nike);



}
