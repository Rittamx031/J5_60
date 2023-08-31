package j5_60.cinematicket.cinematicket.service.admin;

import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.model.entity.ShowTimePrice;
import j5_60.cinematicket.cinematicket.model.entity.ShowTimes;
import j5_60.cinematicket.cinematicket.model.entity.SeatType;
import j5_60.cinematicket.cinematicket.model.entity.key.GiaVeLichChieuKey;
import j5_60.cinematicket.cinematicket.repository.ShowtimePricesRepository;
import j5_60.cinematicket.cinematicket.repository.ShowtimesRepository;
import j5_60.cinematicket.cinematicket.repository.SeatTypeRepository;
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
public class ShowtimePriceService {
    @Autowired
    private ShowtimePricesRepository repo;
    @Autowired
    private ShowtimesRepository lcrepo;
    @Autowired
    private SeatTypeRepository lgrepo;
    private final int ROWCOUNT = 5;
    private int PageNo = -1;

    public ShowTimePrice createGiaVeLichChieu(ShowTimePrice giaVeLichChieuDetail) {
        Optional<ShowTimes> lichChieu = lcrepo.findById(giaVeLichChieuDetail.getId().getId_lich_chieu());
        Optional<SeatType> loaighe = lgrepo.findById(giaVeLichChieuDetail.getId().getId_loai_ghe());
        giaVeLichChieuDetail.setLichChieu(lichChieu.get());
        giaVeLichChieuDetail.setLoaiGhe(loaighe.get());
        return repo.save(giaVeLichChieuDetail);
    }

    public ShowTimePrice updateGiaVeLichChieu(ShowTimePrice giaVeLichChieuDetail) {
        Optional<ShowTimePrice> giaVeLichChieuDetailDb = this.repo.findById(giaVeLichChieuDetail.getId());
        if (giaVeLichChieuDetailDb.isPresent()) {
            ShowTimePrice giaVeLichChieuDetailud = giaVeLichChieuDetailDb.get();
            giaVeLichChieuDetailud.setId(giaVeLichChieuDetail.getId());
            giaVeLichChieuDetailud.setTrangThai(giaVeLichChieuDetail.getTrangThai());
            giaVeLichChieuDetailud.setGia(giaVeLichChieuDetail.getGia());
            giaVeLichChieuDetailud.setLoaiGhe(giaVeLichChieuDetail.getLoaiGhe());
            giaVeLichChieuDetailud.setLichChieu(giaVeLichChieuDetail.getLichChieu());
            giaVeLichChieuDetailud.setCreateAt(giaVeLichChieuDetail.getCreateAt());
            giaVeLichChieuDetailud.setCreateBy(giaVeLichChieuDetail.getCreateBy());
            giaVeLichChieuDetailud.setDeleted(giaVeLichChieuDetail.isDeleted());
            giaVeLichChieuDetailud.setUpdateAt(giaVeLichChieuDetail.getUpdateAt());
            giaVeLichChieuDetailud.setUpdateBy(giaVeLichChieuDetail.getUpdateBy());
            return giaVeLichChieuDetailud;
        } else {
            throw new ResourceNotFoundException("Can not find Hoa Don Do An  with id" + giaVeLichChieuDetail.getId());
        }
    }

    public List<ShowTimePrice> getAllGiaVeLichChieu() {
        return repo.findAll();
    }

    // public List<GiaVeLichChieu> searchByName(){
    // return repo.findAll();
    // }
    public ShowTimePrice getGiaVeLichChieuById(UUID id_lich_chieu, UUID idLoaiGhe) {
        Optional<ShowTimePrice> phuongthucThanhToan = repo.findById(new GiaVeLichChieuKey(id_lich_chieu, idLoaiGhe));
        if (phuongthucThanhToan.isPresent()) {
            return phuongthucThanhToan.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Hoa Don Do An  with id = ");
        }
    }

    public ShowTimePrice deleteGiaVeLichChieu(UUID id_lich_chieu, UUID idLoaiGhe) {
        Optional<ShowTimePrice> phuongthucThanhToan = repo.findById(new GiaVeLichChieuKey(id_lich_chieu, idLoaiGhe));
        if (phuongthucThanhToan.isPresent()) {
            return phuongthucThanhToan.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Hoa Don Do An  with id = ");
        }
    }

    public ShowTimePrice setDeteleteState(UUID id_lich_chieu, UUID idLoaiGhe) {
        Optional<ShowTimePrice> giaVeLichChieuDetailDb = this.repo
                .findById(new GiaVeLichChieuKey(id_lich_chieu, idLoaiGhe));
        if (giaVeLichChieuDetailDb.isPresent()) {
            ShowTimePrice giaVeLichChieuDetailud = giaVeLichChieuDetailDb.get();
            giaVeLichChieuDetailud.setDeleted(true);
            return giaVeLichChieuDetailud;
        } else {
            throw new ResourceNotFoundException(
                    "Can not find Hoa Don Do An  with id_lich_chieu=" + id_lich_chieu + " and idLoaiGhe=" + idLoaiGhe);
        }
    }

    public List<ShowTimePrice> getPageNo(int pageNo, String sortBy, String sortDir) {
        this.PageNo = pageNo;
        List<ShowTimePrice> giaVeLichChieuDetails;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // Pageable object
        Pageable pageable = PageRequest.of(pageNo, ROWCOUNT, sort);
        // findAll method and pass pageable instance
        Page<ShowTimePrice> page = repo.findAll(pageable);
        giaVeLichChieuDetails = page.getContent();
        return giaVeLichChieuDetails;
    }

    public List<ShowTimePrice> getNextPage(String sortBy, String sortDir) {
        if (this.PageNo >= getPanigation().length - 1) {
            return this.getPageNo(this.getPanigation().length - 1, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo + 1, sortBy, sortBy);
        }
    }

    public List<ShowTimePrice> getPrevPage(String sortBy, String sortDir) {
        if (this.PageNo <= 0) {
            return this.getPageNo(0, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo - 1, sortBy, sortBy);
        }
    }

    public int[] getPanigation() {
        Pageable pageable = PageRequest.of(1, ROWCOUNT);
        Page<ShowTimePrice> page = repo.findAll(pageable);
        int totalPage = page.getTotalPages();
        int[] array = IntStream.rangeClosed(0, totalPage).toArray();
        return array;
    }

}
