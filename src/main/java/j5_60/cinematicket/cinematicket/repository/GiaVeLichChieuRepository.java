package j5_60.cinematicket.cinematicket.repository;

import j5_60.cinematicket.cinematicket.entity.GiaVeLichChieu;
import j5_60.cinematicket.cinematicket.entity.key.GiaVeLichChieuKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * GiaVeLichChieuRepository
 */
@Repository
public interface GiaVeLichChieuRepository extends JpaRepository<GiaVeLichChieu, GiaVeLichChieuKey> {

    
}