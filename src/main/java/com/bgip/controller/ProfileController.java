package com.bgip.controller;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import com.bgip.filter.AuthFilter;
import com.bgip.model.ResponseBean;
import com.bgip.model.user.Profile;
import com.bgip.service.UploadServices;

@Path("/profile")
@Produces(APPLICATION_JSON)
public class ProfileController extends BaseController{

	@Autowired
	UploadServices uploadServices;
	
	
	private AuthFilter authFilter;
	
	
	

	@PUT
	@Path("/storage")
	@Produces(APPLICATION_JSON)
	public ResponseBean updateStorageType( @HeaderParam("token") String token, Profile profile) throws Exception{
		ResponseBean response = null;
		this.authFilter = new AuthFilter();
		 String loginUser = this.authFilter.getUserNameFromToken(token);
		response = uploadServices.profileStorageTypeCreate(profile, loginUser);
		
		return response;
		
	}
	
	
	@GET
	@Path("/storage")
	@Produces(APPLICATION_JSON)
	public Profile getProfile( @HeaderParam("token") String token )throws Exception{
		this.authFilter = new AuthFilter();
		 String loginUser = this.authFilter.getUserNameFromToken(token);
		return uploadServices.getProfile(loginUser);
	}
	
	
	
	
	
}
