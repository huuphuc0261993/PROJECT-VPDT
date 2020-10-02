package com.example.demo.service.impl;

import com.example.demo.model.ChiTiet;
import com.example.demo.repository.ChiTietRepository;
import com.example.demo.service.ChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChiTietServiceImpl implements ChiTietService {
    @Autowired
    private ChiTietRepository chiTietRepository;
    @Override
    public Iterable<ChiTiet> findByIsDeletedEquals(int id) {
        return chiTietRepository.findByIsDeletedEquals(id);
    }

    @Override
    public void save(ChiTiet chiTiet) {
        chiTietRepository.save(chiTiet);
    }

//    @Override
//    public void create(int nv_chinh, int cong_viec_id, int nhan_vien_id) {
//        chiTietRepository.create(nv_chinh,cong_viec_id,nhan_vien_id);
//    }
}
