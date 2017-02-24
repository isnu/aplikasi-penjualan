package com.aplikasi.penjualan.controller;

import com.aplikasi.penjualan.dao.TransaksiPenjualanDao;
import com.aplikasi.penjualan.entity.TransaksiPenjualan;
import java.util.Date;
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

@RestController
@RequestMapping("/api")
public class TransaksiPenjualanController {

    @Autowired
    private TransaksiPenjualanDao kd;

    //mengambil seluruh data
    @RequestMapping(value = "/penjualan", method = RequestMethod.GET)
    public Page<TransaksiPenjualan> cariTransaksiPenjualan(Pageable page) {
        return kd.findAll(page);
    }

    //menambah data
    @RequestMapping(value = "/penjualan", method = RequestMethod.POST)
    //menambahkan status created jika input berhasil
    @ResponseStatus(HttpStatus.CREATED)
    //request body untuk menambahkan parameter yang ada di body
    public void insertTransaksiPenjualanBaru(@RequestBody @Valid TransaksiPenjualan k) {
        kd.save(k);
    }

    //mengupdate data sesuai id yang diinput
    @RequestMapping(value = "/penjualan/{noNota}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateTransaksiPenjualan(@PathVariable("noNota") String noNota, @RequestBody @Valid TransaksiPenjualan k) {
        k.setNoNota(noNota);
        kd.save(k);
    }

    //menampilkan 1 data sesuai id yang diinput
    @RequestMapping(value = "/penjualan/{noNota}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TransaksiPenjualan> cariTransaksiPenjualanByNik(@PathVariable("noNota") String noNota) {
        TransaksiPenjualan hasil = kd.findOne(noNota);
        //menambahkan error 404 jika data yang dicari tidak ada
        if (hasil == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hasil, HttpStatus.OK);
    }

    //delete data
    @RequestMapping(value = "/penjualan/{noNota}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void hapusTransaksiPenjualan(@PathVariable("noNota") String noNota) {
        kd.delete(noNota);
    }
    
    @RequestMapping(value = "/penjualan/custom_report", method = RequestMethod.GET)
    public Page<TransaksiPenjualan> searchCustomReport(Date mulai, Date selesai, Pageable page) {
        return kd.searchCustomReport(mulai, selesai, page);
    }

}
