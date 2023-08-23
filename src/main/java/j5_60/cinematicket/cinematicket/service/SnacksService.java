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

import j5_60.cinematicket.cinematicket.entity.DoAn;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.DoAnRepository;

@Service
// @Transactional
public class SnacksService {
    @Autowired
    private DoAnRepository repo;
    private final int ROWCOUNT = 5;
    private int PageNo = -1;

    public DoAn createDoAn(DoAn doAn) {
        return repo.save(doAn);
    }

    public DoAn updateDoAn(DoAn doAn) {
        Optional<DoAn> doAnDb = this.repo.findById(doAn.getId());
        if (doAnDb.isPresent()) {
            DoAn doAnud = doAnDb.get();
            doAnud.setId(doAn.getId());
            doAnud.setGia(doAn.getGia());
            doAnud.setTen(doAn.getTen());
            doAnud.setCreateAt(doAn.getCreateAt());
            doAnud.setCreateBy(doAn.getCreateBy());
            doAnud.setDeleted(doAn.isDeleted());
            doAnud.setUpdateAt(doAn.getUpdateAt());
            doAnud.setUpdateBy(doAn.getUpdateBy());
            return doAnud;
        } else {
            throw new ResourceNotFoundException("Can not find do an with id" + doAn.getId());
        }
    }

    public List<DoAn> getAllDoAn() {
        return repo.findAll();
    }

    public List<DoAn> searchDoAn(String txt) {
        return repo.search(txt);
    }

    // public List<DoAn> searchByName(){
    // return repo.findAll();
    // }
    public DoAn getDoAnById(UUID id) {
        Optional<DoAn> doAn = repo.findById(id);
        if (doAn.isPresent()) {
            return doAn.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find do an with id = " + id);
        }
    }

    public DoAn deleteDoAn(UUID id) {
        Optional<DoAn> doAn = repo.findById(id);
        if (doAn.isPresent()) {
            return doAn.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find do an with id = " + id);
        }
    }

    public DoAn setDeteleteState(UUID id) {
        Optional<DoAn> doAnDb = this.repo.findById(id);
        if (doAnDb.isPresent()) {
            DoAn doAnud = doAnDb.get();
            doAnud.setDeleted(true);
            return doAnud;
        } else {
            throw new ResourceNotFoundException("Can not find do an with id" + id);
        }
    }

    public List<DoAn> getPageNo(int pageNo, String sortBy, String sortDir) {
        this.PageNo = pageNo;
        List<DoAn> doAns;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // Pageable object
        Pageable pageable = PageRequest.of(pageNo - 1, ROWCOUNT, sort);
        // findAll method and pass pageable instance
        Page<DoAn> page = repo.findAll(pageable);
        doAns = page.getContent();
        return doAns;
    }

    public List<DoAn> getNextPage(String sortBy, String sortDir) {
        if (this.PageNo >= getPanigation().length - 1) {
            return this.getPageNo(this.getPanigation().length, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo + 1, sortBy, sortBy);
        }
    }

    public List<DoAn> getPrevPage(String sortBy, String sortDir) {
        if (this.PageNo <= 0) {
            return this.getPageNo(1, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo - 1, sortBy, sortBy);
        }
    }

    public int[] getPanigation() {
        Pageable pageable = PageRequest.of(0, ROWCOUNT);
        Page<DoAn> page = repo.findAll(pageable);
        int totalPage = page.getTotalPages();
        int[] array = IntStream.rangeClosed(1, totalPage).toArray();
        return array;
    }

    public int getCrrentPage() {
        return this.PageNo;
    }
}
