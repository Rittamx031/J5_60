package j5_60.cinematicket.cinematicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import j5_60.cinematicket.cinematicket.entity.Ve;
import j5_60.cinematicket.cinematicket.entity.key.VeKey;

/**
 * VeRepository
 */
public interface VeRepository extends JpaRepository<Ve,VeKey> {

}