package com.example.demo.repository;

import com.example.demo.model.ChiTiet;
import com.example.demo.model.CongViec;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

@Repository
public interface ChiTietRepository extends PagingAndSortingRepository<ChiTiet,Long> {
    Iterable<ChiTiet> findByIsDeletedEquals(int isDeleted);
//    @Transactional
//    @Modifying
//    @Query(value = "INSERT INTO chi_tiet (nv_chinh, cong_viec_id, nhan_vien_id) " +
//            "VALUES ( nv_chinh = :nv_chinh,cong_viec_id = :cong_viec_id, nhan_vien_id = :nhan_vien_id);", nativeQuery = true)
//    void create(@Param("nv_chinh")int nv_chinh,@Param("cong_viec_id")int cong_viec_id,@Param("nhan_vien_id")int nhan_vien_id);
}
