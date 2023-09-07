package j5_60.cinematicket.cinematicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import j5_60.cinematicket.cinematicket.model.entity.ShowTimePrice;
import j5_60.cinematicket.cinematicket.model.entity.key.GiaVeLichChieuKey;

/**
 * GiaVeLichChieuRepository
 */
public interface ShowtimePricesRepository extends JpaRepository<ShowTimePrice, GiaVeLichChieuKey> {

    
}