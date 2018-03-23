package com.sm.portal.uniquekeys;

import com.sm.portal.model.UniqueKey;

public interface UniqueKeyDao {

	public UniqueKey getUniqueKey(Integer userId, String uniqueProperty, Integer noOfIds);
	
	public boolean updateUniqueKey(UniqueKey uniqueKey);
}
