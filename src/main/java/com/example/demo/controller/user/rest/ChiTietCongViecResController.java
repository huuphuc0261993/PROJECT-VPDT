package com.example.demo.controller.user.rest;

import com.example.demo.model.ChiTiet;
import com.example.demo.model.NhanVien;
import com.example.demo.model.PhongBan;
import com.example.demo.model.ThongBao;
import com.example.demo.model.view.TaoCongViecView;
import com.example.demo.service.ChiTietService;
import org.hibernate.validator.internal.util.classhierarchy.ClassHierarchyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

@RestController
@RequestMapping("/api/chitietcongviec")
public class ChiTietCongViecResController {
    @Autowired
    ChiTietService chiTietService;

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<ChiTiet>> getView(@PathVariable("id") Long id) {

        ChiTiet chiTiets = chiTietService.findById(id);

        Long idCongViec = chiTiets.getCongViec().getId();

        List<ChiTiet> listChiTiet = chiTietService.showCongViec(idCongViec);
        if (chiTiets == null) {
            return new ResponseEntity<List<ChiTiet>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ChiTiet>>(listChiTiet, HttpStatus.OK);
    }

}
