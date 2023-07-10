package j5_60.cinematicket.cinematicket.controller;

import j5_60.cinematicket.cinematicket.entity.Ghe;
import j5_60.cinematicket.cinematicket.entity.LoaiGhe;
import j5_60.cinematicket.cinematicket.entity.PhongChieu;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.service.GheService;
import j5_60.cinematicket.cinematicket.service.LoaiGheService;
import j5_60.cinematicket.cinematicket.service.PhongChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/ghe")
public class GheController {

    @Autowired
    private GheService gheService;

    @Autowired
    private LoaiGheService loaiGheService;

    @Autowired
    private PhongChieuService phongChieuService;

    @GetMapping("/index")
    public List<Ghe> getAll() {
        return gheService.getAll();
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
}
