/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplikasi.penjualan.controller;

import com.aplikasi.penjualan.dao.DataBarangDao;
import com.aplikasi.penjualan.entity.DataBarang;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author USER
 */

@RestController
@RequestMapping("/api")
public class DataBarangController {

    @Autowired
    private DataBarangDao kd;

    //mengambil seluruh data
    @RequestMapping(value = "/barang", method = RequestMethod.GET)
    public Page<DataBarang> cariDataBarang(Pageable page) {
        return kd.findAll(page);
    }

    //menambah data
    @RequestMapping(value = "/barang", method = RequestMethod.POST)
    //menambahkan status created jika input berhasil
    @ResponseStatus(HttpStatus.CREATED)
    //request body untuk menambahkan parameter yang ada di body
    public void insertDataBarangBaru(@RequestBody @Valid DataBarang k) {
        kd.save(k);
    }

    //mengupdate data sesuai id yang diinput
    @RequestMapping(value = "/barang/{kode}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateDataBarang(@PathVariable("kode") String kode, @RequestBody @Valid DataBarang k) {
        k.setKode(kode);
        kd.save(k);
    }

    //menampilkan 1 data sesuai id yang diinput
    @RequestMapping(value = "/barang/{kode}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DataBarang> cariDataBarang(@PathVariable("kode") String kode) {
        DataBarang hasil = kd.findOne(kode);
        //menambahkan error 404 jika data yang dicari tidak ada
        if (hasil == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hasil, HttpStatus.OK);
    }

    //delete data
    @RequestMapping(value = "/barang/{kode}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void hapusDataBarang(@PathVariable("kode") String kode) {
        kd.delete(kode);
    }

}
