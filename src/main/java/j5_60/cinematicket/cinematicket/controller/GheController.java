package j5_60.cinematicket.cinematicket.controller;

import j5_60.cinematicket.cinematicket.entity.Ghe;
import j5_60.cinematicket.cinematicket.entity.LoaiGhe;
import j5_60.cinematicket.cinematicket.entity.PhongChieu;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.service.GheService;
import j5_60.cinematicket.cinematicket.service.LoaiGheService;
import j5_60.cinematicket.cinematicket.service.PhongChieuService;
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
@RequestMapping("cimena/ghe")
public class GheController {

    @Autowired
    private GheService gheService;

    @Autowired
    private LoaiGheService loaiGheService;

    @Autowired
    private PhongChieuService phongChieuService;

    @GetMapping("/index")
    public ResponseEntity<Page<Ghe>> getAll(@RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "5") int size) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Ghe> ghePage = gheService.findAll(pageable);
        return ResponseEntity.ok().body(ghePage);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Ghe> getById(@PathVariable("id") UUID id) throws ResourceNotFoundException {
        Ghe ghe = gheService.findById(id);
        return ResponseEntity.ok().body(ghe);
    }

    @PostMapping("/add")
    public ResponseEntity<Ghe> addGhe(@RequestBody Ghe ghe) throws ResourceNotFoundException {
        LoaiGhe loaiGhe = loaiGheService.findById(ghe.getLoaiGhe().getId());
        PhongChieu phongChieu = phongChieuService.findById(ghe.getPhongChieu().getId());
        ghe.setLoaiGhe(loaiGhe);
        ghe.setPhongChieu(phongChieu);
        Ghe savedGhe = gheService.save(ghe);
        return ResponseEntity.ok().body(savedGhe);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Ghe> updateGhe(@PathVariable("id") UUID id, @RequestBody Ghe ghe) throws ResourceNotFoundException {
        Ghe existingGhe = gheService.findById(id);
        LoaiGhe loaiGhe = loaiGheService.findById(ghe.getLoaiGhe().getId());
        PhongChieu phongChieu = phongChieuService.findById(ghe.getPhongChieu().getId());
        existingGhe.setTen(ghe.getTen());
        existingGhe.setLoaiGhe(loaiGhe);
        existingGhe.setPhongChieu(phongChieu);
        existingGhe.setTrangThai(ghe.getTrangThai());
        existingGhe.setUpdateAt(ghe.getUpdateAt());
        Ghe updatedGhe = gheService.save(existingGhe);
        return ResponseEntity.ok().body(updatedGhe);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGhe(@PathVariable("id") UUID id) {
        gheService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<Ghe>> searchGhe(@RequestParam("keyword") String keyword) {
        List<Ghe> gheList = gheService.searchGhe(keyword);
        return ResponseEntity.ok().body(gheList);
    }
    @GetMapping("/sorted")
    public ResponseEntity<List<Ghe>> getSortedGheList() {
        List<Ghe> sortedGheList = gheService.sapXep();
        return ResponseEntity.ok().body(sortedGheList);
    }
}
