package j5_60.cinematicket.cinematicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import j5_60.cinematicket.cinematicket.model.entity.TheLoaiPhim;
import j5_60.cinematicket.cinematicket.model.entity.key.TheLoaiPhimKey;

/**
 * TheLoaiPhimRepository
 */
public interface MovieGenreRepository extends JpaRepository<TheLoaiPhim, TheLoaiPhimKey> {

}