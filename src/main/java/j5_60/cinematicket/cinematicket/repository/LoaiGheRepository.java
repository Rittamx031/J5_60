package j5_60.cinematicket.cinematicket.repository;

import j5_60.cinematicket.cinematicket.entity.Ghe;
import j5_60.cinematicket.cinematicket.entity.LoaiGhe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * LoaiGheRepository
 */
public interface LoaiGheRepository extends JpaRepository<LoaiGhe, UUID> {


    List<LoaiGhe> findAllByOrderByTen();
    List<LoaiGhe> findByTenContainingIgnoreCase(String keyword);

    
}