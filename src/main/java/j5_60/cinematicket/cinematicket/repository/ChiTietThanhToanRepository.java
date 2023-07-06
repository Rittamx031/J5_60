package j5_60.cinematicket.cinematicket.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import j5_60.cinematicket.cinematicket.entity.ChiTietThanhToan;

/**
 * ChiTietThanhToanRepository
 */
public interface ChiTietThanhToanRepository extends JpaRepository<ChiTietThanhToan,UUID> {
    
}