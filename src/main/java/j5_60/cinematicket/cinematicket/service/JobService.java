package j5_60.cinematicket.cinematicket.service;

import j5_60.cinematicket.cinematicket.model.entity.Job;
import j5_60.cinematicket.cinematicket.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobService {
    final int ROWCOUNT = 10;
    @Autowired
    private JobRepository repository;

    public List<Job> getAll() {
        return repository.findAll();
    }

    public Page<Job> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Job add(Job cv) {
        return repository.save(cv);
    }

    public Job update(UUID id, Job chucVu) {
        Job cv = repository.findById(id).get();
        cv.setTenCV(chucVu.getTenCV());
        cv.setUpdateAt(chucVu.getUpdateAt());
        return repository.save(cv);
    }

    public void delete(UUID id) {
        Job cv = repository.findById(id).get();
        repository.delete(cv);
    }

    public Job getById(UUID id) {
        return repository.findById(id).get();
    }

    public Page<Job> search(Job serchNhanVien) {
        return null;
    }

    public List<Job> getPageNo(int pageno) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createBy");
        List<Job> listrs = repository.findAll(PageRequest.of(pageno - 1, ROWCOUNT, sort)).getContent();
        return listrs;
    }
}
