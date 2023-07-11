package j5_60.cinematicket.cinematicket.service.impl;
import j5_60.cinematicket.cinematicket.entity.Ghe;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.GheRepository;
import j5_60.cinematicket.cinematicket.service.GheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GheServiceImpl implements GheService {

    private final GheRepository gheRepository;

    @Autowired
    public GheServiceImpl(GheRepository gheRepository) {
        this.gheRepository = gheRepository;
    }
    @Override
    public Page<Ghe> findAll(Pageable pageable) {
        return gheRepository.findAll(pageable);
    }

    @Override
    public List<Ghe> sapXep() {
        return gheRepository.findAllByOrderByTen();
    }
    @Override
    public List<Ghe> searchGhe(String keyword) {
        return gheRepository.findByTenContainingIgnoreCase(keyword);
    }
    @Override
    public List<Ghe> getAll() {
        return gheRepository.findAll();
    }

    @Override
    public Ghe save(Ghe ghe) {
        ghe.setCreateAt(LocalDateTime.now());
        return gheRepository.save(ghe);
    }

    @Override
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

    @Override
    public void deleteById(UUID id) {
        gheRepository.deleteById(id);
    }

    @Override
    public Ghe findById(UUID id) throws ResourceNotFoundException {
        Optional<Ghe> optionalGhe = gheRepository.findById(id);
        if (optionalGhe.isPresent()) {
            return optionalGhe.get();
        }
        throw new ResourceNotFoundException("Ghe not found with id: " + id);
    }


}
