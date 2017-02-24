package com.aplikasi.penjualan.controller;

import com.aplikasi.penjualan.dao.DataBarangDao;
import com.aplikasi.penjualan.entity.DataBarang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DataBarangReportController {
    
    @Autowired private DataBarangDao kd;
    
    @RequestMapping("/barang/laporan")
    public ModelAndView generateReportBarang(ModelAndView m,
            @RequestParam(value = "format", required = false) String format){
        Iterable<DataBarang> data = kd.findAll();
        m.addObject("dataSource", data);
        //m.addObject("tanggalUpdate", new Date());
        m.addObject("format", "pdf");
        
        if(format != null && !format.isEmpty()){
            m.addObject("format", format);
        }

        m.setViewName("report_barang");
        return m;
    }
}
