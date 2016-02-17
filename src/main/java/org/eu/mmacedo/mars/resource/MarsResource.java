package org.eu.mmacedo.mars.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.eu.mmacedo.mars.service.MarsService;
//import org.springframework.stereotype.Component;

//@Component
@Path("/rest")
public class MarsResource {
	
	@POST
    @Produces("text/plain")	
	@Path("/mars/{command}")
	public Response move(@PathParam("command") String command) {
		try {
			String resp = new MarsService().run(command);
			return Response.ok(resp).build();
		} catch(Throwable e) {
			throw new WebApplicationException(400);
		}
	}
}
