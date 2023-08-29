package j5_60.cinematicket.cinematicket.controller.admin;

import j5_60.cinematicket.cinematicket.model.entity.ChucVu;
import j5_60.cinematicket.cinematicket.model.entity.NhanVien;
import j5_60.cinematicket.cinematicket.model.modelsearch.NhanVienSearch;
import j5_60.cinematicket.cinematicket.service.admin.EmployeeService;
import j5_60.cinematicket.cinematicket.service.admin.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/cemina/nhan-vien")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService nhanVienService;

    @Autowired
    private JobService chucVuService;

    @GetMapping("hien-thi")
    public ResponseEntity<Page<NhanVien>> hienThi(@RequestParam(defaultValue = "1") int page) {
        if (page < 1)
            page = 1;
        Pageable pageable = PageRequest.of(page - 1, 5);
        return ResponseEntity.ok().body(nhanVienService.findAll(pageable));
    }

    @GetMapping
    public ResponseEntity<List<NhanVien>> getAll() {
        return ResponseEntity.ok().body(nhanVienService.getAllNV());
    }

    @GetMapping("/chucvus")
    public ResponseEntity<List<ChucVu>> hienThiCV() {
        return ResponseEntity.ok().body(chucVuService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<NhanVien> addNV(@RequestBody @Valid NhanVien nhanVien) {
        nhanVien.setCreateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(nhanVienService.add(nhanVien));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<NhanVien> updateNV(@PathVariable("id") UUID id, @RequestBody @Valid NhanVien nhanVien) {
        nhanVien.setUpdateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(nhanVienService.update(id, nhanVien));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNV(@PathVariable("id") UUID id) {
        nhanVienService.delete(id);
    }

    @GetMapping("/detail/{id}")
    public HttpStatus detailNV(@PathVariable("id") UUID id) {
        return HttpStatus.OK;
    }

    @PostMapping("/fillter")
    public ResponseEntity<List<NhanVien>> fillter(@RequestBody NhanVienSearch nhanVienSearch) {
        return ResponseEntity.ok().body(this.nhanVienService.fillterNhanVien(nhanVienSearch));
    }
}