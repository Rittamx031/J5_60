package j5_60.cinematicket.cinematicket.repository;

import j5_60.cinematicket.cinematicket.entity.ChucVu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu, UUID> {
    Page<ChucVu> findAll(Pageable pageable);
}