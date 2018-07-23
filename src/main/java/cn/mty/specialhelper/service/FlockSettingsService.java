package cn.mty.specialhelper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mty.specialhelper.bean.FlockSettings;
import cn.mty.specialhelper.mapper.FlockSettingsMapper;
@Service
public class FlockSettingsService implements IFlockSettingsService{
	@Autowired
	private FlockSettingsMapper flockSettingsMapper;

	public void insertFlockSettings(FlockSettings FlockSettings){
		System.out.println("insertFlockSettings");
		Integer count = flockSettingsMapper.selectCunotByFlockId(FlockSettings.getFlockId());
		 if(count==1) {
			 flockSettingsMapper.updeteFlockSettings(FlockSettings);
		    	
		    }else {
		    	flockSettingsMapper.insertFlockSettings(FlockSettings);
		    	
		    }
			
		
		
	}

	public FlockSettings selectRoomIdByFlockId(String flockId) {
		
		FlockSettings flockSettings = flockSettingsMapper.selectRoomIdByFlockId(flockId);
		return flockSettings;
	}

}
