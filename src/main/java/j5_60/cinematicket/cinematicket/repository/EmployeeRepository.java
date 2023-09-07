package j5_60.cinematicket.cinematicket.repository;

import j5_60.cinematicket.cinematicket.model.entity.Employee;
import j5_60.cinematicket.cinematicket.model.modelsearch.NhanVienSearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Page<Employee> findAll(Pageable pageable);

    Page<Employee> findAllByOrderByHoTenDesc(Pageable pageable);

    Page<Employee> findAllByOrderByCreateAtDesc(Pageable pageable);

    @Query(value = """
            select *
            from Employee e
            where (:#{#req.maNV} is null or e.ma_nhan_vien like :#{'%'+#req.maNV+'%'})
            and (:#{#req.hoTen} is null or e.ho_ten like :#{'%'+#req.hoTen+'%'})
            and (:#{#req.email} is null or e.email like :#{'%'+#req.email+'%'})
            and (:#{#req.gioiTinh} is null or e.gioi_tinh = :#{#req.gioiTinh})
            and (:#{#req.trangThai} is null or e.trang_thai = :#{#req.trangThai})
            and (:#{#req.ngaySinh} is null or e.ngay_sinh = :#{#req.ngaySinh})
            and (:#{#req.chucVu} is null or e.id_chuc_vu like :#{#req.chucVu})
            """, nativeQuery = true)
    List<Employee> getNhanVienListFilter(@Param("req") NhanVienSearch req);

    Page<Employee> findByNgaySinhContaining(String ngaySinh, Pageable pageable);

    Page<Employee> findByTrangThaiContaining(String trangThai, Pageable pageable);

    @Query("SELECT kh from NhanVien kh WHERE kh.email = :username")
    Optional<Employee> getuser(@Param("username") String username);

    Optional<Employee> findByEmail(String email);
}