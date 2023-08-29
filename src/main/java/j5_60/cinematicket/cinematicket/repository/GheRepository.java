package j5_60.cinematicket.cinematicket.repository;

import j5_60.cinematicket.cinematicket.entity.Ghe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * GheRepository
 */
public interface GheRepository extends JpaRepository<Ghe, UUID> {
    @Query(value = "SELECT * FROM Ghe WHERE id_phong_chieu = :idPhongChieu", nativeQuery = true)
    List<Ghe> getGheInPhongChieu(@Param("idPhongChieu") UUID idPhongChieu);

    @Query(value = "SELECT * FROM Ghe WHERE id_phong_chieu = :idPhongChieu AND row = :row AND deleted = 0 ORDER BY cot", nativeQuery = true)
    List<Ghe> getRowSeatInPhongChieu(@Param("idPhongChieu") UUID idPhongChieu, @Param("row") int row);

    List<Ghe> findAllByOrderByTen();

}