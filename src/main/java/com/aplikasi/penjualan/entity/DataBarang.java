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
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author USER
 */
@Entity
public class DataBarang {
    
    @Id
    @Column(nullable = false)
    @NotEmpty(message = "Data Harus Diisi")
    private String kode;
    
    @Column(nullable = false)
    @NotEmpty(message = "Data Harus Diisi")
    private String nama;
    
    @Column(nullable = false)
    @NotNull(message = "Data Harus Diisi")
    private Long hargabeli;
    
    @Column(nullable = false)
    @NotNull(message = "Data Harus Diisi")
    private Long hargajual;

    @OneToMany(mappedBy = "barang")
    private List<TransaksiPenjualan> daftarTransaksi;
    
    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Long getHargabeli() {
        return hargabeli;
    }

    public void setHargabeli(Long hargabeli) {
        this.hargabeli = hargabeli;
    }

    public Long getHargajual() {
        return hargajual;
    }

    public void setHargajual(Long hargajual) {
        this.hargajual = hargajual;
    }


    public List<TransaksiPenjualan> getDaftarTransaksi() {
        return daftarTransaksi;
    }

    public void setDaftarTransaksi(List<TransaksiPenjualan> daftarTransaksi) {
        this.daftarTransaksi = daftarTransaksi;
    }
    
    
}
