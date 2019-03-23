package edu.upc.eetac.dsa.services;


import edu.upc.eetac.dsa.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/MyBike", description = "Endpoint to My Bike edu.upc.eetac.dsa.services")
@Path("/MyBike")
public class MyBikeServices {

    private MyBike myBike;

    public MyBikeServices() throws StationFullException, StationNotFoundException {
        this.myBike = MyBikeImpl.getInstance();
        this.myBike.addUser("user1", "Juan", "Lopex");


        this.myBike.addStation("Station1","description:: station1", 10, 3, 3);
        this.myBike.addStation("Station2","description:: station2", 10, 3, 3);

        this.myBike.addBike("bike101", "descripton", 25.45, "Station1");
        this.myBike.addBike("bike102", "descripton", 70.3, "Station1");
        this.myBike.addBike("bike103", "descripton", 10.2, "Station1");

        this.myBike.addBike("bike201", "descripton", 1325.45, "Station2");
        this.myBike.addBike("bike202", "descripton", 74430.3, "Station2");
        this.myBike.addBike("bike203", "descripton", 1320.2, "Station2");
    }

    @GET
    @ApiOperation(value = "get list of bikes ordered by km", notes = "X")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Bike.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Station not found")
    })
    @Path("/1{idStation}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBikesOrderedByKm(@PathParam("idStation") String idStation) {
        List<Bike> bikes;
        try {
            bikes = this.myBike.bikesByStationOrderByKms(idStation);
            GenericEntity<List<Bike>> entity = new GenericEntity<List<Bike>>(bikes) {};
            return Response.status(201).entity(entity).build();
        } catch (StationNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "get list of bikes of a user", notes = "X")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Bike.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Station not found")

    })
    @Path("/2{idUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response bikesByUser(@PathParam("idUser") String idUser) {

        try{
            List<Bike> bicis = this.myBike.bikesByUser(idUser);
            GenericEntity<List<Bike>> entity = new GenericEntity<List<Bike>>(bicis) {};
            return Response.status(201).entity(entity).build();
        }
        catch (UserNotFoundException e){
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "get a bike", notes = "X")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Bike.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Station not found/User not found"),
            @ApiResponse(code = 402, message = "StationNotFoundException")


    })
    @Path("/3{idStation}/{idUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response bikesByUser(@PathParam("idStation") String idStation, @PathParam("idUser") String idUser) {

        try {
            Bike bike = this.myBike.getBike(idStation, idUser);
            GenericEntity<Bike> entity = new GenericEntity<Bike>(bike) {
            };
            return Response.status(201).entity(entity).build();
        } catch (StationNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(402).build();
        }
    }

    @POST
    @ApiOperation(value = "add user", notes = "X")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })
    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User u) {
        String idUser = u.getIdUser();
        String name = u.getName();
        String surname = u.getSurname();
        this.myBike.addUser(idUser, name, surname);
        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "add station", notes = "X")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })
    @Path("/addStation")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStation(Station s) {
        String idStation = s.getidStation();
        String description = s.getDescription();
        int max = s.getMax();
        double lat = s.getLat();
        double lon = s.getLon();
        this.myBike.addStation(idStation, description, max, lat, lon);
        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "add bike", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "StationFullException"),
            @ApiResponse(code = 402, message = "StationNotFoundException")
    })
    @Path("/addBike")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBike(Bike bike) {
        String idBike = bike.getId();
        String description = bike.getDescription();
        double kms = bike.getKm();
        String idStation = bike.getIdStation();

        try {
            this.myBike.addBike(idBike, description, kms, idStation);
            return Response.status(201).build();
        } catch (StationFullException e) {
            e.printStackTrace();
            return Response.status(402).build();
        } catch (StationNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

}
