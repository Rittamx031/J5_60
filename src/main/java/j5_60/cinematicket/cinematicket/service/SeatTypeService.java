package j5_60.cinematicket.cinematicket.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.model.entity.SeatType;
import j5_60.cinematicket.cinematicket.repository.SeatTypeRepository;
import jakarta.transaction.Transactional;
@Service
@Transactional  
public class SeatTypeService {
    
    @Autowired
    public SeatTypeRepository loaiGheRepository;

    public List<SeatType> sapXep() {
        return loaiGheRepository.findAllByOrderByTen();
    }
    public List<SeatType> searchLoaiGhe(String keyword) {
        return loaiGheRepository.findByTenContainingIgnoreCase(keyword);
    }
    public Page<SeatType> findAll(Pageable pageable) {
        return loaiGheRepository.findAll(pageable);
    }
    
    public List<SeatType> getAll() {
        return loaiGheRepository.findAll();
    }

    
    public SeatType save(SeatType loaiGhe) {
        return loaiGheRepository.save(loaiGhe);
    }

    
    public SeatType updateLoaiGhe(UUID id, SeatType loaiGhe) throws ResourceNotFoundException {
        Optional<SeatType> optionalLoaiGhe = loaiGheRepository.findById(id);
        if (optionalLoaiGhe.isPresent()) {
            SeatType existingLoaiGhe = optionalLoaiGhe.get();
            existingLoaiGhe.setTen(loaiGhe.getTen());
            existingLoaiGhe.setTrangThai(loaiGhe.getTrangThai());
            existingLoaiGhe.setUpdateAt(LocalDateTime.now());
            // Cập nhật các thuộc tính khác của LoaiGhe

            return loaiGheRepository.save(existingLoaiGhe);
        }
        throw new ResourceNotFoundException("LoaiGhe not found with id: " + id);
    }

    
    public void deleteById(UUID id) {
        loaiGheRepository.deleteById(id);
    }

    
    public SeatType findById(UUID id) throws ResourceNotFoundException {
        Optional<SeatType> optionalLoaiGhe = loaiGheRepository.findById(id);
        if (optionalLoaiGhe.isPresent()) {
            return optionalLoaiGhe.get();
        }
        throw new ResourceNotFoundException("LoaiGhe not found with id: " + id);
    }}
