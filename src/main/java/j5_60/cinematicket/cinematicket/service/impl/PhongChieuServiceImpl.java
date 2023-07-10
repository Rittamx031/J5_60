package j5_60.cinematicket.cinematicket.service.impl;

import j5_60.cinematicket.cinematicket.entity.PhongChieu;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.PhongChieuRepository;
import j5_60.cinematicket.cinematicket.service.PhongChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PhongChieuServiceImpl implements PhongChieuService {

    @Autowired
    private PhongChieuRepository phongChieuRepository;

    @Override
    public List<PhongChieu> getAll() {
        return phongChieuRepository.findAll();
    }

    @Override
    public PhongChieu save(PhongChieu phongChieu) {
        phongChieu.setCreateAt(LocalDateTime.now());
        return phongChieuRepository.save(phongChieu);
    }

    @Override
    public PhongChieu updatePhongChieu(UUID id, PhongChieu phongChieu) throws ResourceNotFoundException {
        Optional<PhongChieu> optionalPhongChieu = phongChieuRepository.findById(id);
        if (optionalPhongChieu.isPresent()) {
            PhongChieu existingPhongChieu = optionalPhongChieu.get();
            existingPhongChieu.setTen(phongChieu.getTen());
            existingPhongChieu.setSoLuongGhe(phongChieu.getSoLuongGhe());
            existingPhongChieu.setTrangThai(phongChieu.getTrangThai());
            existingPhongChieu.setUpdateAt(LocalDateTime.now());
            // Cập nhật các thuộc tính khác của đối tượng PhongChieu

            return phongChieuRepository.save(existingPhongChieu);
        }
        throw new ResourceNotFoundException("PhongChieu not found with id: " + id);
    }

    @Override
    public void deleteById(UUID id) {
        phongChieuRepository.deleteById(id);
    }

    @Override
    public PhongChieu findById(UUID id) throws ResourceNotFoundException {
        Optional<PhongChieu> optionalPhongChieu = phongChieuRepository.findById(id);
        if (optionalPhongChieu.isPresent()) {
            return optionalPhongChieu.get();
        }
        throw new ResourceNotFoundException("PhongChieu not found with id: " + id);
    }
}