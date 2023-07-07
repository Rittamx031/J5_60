package j5_60.cinematicket.cinematicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import j5_60.cinematicket.cinematicket.entity.HoaDonDoAn;
import j5_60.cinematicket.cinematicket.entity.key.HoaDonDoAnKey;

/**
 * HoaDonDoAnRepository
 */
public interface HoaDonDoAnRepository extends JpaRepository<HoaDonDoAn,HoaDonDoAnKey> {

    
}