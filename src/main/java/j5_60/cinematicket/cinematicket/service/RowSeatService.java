package j5_60.cinematicket.cinematicket.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import j5_60.cinematicket.cinematicket.dto.ghe.RowSeat;
import j5_60.cinematicket.cinematicket.entity.Ghe;
import j5_60.cinematicket.cinematicket.repository.GheRepository;

public class RowSeatService {
  @Autowired
  GheRepository repository;

  public RowSeat getRow(int row, UUID idPhongChieu) {
    List<Ghe> list = repository.getRowSeatInPhongChieu(idPhongChieu, row);
    RowSeat rowSeat = new RowSeat(idPhongChieu, row, list);
    return rowSeat;
  }

  public RowSeat addSeatInRow(int row, int colum, UUID idLoaiGhe, UUID idPhongChieu) {
    List<Ghe> ghe = getRow(row, idPhongChieu).getRowSeat();
    Ghe gheadd = new Ghe();
    gheadd.setCot(null);
    gheadd.setHang(null);
    gheadd.setLoaiGhe(null);
    gheadd.setPhongChieu(null);

    return null;
  }
}
