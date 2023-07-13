package j5_60.cinematicket.cinematicket.controller;

/**
 * LoaiGheController
 */

import j5_60.cinematicket.cinematicket.entity.LoaiGhe;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.service.LoaiGheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("cimena/loaighe")
public class LoaiGheController {

    @Autowired
    private LoaiGheService loaiGheService;

    // @GetMapping("/index")
    // public ResponseEntity<Page<LoaiGhe>> getAll(@RequestParam(defaultValue = "1") int page,
    //                                             @RequestParam(defaultValue = "5") int size) {
    //     if (page < 1) page = 1;
    //     Pageable pageable = PageRequest.of(page - 1, size);
    //     Page<LoaiGhe> loaiGhePage = loaiGheService.findAll(pageable);
    //     return ResponseEntity.ok().body(loaiGhePage);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<LoaiGhe> getById(@PathVariable("id") UUID id) throws ResourceNotFoundException {
        LoaiGhe loaiGhe = loaiGheService.findById(id);
        return ResponseEntity.ok().body(loaiGhe);
    }

    @PostMapping("/add")
    public ResponseEntity<LoaiGhe> addLoaiGhe(@RequestBody LoaiGhe loaiGhe) {
        LoaiGhe savedLoaiGhe = loaiGheService.save(loaiGhe);
        return ResponseEntity.ok().body(savedLoaiGhe);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LoaiGhe> updateLoaiGhe(@PathVariable("id") UUID id, @RequestBody LoaiGhe loaiGhe) throws ResourceNotFoundException {
        LoaiGhe existingLoaiGhe = loaiGheService.findById(id);
        existingLoaiGhe.setTen(loaiGhe.getTen());
        existingLoaiGhe.setTrangThai(loaiGhe.getTrangThai());
        existingLoaiGhe.setUpdateAt(loaiGhe.getUpdateAt());
        // Set other properties of LoaiGhe as needed

        LoaiGhe updatedLoaiGhe = loaiGheService.updateLoaiGhe(id, existingLoaiGhe);
        return ResponseEntity.ok().body(updatedLoaiGhe);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLoaiGhe(@PathVariable("id") UUID id) {
        loaiGheService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<LoaiGhe>> searchLoaiGhe(@RequestParam("keyword") String keyword) {
        List<LoaiGhe> loaiGheList = loaiGheService.searchLoaiGhe(keyword);
        return ResponseEntity.ok().body(loaiGheList);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<LoaiGhe>> getSortedLoaiGheList() {
        List<LoaiGhe> sortedLoaiGheList = loaiGheService.sapXep();
        return ResponseEntity.ok().body(sortedLoaiGheList);
    }
}