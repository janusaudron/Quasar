package co.com.janus.quasar.web;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import co.com.janus.quasar.business.IQuasarService;
import co.com.janus.quasar.business.QuasarService;
import co.com.janus.quasar.business.domain.Constant;
import co.com.janus.quasar.business.domain.rest.PositionRequest;
import co.com.janus.quasar.business.domain.rest.PositionResponse;
import co.com.janus.quasar.business.domain.rest.Satellite;

/**
 * The Class QuasarController.
 * @author Oscar Ortiz
 */
@Path("/")
@RequestScoped
public class QuasarController {
	
	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger(QuasarController.class);
	
	/**
	 * Rest Service that returns the message and the position
	 *
	 * @param request the request
	 * @return the location message
	 */
	@POST
	@Path("/topsecret")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getLocationMessage(PositionRequest request) {
		try {
			IQuasarService quasarService = new QuasarService();
			
			PositionResponse positionResponse = quasarService.getLocationMessage(request);
			return Response.status(Response.Status.OK).entity(positionResponse).build();
		} catch (Exception e) {
			logger.error("Error procesando mensaje", e);
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
	}
	
	/**
	 * Rest Service that stores the message in memory
	 *
	 * @param request the request
	 * @param satelliteName the satellite name
	 * @return the response
	 */
	@POST
	@Path("/topsecret_split/{satelliteName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response setLocationMessage(Satellite request, @PathParam("satelliteName") String satelliteName) {
		try {
			request.setName(satelliteName);
			if(Constant.KENOBI.equals(satelliteName)) {
				QuasarMemoria.satelite1=request;
			} else if(Constant.SKYWALKER.equals(satelliteName)) {
				QuasarMemoria.satelite2=request;
			} else {
				QuasarMemoria.satelite3=request;
			}
			
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			logger.error("Error procesando mensaje", e);
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
	}
	
	/**
	 * Rest Service that returns the split message.
	 *
	 * @return the location message split
	 */
	@GET
	@Path("/topsecret_split")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getLocationMessageSplit(){
		IQuasarService quasarService = new QuasarService();
		try {
			PositionRequest request = new PositionRequest();
			request.setSatellites(new ArrayList<Satellite>());
			
			request.getSatellites().add(QuasarMemoria.satelite1);
			request.getSatellites().add(QuasarMemoria.satelite2);
			request.getSatellites().add(QuasarMemoria.satelite3);
			
			PositionResponse positionResponse = quasarService.getLocationMessage(request);
			
			return Response.status(Response.Status.OK).entity(positionResponse).build();
		} catch (Exception e) {
			logger.error("Error procesando mensaje", e);
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
	}

}
