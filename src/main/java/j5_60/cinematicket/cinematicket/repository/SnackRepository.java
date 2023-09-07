package j5_60.cinematicket.cinematicket.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import j5_60.cinematicket.cinematicket.model.entity.Snacks;

/**
 * DoAnRepository
 */
public interface SnackRepository extends JpaRepository<Snacks,UUID> {
    public List<Snacks> search(@Param("txtSearch") String txtSearch);
}