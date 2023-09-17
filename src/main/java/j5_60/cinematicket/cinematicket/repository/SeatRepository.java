package j5_60.cinematicket.cinematicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import j5_60.cinematicket.cinematicket.model.entity.Seat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * GheRepository
 */
public interface SeatRepository extends JpaRepository<Seat, UUID> {
    @Query(value = "SELECT * FROM Ghe WHERE id_phong_chieu = :idPhongChieu", nativeQuery = true)
    List<Seat> getGheInPhongChieu(@Param("idPhongChieu") UUID idPhongChieu);

    @Query(value = "SELECT * FROM Ghe WHERE id_phong_chieu = :idPhongChieu AND row = :row AND deleted = 0 ORDER BY cot", nativeQuery = true)
    List<Seat> getRowSeatInPhongChieu(@Param("idPhongChieu") UUID idPhongChieu, @Param("row") int row);

    List<Seat> findAllByOrderByTen();

    @Query(value = "SELECT MAX(row) FROM Ghe where id_phong_chieu = :idPhongChieu",nativeQuery = true)
    Optional<Integer> getMaxRow(@Param("idPhongChieu") UUID idPhongChieu);
}