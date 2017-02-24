package com.aplikasi.penjualan.controller;

import com.aplikasi.penjualan.dao.TransaksiPenjualanDao;
import com.aplikasi.penjualan.entity.TransaksiPenjualan;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/penjualan")
public class TransaksiPenjualanHtmlController {

    @Autowired
    private TransaksiPenjualanDao kd;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @InitBinder
    public void initBinder2(WebDataBinder binder) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setGroupingUsed(false);
        binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, numberFormat, true));
    }

    @RequestMapping("/list")
    public void daftarTransaksiPenjualan(Model m) {
        m.addAttribute("daftarTransaksiPenjualan", kd.findAll());
    }

    @RequestMapping(value = "/cari")
    public String cariTransaksiPenjualan(@RequestParam("noNota") String noNota, Model m) {

        //defaultnya isi dengan object baru
        m.addAttribute("cariTransaksiPenjualan", kd.findOne(noNota));
        return "/penjualan/cari";

    }

    @RequestMapping("/hapus")
    public String hapus(@RequestParam("noNota") String noNota) {
        kd.delete(noNota);
        return "redirect:list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String tampilkanFormEdit(@RequestParam(value = "noNota", required = false) String noNota,
            Model m) {

        // defaultnya, isi dengan object baru
        m.addAttribute("penjualan", new TransaksiPenjualan());

        if (noNota != null && !noNota.isEmpty()) {
            TransaksiPenjualan k = kd.findOne(noNota);
            if (k != null) {
                m.addAttribute("penjualan", k);
            }
        }
        return "/penjualan/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String prosesFormEdit(@Valid TransaksiPenjualan k, BindingResult errors) {
        if (errors.hasErrors()) {
            return "/penjualan/edit";
        }
        kd.save(k);
        if (k.getNoNota() != null && !k.getNoNota().isEmpty()) {
            TransaksiPenjualan tp = kd.findOne(k.getNoNota());
            if (tp != null) {
                Long banyak = tp.getJumlah();
                Long harga = tp.getBarang().getHargajual();
                Long total_belanja = (banyak * harga);
                tp.setTotal(total_belanja);
                kd.save(tp);
            }
        }

        return "redirect:list";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String tampilkanForm(@RequestParam(value = "noNota", required = false) String noNota,
            Model m) {

        // defaultnya, isi dengan object baru
        m.addAttribute("penjualan", new TransaksiPenjualan());

        if (noNota != null && !noNota.isEmpty()) {
            TransaksiPenjualan k = kd.findOne(noNota);
            if (k != null) {
                m.addAttribute("penjualan", k);
            }
        }
        return "/penjualan/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String prosesForm(@Valid TransaksiPenjualan k, BindingResult errors) {
        if (errors.hasErrors()) {
            return "/penjualan/form";
        }
        kd.save(k);
        if (k.getNoNota() != null && !k.getNoNota().isEmpty()) {
            TransaksiPenjualan tp = kd.findOne(k.getNoNota());
            if (tp != null) {
                Long banyak = tp.getJumlah();
                Long harga = tp.getBarang().getHargajual();
                Long total_belanja = (banyak * harga);
                tp.setTotal(total_belanja);
                kd.save(tp);
            }
        }

        return "redirect:list";
    }
    
    @RequestMapping("/allreport")
    public void reportTransaksiPenjualan(Model m) {
        Locale lokal = new Locale("id", "ID");
        String now = new SimpleDateFormat("EEEE, dd MMMM yyyy", lokal).format(new Date());
        m.addAttribute("now", now);
        m.addAttribute("reportTransaksiPenjualan", kd.findAll());
    }

    @RequestMapping("/report_custom")
    public void searchReportCustom(Model m) {
        m.addAttribute("searchAllReport", kd.findAll());
    }

    @RequestMapping("/costum_report")
    public void searchCustomReport(@RequestParam(value = "mulai") Date mulai ,
            @RequestParam(value = "selesai") Date selesai,
            Model m) {
        Locale lokal = new Locale("id", "ID");
        String now = new SimpleDateFormat("EEEE, dd MMMM yyyy", lokal).format(new Date());
        m.addAttribute("now", now);
        PageRequest page = new PageRequest(0, 100);
        m.addAttribute("searchCustomReport", kd.searchCustomReport(mulai, selesai, page));
    }
}
