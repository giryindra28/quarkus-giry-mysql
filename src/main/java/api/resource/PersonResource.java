package api.resource;

import api.dto.PersonDto.MoneyRequestDto;
import api.dto.PersonDto.PersonRequestDto;
import api.service.PersonImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/person")
public class PersonResource {
    @Inject
    PersonImpl personSevice;

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(PersonRequestDto personRequestDto){
        return Response.ok(personSevice.create(personRequestDto)).build();
    }
    @GET
    public Response getAllPerson(){
        return Response.ok(personSevice.getAll()).build();
    }

    @GET
    @Path("{personId}")
    public Response getPersonById(@PathParam("personId") Long personId){
        return Response.ok(personSevice.getById(personId)).build();
    }

    @PUT
    @Path("/update/{personId}")
    public Response updatePerson(@PathParam("personId") Long personId,  PersonRequestDto
                                 personRequestDto){
        return Response.ok(personSevice.update(personId, personRequestDto)).build();
    }

    @DELETE
    @Path("/delete/{personId}")
    public Response deletePersonById(@PathParam("personId") Long personId){
        personSevice.delete(personId);
        return Response.noContent().build();
    }

    @PUT
    @Path("/topup/{personId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMoney(@PathParam("personId") Long personId, MoneyRequestDto moneyRequestDto){
        return Response.ok(personSevice.topupMoney(personId, moneyRequestDto)).build();
    }


}
