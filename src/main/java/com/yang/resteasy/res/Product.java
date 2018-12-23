package com.yang.resteasy.res;


import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/product")
@Component
public class Product {

    @GET
    @Path("/{id}")
    public String getProduct(@PathParam("id") String id) {
        return "Product "+id;
    }

}
