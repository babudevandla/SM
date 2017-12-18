package com.sm.portal.service;

import java.util.List;

import com.sm.portal.model.EDairyDto;
import com.sm.portal.model.Users;

public interface EDairyService {

	void saveEDairyData(EDairyDto eDairyDto, Users user);

	List<EDairyDto> getEDairyList(Integer userId);

	EDairyDto getEDairyDataById(Integer dairyId);

	void updateEDairyData(EDairyDto eDairyDto, Users user);

}
