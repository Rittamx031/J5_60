package j5_60.cinematicket.cinematicket.controller;

import j5_60.cinematicket.cinematicket.entity.NhanVien;
import j5_60.cinematicket.cinematicket.service.ChucVuService;
import j5_60.cinematicket.cinematicket.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/admin/nhan-vien")
@CrossOrigin
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private ChucVuService chucVuService;

    @GetMapping
    public ResponseEntity hienThi(@RequestParam(defaultValue = "1") int page) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 3);
        return new ResponseEntity(nhanVienService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/chucvus")
    public ResponseEntity hienThi() {
        return new ResponseEntity(chucVuService.getAll1(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<NhanVien> addNV(@RequestBody NhanVien nhanVien) {
        nhanVien.setCreateAt(LocalDateTime.now());
        return new ResponseEntity<>(nhanVienService.add(nhanVien), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<NhanVien> updateNV(@PathVariable("id") UUID id, @RequestBody NhanVien nhanVien) {
        nhanVien.setUpdateAt(LocalDateTime.now());
        return new ResponseEntity<>(nhanVienService.update(id, nhanVien), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNV(@PathVariable("id") UUID id) {
        nhanVienService.delete(id);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity detailNV(@PathVariable("id") UUID id) {
        return new ResponseEntity(nhanVienService.findById(id), HttpStatus.OK);
    }
}