package j5_60.cinematicket.cinematicket.service.admin;

import j5_60.cinematicket.cinematicket.model.entity.ChucVu;
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

    public List<ChucVu> getAll() {
        return repository.findAll();
    }

    public Page<ChucVu> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public ChucVu add(ChucVu cv) {
        return repository.save(cv);
    }

    public ChucVu update(UUID id, ChucVu chucVu) {
        ChucVu cv = repository.findById(id).get();
        cv.setTenCV(chucVu.getTenCV());
        cv.setUpdateAt(chucVu.getUpdateAt());
        return repository.save(cv);
    }

    public void delete(UUID id) {
        ChucVu cv = repository.findById(id).get();
        repository.delete(cv);
    }

    public ChucVu getById(UUID id) {
        return repository.findById(id).get();
    }

    public Page<ChucVu> search(ChucVu serchNhanVien) {
        return null;
    }

    public List<ChucVu> getPageNo(int pageno) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createBy");
        List<ChucVu> listrs = repository.findAll(PageRequest.of(pageno - 1, ROWCOUNT, sort)).getContent();
        return listrs;
    }
}
