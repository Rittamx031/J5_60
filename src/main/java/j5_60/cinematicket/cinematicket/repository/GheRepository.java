package j5_60.cinematicket.cinematicket.repository;

import j5_60.cinematicket.cinematicket.entity.Ghe;
import j5_60.cinematicket.cinematicket.entity.NhanVien;
import j5_60.cinematicket.cinematicket.entity.PhongChieu;
import j5_60.cinematicket.cinematicket.modelsearch.NhanVienSearch;

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

    @Query(value = "SELECT * FROM Ghe WHERE id_phong_chieu = :idPhongChieu AND hang = :row AND deleted = true", nativeQuery = true)
    List<Ghe> getRowSeatInPhongChieu(@Param("idPhongChieu") UUID idPhongChieu, @Param("row") int row);

    List<Ghe> findAllByOrderByTen();

    List<Ghe> findByTenContainingIgnoreCase(String keyword);

    List<Ghe> findByTenAndHangAndCotAndPhongChieu(String ten, String hang, String cot, PhongChieu phongChieu);

    List<Ghe> findByHangAndCotAndPhongChieu(String hang, String cot, PhongChieu phongChieu);
}