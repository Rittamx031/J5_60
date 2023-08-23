package j5_60.cinematicket.cinematicket.controller;

import j5_60.cinematicket.cinematicket.entity.PhongChieu;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.service.CeminaRoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("api/cimena/phongchieu")
public class PhongChieuController {

    @Autowired
    private CeminaRoomService phongChieuService;

    @GetMapping("/index")
    public ResponseEntity<Page<PhongChieu>> getAll(@RequestParam(defaultValue = "1") int page,
                                                   @RequestParam(defaultValue = "5") int size) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<PhongChieu> phongChieuPage = phongChieuService.findAll(pageable);
        return ResponseEntity.ok().body(phongChieuPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhongChieu> getById(@PathVariable("id") UUID id) throws ResourceNotFoundException {
        PhongChieu phongChieu = phongChieuService.findById(id);
        return ResponseEntity.ok().body(phongChieu);
    }

    @PostMapping("/add")
    public ResponseEntity<PhongChieu> addPhongChieu(@RequestBody @Valid PhongChieu phongChieu) {
        PhongChieu savedPhongChieu = phongChieuService.save(phongChieu);
        return ResponseEntity.ok().body(savedPhongChieu);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PhongChieu> updatePhongChieu(@PathVariable("id") UUID id, @RequestBody @Valid PhongChieu phongChieu) throws ResourceNotFoundException {
        PhongChieu existingPhongChieu = phongChieuService.findById(id);
        existingPhongChieu.setTen(phongChieu.getTen());
        existingPhongChieu.setSoLuongGhe(phongChieu.getSoLuongGhe());
        existingPhongChieu.setTrangThai(phongChieu.getTrangThai());
        existingPhongChieu.setUpdateAt(phongChieu.getUpdateAt());
        // Set other properties of PhongChieu as needed

        PhongChieu updatedPhongChieu = phongChieuService.updatePhongChieu(id, existingPhongChieu);
        return ResponseEntity.ok().body(updatedPhongChieu);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePhongChieu(@PathVariable("id") UUID id) {
        phongChieuService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<PhongChieu>> searchPhongChieu(@RequestParam("keyword") String keyword) {
        List<PhongChieu> phongChieuList = phongChieuService.searchPhongChieu(keyword);
        return ResponseEntity.ok().body(phongChieuList);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<PhongChieu>> getSortedPhongChieuList() {
        List<PhongChieu> sortedPhongChieuList = phongChieuService.sapXep();
        return ResponseEntity.ok().body(sortedPhongChieuList);
    }


     @GetMapping
    public ResponseEntity<List<PhongChieu>> getPhongChieus() {
        return ResponseEntity.ok().body(phongChieuService.getAll());
    }
}
