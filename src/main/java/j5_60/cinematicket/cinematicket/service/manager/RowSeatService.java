package j5_60.cinematicket.cinematicket.service.manager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import j5_60.cinematicket.cinematicket.dto.ghe.RowSeat;
import j5_60.cinematicket.cinematicket.dto.ghe.request.NewRow;
import j5_60.cinematicket.cinematicket.dto.ghe.request.Seat;
import j5_60.cinematicket.cinematicket.entity.Ghe;
import j5_60.cinematicket.cinematicket.entity.LoaiGhe;
import j5_60.cinematicket.cinematicket.entity.PhongChieu;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.CenimaRoomRepository;
import j5_60.cinematicket.cinematicket.repository.GheRepository;
import j5_60.cinematicket.cinematicket.repository.SeatTypeRepository;

@Service
public class RowSeatService {
  @Autowired
  GheRepository repository;
  @Autowired
  SeatTypeRepository lgrepository;
  @Autowired
  CenimaRoomRepository pcrepository;

  public RowSeat getRow(int row, UUID idPhongChieu) {
    List<Ghe> list = repository.getRowSeatInPhongChieu(idPhongChieu, row);
    RowSeat rowSeat = new RowSeat(idPhongChieu, row, list);
    return rowSeat;
  }

  public RowSeat addSeatInRow(Seat seat) {
    List<Ghe> rowcount = getRow(seat.getRow(), seat.getIdPhongChieu()).getRowSeat();
    for (Ghe gheinrow : rowcount) {
      if (seat.getColum() == gheinrow.getHang()) {
        rowcount.stream().map(ghe -> {
          if (ghe.getCot() >= seat.getRow()) {
            ghe.setCot(ghe.getCot() + 1);
            ghe.setTen(Seat.convertToCellReference(ghe.getHang(), ghe.getCot()));
          }
          return ghe;
        }).collect(Collectors.toList());
        break;
      }
    }
    // update seat after seat add
    {
      Ghe ghe = new Ghe();
      ghe.setCot(seat.getColum());
      ghe.setHang(seat.getRow());
      ghe.setTen(Seat.convertToCellReference(seat.getRow(), seat.getColum()));
      ghe.setTrangThai(1);
      ghe.setDeleted(false);
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
      rowcount.add(repository.save(ghe));
    }
    repository.saveAll(rowcount);
    return new RowSeat(seat.getIdPhongChieu(), seat.getRow(),
        repository.getRowSeatInPhongChieu(seat.getIdPhongChieu(), seat.getRow()));
  }

  public RowSeat addNewRowSeat(NewRow newRow) {
    Optional<PhongChieu> phongchieu = pcrepository.findById(newRow.getIdPhongChieu());
    Optional<LoaiGhe> loaiGhe = lgrepository.findById(newRow.getIdLoaiGhe());
    List<Ghe> listghe = new ArrayList<>();
    for (int i = 1; i <= newRow.getQuantity(); i++) {
      Ghe ghe = new Ghe();
      ghe.setCot(i);
      ghe.setHang(newRow.getRow());
      ghe.setPhongChieu(phongchieu.get());
      ghe.setLoaiGhe(loaiGhe.get());
      ghe.setTrangThai(1);
      ghe.setCreateAt(LocalDateTime.now());
      ghe.setTen(Seat.convertToCellReference(ghe.getHang(), ghe.getCot()));
      listghe.add(ghe);
    }
    List<Ghe> rs = repository.saveAll(listghe);
    return new RowSeat(newRow.getIdPhongChieu(), newRow.getRow(), rs);
  }

  public Ghe updateSeat(Seat seat) {
    Optional<PhongChieu> phongchieu = pcrepository.findById(seat.getIdPhongChieu());
    Optional<LoaiGhe> loaiGhe = lgrepository.findById(seat.getIdLoaiGhe());
    Optional<Ghe> ghe = repository.findById(seat.getId());
    Ghe gheud = ghe.get();
    gheud.setCot(seat.getColum());
    gheud.setHang(seat.getRow());
    gheud.setTrangThai(seat.getTrangThai());
    gheud.setLoaiGhe(loaiGhe.get());
    gheud.setTen(Seat.convertToCellReference(seat.getRow(), seat.getColum()));
    gheud.setPhongChieu(phongchieu.get());
    gheud.setUpdateAt(LocalDateTime.now());
    return repository.save(gheud);
  }
}