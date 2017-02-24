/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplikasi.penjualan.controller;

import com.aplikasi.penjualan.dao.DataPelangganDao;
import com.aplikasi.penjualan.entity.DataPelanggan;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author USER
 */
@Controller
@RequestMapping("/pelanggan")
public class DataPelangganHtmlController {

    @Autowired
    private DataPelangganDao kd;

  
    @RequestMapping("/list")
    public void daftarDataPelanggan(Model m) {
        m.addAttribute("daftarDataPelanggan", kd.findAll());
    }

    @RequestMapping(value = "/cari")
    public String cariDataPelanggan(@RequestParam("kode") String kode, Model m) {

        //defaultnya isi dengan object baru
        m.addAttribute("cariPelanggan", kd.findOne(kode));
        return "/pelanggan/cari";

    }

    @RequestMapping("/hapus")
    public String hapus(@RequestParam("kode") String kode) {
        kd.delete(kode);
        return "redirect:list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String tampilkanFormEdit(@RequestParam(value = "kode", required = false) String kode,
            Model m) {

        // defaultnya, isi dengan object baru
        m.addAttribute("pelanggan", new DataPelanggan());

        if (kode != null && !kode.isEmpty()) {
            DataPelanggan k = kd.findOne(kode);
            if (k != null) {
                m.addAttribute("pelanggan", k);
            }
        }
        return "/pelanggan/edit";
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String prosesFormEdit(@Valid DataPelanggan k, BindingResult errors) {
        if (errors.hasErrors()) {
            return "/pelanggan/edit";
        }
        kd.save(k);
        return "redirect:list";
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String tampilkanForm(@RequestParam(value = "kode", required = false) String kode,
            Model m) {

        // defaultnya, isi dengan object baru
        m.addAttribute("pelanggan", new DataPelanggan());

        if (kode != null && !kode.isEmpty()) {
            DataPelanggan k = kd.findOne(kode);
            if (k != null) {
                m.addAttribute("pelanggan", k);
            }
        }
        return "/pelanggan/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String prosesForm(@Valid DataPelanggan k, BindingResult errors) {
        if (errors.hasErrors()) {
            return "/pelanggan/form";
        }
        kd.save(k);
        return "redirect:list";
    }

}
