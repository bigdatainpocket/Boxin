package com.bgip.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bgip.constants.BgipConstants;
import com.bgip.constants.StatusCodes;
import com.bgip.model.ResponseBean;
import com.bgip.model.user.Profile;
import com.bgip.mongo.MongoManager;

@Component
public class ProfileDAO extends BaseDAO{

	
	@Autowired
	MongoManager mongoManager;

	@Autowired
	BgipConstants bgipConstants;

	@Autowired
	StatusCodes statusCodes;
	

	public ResponseBean profileStorageTypeCreate( Profile profile  ) throws Exception {
		
		ResponseBean response = null;
		
		Profile profileFromDB = mongoManager.getObjectByField(BgipConstants.PROFILE_COLLECTION, "userName", profile.getUserName(), Profile.class);
		
	
		
		if( profileFromDB == null) {
			mongoManager.insertCollection(BgipConstants.PROFILE_COLLECTION, profile);
			response = new ResponseBean( StatusCodes.SUCCESS_MESSAGE);
		}else {
			mongoManager.updateByField(BgipConstants.PROFILE_COLLECTION, "userName", profile.getUserName(), "storageType", profile.getStorageType());
			response = new ResponseBean( StatusCodes.SUCCESS_MESSAGE);
		}
		
		return response;
	}
	
	
	public Profile getProfile( String userName) throws Exception{
		
		System.out.println(" user : "+userName);
		
		return  mongoManager.getObjectByField(BgipConstants.PROFILE_COLLECTION, "userName", "muthahar", Profile.class);
	}
	
	
	
	
	
	
	
}
