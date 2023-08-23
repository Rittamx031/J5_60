package j5_60.cinematicket.cinematicket.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import j5_60.cinematicket.cinematicket.entity.DoAn;

/**
 * DoAnRepository
 */
public interface SnackRepository extends JpaRepository<DoAn,UUID> {
    public List<DoAn> search(@Param("txtSearch") String txtSearch);
}