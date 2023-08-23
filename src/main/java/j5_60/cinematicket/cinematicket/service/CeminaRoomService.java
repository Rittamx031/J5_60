package j5_60.cinematicket.cinematicket.service;

import j5_60.cinematicket.cinematicket.entity.PhongChieu;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CeminaRoomService {
    List<PhongChieu> getAll();

    PhongChieu save(PhongChieu phongChieu);

    PhongChieu updatePhongChieu(UUID id, PhongChieu phongChieu) throws ResourceNotFoundException;

    void deleteById(UUID id);

    PhongChieu findById(UUID id) throws ResourceNotFoundException;

    Page<PhongChieu> findAll(Pageable pageable);

    List<PhongChieu> sapXep();
    List<PhongChieu> searchPhongChieu(String keyword);
}
