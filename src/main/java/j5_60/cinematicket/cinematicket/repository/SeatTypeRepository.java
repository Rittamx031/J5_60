package j5_60.cinematicket.cinematicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import j5_60.cinematicket.cinematicket.model.entity.SeatType;

import java.util.List;
import java.util.UUID;

/**
 * LoaiGheRepository
 */
public interface SeatTypeRepository extends JpaRepository<SeatType, UUID> {


    List<SeatType> findAllByOrderByTen();
    List<SeatType> findByTenContainingIgnoreCase(String keyword);

    
}