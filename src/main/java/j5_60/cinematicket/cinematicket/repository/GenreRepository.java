package j5_60.cinematicket.cinematicket.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import j5_60.cinematicket.cinematicket.model.entity.Gener;

import java.util.UUID;

public interface GenreRepository extends JpaRepository<Gener, UUID> {
}