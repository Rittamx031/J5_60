package j5_60.cinematicket.cinematicket.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import j5_60.cinematicket.cinematicket.entity.HoaDon;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.HoaDonRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class HoaDonService {
    @Autowired
    private HoaDonRepository repo;
    private final int ROWCOUNT = 5;
    private int PageNo = -1;

    public HoaDon createHoaDon(HoaDon hoaDon) {
        return repo.save(hoaDon);
    }

    public HoaDon updateHoaDon(HoaDon hoaDon) {
        Optional<HoaDon> hoaDonDb = this.repo.findById(hoaDon.getId());
        if (hoaDonDb.isPresent()) {
            HoaDon hoaDonud = hoaDonDb.get();
            hoaDonud.setId(hoaDon.getId());
            hoaDonud.setThoiGianThanhToan(hoaDon.getThoiGianThanhToan());
            hoaDonud.setTongGia(hoaDon.getTongGia());
            hoaDonud.setTongGiaSauGiam(hoaDon.getTongGiaSauGiam());
            hoaDonud.setGhiChu(hoaDon.getGhiChu());
            hoaDonud.setPhuongThucThanhToan(hoaDon.getPhuongThucThanhToan());
            hoaDonud.setTrangThai(hoaDon.getTrangThai());
            hoaDonud.setMaHoaDon(hoaDon.getMaHoaDon());
            hoaDonud.setCreateAt(hoaDon.getCreateAt());
            hoaDonud.setCreateBy(hoaDon.getCreateBy());
            hoaDonud.setDeleted(hoaDon.isDeleted());
            hoaDonud.setUpdateAt(hoaDon.getUpdateAt());
            hoaDonud.setUpdateBy(hoaDon.getUpdateBy());
            return hoaDonud;
        } else {
            throw new ResourceNotFoundException("Can not find Hoa Don with id" + hoaDon.getId());
        }
    }

    public List<HoaDon> getAllHoaDon() {
        return repo.findAll();
    }

    public List<HoaDon> search(String txtSearch) {
        return repo.search(txtSearch);
    }

    public HoaDon getHoaDonById(UUID id) {
        Optional<HoaDon> hoaDon = repo.findById(id);
        if (hoaDon.isPresent()) {
            return hoaDon.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Hoa Don  with id = " + id);
        }
    }

    public HoaDon deleteHoaDon(UUID id) {
        Optional<HoaDon> hoaDon = repo.findById(id);
        if (hoaDon.isPresent()) {
            return hoaDon.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Hoa Don  with id = " + id);
        }
    }

    public HoaDon setDeteleteState(UUID id) {
        Optional<HoaDon> hoaDonDb = this.repo.findById(id);
        if (hoaDonDb.isPresent()) {
            HoaDon hoaDonud = hoaDonDb.get();
            hoaDonud.setDeleted(true);
            return hoaDonud;
        } else {
            throw new ResourceNotFoundException("Can not find Hoa Don with id" + id);
        }
    }

    public List<HoaDon> getPageNo(int pageNo, String sortBy, String sortDir) {
        this.PageNo = pageNo;
        List<HoaDon> hoaDons;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // Pageable object
        Pageable pageable = PageRequest.of(pageNo - 1, ROWCOUNT, sort);
        // findAll method and pass pageable instance
        Page<HoaDon> page = repo.findAll(pageable);
        hoaDons = page.getContent();
        return hoaDons;
    }

    public List<HoaDon> getNextPage(String sortBy, String sortDir) {
        if (this.PageNo >= getPanigation().length - 1) {
            return this.getPageNo(this.getPanigation().length - 1, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo + 1, sortBy, sortBy);
        }
    }

    public List<HoaDon> getPrevPage(String sortBy, String sortDir) {
        if (this.PageNo <= 0) {
            return this.getPageNo(0, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo - 1, sortBy, sortBy);
        }
    }

    public int[] getPanigation() {
        Pageable pageable = PageRequest.of(1, ROWCOUNT);
        Page<HoaDon> page = repo.findAll(pageable);
        int totalPage = page.getTotalPages();
        int[] array = IntStream.rangeClosed(1, totalPage).toArray();
        return array;
    }
}
