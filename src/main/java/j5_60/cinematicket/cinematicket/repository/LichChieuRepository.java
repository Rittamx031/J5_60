package j5_60.cinematicket.cinematicket.repository;

import j5_60.cinematicket.cinematicket.entity.LichChieu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * LichChieuRepository
 */
public interface LichChieuRepository extends JpaRepository<LichChieu, UUID> {

    
}