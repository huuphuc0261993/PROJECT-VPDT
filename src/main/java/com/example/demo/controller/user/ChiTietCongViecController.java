package com.example.demo.controller.user;

import com.example.demo.model.ChiTiet;
import com.example.demo.model.ThongBao;
import com.example.demo.service.ChiTietService;
import com.example.demo.service.ThongBaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class ChiTietCongViecController {
    @Autowired
    private ChiTietService chiTietService;
    @GetMapping("/chitietcongviec/{id}")
    public ModelAndView getView(@PathVariable("id")Long id) {
        ChiTiet chiTiet = chiTietService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/user/ChiTietCongViec");
        modelAndView.addObject("chiTiets",chiTiet);
        return modelAndView;
    }

}
