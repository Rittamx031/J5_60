package j5_60.cinematicket.cinematicket.service.admin;

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
import j5_60.cinematicket.cinematicket.model.entity.Snacks;
import j5_60.cinematicket.cinematicket.repository.SnackRepository;

@Service
// @Transactional
public class SnacksService {
    @Autowired
    private SnackRepository repo;
    private final int ROWCOUNT = 5;
    private int PageNo = -1;

    public Snacks createDoAn(Snacks doAn) {
        return repo.save(doAn);
    }

    public Snacks updateDoAn(Snacks doAn) {
        Optional<Snacks> doAnDb = this.repo.findById(doAn.getId());
        if (doAnDb.isPresent()) {
            Snacks doAnud = doAnDb.get();
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

    public List<Snacks> getAllDoAn() {
        return repo.findAll();
    }

    public List<Snacks> searchDoAn(String txt) {
        return repo.search(txt);
    }

    // public List<DoAn> searchByName(){
    // return repo.findAll();
    // }
    public Snacks getDoAnById(UUID id) {
        Optional<Snacks> doAn = repo.findById(id);
        if (doAn.isPresent()) {
            return doAn.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find do an with id = " + id);
        }
    }

    public Snacks deleteDoAn(UUID id) {
        Optional<Snacks> doAn = repo.findById(id);
        if (doAn.isPresent()) {
            return doAn.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find do an with id = " + id);
        }
    }

    public Snacks setDeteleteState(UUID id) {
        Optional<Snacks> doAnDb = this.repo.findById(id);
        if (doAnDb.isPresent()) {
            Snacks doAnud = doAnDb.get();
            doAnud.setDeleted(true);
            return doAnud;
        } else {
            throw new ResourceNotFoundException("Can not find do an with id" + id);
        }
    }

    public List<Snacks> getPageNo(int pageNo, String sortBy, String sortDir) {
        this.PageNo = pageNo;
        List<Snacks> doAns;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // Pageable object
        Pageable pageable = PageRequest.of(pageNo - 1, ROWCOUNT, sort);
        // findAll method and pass pageable instance
        Page<Snacks> page = repo.findAll(pageable);
        doAns = page.getContent();
        return doAns;
    }

    public List<Snacks> getNextPage(String sortBy, String sortDir) {
        if (this.PageNo >= getPanigation().length - 1) {
            return this.getPageNo(this.getPanigation().length, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo + 1, sortBy, sortBy);
        }
    }

    public List<Snacks> getPrevPage(String sortBy, String sortDir) {
        if (this.PageNo <= 0) {
            return this.getPageNo(1, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo - 1, sortBy, sortBy);
        }
    }

    public int[] getPanigation() {
        Pageable pageable = PageRequest.of(0, ROWCOUNT);
        Page<Snacks> page = repo.findAll(pageable);
        int totalPage = page.getTotalPages();
        int[] array = IntStream.rangeClosed(1, totalPage).toArray();
        return array;
    }

    public int getCrrentPage() {
        return this.PageNo;
    }
}
