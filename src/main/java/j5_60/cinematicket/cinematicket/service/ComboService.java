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

import j5_60.cinematicket.cinematicket.entity.Combo;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.ComboRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComboService {
      @Autowired
    private ComboRepository repo;
    private final int ROWCOUNT = 5;
    private int PageNo = -1;
    public Combo createCombo(Combo combo) {
        return repo.save(combo);
    }

    public Combo updateCombo(Combo combo) {
        Optional<Combo> comboDb = this.repo.findById(combo.getId());
        if (comboDb.isPresent()) {
            Combo comboud = comboDb.get();
            comboud.setId(combo.getId());
            comboud.setGia(combo.getGia());
            comboud.setTen(combo.getTen());
            comboud.setCreateAt(combo.getCreateAt());
            comboud.setCreateBy(combo.getCreateBy());
            comboud.setDeleted(combo.isDeleted());
            comboud.setUpdateAt(combo.getUpdateAt());
            comboud.setUpdateBy(combo.getUpdateBy());
            return comboud;
        } else {
            throw new ResourceNotFoundException("Can not find Com Bo with id" + combo.getId());
        }
    }

    public List<Combo> getAllCombo() {
        return repo.findAll();
    }

    // public List<Combo> searchByName(){
    // return repo.findAll();
    // }
    public Combo getComboById(UUID id) {
        Optional<Combo> combo = repo.findById(id);
        if (combo.isPresent()) {
            return combo.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Com Bo  with id = " + id);
        }
    }

    public Combo deleteCombo(UUID id) {
        Optional<Combo> combo = repo.findById(id);
        if (combo.isPresent()) {
            return combo.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Com Bo  with id = " + id);
        }
    }

    public Combo setDeteleteState(UUID id) {
        Optional<Combo> comboDb = this.repo.findById(id);
        if (comboDb.isPresent()) {
            Combo comboud = comboDb.get();
            comboud.setDeleted(true);
            return comboud;
        } else {
            throw new ResourceNotFoundException("Can not find Com Bo  with id" + id);
        }
    }

    public List<Combo> getPageNo(int pageNo, String sortBy, String sortDir) {
        this.PageNo = pageNo;
        List<Combo> combos;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // Pageable object
        Pageable pageable = PageRequest.of(pageNo, ROWCOUNT, sort);
        // findAll method and pass pageable instance
        Page<Combo> page = repo.findAll(pageable);
        combos = page.getContent();
        return combos;
    }

    public List<Combo> getNextPage(String sortBy, String sortDir) {
        if (this.PageNo >= getPanigation().length - 1) {
            return this.getPageNo(this.getPanigation().length - 1, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo + 1, sortBy, sortBy);
        }
    }

    public List<Combo> getPrevPage(String sortBy, String sortDir) {
        if (this.PageNo <= 0) {
            return this.getPageNo(0, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo - 1, sortBy, sortBy);
        }
    }

    public int[] getPanigation() {
        Pageable pageable = PageRequest.of(1, ROWCOUNT);
        Page<Combo> page = repo.findAll(pageable);
        int totalPage = page.getTotalPages();
        int[] array = IntStream.rangeClosed(0, totalPage).toArray();
        return array;
    }
}
