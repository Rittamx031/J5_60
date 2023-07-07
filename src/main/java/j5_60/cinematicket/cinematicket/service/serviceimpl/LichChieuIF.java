package j5_60.cinematicket.cinematicket.service.serviceimpl;

import j5_60.cinematicket.cinematicket.entity.LichChieu;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;


import java.util.List;
import java.util.UUID;

public interface LichChieuIF {
    List<LichChieu> getAll();
    LichChieu save(LichChieu lichChieu);
    LichChieu update(UUID id, LichChieu lichChieu);
    void deleteById(UUID id);
    LichChieu findById(UUID id) throws ResourceNotFoundException;
}
