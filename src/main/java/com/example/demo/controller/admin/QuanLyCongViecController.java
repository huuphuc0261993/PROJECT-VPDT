package com.example.demo.controller.admin;

import com.example.demo.service.CongViecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/admin/quanlycongviec")
public class QuanLyCongViecController {
    @Autowired
    private CongViecService congViecService;
    @GetMapping("")
    public ModelAndView getView() {
        ModelAndView modelAndView = new ModelAndView("admin/congViec/QuanLyCongViec");
        return modelAndView;
    }
}
