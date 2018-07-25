package cn.mty.specialhelper.mapper;



import cn.mty.specialhelper.bean.FlockSettings;

public interface FlockSettingsMapper {
	/**
	 * 添加群设置
	 * @param fsFlockSettings
	 */
	void insertFlockSettings(FlockSettings fsFlockSettings);
	/**
	 * 通过群id查询群的房间设置
	 * @param flockId
	 * @return 返回群设置
	 */
	FlockSettings selectRoomIdByFlockId(String flockId);
	/**
	 * 通过flockId查询是否有这个群
	 * @param flockId
	 * @return 0代表没有，1表有
	 */
	Integer selectCunotByFlockId(String flockId);
	/**
	 * 修个群设置
	 * @param flocksettings 要修改的内容
	 */
	void updeteFlockSettings(FlockSettings flocksettings);



}
