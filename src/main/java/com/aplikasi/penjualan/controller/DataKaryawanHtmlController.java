/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplikasi.penjualan.controller;

import com.aplikasi.penjualan.dao.DataKaryawanDao;
import com.aplikasi.penjualan.entity.DataKaryawan;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
@RequestMapping("/karyawan")
public class DataKaryawanHtmlController {

    @Autowired
    private DataKaryawanDao kd;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/list")
    public void daftarKaryawan(Model m) {
        m.addAttribute("daftarKaryawan", kd.findAll());
    }

    @RequestMapping(value = "/cari")
    public String cariKaryawan(@RequestParam("nik") String nik, Model m) {

        //defaultnya isi dengan object baru
        m.addAttribute("cariKaryawan", kd.findOne(nik));
        return "/karyawan/cari";

    }

    @RequestMapping("/hapus")
    public String hapus(@RequestParam("nik") String nik) {
        kd.delete(nik);
        return "redirect:list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String tampilkanFormEdit(@RequestParam(value = "nik", required = false) String nik,
            Model m) {

        // defaultnya, isi dengan object baru
        m.addAttribute("karyawan", new DataKaryawan());

        if (nik != null && !nik.isEmpty()) {
            DataKaryawan k = kd.findOne(nik);
            if (k != null) {
                m.addAttribute("karyawan", k);
            }
        }
        return "/karyawan/edit";
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String prosesFormEdit(@Valid DataKaryawan k, BindingResult errors) {
        if (errors.hasErrors()) {
            return "/karyawan/edit";
        }
        kd.save(k);
        return "redirect:list";
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String tampilkanForm(@RequestParam(value = "nik", required = false) String nik,
            Model m) {

        // defaultnya, isi dengan object baru
        m.addAttribute("karyawan", new DataKaryawan());

        if (nik != null && !nik.isEmpty()) {
            DataKaryawan k = kd.findOne(nik);
            if (k != null) {
                m.addAttribute("karyawan", k);
            }
        }
        return "/karyawan/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String prosesForm(@Valid DataKaryawan k, BindingResult errors) {
        if (errors.hasErrors()) {
            return "/karyawan/form";
        }
        kd.save(k);
        return "redirect:list";
    }

}
