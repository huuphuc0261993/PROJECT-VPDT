package com.example.demo.controller.admin.rest;

import com.example.demo.model.NhanVien;
import com.example.demo.model.PhongBan;
import com.example.demo.model.ThongBao;
import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/nhanvien")
public class NhanVienResController {
    @Autowired
    private NhanVienService nhanVienService;

    @RequestMapping(value = "/view/{id}",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<NhanVien>> showView(@PathVariable("id")Long id){
        List<NhanVien>nhanViens=nhanVienService.listNhanVien(id);
        List<NhanVien> nhanVienList = new ArrayList<>();
        for (NhanVien nv:nhanViens) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setFullName(nv.getFullName());
            nhanVien.setPhongBan(nv.getPhongBan());
            nhanVien.setMnv(nv.getMnv());
            nhanVienList.add(nhanVien);
        }
        return new ResponseEntity<List<NhanVien>>(nhanVienList, HttpStatus.OK);
    }

}
