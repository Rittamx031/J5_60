package j5_60.cinematicket.cinematicket.controller;

import j5_60.cinematicket.cinematicket.entity.KhachHang;
import j5_60.cinematicket.cinematicket.entity.ThongTinPhim;
import j5_60.cinematicket.cinematicket.modelsearch.KhachHangSearch;
import j5_60.cinematicket.cinematicket.modelsearch.ThongTinPhimSearch;
import j5_60.cinematicket.cinematicket.service.KhachHangService;
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
@RequestMapping("/cimena/khach-hang")
@CrossOrigin
public class KhachHangController {

    @Autowired
    private KhachHangService khService;

    @GetMapping("/hien-thi")
    public ResponseEntity<Page<KhachHang>> hienThi(@RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String sapXepTheoName) {
        if (page < 1)
            page = 1;
        Pageable pageable = PageRequest.of(page - 1, 5);
        if (sapXepTheoName != null && sapXepTheoName.equalsIgnoreCase("true")) {
            return ResponseEntity.ok().body(khService.sapXepTheoNgayTao(pageable));
        } else {
            return ResponseEntity.ok().body(khService.findAllKH(pageable));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<KhachHang> addKH(@RequestBody @Valid KhachHang khachHang) {
        khachHang.setCreateAt(LocalDateTime.now());
        return new ResponseEntity<>(khService.add(khachHang), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<KhachHang> updateKH(@PathVariable("id") UUID id, @RequestBody @Valid KhachHang khachHang) {
        khachHang.setUpdateAt(LocalDateTime.now());
        return new ResponseEntity<>(khService.update(id, khachHang), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") UUID id) {
        khService.delete(id);
    }

    @GetMapping("/detail/{id}")
    public HttpStatus detail(@PathVariable("id") UUID id) {
        return HttpStatus.OK;
    }

    @GetMapping
    public ResponseEntity<List<KhachHang>> getAll() {
        return ResponseEntity.ok().body(khService.getAllKH());
    }

    @PostMapping("/fillter")
    public ResponseEntity<List<KhachHang>> fillter(@RequestBody KhachHangSearch khachHangSearch) {
        return ResponseEntity.ok().body(this.khService.fillterKhachHang(khachHangSearch));
    }


}