package j5_60.cinematicket.cinematicket.repository;

import j5_60.cinematicket.cinematicket.entity.ThongTinPhim;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * ThongTinPhimRepository
 */
public interface MovieRepository extends JpaRepository<ThongTinPhim, UUID> {
  List<ThongTinPhim> search(@Param("txtSearch") String txt);
}