package j5_60.cinematicket.cinematicket.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import j5_60.cinematicket.cinematicket.entity.HoaDon;

/**
 * HoaDonRepository
 */
public interface HoaDonRepository extends JpaRepository<HoaDon,UUID> {
}