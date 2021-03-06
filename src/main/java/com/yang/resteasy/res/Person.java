package com.yang.resteasy.res;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@Path("/person")
@Component
public class Person {

    @Path("/{name}")
    @GET
//  @Produces({MediaType.TEXT_PLAIN})
    public String getPerson(@PathParam("name") String name) {
        return "Hello "+name;
    }
}
