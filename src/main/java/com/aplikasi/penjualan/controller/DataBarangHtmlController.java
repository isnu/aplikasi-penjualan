/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplikasi.penjualan.controller;

import com.aplikasi.penjualan.dao.DataBarangDao;
import com.aplikasi.penjualan.entity.DataBarang;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author USER
 */
@Controller
@RequestMapping("/barang")
public class DataBarangHtmlController {

    @Autowired
    private DataBarangDao kd;

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
    public void daftarDataBarang(Model m) {
        m.addAttribute("daftarBarang", kd.findAll());
    }

    @RequestMapping(value = "/cari")
    public String cariDataKaryawan(@RequestParam("kode") String kode, Model m) {

        //defaultnya isi dengan object baru
        m.addAttribute("cariBarang", kd.findOne(kode));
        return "/barang/cari";

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
        m.addAttribute("barang", new DataBarang());

        if (kode != null && !kode.isEmpty()) {
            DataBarang k = kd.findOne(kode);
            if (k != null) {
                m.addAttribute("barang", k);
            }
        }
        return "/barang/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String prosesFormEdit(@Valid DataBarang k, BindingResult errors) {
        if (errors.hasErrors()) {
            return "/barang/edit";
        }
        kd.save(k);
        return "redirect:list";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String tampilkanForm(@RequestParam(value = "kode", required = false) String kode,
            Model m) {

        // defaultnya, isi dengan object baru
        m.addAttribute("barang", new DataBarang());

        if (kode != null && !kode.isEmpty()) {
            DataBarang k = kd.findOne(kode);
            if (k != null) {
                m.addAttribute("barang", k);
            }
        }
        return "/barang/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String prosesForm(@Valid DataBarang k, BindingResult errors) {
        if (errors.hasErrors()) {
            return "/barang/form";
        }
        kd.save(k);
        return "redirect:list";
    }

}
