package com.example.demo.service;

import com.example.demo.model.ChiTiet;
import com.example.demo.model.CongViec;
import com.example.demo.model.NhanVien;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuanLyCongViecService {
    Iterable<ChiTiet> findByIsDeletedEquals(int id);
    Iterable<ChiTiet> listQuanLy();
}
