package j5_60.cinematicket.cinematicket.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import j5_60.cinematicket.cinematicket.entity.Ghe;
import j5_60.cinematicket.cinematicket.entity.PhongChieu;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.GheRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class GheService {

    @Autowired
    GheRepository gheRepository;

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
        validateUniqueTenInHangCotPhongChieu(ghe);
        validateUniqueHangCotInPhongChieu(ghe);
        ghe.setCreateAt(LocalDateTime.now());
        return gheRepository.save(ghe);
    }

    public Ghe updateGhe(UUID id, Ghe ghe) throws ResourceNotFoundException {
        Optional<Ghe> optionalGhe = gheRepository.findById(id);
        if (optionalGhe.isPresent()) {
            Ghe existingGhe = optionalGhe.get();
            existingGhe.setTen(ghe.getTen());
            existingGhe.setHang(ghe.getHang());
            existingGhe.setCot(ghe.getCot());
            existingGhe.setLoaiGhe(ghe.getLoaiGhe());
            existingGhe.setPhongChieu(ghe.getPhongChieu());
            existingGhe.setTrangThai(ghe.getTrangThai());
            existingGhe.setUpdateAt(LocalDateTime.now());
            validateUniqueTenInHangCotPhongChieu(existingGhe);
            validateUniqueHangCotInPhongChieu(existingGhe);

            return gheRepository.save(existingGhe);
        }
        throw new ResourceNotFoundException("Ghe not found with id: " + id);
    }

    public void deleteById(UUID id) {
        gheRepository.deleteById(id);
    }

    public List<Ghe> fillGheInPhongChieu(PhongChieu phongChieu) {
        return gheRepository.findAll()
                .stream()
                .filter(ghe -> ghe.getPhongChieu().equals(phongChieu))
                .collect(Collectors.toList());
    }

    public Ghe findById(UUID id) throws ResourceNotFoundException {
        Optional<Ghe> optionalGhe = gheRepository.findById(id);
        if (optionalGhe.isPresent()) {
            return optionalGhe.get();
        }
        throw new ResourceNotFoundException("Ghe not found with id: " + id);
    }

    // Custom validation logic

    private void validateUniqueTenInHangCotPhongChieu(Ghe ghe) {
        List<Ghe> ghesWithSameTen = gheRepository.findByTenAndHangAndCotAndPhongChieu(
                ghe.getTen(), ghe.getHang(), ghe.getCot(), ghe.getPhongChieu());

        if (!ghesWithSameTen.isEmpty()) {
            throw new IllegalArgumentException("Tên ghế đã tồn tại trong hàng, cột và phòng chiếu.");
        }
    }

    private void validateUniqueHangCotInPhongChieu(Ghe ghe) {
        List<Ghe> ghesWithSameHangCot = gheRepository.findByHangAndCotAndPhongChieu(
                ghe.getHang(), ghe.getCot(), ghe.getPhongChieu());

        if (!ghesWithSameHangCot.isEmpty()) {
            throw new IllegalArgumentException("Ghế đã tồn tại cùng hàng và cột trong phòng chiếu.");
        }
    }
}