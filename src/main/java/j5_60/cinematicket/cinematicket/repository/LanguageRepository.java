package j5_60.cinematicket.cinematicket.repository;

import j5_60.cinematicket.cinematicket.entity.NgonNgu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LanguageRepository extends JpaRepository<NgonNgu, UUID> {
}