package j5_60.cinematicket.cinematicket.service.manager;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import j5_60.cinematicket.cinematicket.dto.ghe.RowSeat;
import j5_60.cinematicket.cinematicket.dto.ghe.Seat;
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
}
