package j5_60.cinematicket.cinematicket.repository;

import j5_60.cinematicket.cinematicket.entity.ThongTinPhim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * ThongTinPhimRepository
 */
public interface ThongTinPhimRepository extends JpaRepository<ThongTinPhim, UUID> {
}