package j5_60.cinematicket.cinematicket.service;


import j5_60.cinematicket.cinematicket.entity.LoaiGhe;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

public interface LoaiGheService {
    List<LoaiGhe> getAll();

    LoaiGhe save(LoaiGhe loaiGhe);

    LoaiGhe updateLoaiGhe(UUID id, LoaiGhe loaiGhe) throws ResourceNotFoundException;

    void deleteById(UUID id);

    LoaiGhe findById(UUID id) throws ResourceNotFoundException;
}
