package org.acme.client;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.acme.dto.CostumerDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/costumers")
@RegisterRestClient
@ApplicationScoped
public interface CostumerClient {

    @GET
    @Path("/{id}")
    CostumerDTO getCostumerById(@PathParam("id") Long id);
}
