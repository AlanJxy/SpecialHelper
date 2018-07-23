package cn.mty.specialhelper.service;

import cn.mty.specialhelper.bean.FlockSettings;

public interface IFlockSettingsService {
	/**
	 * 添加群设置
	 * @param fsFlockSettings
	 */
	void insertFlockSettings(FlockSettings FlockSettings);
	/**
	 * 通过群id查询群的房间设置
	 * @param flockId
	 * @return 返回群设置
	 */
	FlockSettings selectRoomIdByFlockId(String flockId);

	
	
}
