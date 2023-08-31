package j5_60.cinematicket.cinematicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import j5_60.cinematicket.cinematicket.model.entity.CimenaRoom;

import java.util.List;
import java.util.UUID;

/**
 * PhongChieuRepository
 */
public interface CenimaRoomRepository  extends JpaRepository<CimenaRoom, UUID> {

    List<CimenaRoom> findAllByOrderByTen();
    List<CimenaRoom> findByTenContainingIgnoreCase(String keyword);
}
