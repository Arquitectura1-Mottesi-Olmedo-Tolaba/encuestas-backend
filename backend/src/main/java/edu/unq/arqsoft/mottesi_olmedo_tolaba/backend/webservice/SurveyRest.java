package edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.webservice;

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

import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.dto.SurveyDTO;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model.Survey;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.service.GenericService;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.service.SurveyService;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.utils.ResponseGenerator;

@Service
@Produces("application/json")
@Consumes("application/json")
@Path("/surveys")
public class SurveyRest  extends GenericRest<Survey> {

	@Autowired
	private ResponseGenerator responseGenerator;
	
	@Autowired
	private SurveyService subjectService;
	

	@Override
	public GenericService<Survey> getService() {
		return subjectService;
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
	public Response create(@Context HttpServletRequest request, Survey survey) {
		return super.create(survey);
	}


	@PUT
	public Response update(@Context HttpServletRequest request, Survey survey) {
		return super.update(survey);
	}
	
	@POST
	public Response create(@Context HttpServletRequest request, SurveyDTO surveyDTO) {
		//return subjectService.createSurveyFromDto();
		try {
			Survey survey = subjectService.createSurveyFromDto(surveyDTO);
			return responseGenerator.buildSuccessResponse(survey);
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@Context HttpServletRequest request, @PathParam("id") final Long id) {
		return super.delete(id);
	}

}
