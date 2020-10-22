package com.example.demo.controller.user;

import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/nhanvien")
public class NhanVienUserController {
    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/changPass")
    public ModelAndView getView() {
        ModelAndView modelAndView = new ModelAndView("user/DoiMatKhau");
        return modelAndView;
    }
}
