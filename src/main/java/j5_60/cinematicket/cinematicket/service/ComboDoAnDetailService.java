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

import j5_60.cinematicket.cinematicket.entity.ComboDoAnDetail;
import j5_60.cinematicket.cinematicket.entity.key.ComBoDoAnDetailKey;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.ComboDoAnDetailRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComboDoAnDetailService {
        @Autowired
    private ComboDoAnDetailRepository repo;
    private final int ROWCOUNT = 5;
    private int PageNo = -1;

    public ComboDoAnDetail createComboDoAnDetail(ComboDoAnDetail comboDoAnDetail) {
        return repo.save(comboDoAnDetail);
    }

    public ComboDoAnDetail updateComboDoAnDetail(ComboDoAnDetail comboDoAnDetail) {
        Optional<ComboDoAnDetail> comboDoAnDetailDb = this.repo.findById(comboDoAnDetail.getId());
        if (comboDoAnDetailDb.isPresent()) {
            ComboDoAnDetail comboDoAnDetailud = comboDoAnDetailDb.get();
            comboDoAnDetailud.setId(comboDoAnDetail.getId());
            comboDoAnDetailud.setCreateAt(comboDoAnDetail.getCreateAt());
            comboDoAnDetailud.setCreateBy(comboDoAnDetail.getCreateBy());
            comboDoAnDetailud.setDeleted(comboDoAnDetail.isDeleted());
            comboDoAnDetailud.setUpdateAt(comboDoAnDetail.getUpdateAt());
            comboDoAnDetailud.setUpdateBy(comboDoAnDetail.getUpdateBy());
            return comboDoAnDetailud;
        } else {
            throw new ResourceNotFoundException("Can not find person with id" + comboDoAnDetail.getId());
        }
    }

    public List<ComboDoAnDetail> getAllComboDoAnDetail() {
        return repo.findAll();
    }

    // public List<ComboDoAnDetail> searchByName(){
    // return repo.findAll();
    // }
    public ComboDoAnDetail getComboDoAnDetailById(UUID idcombo, UUID idDoAn) {
        Optional<ComboDoAnDetail> phuongthucThanhToan = repo.findById(new ComBoDoAnDetailKey(idcombo, idDoAn));
        if (phuongthucThanhToan.isPresent()) {
            return phuongthucThanhToan.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Chi Tiet com bo with id = ");
        }
    }

    public ComboDoAnDetail deleteComboDoAnDetail(UUID idcombo, UUID idDoAn) {
        Optional<ComboDoAnDetail> phuongthucThanhToan = repo.findById(new ComBoDoAnDetailKey(idcombo, idDoAn));
        if (phuongthucThanhToan.isPresent()) {
            return phuongthucThanhToan.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Chi Tiet com bo with id = ");
        }
    }

    public ComboDoAnDetail setDeteleteState(UUID idcombo, UUID idDoAn) {
        Optional<ComboDoAnDetail> comboDoAnDetailDb = this.repo.findById(new ComBoDoAnDetailKey(idcombo, idDoAn));
        if (comboDoAnDetailDb.isPresent()) {
            ComboDoAnDetail comboDoAnDetailud = comboDoAnDetailDb.get();
            comboDoAnDetailud.setDeleted(true);
            return comboDoAnDetailud;
        } else {
            throw new ResourceNotFoundException("Can not find combo  with idcombo="+idcombo+" and idDoAn="+idDoAn);
        }
    }

    public List<ComboDoAnDetail> getPageNo(int pageNo, String sortBy, String sortDir) {
        this.PageNo = pageNo;
        List<ComboDoAnDetail> comboDoAnDetails;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // Pageable object
        Pageable pageable = PageRequest.of(pageNo, ROWCOUNT, sort);
        // findAll method and pass pageable instance
        Page<ComboDoAnDetail> page = repo.findAll(pageable);
        comboDoAnDetails = page.getContent();
        return comboDoAnDetails;
    }

    public List<ComboDoAnDetail> getNextPage(String sortBy, String sortDir) {
        if (this.PageNo >= getPanigation().length - 1) {
            return this.getPageNo(this.getPanigation().length - 1, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo + 1, sortBy, sortBy);
        }
    }

    public List<ComboDoAnDetail> getPrevPage(String sortBy, String sortDir) {
        if (this.PageNo <= 0) {
            return this.getPageNo(0, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo - 1, sortBy, sortBy);
        }
    }

    public int[] getPanigation() {
        Pageable pageable = PageRequest.of(1, ROWCOUNT);
        Page<ComboDoAnDetail> page = repo.findAll(pageable);
        int totalPage = page.getTotalPages();
        int[] array = IntStream.rangeClosed(0, totalPage).toArray();
        return array;
    }
}
