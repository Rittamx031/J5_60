package j5_60.cinematicket.cinematicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import j5_60.cinematicket.cinematicket.model.entity.LoaiGhe;

import java.util.List;
import java.util.UUID;

/**
 * LoaiGheRepository
 */
public interface SeatTypeRepository extends JpaRepository<LoaiGhe, UUID> {


    List<LoaiGhe> findAllByOrderByTen();
    List<LoaiGhe> findByTenContainingIgnoreCase(String keyword);

    
}