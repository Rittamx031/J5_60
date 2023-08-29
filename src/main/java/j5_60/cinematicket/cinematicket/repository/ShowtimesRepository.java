package j5_60.cinematicket.cinematicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import j5_60.cinematicket.cinematicket.model.entity.LichChieu;

import java.util.UUID;

/**
 * LichChieuRepository
 */
public interface ShowtimesRepository extends JpaRepository<LichChieu, UUID> {

    
}