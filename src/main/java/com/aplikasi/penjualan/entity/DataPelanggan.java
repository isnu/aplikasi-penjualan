/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplikasi.penjualan.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author USER
 */
@Entity
public class DataPelanggan {
    
    @Id
    @Column(nullable = false)
    @NotEmpty(message = "Data Harus Diisi")
    private String kode;
    
    @Column(nullable = false)
    @NotEmpty(message = "Data Harus Diisi")
    private String namaLengkap;
    
    @Column(nullable = false)
    @NotEmpty(message = "Data Harus Diisi")
    private String alamat;
    
    @Column(nullable = false)
    @NotEmpty(message = "Data Harus Diisi")
    private String phone;
    
    @OneToMany(mappedBy = "pelanggan")
    private List<TransaksiPenjualan> daftarTransaksi; 

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<TransaksiPenjualan> getDaftarTransaksi() {
        return daftarTransaksi;
    }

    public void setDaftarTransaksi(List<TransaksiPenjualan> daftarTransaksi) {
        this.daftarTransaksi = daftarTransaksi;
    }
    
    
}
