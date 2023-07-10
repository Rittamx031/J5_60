package j5_60.cinematicket.cinematicket.repository;


import j5_60.cinematicket.cinematicket.entity.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TheLoaiRepository extends JpaRepository<TheLoai, UUID> {
}