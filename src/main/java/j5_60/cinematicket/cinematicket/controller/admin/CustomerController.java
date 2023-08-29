package j5_60.cinematicket.cinematicket.controller.admin;

import j5_60.cinematicket.cinematicket.model.entity.KhachHang;
import j5_60.cinematicket.cinematicket.model.modelsearch.KhachHangSearch;
import j5_60.cinematicket.cinematicket.service.admin.CustomerService;
import jakarta.validation.Valid;
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
@RequestMapping("api/cimena/khach-hang")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService khService;

    @GetMapping("/hien-thi")
    public ResponseEntity hienThi(@RequestParam(defaultValue = "1") int page, @RequestParam(required = false) String sapXepTheoName) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 10);
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