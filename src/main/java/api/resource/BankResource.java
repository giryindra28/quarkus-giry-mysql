package api.resource;

import api.dto.BankDto.BankRequestDto;
import api.service.BankImpl;
import jakarta.enterprise.inject.build.compatible.spi.Validation;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.print.attribute.standard.Media;

@Path("/bank")
public class BankResource {

    @Inject
    BankImpl bankService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBank(){
        return Response.ok(bankService.getAll()).build();
    }

    @GET
    @Path("{bankId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBankById(@PathParam("bankId")Long bankId){
        return Response.ok(bankService.getById(bankId)).build();
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBank(BankRequestDto bankRequestDto){
        return Response.ok(bankService.create(bankRequestDto)).build();
    }

    @PUT
    @Path("/update/{bankId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBank(@PathParam("bankId") Long bankId, BankRequestDto bankRequestDto){
        return Response.ok(bankService.update(bankId,bankRequestDto)).build();
    }

    @DELETE
    @Path("/delete/{bankId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBankById(@PathParam("bankId") Long bankId){
        bankService.delete(bankId);
        return Response.noContent().build();
    }
}
