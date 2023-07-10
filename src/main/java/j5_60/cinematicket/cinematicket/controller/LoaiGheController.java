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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/loaighe")
public class LoaiGheController {

    @Autowired
    private LoaiGheService loaiGheService;

    @GetMapping("/index")
    public List<LoaiGhe> getAll() {
        return loaiGheService.getAll();
    }

    @GetMapping("/{id}")
    public LoaiGhe getById(@PathVariable("id") UUID id) throws ResourceNotFoundException {
        return loaiGheService.findById(id);
    }

    @PostMapping("/add")
    public LoaiGhe add(@RequestBody LoaiGhe loaiGhe) {
        return loaiGheService.save(loaiGhe);
    }

    @PutMapping("/update/{id}")
    public LoaiGhe update(@PathVariable("id") UUID id, @RequestBody LoaiGhe loaiGhe) throws ResourceNotFoundException {
        return loaiGheService.updateLoaiGhe(id, loaiGhe);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        loaiGheService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}