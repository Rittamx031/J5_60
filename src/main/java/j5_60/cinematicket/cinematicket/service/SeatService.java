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
import j5_60.cinematicket.cinematicket.dto.ghe.Seat;
import j5_60.cinematicket.cinematicket.entity.Ghe;
import j5_60.cinematicket.cinematicket.entity.LichChieu;
import j5_60.cinematicket.cinematicket.entity.LoaiGhe;
import j5_60.cinematicket.cinematicket.entity.PhongChieu;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.CenimaRoomRepository;
import j5_60.cinematicket.cinematicket.repository.GheRepository;
import j5_60.cinematicket.cinematicket.repository.SeatTypeRepository;
import j5_60.cinematicket.cinematicket.repository.ShowtimesRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SeatService {

    @Autowired
    GheRepository repository;
    @Autowired
    SeatTypeRepository lgrepository;
    @Autowired
    CenimaRoomRepository pcrepository;

    public Page<Ghe> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Ghe> getAll() {
        return repository.findAll();
    }

    public Ghe saveGhe(Seat seat) {
        Ghe ghe = new Ghe();
        ghe.setCot(seat.getColum());
        ghe.setHang(seat.getColum());
        Optional<LoaiGhe> optionalLoaiGhe = lgrepository.findById(seat.getIdLoaiGhe());
        if (optionalLoaiGhe.isPresent()) {
            ghe.setLoaiGhe(optionalLoaiGhe.get());
        } else {
            throw new ResourceNotFoundException("PhongChieu not found with id: ");
        }
        Optional<PhongChieu> optionalPhongChieu = pcrepository.findById(seat.getIdPhongChieu());
        if (optionalPhongChieu.isPresent()) {
            ghe.setPhongChieu(optionalPhongChieu.get());
        } else {
            throw new ResourceNotFoundException("PhongChieu not found with id: ");
        }
        return repository.save(ghe);
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

    public Ghe findById(UUID id) throws ResourceNotFoundException {
        Optional<Ghe> optionalGhe = repository.findById(id);
        if (optionalGhe.isPresent()) {
            return optionalGhe.get();
        }
        throw new ResourceNotFoundException("Ghe not found with id: " + id);
    }
}