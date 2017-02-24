/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplikasi.penjualan.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author USER
 */
@Entity
public class DataKaryawan {
    
    @Id
    @Column(nullable = false)
    @NotEmpty(message = "Data Harus Diisi")
    private String nik;
    
    @Column(nullable = false)
    @NotEmpty(message = "Data Harus Diisi")
    private String nama;
    
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Data Harus Diisi")
    private String email;
    
    @Column(nullable = false)
    @NotEmpty(message = "Data Harus Diisi")
    private String jabatan;
    
    @Column(nullable = false)
    @NotEmpty(message = "Data Harus Diisi")
    private String phone;
    
    @Temporal (TemporalType.DATE)
    @NotNull(message = "Data Harus Diisi")
    private Date tanggalLahir;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }
    
    
    
}
