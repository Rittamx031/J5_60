package j5_60.cinematicket.cinematicket.repository;

import j5_60.cinematicket.cinematicket.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NhanVienRepository extends JpaRepository<NhanVien, UUID> {
    Page<NhanVien> findAll(Pageable pageable);
    Page<NhanVien> findAllByOrderByHoTenDesc(Pageable pageable);
    Page<NhanVien> findByNgaySinhContaining(String ngaySinh,Pageable pageable);
    Page<NhanVien> findByTrangThaiContaining(String trangThai,Pageable pageable);
}