/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplikasi.penjualan.dao;

import com.aplikasi.penjualan.entity.DataKaryawan;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author USER
 */
public interface DataKaryawanDao extends PagingAndSortingRepository<DataKaryawan, String>{
    
}
