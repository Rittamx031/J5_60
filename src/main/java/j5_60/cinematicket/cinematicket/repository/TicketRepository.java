package j5_60.cinematicket.cinematicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import j5_60.cinematicket.cinematicket.model.entity.Ve;
import j5_60.cinematicket.cinematicket.model.entity.key.VeKey;

/**
 * VeRepository
 */
public interface TicketRepository extends JpaRepository<Ve,VeKey> {

}