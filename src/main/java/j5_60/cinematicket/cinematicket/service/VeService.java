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

import j5_60.cinematicket.cinematicket.entity.Ve;
import j5_60.cinematicket.cinematicket.entity.key.VeKey;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.VeRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class VeService {
    @Autowired
    private VeRepository repo;

    private final int ROWCOUNT = 5;
    private int PageNo = -1;

    public Ve createVe(Ve ve) {
        return repo.save(ve);
    }

    public Ve updateVe(Ve ve) {
        Optional<Ve> veDb = this.repo.findById(ve.getId());
        if (veDb.isPresent()) {
            Ve veud = veDb.get();
            veud.setId(ve.getId());
            veud.setTrangThai(ve.getTrangThai());
            veud.setCreateAt(ve.getCreateAt());
            veud.setCreateBy(ve.getCreateBy());
            veud.setDeleted(ve.isDeleted());
            veud.setUpdateAt(ve.getUpdateAt());
            veud.setUpdateBy(ve.getUpdateBy());
            return veud;
        } else {
            throw new ResourceNotFoundException("Can not find person with id" + ve.getId());
        }
    }

    public List<Ve> getAllVe() {
        return repo.findAll();
    }

    // public List<Ve> searchByName(){
    // return repo.findAll();
    // }
    public Ve getVeById(UUID id_lich_chieu, UUID id_ghe) {
        Optional<Ve> ve = repo.findById(new VeKey(id_lich_chieu, id_ghe));
        if (ve.isPresent()) {
            return ve.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Phuong thuc thanh toan with id = ");
        }
    }

    public Ve deleteVe(UUID id_lich_chieu, UUID id_ghe) {
        Optional<Ve> ve = repo.findById(new VeKey(id_lich_chieu, id_ghe));
        if (ve.isPresent()) {
            return ve.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Phuong thuc thanh toan with id = ");
        }
    }

    public Ve setDeteleteState(UUID id_lich_chieu, UUID id_ghe) {
        Optional<Ve> veDb = this.repo.findById(new VeKey(id_lich_chieu, id_ghe));
        if (veDb.isPresent()) {
            Ve veud = veDb.get();
            veud.setDeleted(true);
            return veud;
        } else {
            throw new ResourceNotFoundException("Can not find person with id");
        }
    }

    public List<Ve> getPageNo(int pageNo, String sortBy, String sortDir) {
        this.PageNo = pageNo;
        List<Ve> ves;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // Pageable object
        Pageable pageable = PageRequest.of(pageNo-1, ROWCOUNT, sort);
        // findAll method and pass pageable instance
        Page<Ve> page = repo.findAll(pageable);
        ves = page.getContent();
        return ves;
    }

    public List<Ve> getNextPage(String sortBy, String sortDir) {
        if (this.PageNo >= getPanigation().length - 1) {
            return this.getPageNo(this.getPanigation().length - 1, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo + 1, sortBy, sortBy);
        }
    }
    public List<Ve> getPrevPage(String sortBy, String sortDir) {
        if (this.PageNo <= 0) {
            return this.getPageNo(0, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo - 1, sortBy, sortBy);
        }
    }

    public int[] getPanigation() {
        Pageable pageable = PageRequest.of(1, ROWCOUNT);
        Page<Ve> page = repo.findAll(pageable);
        int totalPage = page.getTotalPages();
        int[] array = IntStream.rangeClosed(0, totalPage-1).toArray();
        return array;
    }
    public int getCrrentPage(){
        return this.PageNo;
    }
}
