package com.techm.ms.resource;


import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.techm.ms.exception.CustomError;
import com.techm.ms.model.User;
import com.techm.ms.model.representation.Resource;
import com.techm.ms.model.representation.ResourceCollection;
import com.techm.ms.service.UserService;

@Controller
public class UserResourceImpl implements UserResource {
	
	
	public static final Logger logger = LoggerFactory.getLogger(UserResourceImpl.class);

	@Autowired
	UserService userService; //Service which will do all data retrieval/manipulation work

	private static String baseUrl = "/users";


	@Override
	public Response getUser(long userid) {
		User user = userService.findById(userid);
		if (user == null) {
			return Response.status(404).entity(new CustomError("Account with id " + userid + " not found","NOT_FOUND")).build();
		}
		
		Link link = Link.fromUri(baseUrl).rel("self").build();	

		return Response.ok(user).links(link).build();
	}


	@Override
	public Response addUser(long userid, String name,int age, int accountId)
	{
		User user = userService.findById(userid);
		Link link = Link.fromUri(baseUrl).rel("self").build();
		if (user != null) {
			return Response.status(409).entity(new CustomError("Unable to create. A Account with name already exist","409")).build();
		}
		User newUser = new User(userid,name,age,accountId);
		userService.saveUser(newUser);
		Resource<User> resource = new Resource<>(newUser);
		return Response.ok(resource).links(link).build();
	}


	@Override
	public Response findAllUsers() {
		List<User> users = userService.findAllUsers();	
		if (users == null) {
			return Response.noContent().build();
		}		
		Link link = Link.fromUri(baseUrl).rel("self").build();		
		ResourceCollection<User> resource = new ResourceCollection<>(users);
		return Response.ok(resource).links(link).build();
	}
	
}
