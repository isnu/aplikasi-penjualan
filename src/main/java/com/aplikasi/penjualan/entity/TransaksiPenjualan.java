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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author USER
 */
@Entity
public class TransaksiPenjualan {

    @Id
    @NotEmpty(message = "Data Harus Diisi")
    private String noNota;

    @Column(nullable = false)
    @NotEmpty(message = "Data Harus Diisi")
    private String noNotaTerakhir;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "Data Harus Diisi")
    private Date tanggal;

    @Column(nullable = false)
    @NotNull(message = "Data Harus Diisi")
    private Long jumlah;

    private Long total;

    @ManyToOne
    @JoinColumn(name = "kode_barang", nullable = false)
    private DataBarang barang;

    @ManyToOne
    @JoinColumn(name = "kode_pelanggan", nullable = false)
    private DataPelanggan pelanggan;

    public String getNoNota() {
        return noNota;
    }

    public void setNoNota(String noNota) {
        this.noNota = noNota;
    }

    public String getNoNotaTerakhir() {
        return noNotaTerakhir;
    }

    public void setNoNotaTerakhir(String noNotaTerakhir) {
        this.noNotaTerakhir = noNotaTerakhir;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Long getJumlah() {
        return jumlah;
    }

    public void setJumlah(Long jumlah) {
        this.jumlah = jumlah;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

        
    public DataBarang getBarang() {
        return barang;
    }

    public void setBarang(DataBarang barang) {
        this.barang = barang;
    }

    public DataPelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(DataPelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

}
