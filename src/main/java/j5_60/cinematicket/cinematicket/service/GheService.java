package j5_60.cinematicket.cinematicket.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import j5_60.cinematicket.cinematicket.entity.Ghe;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.GheRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class GheService {
    @Autowired
    public GheRepository gheRepository;

    public Page<Ghe> findAll(Pageable pageable) {
        return gheRepository.findAll(pageable);
    }

    public List<Ghe> sapXep() {
        return gheRepository.findAllByOrderByTen();
    }

    public List<Ghe> searchGhe(String keyword) {
        return gheRepository.findByTenContainingIgnoreCase(keyword);
    }

    public List<Ghe> getAll() {
        return gheRepository.findAll();
    }

    public Ghe save(Ghe ghe) {
        ghe.setCreateAt(LocalDateTime.now());
        return gheRepository.save(ghe);
    }

    public Ghe updateGhe(UUID id, Ghe ghe) throws ResourceNotFoundException {
        Optional<Ghe> optionalGhe = gheRepository.findById(id);
        if (optionalGhe.isPresent()) {
            Ghe existingGhe = optionalGhe.get();
            existingGhe.setTen(ghe.getTen());
            existingGhe.setLoaiGhe(ghe.getLoaiGhe());
            existingGhe.setPhongChieu(ghe.getPhongChieu());
            existingGhe.setTrangThai(ghe.getTrangThai());
            existingGhe.setUpdateAt(LocalDateTime.now());
            // Cập nhật các thuộc tính khác của đối tượng Ghe

            return gheRepository.save(existingGhe);
        }
        throw new ResourceNotFoundException("Ghe not found with id: " + id);
    }

    public void deleteById(UUID id) {
        gheRepository.deleteById(id);
    }

    public Ghe findById(UUID id) throws ResourceNotFoundException {
        Optional<Ghe> optionalGhe = gheRepository.findById(id);
        if (optionalGhe.isPresent()) {
            return optionalGhe.get();
        }
        throw new ResourceNotFoundException("Ghe not found with id: " + id);
    }
}
