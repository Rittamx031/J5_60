package j5_60.cinematicket.cinematicket.repository;

import j5_60.cinematicket.cinematicket.entity.LichChieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * LichChieuRepository
 */
@Repository
public interface LichChieuRepository extends JpaRepository<LichChieu, UUID> {

    
}