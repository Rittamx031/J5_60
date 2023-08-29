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

import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.model.entity.PhuongThucThanhToan;
import j5_60.cinematicket.cinematicket.repository.PayMothodRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PayMethodService {
    @Autowired
    private PayMothodRepository repo;
    private final int ROWCOUNT = 5;
    private int PageNo = -1;

    public PhuongThucThanhToan createPhuongThucThanhToan(PhuongThucThanhToan phuongThucThanhToan) {
        return repo.save(phuongThucThanhToan);
    }

    public PhuongThucThanhToan updatePhuongThucThanhToan(PhuongThucThanhToan phuongThucThanhToan) {
        Optional<PhuongThucThanhToan> phuongThucThanhToanDb = this.repo.findById(phuongThucThanhToan.getId());
        if (phuongThucThanhToanDb.isPresent()) {
            PhuongThucThanhToan phuongThucThanhToanud = phuongThucThanhToanDb.get();
            phuongThucThanhToanud.setId(phuongThucThanhToan.getId());
            phuongThucThanhToanud.setHinhThucThanhToan(phuongThucThanhToan.getHinhThucThanhToan());
            phuongThucThanhToanud.setTrangThai(phuongThucThanhToan.getTrangThai());
            phuongThucThanhToanud.setCreateAt(phuongThucThanhToan.getCreateAt());
            phuongThucThanhToanud.setCreateBy(phuongThucThanhToan.getCreateBy());
            phuongThucThanhToanud.setDeleted(phuongThucThanhToan.isDeleted());
            phuongThucThanhToanud.setUpdateAt(phuongThucThanhToan.getUpdateAt());
            phuongThucThanhToanud.setUpdateBy(phuongThucThanhToan.getUpdateBy());
            return phuongThucThanhToanud;
        } else {
            throw new ResourceNotFoundException("Can not find person with id" + phuongThucThanhToan.getId());
        }
    }

    public List<PhuongThucThanhToan> getAllPhuongThucThanhToan() {
        return repo.findAll();
    }

    public List<PhuongThucThanhToan> searchByName(String txtSearch) {
        return repo.search(txtSearch);
    }

    public PhuongThucThanhToan getPhuongThucThanhToanById(UUID id) {
        Optional<PhuongThucThanhToan> phuongthucThanhToan = repo.findById(id);
        if (phuongthucThanhToan.isPresent()) {
            return phuongthucThanhToan.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Phuong thuc thanh toan with id = " + id);
        }
    }

    public PhuongThucThanhToan deletePhuongThucThanhToan(UUID id) {
        Optional<PhuongThucThanhToan> phuongthucThanhToan = repo.findById(id);
        if (phuongthucThanhToan.isPresent()) {
            return phuongthucThanhToan.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Phuong thuc thanh toan with id = " + id);
        }
    }

    public PhuongThucThanhToan setDeteleteState(UUID id) {
        Optional<PhuongThucThanhToan> phuongThucThanhToanDb = this.repo.findById(id);
        if (phuongThucThanhToanDb.isPresent()) {
            PhuongThucThanhToan phuongThucThanhToanud = phuongThucThanhToanDb.get();
            phuongThucThanhToanud.setDeleted(true);
            return phuongThucThanhToanud;
        } else {
            throw new ResourceNotFoundException("Can not find person with id" + id);
        }
    }

    public List<PhuongThucThanhToan> getPageNo(int pageNo, String sortBy, String sortDir) {
        this.PageNo = pageNo;
        List<PhuongThucThanhToan> phuongThucThanhToans;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // Pageable object
        Pageable pageable = PageRequest.of(pageNo-1, ROWCOUNT, sort);
        // findAll method and pass pageable instance
        Page<PhuongThucThanhToan> page = repo.findAll(pageable);
        phuongThucThanhToans = page.getContent();
        return phuongThucThanhToans;
    }

    public List<PhuongThucThanhToan> getNextPage(String sortBy, String sortDir) {
        if (this.PageNo >= getPanigation().length) {
            return this.getPageNo(this.getPanigation().length, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo + 1, sortBy, sortBy);
        }
    }

    public List<PhuongThucThanhToan> getPrevPage(String sortBy, String sortDir) {
        if (this.PageNo <= 0) {
            return this.getPageNo(1, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo - 1, sortBy, sortBy);
        }
    }

    public int[] getPanigation() {
        Pageable pageable = PageRequest.of(1, ROWCOUNT);
        Page<PhuongThucThanhToan> page = repo.findAll(pageable);
        int totalPage = page.getTotalPages();
        int[] array = IntStream.rangeClosed(1, totalPage).toArray();
        return array;
    }
}
