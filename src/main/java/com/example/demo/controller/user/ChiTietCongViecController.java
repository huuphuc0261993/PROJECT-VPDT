package com.example.demo.controller.user;

import com.example.demo.model.ChiTiet;
import com.example.demo.model.PhongBan;
import com.example.demo.model.ThongBao;
import com.example.demo.service.ChiTietService;
import com.example.demo.service.ThongBaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class ChiTietCongViecController {
    @Autowired
    private ChiTietService chiTietService;

    @GetMapping("/chitietcongviec/{id}")
    public ModelAndView getView(@PathVariable("id")Long id) {
        ChiTiet chiTiet = chiTietService.findById(id);

        Long idCongViec = chiTiet.getCongViec().getId();

        List<ChiTiet> listChiTiet = chiTietService.showCongViec(idCongViec);
        List<String> nhanVienLamViec = chiTietService.nhanVienLamViec(idCongViec);
        ChiTiet nhanVienLamChinh = chiTietService.nhanVienChinh(idCongViec,1L);



        ModelAndView modelAndView = new ModelAndView("/user/ChiTietCongViec");
        modelAndView.addObject("chiTiets",chiTiet);
        modelAndView.addObject("nhanVienLamViec",nhanVienLamViec);
        modelAndView.addObject("nhanVienChinh",nhanVienLamChinh);
        modelAndView.addObject("listChiTiet",listChiTiet);
        return modelAndView;
    }

    @RequestMapping(value = "/chitietcongviec/submit/{id}", method = RequestMethod.POST,  produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<?> uploadFile(HttpServletRequest request, @RequestParam("filename") MultipartFile uploadfile, @PathVariable("id")Long id, @RequestParam("baoCao") String baoCao){
        ChiTiet chiTiets = chiTietService.findById(id);
        ChiTiet chiTiet = new ChiTiet();
        try {
            // Get the filename and build the local file path (be sure that the
            // application have write permissions on such directory)
            String filename = uploadfile.getOriginalFilename();
        chiTiets.setFile(filename);
        chiTiets.setBaoCao(baoCao);
        chiTiets.setNgayBaoCao(java.time.LocalDate.now());
        chiTietService.save(chiTiets);
//            String directory = request.getServletContext().getRealPath("upload");
//            System.out.println(directory);
//            String directory = "\\vpdt\\src\\main\\resources\\static\\upload";
            String directory = "src\\main\\resources\\static\\upload";
            String filepath = Paths.get(directory, filename).toString();

            // Save the file locally
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            stream.write(uploadfile.getBytes());
            stream.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
