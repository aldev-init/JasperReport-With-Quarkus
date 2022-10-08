package com.aldev.Util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Map;

@ApplicationScoped
public class PdfGenerator {
    @Inject
    DataSource dataSource;

    public void Generate(String jasperSamplePath, String outputFileName, Map<String,Object> map ) throws Exception{
        /*
            Untuk jasper report ini,bisa set langsung query ke jasperstudio dan langsung memasukan ke
            file jrxmlnya,tapi bisa juga lewat custom query dari JasperDesign dan JRDesignQuery
         */
        System.out.println("Connection: "+dataSource.getConnection());
        //get connection
        Connection connection = dataSource.getConnection();
        //load sample
        JasperDesign jasperDesign = JRXmlLoader.load(jasperSamplePath);
        JRDesignQuery query = new JRDesignQuery();
        query.setText("select id,nim,nama_lengkap,jurusan from mahasiswa m where m.jurusan = 'RPL'");
        //set query
        jasperDesign.setQuery(query);
        //insert to report
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        //generate report to pdf
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,connection);
        //view report
        JasperViewer.viewReport(jasperPrint);
    }

}
