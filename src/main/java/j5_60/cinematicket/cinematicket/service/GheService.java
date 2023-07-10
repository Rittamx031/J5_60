package j5_60.cinematicket.cinematicket.service;

import j5_60.cinematicket.cinematicket.entity.Ghe;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.UUID;

public interface GheService {
    List<Ghe> getAll();

    Ghe save(Ghe ghe);

    Ghe updateGhe(UUID id, Ghe ghe) throws ResourceNotFoundException;

    void deleteById(UUID id);

    Ghe findById(UUID id) throws ResourceNotFoundException;

    Page<Ghe> findAll(Pageable pageable);

    List<Ghe> sapXep();
    List<Ghe> searchGhe(String keyword);
}
