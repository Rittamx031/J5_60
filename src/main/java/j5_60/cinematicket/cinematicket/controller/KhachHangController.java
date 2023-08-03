package j5_60.cinematicket.cinematicket.controller;

import j5_60.cinematicket.cinematicket.entity.KhachHang;
import j5_60.cinematicket.cinematicket.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public ResponseEntity hienThi(@RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String sapXepTheoName) {
        if (page < 1)
            page = 1;
        Pageable pageable = PageRequest.of(page - 1, 10);
        if (sapXepTheoName != null && sapXepTheoName.equalsIgnoreCase("true")) {
            return new ResponseEntity(khService.sapXepTheoName(pageable), HttpStatus.OK);
        } else {
            return new ResponseEntity(khService.findAllKH(pageable), HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<KhachHang> addKH(@RequestBody KhachHang khachHang) {
        khachHang.setCreateAt(LocalDateTime.now());
        return new ResponseEntity<>(khService.add(khachHang), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<KhachHang> updateKH(@PathVariable("id") UUID id, @RequestBody KhachHang khachHang) {
        khachHang.setUpdateAt(LocalDateTime.now());
        return new ResponseEntity<>(khService.update(id, khachHang), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") UUID id) {
        khService.delete(id);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity detail(@PathVariable("id") UUID id) {
        return new ResponseEntity(khService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<KhachHang>> getAll() {
        return ResponseEntity.ok().body(khService.getAllKH());
    }
}