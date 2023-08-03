package j5_60.cinematicket.cinematicket.controller;

import j5_60.cinematicket.cinematicket.entity.ChucVu;
import j5_60.cinematicket.cinematicket.service.ChucVuService;

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
@RequestMapping("/cimena/chuc-vu")
@CrossOrigin
public class ChucVuController {

    @Autowired
    private ChucVuService chucVuService;

    @GetMapping
    public ResponseEntity<Page<ChucVu>> hienThi(@RequestParam(defaultValue = "1") int page) {
        if (page < 1)
            page = 1;
        Pageable pageable = PageRequest.of(page - 1, 5);
        return ResponseEntity.ok().body(chucVuService.findAll(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<ChucVu> addChucVu(@RequestBody ChucVu chucVu) {
        chucVu.setCreateAt(LocalDateTime.now());
        return new ResponseEntity<>(chucVuService.add(chucVu), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ChucVu> updateChucVu(@PathVariable("id") UUID id, @RequestBody ChucVu chucVu) {
        chucVu.setUpdateAt(LocalDateTime.now());
        return new ResponseEntity<>(chucVuService.update(id, chucVu), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") UUID id) {
        chucVuService.delete(id);
    }

    @GetMapping("/detail/{id}")
    public HttpStatus detail(@PathVariable("id") UUID id) {
        chucVuService.getById(id);
        return HttpStatus.OK;
    }

    @GetMapping("getall")
    public ResponseEntity<List<ChucVu>> getAll() {
        return ResponseEntity.ok().body(chucVuService.getAll());
    }
}