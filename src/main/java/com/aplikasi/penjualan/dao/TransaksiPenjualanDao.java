/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplikasi.penjualan.dao;

import com.aplikasi.penjualan.entity.TransaksiPenjualan;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author USER
 */
public interface TransaksiPenjualanDao extends PagingAndSortingRepository<TransaksiPenjualan, String>{
    
//melakukan query method custom
    //x, m, s, k merupakan nama variable
    @Query ("select x from TransaksiPenjualan x where x.tanggal>=:m "
            + "and x.tanggal<:s ")
    public Page<TransaksiPenjualan> searchCustomReport(
            @Param("m") Date mulai, 
            @Param("s") Date selesai, 
            Pageable page);
}
