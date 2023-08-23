package j5_60.cinematicket.cinematicket.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import j5_60.cinematicket.cinematicket.dto.ghe.RowSeat;
import j5_60.cinematicket.cinematicket.entity.Ghe;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.GheRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SeatService {

    @Autowired
    GheRepository repository;

    public Page<Ghe> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Ghe> searchGhe(String keyword) {
        return repository.findByTenContainingIgnoreCase(keyword);
    }

    public List<Ghe> getAll() {
        return repository.findAll();
    }

    public Ghe updateGhe(UUID id, Ghe ghe) throws ResourceNotFoundException {
        Optional<Ghe> optionalGhe = repository.findById(id);
        if (optionalGhe.isPresent()) {
            Ghe existingGhe = optionalGhe.get();
            existingGhe.setTen(ghe.getTen());
            existingGhe.setHang(ghe.getHang());
            existingGhe.setCot(ghe.getCot());
            existingGhe.setLoaiGhe(ghe.getLoaiGhe());
            existingGhe.setPhongChieu(ghe.getPhongChieu());
            existingGhe.setTrangThai(ghe.getTrangThai());
            existingGhe.setUpdateAt(LocalDateTime.now());
            return repository.save(existingGhe);
        }
        throw new ResourceNotFoundException("Ghe not found with id: " + id);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public List<Ghe> getAllgheByPhongChieu(UUID idPhongChieu) {
        return repository.getGheInPhongChieu(idPhongChieu);
    }

    public List<Ghe> createRowNew() {
        return null;
    }

    public Ghe findById(UUID id) throws ResourceNotFoundException {
        Optional<Ghe> optionalGhe = repository.findById(id);
        if (optionalGhe.isPresent()) {
            return optionalGhe.get();
        }
        throw new ResourceNotFoundException("Ghe not found with id: " + id);
    }
}