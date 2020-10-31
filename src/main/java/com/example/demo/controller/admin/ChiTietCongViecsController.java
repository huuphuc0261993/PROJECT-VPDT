package com.example.demo.controller.admin;

import com.example.demo.model.ChiTiet;
import com.example.demo.model.NhanVien;
import com.example.demo.model.PhongBan;
import com.example.demo.service.ChiTietService;
import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ChiTietCongViecsController {
    @Autowired
    private ChiTietService chiTietService;
    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/chitietcongviec")
    public ModelAndView getView() {
        ModelAndView modelAndView = new ModelAndView("/admin/congViec/QuanLyCongViec");
        return modelAndView;
    }

    @GetMapping("/chitietcongviec/{id}")
    public ModelAndView getView(@PathVariable("id")Long id) {
        ChiTiet chiTiet = chiTietService.findById(id);

        Long idCongViec = chiTiet.getCongViec().getId();

        List<ChiTiet> listChiTiet = chiTietService.showCongViec(idCongViec);
        List<String> nhanVienLamViec = chiTietService.nhanVienLamViec(idCongViec);
        ChiTiet nhanVienLamChinh = chiTietService.nhanVienChinh(idCongViec,1L);

        NhanVien nhanVien = nhanVienService.findById(chiTiet.getThongTinChuyenGiao());


        ModelAndView modelAndView = new ModelAndView("/admin/congViec/ChiTietCongViec");
        modelAndView.addObject("chiTiets",chiTiet);
        modelAndView.addObject("nhanVienLamViec",nhanVienLamViec);
        modelAndView.addObject("nhanVienChinh",nhanVienLamChinh);
        modelAndView.addObject("listChiTiet",listChiTiet);
        modelAndView.addObject("nhanVienChuyenGiao",nhanVien);
        return modelAndView;
    }
}
