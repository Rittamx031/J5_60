package j5_60.cinematicket.cinematicket.repository;


import j5_60.cinematicket.cinematicket.entity.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TheLoaiRepository extends JpaRepository<TheLoai, UUID> {
}