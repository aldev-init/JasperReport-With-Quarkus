package com.aldev.Controller;

import com.aldev.Models.Mahasiswa;
import com.aldev.Services.MahasiswaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/mahasiswa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MahasiswaController {

    @Inject
    MahasiswaService mahasiswaService;

    @GET
    public Response getAll() throws Exception{
        return mahasiswaService.GetAll();
    }

    @POST
    public Response createMahasiswa(Mahasiswa request){
        return mahasiswaService.CreateMahasiswa(request);
    }
}
