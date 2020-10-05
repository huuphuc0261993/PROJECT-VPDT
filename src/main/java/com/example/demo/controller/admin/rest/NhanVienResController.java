package com.example.demo.controller.admin.rest;

import com.example.demo.model.NhanVien;
import com.example.demo.model.PhongBan;
import com.example.demo.model.ThongBao;
import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/view", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Iterable<NhanVien>> showView() {
        Iterable<NhanVien> nhanViens = nhanVienService.findAllByIsDeletedEquals(0);
        if (nhanViens == null) {
            return new ResponseEntity<Iterable<NhanVien>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Iterable<NhanVien>>(nhanViens, HttpStatus.OK);
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public HttpStatus create(@RequestBody NhanVien nhanVien) {

        BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
        String rawPassword = nhanVien.getPassword();;
        String encodePassword =  encoder.encode(rawPassword);
        System.out.println(encodePassword);
        nhanVien.setPassword(encodePassword);
        
        nhanVienService.save(nhanVien);
        return HttpStatus.OK;
    }

}
