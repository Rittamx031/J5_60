package j5_60.cinematicket.cinematicket.repository;


import j5_60.cinematicket.cinematicket.entity.NhanVien;
import j5_60.cinematicket.cinematicket.modelsearch.NhanVienSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, UUID> {
    Page<NhanVien> findAll(Pageable pageable);
    Page<NhanVien> findAllByOrderByHoTenDesc(Pageable pageable);

    Page<NhanVien> findAllByOrderByCreateAtDesc(Pageable pageable);

    @Query(value = """
    select *
    from NhanVien e
    where (:#{#req.maNV} is null or e.ma_nhan_vien like :#{'%'+#req.maNV+'%'})
    and (:#{#req.hoTen} is null or e.ho_ten like :#{'%'+#req.hoTen+'%'})
    and (:#{#req.email} is null or e.email like :#{'%'+#req.email+'%'})
    and (:#{#req.gioiTinh} is null or e.gioi_tinh = :#{#req.gioiTinh})
    and (:#{#req.trangThai} is null or e.trang_thai = :#{#req.trangThai})
    and (:#{#req.ngaySinh} is null or e.ngay_sinh = :#{#req.ngaySinh})
    and (:#{#req.chucVu} is null or e.id_chuc_vu like :#{#req.chucVu})
    """, nativeQuery = true)
    List<NhanVien> getNhanVienListFilter(@Param("req") NhanVienSearch req);

    Page<NhanVien> findByNgaySinhContaining(String ngaySinh,Pageable pageable);
    Page<NhanVien> findByTrangThaiContaining(String trangThai,Pageable pageable);
}