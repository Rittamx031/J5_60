package j5_60.cinematicket.cinematicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import j5_60.cinematicket.cinematicket.model.entity.HoaDonDoAn;
import j5_60.cinematicket.cinematicket.model.entity.key.HoaDonDoAnKey;

/**
 * HoaDonDoAnRepository
 */
public interface BillSnacksRepository extends JpaRepository<HoaDonDoAn,HoaDonDoAnKey> {

    
}