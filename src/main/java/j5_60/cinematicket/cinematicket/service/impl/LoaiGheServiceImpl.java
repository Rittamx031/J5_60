package j5_60.cinematicket.cinematicket.service.impl;

import j5_60.cinematicket.cinematicket.entity.Ghe;
import j5_60.cinematicket.cinematicket.entity.LoaiGhe;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.LoaiGheRepository;
import j5_60.cinematicket.cinematicket.service.LoaiGheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoaiGheServiceImpl implements LoaiGheService {

    @Autowired
    private LoaiGheRepository loaiGheRepository;

    @Override
    public Page<LoaiGhe> findAll(Pageable pageable) {
        return loaiGheRepository.findAll(pageable);
    }

    @Override
    public List<LoaiGhe> sapXep() {
        return loaiGheRepository.findAllByOrderByTen();
    }
    @Override
    public List<LoaiGhe> searchLoaiGhe(String keyword) {
        return loaiGheRepository.findByTenContainingIgnoreCase(keyword);
    }

    @Override
    public List<LoaiGhe> getAll() {
        return loaiGheRepository.findAll();
    }

    @Override
    public LoaiGhe save(LoaiGhe loaiGhe) {
        return loaiGheRepository.save(loaiGhe);
    }

    @Override
    public LoaiGhe updateLoaiGhe(UUID id, LoaiGhe loaiGhe) throws ResourceNotFoundException {
        Optional<LoaiGhe> optionalLoaiGhe = loaiGheRepository.findById(id);
        if (optionalLoaiGhe.isPresent()) {
            LoaiGhe existingLoaiGhe = optionalLoaiGhe.get();
            existingLoaiGhe.setTen(loaiGhe.getTen());
            existingLoaiGhe.setTrangThai(loaiGhe.getTrangThai());
            existingLoaiGhe.setUpdateAt(LocalDateTime.now());
            // Cập nhật các thuộc tính khác của LoaiGhe

            return loaiGheRepository.save(existingLoaiGhe);
        }
        throw new ResourceNotFoundException("LoaiGhe not found with id: " + id);
    }

    @Override
    public void deleteById(UUID id) {
        loaiGheRepository.deleteById(id);
    }

    @Override
    public LoaiGhe findById(UUID id) throws ResourceNotFoundException {
        Optional<LoaiGhe> optionalLoaiGhe = loaiGheRepository.findById(id);
        if (optionalLoaiGhe.isPresent()) {
            return optionalLoaiGhe.get();
        }
        throw new ResourceNotFoundException("LoaiGhe not found with id: " + id);
    }
}