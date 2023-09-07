package j5_60.cinematicket.cinematicket.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.model.entity.Seat;
import j5_60.cinematicket.cinematicket.model.entity.SeatType;
import j5_60.cinematicket.cinematicket.model.dto.request.NewRow;
import j5_60.cinematicket.cinematicket.model.dto.request.SeatRequest;
import j5_60.cinematicket.cinematicket.model.dto.seat.RowSeat;
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

    public Seat saveGhe(SeatRequest seat) {
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
    // manager

    public RowSeat getRow(int row, UUID idPhongChieu) {
        List<Seat> list = repository.getRowSeatInPhongChieu(idPhongChieu, row);
        RowSeat rowSeat = new RowSeat(idPhongChieu, row, list);
        return rowSeat;
    }

    public RowSeat addSeatInRow(SeatRequest seat) {
        List<Seat> rowcount = getRow(seat.getRow(), seat.getIdPhongChieu()).getRowSeat();
        for (Seat gheinrow : rowcount) {
            if (seat.getColum() == gheinrow.getHang()) {
                rowcount.stream().map(ghe -> {
                    if (ghe.getCot() >= seat.getRow()) {
                        ghe.setCot(ghe.getCot() + 1);
                        ghe.setTen(SeatRequest.convertToCellReference(ghe.getHang(), ghe.getCot()));
                    }
                    return ghe;
                }).collect(Collectors.toList());
                break;
            }
        }
        // update seat after seat add
        {
            Seat ghe = new Seat();
            ghe.setCot(seat.getColum());
            ghe.setHang(seat.getRow());
            ghe.setTen(SeatRequest.convertToCellReference(seat.getRow(), seat.getColum()));
            ghe.setTrangThai(1);
            ghe.setDeleted(false);
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
            rowcount.add(repository.save(ghe));
        }
        repository.saveAll(rowcount);
        return new RowSeat(seat.getIdPhongChieu(), seat.getRow(),
                repository.getRowSeatInPhongChieu(seat.getIdPhongChieu(), seat.getRow()));
    }

    public RowSeat addNewRowSeat(NewRow newRow) {
        Optional<CimenaRoom> phongchieu = pcrepository.findById(newRow.getIdPhongChieu());
        Optional<SeatType> loaiGhe = lgrepository.findById(newRow.getIdLoaiGhe());
        List<Seat> listghe = new ArrayList<>();
        for (int i = 1; i <= newRow.getQuantity(); i++) {
            Seat ghe = new Seat();
            ghe.setCot(i);
            ghe.setHang(newRow.getRow());
            ghe.setPhongChieu(phongchieu.get());
            ghe.setLoaiGhe(loaiGhe.get());
            ghe.setTrangThai(1);
            ghe.setCreateAt(LocalDateTime.now());
            ghe.setTen(SeatRequest.convertToCellReference(ghe.getHang(), ghe.getCot()));
            listghe.add(ghe);
        }
        List<Seat> rs = repository.saveAll(listghe);
        return new RowSeat(newRow.getIdPhongChieu(), newRow.getRow(), rs);
    }

    public Seat updateSeat(SeatRequest seat) {
        Optional<CimenaRoom> phongchieu = pcrepository.findById(seat.getIdPhongChieu());
        Optional<SeatType> loaiGhe = lgrepository.findById(seat.getIdLoaiGhe());
        Optional<Seat> ghe = repository.findById(seat.getId());
        Seat gheud = ghe.get();
        gheud.setCot(seat.getColum());
        gheud.setHang(seat.getRow());
        gheud.setTrangThai(seat.getTrangThai());
        gheud.setLoaiGhe(loaiGhe.get());
        gheud.setTen(SeatRequest.convertToCellReference(seat.getRow(), seat.getColum()));
        gheud.setPhongChieu(phongchieu.get());
        gheud.setUpdateAt(LocalDateTime.now());
        return repository.save(gheud);
    }

}