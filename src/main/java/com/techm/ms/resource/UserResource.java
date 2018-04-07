package com.techm.ms.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.techm.ms.model.swagger.UserResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This is the Interface definition for User Resource
 * 
 */
@Api("user")
@Path("/users")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface UserResource {
	 /**
     * Service definition which add user
     * @return User - Returns the details of the user being added
     */
	@POST
	@Path("adduser")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@ApiOperation(
			value = "Add User",
			notes = "Add User",
			response = UserResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 201, message = "OK"),
					@ApiResponse(code = 409, message = "Unable to create. A Account with name already exist.")					
					})
	public Response addUser(@FormParam("userid")long userid,@FormParam("name")String name,@FormParam("age")int age, @FormParam("accountId")int accountId);
	
	
	 /**
     * Service definition which returns all the users
     * @return User - Returns the details of the users being searched
     */
	@GET
	@Path("finduserid/{userid}")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@ApiOperation(
			value = "Get User Resource",
			notes = "Returns user in ResourceCollection representation format",
			response = UserResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 404, message = "User not found")					
					})
	public Response getUser(@PathParam("userid") long userid);
	
	/**
     * Service definition which returns all the accounts
     * @return User - Returns the details of the accounts being searched
     */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Get Account Resource",
			notes = "Returns all the accounts in ResourceCollection representation format",
			response = UserResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 204, message = "No Content")					
					})
	public Response findAllUsers();
	
}