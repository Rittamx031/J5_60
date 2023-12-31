package j5_60.cinematicket.cinematicket.repository;

import j5_60.cinematicket.cinematicket.model.entity.Customer;
import j5_60.cinematicket.cinematicket.model.modelsearch.KhachHangSearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Page<Customer> findAll(Pageable pageable);

    Page<Customer> findAllByOrderByCreateAtDesc(Pageable pageable);

    @Query(value = """
            select *
            from Customer e
            where (:#{#req.maKhachHang} is null or e.ma_khach_hang like :#{'%'+#req.maKhachHang+'%'})
            and (:#{#req.hoTen} is null or e.ho_ten like :#{'%'+#req.hoTen+'%'})
            and (:#{#req.email} is null or e.email like :#{'%'+#req.email+'%'})
            and (:#{#req.gioiTinh} is null or e.gioi_tinh = :#{#req.gioiTinh})
            and (:#{#req.trangThai} is null or e.trang_thai = :#{#req.trangThai})
            and (:#{#req.ngaySinh} is null or e.ngay_sinh = :#{#req.ngaySinh})
            and (:#{#req.diaChi} is null or e.dia_chi like :#{'%'+#req.diaChi+'%'})
            """, nativeQuery = true)
    List<Customer> getKhachHangListFilter(@Param("req") KhachHangSearch req);

    @Query("SELECT kh from KhachHang kh WHERE kh.email = :username")
    Optional<Customer> getuser(@Param("username") String username);
}