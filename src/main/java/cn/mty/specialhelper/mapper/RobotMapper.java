package cn.mty.specialhelper.mapper;

import java.util.List;

import cn.mty.specialhelper.bean.Robot;



public interface RobotMapper {
	
	/**
	 * ͨ�������˵�nike��ѯ�����˵�������Ϣ
	 * @param nike
	 * @return 
	 */
	List<Robot> selectRobotByNike(String nike);
	/**
	 * ͨ��Nike��ѯ�������Ƿ����
	 * @param nike
	 * @return 0���ǻ����˲����ڣ�����0���ǻ����˴���
	 */
	Integer selectCountByNike(String nike);
	
	

}
