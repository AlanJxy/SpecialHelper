package cn.mty.specialhelper.service;

import cn.mty.specialhelper.bean.FlockSettings;

public interface IFlockSettingsService {
	/**
	 * ���Ⱥ����
	 * @param fsFlockSettings
	 */
	void insertFlockSettings(FlockSettings FlockSettings);
	/**
	 * ͨ��Ⱥid��ѯȺ�ķ�������
	 * @param flockId
	 * @return ����Ⱥ����
	 */
	FlockSettings selectRoomIdByFlockId(String flockId);

	
	
}
