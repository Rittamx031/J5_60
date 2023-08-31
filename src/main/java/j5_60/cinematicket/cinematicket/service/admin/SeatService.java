package j5_60.cinematicket.cinematicket.service.admin;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.model.dto.ghe.request.Seat;
import j5_60.cinematicket.cinematicket.model.entity.Seat;
import j5_60.cinematicket.cinematicket.model.entity.SeatType;
import j5_60.cinematicket.cinematicket.model.entity.CimenaRoom;
import j5_60.cinematicket.cinematicket.repository.CenimaRoomRepository;
import j5_60.cinematicket.cinematicket.repository.SeatRepository;
import j5_60.cinematicket.cinematicket.repository.SeatTypeRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SeatService {

    @Autowired
    SeatRepository repository;
    @Autowired
    SeatTypeRepository lgrepository;
    @Autowired
    CenimaRoomRepository pcrepository;

    public Page<Seat> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Seat> getAll() {
        return repository.findAll();
    }

    public Seat saveGhe(Seat seat) {
        Seat ghe = new Seat();
        ghe.setCot(seat.getColum());
        ghe.setHang(seat.getColum());
        Optional<SeatType> optionalLoaiGhe = lgrepository.findById(seat.getIdLoaiGhe());
        if (optionalLoaiGhe.isPresent()) {
            ghe.setLoaiGhe(optionalLoaiGhe.get());
        } else {
            throw new ResourceNotFoundException("PhongChieu not found with id: ");
        }
        Optional<CimenaRoom> optionalPhongChieu = pcrepository.findById(seat.getIdPhongChieu());
        if (optionalPhongChieu.isPresent()) {
            ghe.setPhongChieu(optionalPhongChieu.get());
        } else {
            throw new ResourceNotFoundException("PhongChieu not found with id: ");
        }
        return repository.save(ghe);
    }

    public Seat updateGhe(UUID id, Seat ghe) throws ResourceNotFoundException {
        Optional<Seat> optionalGhe = repository.findById(id);
        if (optionalGhe.isPresent()) {
            Seat existingGhe = optionalGhe.get();
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

    public List<Seat> getAllgheByPhongChieu(UUID idPhongChieu) {
        return repository.getGheInPhongChieu(idPhongChieu);
    }

    public Seat findById(UUID id) throws ResourceNotFoundException {
        Optional<Seat> optionalGhe = repository.findById(id);
        if (optionalGhe.isPresent()) {
            return optionalGhe.get();
        }
        throw new ResourceNotFoundException("Ghe not found with id: " + id);
    }
}