package j5_60.cinematicket.cinematicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import j5_60.cinematicket.cinematicket.model.entity.Movie;

import java.util.List;
import java.util.UUID;

/**
 * ThongTinPhimRepository
 */
public interface MovieRepository extends JpaRepository<Movie, UUID> {
  List<Movie> search(@Param("txtSearch") String txt);
}