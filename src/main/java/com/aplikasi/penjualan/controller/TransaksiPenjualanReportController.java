package com.aplikasi.penjualan.controller;

import com.aplikasi.penjualan.dao.TransaksiPenjualanDao;
import com.aplikasi.penjualan.entity.TransaksiPenjualan;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransaksiPenjualanReportController {
    
    @Autowired private TransaksiPenjualanDao kd;
    
    @RequestMapping("/penjualan/laporan")
    public ModelAndView generateReportPenjualan(ModelAndView m,
            @RequestParam(value = "format", required = false) String format){
        Iterable<TransaksiPenjualan> data = kd.findAll();
        m.addObject("dataSource", data);
        //System.out.println(data);
        //m.addObject("tanggalUpdate", new Date());
        m.addObject("format", "pdf");
        
        if(format != null && !format.isEmpty()){
            m.addObject("format", format);
        }

        m.setViewName("report_penjualan");
        return m;
    }
    
    @RequestMapping("/penjualan/kwitansi")
    public ModelAndView generateReportKwitansi(ModelAndView m,
            @RequestParam("noNota") String noNota, @RequestParam(value = "format", required = false) String format){
        
        //m.addObject("noNota", noNota);
        TransaksiPenjualan data = kd.findOne(noNota);
        m.addObject("noNota", data.getNoNota());
        m.addObject("noNotaTerakhir", data.getNoNotaTerakhir());
        m.addObject("tanggal", data.getTanggal());
        m.addObject("total", data.getTotal());
        m.addObject("jumlah", data.getJumlah());
        m.addObject("namaLengkap", data.getPelanggan().getNamaLengkap());
        m.addObject("alamat", data.getPelanggan().getAlamat());
        m.addObject("phone", data.getPelanggan().getPhone());
        m.addObject("namaBarang", data.getBarang().getNama());
        m.addObject("harga", data.getBarang().getHargajual());
        m.addObject("format", "pdf");
        
        if(format != null && !format.isEmpty()){
            m.addObject("format", format);
        }

        m.setViewName("report_kwitansi");
        return m;
    }
}
