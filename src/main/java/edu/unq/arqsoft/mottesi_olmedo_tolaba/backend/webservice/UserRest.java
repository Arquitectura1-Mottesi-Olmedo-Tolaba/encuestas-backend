package edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.webservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.dto.DirectorDTO;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.dto.UserAuthDTO;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model.Offer;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model.UserCredential;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.service.DirectorService;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.service.GenericService;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.service.UserService;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.utils.ResponseGenerator;

@Service
@Produces("application/json")
@Consumes("application/json")
@Path("/users")
public class UserRest  extends GenericRest<UserCredential> {

	@Autowired
	private ResponseGenerator responseGenerator;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DirectorService directorService;
	

	@Override
	public GenericService<UserCredential> getService() {
		return userService;
	}

	@GET
	@Path("/all")
	public Response findAll() {
		return super.findAll();
	}

	@GET
	@Path("/{id}")
	public Response find(@Context HttpServletRequest request, @PathParam("id") final Long id) {
		return super.find(id);
	}
	
	@GET
	public Response ok() {
		return responseGenerator.responseOK("OK");
	}
	
	@POST
	public Response create(@Context HttpServletRequest request, UserCredential user) {
		return super.create(user);
	}
	
	@GET
	@Path("/getDirector")
	public Response offers(@Context HttpServletRequest request) {
		try {
			DirectorDTO director = directorService.getDirectorDTO();
			return responseGenerator.buildSuccessResponse(director);
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}
	

	@POST
	@Path("/signup")
	public Response signup(@Context HttpServletRequest request, UserCredential user) {
		try {
			return responseGenerator.buildSuccessResponse(userService.signup(user));
		} catch( Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}
	
	@POST
	@Path("/getOrCreateProfile")
	public Response getOrCreateProfile(@Context HttpServletRequest request, UserAuthDTO user) {
		try {
			UserCredential userC = userService.findByEmail(user.getEmail());
			if (userC != null){
				return responseGenerator.buildSuccessResponse(userService.signin(userC));
			}
			else{
				userC = new UserCredential(user.getName() + user.getSurname(), 
						user.getSurname(), user.getEmail());
				return responseGenerator.buildSuccessResponse(userService.signup(userC));
			}
			
		} catch( Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}
	
	
	@POST
	@Path("/signin")
	public Response signin(@Context HttpServletRequest request, UserCredential user) {
		try {
			return responseGenerator.buildSuccessResponse(userService.signin(user));
		} catch( Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}

	@PUT
	public Response update(@Context HttpServletRequest request, UserCredential user) {
		return super.update(user);
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@Context HttpServletRequest request, @PathParam("id") final Long id) {
		return super.delete(id);
	}

}
