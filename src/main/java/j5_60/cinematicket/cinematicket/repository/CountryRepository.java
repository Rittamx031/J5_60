package j5_60.cinematicket.cinematicket.repository;

import j5_60.cinematicket.cinematicket.entity.QuocGia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CountryRepository extends JpaRepository<QuocGia, UUID> {
}
