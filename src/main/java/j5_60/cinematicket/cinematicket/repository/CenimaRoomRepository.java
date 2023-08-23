package j5_60.cinematicket.cinematicket.repository;

import j5_60.cinematicket.cinematicket.entity.PhongChieu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * PhongChieuRepository
 */
public interface CenimaRoomRepository  extends JpaRepository<PhongChieu, UUID> {

    List<PhongChieu> findAllByOrderByTen();
    List<PhongChieu> findByTenContainingIgnoreCase(String keyword);
}
