package j5_60.cinematicket.cinematicket.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.model.entity.CimenaRoom;
import j5_60.cinematicket.cinematicket.repository.CenimaRoomRepository;

public class CeminaRoomService {
    @Autowired
    private CenimaRoomRepository phongChieuRepository;

    public List<CimenaRoom> getAll() {
        return phongChieuRepository.findAll();
    }

    public Page<CimenaRoom> findAll(Pageable pageable) {
        return phongChieuRepository.findAll(pageable);
    }

    public List<CimenaRoom> sapXep() {
        return phongChieuRepository.findAllByOrderByTen();
    }

    public List<CimenaRoom> searchPhongChieu(String keyword) {
        return phongChieuRepository.findByTenContainingIgnoreCase(keyword);
    }

    public CimenaRoom save(CimenaRoom phongChieu) {
        phongChieu.setCreateAt(LocalDateTime.now());
        return phongChieuRepository.save(phongChieu);
    }

    public CimenaRoom updatePhongChieu(UUID id, CimenaRoom phongChieu) throws ResourceNotFoundException {
        Optional<CimenaRoom> optionalPhongChieu = phongChieuRepository.findById(id);
        if (optionalPhongChieu.isPresent()) {
            CimenaRoom existingPhongChieu = optionalPhongChieu.get();
            existingPhongChieu.setTen(phongChieu.getTen());
            existingPhongChieu.setSoLuongGhe(phongChieu.getSoLuongGhe());
            existingPhongChieu.setTrangThai(phongChieu.getTrangThai());
            existingPhongChieu.setUpdateAt(LocalDateTime.now());
            // Cập nhật các thuộc tính khác của đối tượng PhongChieu

            return phongChieuRepository.save(existingPhongChieu);
        }
        throw new ResourceNotFoundException("PhongChieu not found with id: " + id);
    }

    public void deleteById(UUID id) {
        phongChieuRepository.deleteById(id);
    }

    public CimenaRoom findById(UUID id) throws ResourceNotFoundException {
        Optional<CimenaRoom> optionalPhongChieu = phongChieuRepository.findById(id);
        if (optionalPhongChieu.isPresent()) {
            return optionalPhongChieu.get();
        }
        throw new ResourceNotFoundException("PhongChieu not found with id: " + id);
    }
}
