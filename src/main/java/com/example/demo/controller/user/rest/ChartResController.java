package com.example.demo.controller.user.rest;

import com.example.demo.model.PhongBan;
import com.example.demo.model.ThongBao;
import com.example.demo.service.ChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chart")
public class ChartResController {
    @Autowired
    ChiTietService chiTietService;

    @RequestMapping(value = "", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public HttpStatus chart(){

        return HttpStatus.OK;
    }
}
