package com.aldev.Services;

import com.aldev.Models.Mahasiswa;
import com.aldev.Util.PdfGenerator;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class MahasiswaService {
    @Inject
    EntityManager em;
    @Inject
    PdfGenerator pdfGenerator;

    public Response GetAll() throws Exception {
        String samplePath = "export/sample/Simple_Blue.jrxml";
        String outputPath = "export/pdf/report.pdf";
        Map<String,Object> param = new HashMap<>();
        pdfGenerator.Generate(samplePath,outputPath,param);
        return Response.ok(em.createNamedQuery("Mahasiswa.findAll", Mahasiswa.class).getResultList().toArray()
        ).build();
    }

    @Transactional
    public Response CreateMahasiswa(Mahasiswa requestMahasiswa){
        em.persist(requestMahasiswa);
        JsonObject result = new JsonObject();
        result.put("data",requestMahasiswa);
        return Response.ok(result).build();
    }
}
