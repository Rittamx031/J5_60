package j5_60.cinematicket.cinematicket.service;

import j5_60.cinematicket.cinematicket.entity.LichChieu;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.LichChieuRepository;
import j5_60.cinematicket.cinematicket.service.serviceimpl.LichChieuIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LichChieuService implements LichChieuIF {
    @Autowired
    LichChieuRepository lichChieuRepository;

    @Override
    public List<LichChieu> getAll() {
        return lichChieuRepository.findAll();
    }

    @Override
    public LichChieu save(LichChieu lichChieu) {
        return lichChieuRepository.save(lichChieu);
    }

    @Override
    public LichChieu update(UUID id, LichChieu lichChieu) {
        if (lichChieu != null) {
            LichChieu lc = lichChieuRepository.getById(id);
            if (lc != null) {
                lc.setGioiChieu(lichChieu.getGioiChieu());
                lc.setGioiKetThuc(lichChieu.getGioiKetThuc());
                lc.setTrangThai(lichChieu.getTrangThai());
                lc.setNgayChieu(lichChieu.getNgayChieu());
                lc.setPhongChieu(lichChieu.getPhongChieu());
                lc.setThongTinPhim(lichChieu.getThongTinPhim());
                lc.setUpdateAt(lichChieu.getUpdateAt());
            }
            return lichChieuRepository.save(lc);
        }
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        lichChieuRepository.deleteById(id);
    }

    @Override
    public LichChieu findById(UUID id) throws ResourceNotFoundException {
        Optional<LichChieu> lichChieu = lichChieuRepository.findById(id);
        if (lichChieu.isPresent()) {
            return lichChieu.get();
        }
        throw new ResourceNotFoundException("Can not find player with id: " + id);
    }

}
