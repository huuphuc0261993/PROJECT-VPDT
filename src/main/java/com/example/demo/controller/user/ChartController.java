package com.example.demo.controller.user;

import com.example.demo.model.ChiTiet;
import com.example.demo.model.NhanVien;
import com.example.demo.model.TinhTrang;
import com.example.demo.service.ChiTietService;
import com.example.demo.service.CongViecService;
import com.example.demo.service.NhanVienService;
import com.example.demo.service.TinhTrangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user/chart")
public class ChartController {
    @Autowired
    ChiTietService chiTietService;
    @Autowired
    NhanVienService nhanVienService;
    @Autowired
    CongViecService congViecService;
    @Autowired
    TinhTrangService tinhTrangService;

    @RequestMapping(value = "/view", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<HashMap<Integer, String>> showView(HttpServletRequest request) {
        // Lấy Id của nhân viên đang đăng nhập hiện tại
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println(username);
        NhanVien nv = nhanVienService.findByUsername(username);
        request.getSession().setAttribute("userId", nv.getMnv());
        Long userId = (Long) request.getSession().getAttribute("userId");


        HashMap<Integer, String> thongke = new HashMap<Integer, String>();
        Long tinhTrang = tinhTrangService.findById(1L);
        // dang thuc hien
        chiTietService.count(tinhTrang,userId);
        // qua han
        // dung han




        return new ResponseEntity<HashMap<Integer, String>>(chiTiets, HttpStatus.OK);
    }
}
