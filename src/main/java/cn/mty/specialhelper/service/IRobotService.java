package cn.mty.specialhelper.service;

import java.util.List;

import cn.mty.specialhelper.bean.Robot;

public interface IRobotService {
	/**
	 * ͨ�������˵�nike��ѯ�����˵�������Ϣ
	 * @param nike
	 * @return 
	 */
	List<Robot> selectRobotByNike(String nike);
	

}
