package org.eu.mmacedo.mars.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

//import org.springframework.stereotype.Component;

//@Component
@Path("/health")
public class HealthResource {
    @GET
    @Produces("text/plain")	
    public Response health() {
        return Response.ok("Jersey: Up and Running! \n").build();
    }
}