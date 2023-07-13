package j5_60.cinematicket.cinematicket.service;

import j5_60.cinematicket.cinematicket.entity.GiaVeLichChieu;
import j5_60.cinematicket.cinematicket.entity.HoaDonDoAn;
import j5_60.cinematicket.cinematicket.entity.key.HoaDonDoAnKey;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.GiaVeLichChieuRepository;
import j5_60.cinematicket.cinematicket.repository.HoaDonDoAnRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
@Transactional
public class GiaVeLichChieuService {
    @Autowired
    private GiaVeLichChieuRepository repo;
    private final int ROWCOUNT = 5;
    private int PageNo = -1;

    public GiaVeLichChieu createGiaVeLichChieu(GiaVeLichChieu gvlc) {
        return repo.save(gvlc);
    }

    public GiaVeLichChieu updateGiaVeLichChieun(GiaVeLichChieu giaVeLichChieu) {
        Optional<GiaVeLichChieu> gvlcDB = this.repo.findById(giaVeLichChieu.getId());
        if (gvlcDB.isPresent()) {
            GiaVeLichChieu gvlcUd = gvlcDB.get();
            gvlcUd.setId(gvlcUd.getId());
            gvlcUd.setCreateAt(gvlcUd.getCreateAt());
            gvlcUd.setCreateBy(gvlcUd.getCreateBy());
            gvlcUd.setDeleted(gvlcUd.isDeleted());
            gvlcUd.setUpdateAt(gvlcUd.getUpdateAt());
            gvlcUd.setUpdateBy(gvlcUd.getUpdateBy());
            gvlcUd.setGia(gvlcUd.getGia());
            gvlcUd.setTrangThai(gvlcUd.getTrangThai());
            gvlcUd.setSlGhe(gvlcUd.getSlGhe());
            gvlcUd.setLichChieu(gvlcUd.getLichChieu());
            gvlcUd.setLoaiGhe(gvlcUd.getLoaiGhe());
            return gvlcUd;
        } else {
            throw new ResourceNotFoundException("Can not find Hoa Don Do An  with id" + giaVeLichChieu.getId());
        }
    }
}
