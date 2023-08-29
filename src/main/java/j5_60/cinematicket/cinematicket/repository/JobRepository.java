package j5_60.cinematicket.cinematicket.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import j5_60.cinematicket.cinematicket.model.entity.ChucVu;

import java.util.UUID;

public interface JobRepository extends JpaRepository<ChucVu, UUID> {
    Page<ChucVu> findAll(Pageable pageable);
}