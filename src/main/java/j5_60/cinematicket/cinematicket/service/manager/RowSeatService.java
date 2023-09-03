package j5_60.cinematicket.cinematicket.service.manager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.model.dto.ghe.RowSeat;
import j5_60.cinematicket.cinematicket.model.dto.ghe.request.NewRow;
import j5_60.cinematicket.cinematicket.model.dto.ghe.request.SeatRequest;
import j5_60.cinematicket.cinematicket.model.entity.Seat;
import j5_60.cinematicket.cinematicket.model.entity.SeatType;
import j5_60.cinematicket.cinematicket.model.entity.CimenaRoom;
import j5_60.cinematicket.cinematicket.repository.CenimaRoomRepository;
import j5_60.cinematicket.cinematicket.repository.SeatRepository;
import j5_60.cinematicket.cinematicket.repository.SeatTypeRepository;

@Service
public class RowSeatService {
  @Autowired
  SeatRepository repository;
  @Autowired
  SeatTypeRepository lgrepository;
  @Autowired
  CenimaRoomRepository pcrepository;

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
