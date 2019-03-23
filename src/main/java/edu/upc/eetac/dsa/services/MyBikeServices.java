package edu.upc.eetac.dsa.services;


import edu.upc.eetac.dsa.*;
import io.swagger.annotations.Api;

import javax.ws.rs.Path;

@Api(value = "/MyBike", description = "Endpoint to My Bike edu.upc.eetac.dsa.services")
@Path("/MyBike")
public class MyBikeServices {

    private MyBike myBike;
}
