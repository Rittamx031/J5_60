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

import j5_60.cinematicket.cinematicket.entity.ChiTietThanhToan;
import j5_60.cinematicket.cinematicket.entity.key.ChiTietThanhToanKey;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.ChiTietThanhToanRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ChiTietThanhToanService {
    @Autowired
    private ChiTietThanhToanRepository repo;
    private final int ROWCOUNT = 5;
    private int PageNo = -1;

    public ChiTietThanhToan createChiTietThanhToan(ChiTietThanhToan chiTietThanhToan) {
        return repo.save(chiTietThanhToan);
    }

    public ChiTietThanhToan updateChiTietThanhToan(ChiTietThanhToan chiTietThanhToan) {
        Optional<ChiTietThanhToan> chiTietThanhToanDb = this.repo.findById(chiTietThanhToan.getId());
        if (chiTietThanhToanDb.isPresent()) {
            ChiTietThanhToan chiTietThanhToanud = chiTietThanhToanDb.get();
            chiTietThanhToanud.setId(chiTietThanhToan.getId());
            chiTietThanhToanud.setTrangThai(chiTietThanhToan.getTrangThai());
            chiTietThanhToanud.setTongTien(chiTietThanhToan.getTongTien());
            chiTietThanhToanud.setCreateAt(chiTietThanhToan.getCreateAt());
            chiTietThanhToanud.setCreateBy(chiTietThanhToan.getCreateBy());
            chiTietThanhToanud.setDeleted(chiTietThanhToan.isDeleted());
            chiTietThanhToanud.setUpdateAt(chiTietThanhToan.getUpdateAt());
            chiTietThanhToanud.setUpdateBy(chiTietThanhToan.getUpdateBy());
            return chiTietThanhToanud;
        } else {
            throw new ResourceNotFoundException("Can not find person with id" + chiTietThanhToan.getId());
        }
    }

    public List<ChiTietThanhToan> getAllChiTietThanhToan() {
        return repo.findAll();
    }

    // public List<ChiTietThanhToan> searchByName(){
    // return repo.findAll();
    // }
    public ChiTietThanhToan getChiTietThanhToanById(UUID idHD, UUID idPTTT) {
        Optional<ChiTietThanhToan> phuongthucThanhToan = repo.findById(new ChiTietThanhToanKey(idHD, idPTTT));
        if (phuongthucThanhToan.isPresent()) {
            return phuongthucThanhToan.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Phuong thuc thanh toan with id = ");
        }
    }

    public ChiTietThanhToan deleteChiTietThanhToan(UUID idHD, UUID idPTTT) {
        Optional<ChiTietThanhToan> phuongthucThanhToan = repo.findById(new ChiTietThanhToanKey(idHD, idPTTT));
        if (phuongthucThanhToan.isPresent()) {
            return phuongthucThanhToan.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Phuong thuc thanh toan with id = ");
        }
    }

    public ChiTietThanhToan setDeteleteState(UUID idHD, UUID idPTTT) {
        Optional<ChiTietThanhToan> chiTietThanhToanDb = this.repo.findById(new ChiTietThanhToanKey(idHD, idPTTT));
        if (chiTietThanhToanDb.isPresent()) {
            ChiTietThanhToan chiTietThanhToanud = chiTietThanhToanDb.get();
            chiTietThanhToanud.setDeleted(true);
            return chiTietThanhToanud;
        } else {
            throw new ResourceNotFoundException("Can not find person with id");
        }
    }

    public List<ChiTietThanhToan> getPageNo(int pageNo, String sortBy, String sortDir) {
        this.PageNo = pageNo;
        List<ChiTietThanhToan> chiTietThanhToans;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // Pageable object
        Pageable pageable = PageRequest.of(pageNo, ROWCOUNT, sort);
        // findAll method and pass pageable instance
        Page<ChiTietThanhToan> page = repo.findAll(pageable);
        chiTietThanhToans = page.getContent();
        return chiTietThanhToans;
    }

    public List<ChiTietThanhToan> getNextPage(String sortBy, String sortDir) {
        if (this.PageNo >= getPanigation().length - 1) {
            return this.getPageNo(this.getPanigation().length - 1, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo + 1, sortBy, sortBy);
        }
    }

    public List<ChiTietThanhToan> getPrevPage(String sortBy, String sortDir) {
        if (this.PageNo <= 0) {
            return this.getPageNo(0, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo - 1, sortBy, sortBy);
        }
    }

    public int[] getPanigation() {
        Pageable pageable = PageRequest.of(1, ROWCOUNT);
        Page<ChiTietThanhToan> page = repo.findAll(pageable);
        int totalPage = page.getTotalPages();
        int[] array = IntStream.rangeClosed(0, totalPage).toArray();
        return array;
    }
}
