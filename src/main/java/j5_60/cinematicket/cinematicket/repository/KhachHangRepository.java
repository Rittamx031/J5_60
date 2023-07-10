package j5_60.cinematicket.cinematicket.repository;

import j5_60.cinematicket.cinematicket.entity.KhachHang;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {
    Page<KhachHang> findAll(Pageable pageable);
    Page<KhachHang> findAllByOrderByHoTenDesc(Pageable pageable);
}