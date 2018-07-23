package cn.mty.specialhelper.mapper;



import cn.mty.specialhelper.bean.FlockSettings;

public interface FlockSettingsMapper {
	/**
	 * ���Ⱥ����
	 * @param fsFlockSettings
	 */
	void insertFlockSettings(FlockSettings fsFlockSettings);
	/**
	 * ͨ��Ⱥid��ѯȺ�ķ�������
	 * @param flockId
	 * @return ����Ⱥ����
	 */
	FlockSettings selectRoomIdByFlockId(String flockId);
	/**
	 * ͨ��flockId��ѯ�Ƿ������Ⱥ
	 * @param flockId
	 * @return 0����û�У�1����
	 */
	Integer selectCunotByFlockId(String flockId);
	/**
	 * �޸�Ⱥ����
	 * @param flocksettings Ҫ�޸ĵ�����
	 */
	void updeteFlockSettings(FlockSettings flocksettings);
	
	

}
