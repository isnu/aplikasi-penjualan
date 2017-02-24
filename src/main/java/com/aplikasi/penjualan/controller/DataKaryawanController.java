package com.aplikasi.penjualan.controller;


import com.aplikasi.penjualan.dao.DataKaryawanDao;
import com.aplikasi.penjualan.entity.DataKaryawan;
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
public class DataKaryawanController {

    @Autowired
    private DataKaryawanDao kd;

    //mengambil seluruh data
    @RequestMapping(value = "/karyawan", method = RequestMethod.GET)
    public Page<DataKaryawan> cariKaryawan(Pageable page) {
        return kd.findAll(page);
    }

    //menambah data
    @RequestMapping(value = "/karyawan", method = RequestMethod.POST)
    //menambahkan status created jika input berhasil
    @ResponseStatus(HttpStatus.CREATED)
    //request body untuk menambahkan parameter yang ada di body
    public void insertKaryawanBaru(@RequestBody @Valid DataKaryawan k) {
        kd.save(k);
    }

    //mengupdate data sesuai id yang diinput
    @RequestMapping(value = "/karyawan/{nik}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateDataKaryawan(@PathVariable("nik") String nik, @RequestBody @Valid DataKaryawan k) {
        k.setNik(nik);
        kd.save(k);
    }

    //menampilkan 1 data sesuai id yang diinput
    @RequestMapping(value = "/karyawan/{nik}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DataKaryawan> cariKaryawanByNik(@PathVariable("nik") String nik) {
        DataKaryawan hasil = kd.findOne(nik);
        //menambahkan error 404 jika data yang dicari tidak ada
        if (hasil == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hasil, HttpStatus.OK);
    }

    //delete data
    @RequestMapping(value = "/karyawan/{nik}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void hapusKaryawan(@PathVariable("nik") String nik) {
        kd.delete(nik);
    }

}
