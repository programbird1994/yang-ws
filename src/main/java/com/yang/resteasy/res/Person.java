package com.yang.resteasy.res;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/person")
public class Person {

    @GET
    @Path("/test")
    @Consumes({MediaType.MEDIA_TYPE_WILDCARD})
    @Produces({MediaType.MEDIA_TYPE_WILDCARD})
    public String getPerson() {
        return "Hello ";
    }
}
